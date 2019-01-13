package cn.kindg.jscrapy.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 测试watchService的监控文件变化
 * 获取最新写入的行数
 */
public class WatchLog {
    private long lastTimeFileSize = 0;  //上次文件大小

    /**
     * 实时输出日志信息
     *
     * @param logFile 日志文件
     * @throws IOException
     */
    public void realtimeShowLog(File logFile) throws IOException {
        //指定文件可读可写
        final RandomAccessFile randomFile = new RandomAccessFile(logFile, "r");

        //启动一个线程每10秒钟读取新增的日志信息
        ScheduledExecutorService exec =
                Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(() -> {
            try {
                //获得变化部分的
                randomFile.seek(lastTimeFileSize);
                String tmp = "";
                while ((tmp = randomFile.readLine()) != null) {
                    System.out.println(new String(tmp.getBytes(StandardCharsets.ISO_8859_1)));
                }
                lastTimeFileSize = randomFile.length();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        final File tmpLogFile = new File("G:\\watcher\\1.txt");
        new WatchLog().realtimeShowLog(tmpLogFile);
    }

}
