package cn.kindg.jscrapy.io;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程间的通讯
 * 1.生产线程实现了监听log文件的实时变化，同时能够记忆流读取在文件弄个位置
 *
 */
public class ProducerAndCustomerTest {
    static final ConcurrentLinkedQueue<String> fileDataQueue = new ConcurrentLinkedQueue<String>();
    static volatile long lastModifiedTime = 0L;
    static final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
    static final ExecutorService poolExecutor = Executors.newWorkStealingPool(3);


    public static void main(String[] args) {
        final File tmpLogFile = new File("G:\\watcher\\1.txt");

        exec.scheduleWithFixedDelay(() -> {
            synchronized (fileDataQueue) {
                while (fileDataQueue.size() >= 2) {
                    try {
                        fileDataQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try (RandomAccessFile randomAccessFile = new RandomAccessFile(tmpLogFile, "r")) {
                    String row;
                    randomAccessFile.seek(lastModifiedTime);
                    while ((row = randomAccessFile.readLine()) != null) {
                        System.out.println("produce" + row);

                        fileDataQueue.offer(row);
                        fileDataQueue.notifyAll();

                        lastModifiedTime = tmpLogFile.length();// 必须是长度变化
//                        lastModifiedTime = Files.getLastModifiedTime(tmpLogFile.toPath()).toMillis();
                    }
                    fileDataQueue.notifyAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        new Thread(() -> {
            while (true) {
                synchronized (fileDataQueue) {
                    while (fileDataQueue.isEmpty()) {
                        try {
                            fileDataQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String poll = fileDataQueue.poll();
                    System.out.println("Customer" + poll);
                    // 异步线程走
                    try {
                        List<String> polls = Lists.newArrayList();
                        polls.add(poll);
                        poolExecutor.invokeAll(Collections.singletonList(new Task(polls)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 会等待异步线程走完
                    fileDataQueue.notifyAll();
                }
            }
        }, "Customer").start();
    }

    static class Task implements Callable<Boolean> {

        private final List<String> task;

        public Task(List<String> task) {
            this.task = task;
        }

        @Override
        public Boolean call() throws Exception {
            // 两种决策
            // 1 满了2条就处理
            // 2 满了1分钟就处理

            System.out.println("处理消费者给我的" + task);
            return Boolean.TRUE;
        }
    }
}
