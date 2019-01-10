package cn.kindg.jscrapy;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountLatchTest {
    private static final List<Integer> fileSize = Lists.newArrayList();

    static {
        IntStream.range(0, 9999999)
                .forEach(fileSize::add);
    }

    private static final int DEFAULT_THREAD_POOLS = 4;

    private CountDownLatch countDownLatch = new CountDownLatch(DEFAULT_THREAD_POOLS);

    public Map<String, String> handleOkMap = Maps.newConcurrentMap();

    public CountLatchTest() {
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        new CountLatchTest().doTask();
        long l1 = System.currentTimeMillis() - l;
        System.out.println(l1);
    }

    private void doTask() {
        int totalSize = fileSize.size();
        int per = totalSize / DEFAULT_THREAD_POOLS;
        List<Integer> oneLists = fileSize.subList(0, per);
        List<Integer> secondists = fileSize.subList(per, per * 2);
        List<Integer> thirdLists = fileSize.subList(per * 2, per * 3);
        List<Integer> fourLists = fileSize.subList(per * 3, totalSize);
//        Thread.currentThread().setDaemon(true);

        new Thread(new TaskThread(oneLists)).start();
        new Thread(new TaskThread(secondists)).start();
        new Thread(new TaskThread(thirdLists)).start();
        new Thread(new TaskThread(fourLists)).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (countDownLatch.getCount()==0){
            System.out.println(handleOkMap.size()+" size ");
        }
    }

    private class TaskThread implements Runnable {
        private List<Integer> list = Lists.newArrayList();

        public TaskThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            list.forEach(i -> handleOkMap.put(String.valueOf(i), String.valueOf(i)));
            countDownLatch.countDown();
        }
    }

}
