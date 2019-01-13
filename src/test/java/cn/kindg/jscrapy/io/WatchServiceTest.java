package cn.kindg.jscrapy.io;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * jdk7 自带的watch 事件监听类
 */
public class WatchServiceTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("G:\\watcher");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                //事件可能lost or discarded
                if (kind == OVERFLOW) {
                    continue;
                }

                Path fileName = (Path) event.context();
                if ("1.txt".equals(fileName.toFile().getName())){
                    //System.out.printf("Event %s has happened,which fileName is %s%n"
                    //      ,kind.name(),fileName);
                    if ("ENTRY_CREATE".equals(kind.name())) {
                        System.out.println(fileName + "-----> 创建！");
                    } else if ("ENTRY_DELETE".equals(kind.name())) {
                        System.out.println(fileName + "-----> 删除！");
                    } else if ("ENTRY_MODIFY".equals(kind.name())) {
                        System.out.println(fileName + "-----> 修改！");
                    }
                }else {
                    System.out.println("非 1.txt文件变化"+ fileName+">>"+kind.name());
                }
                //System.out.printf("%s -----> %s%n",fileName,kind.name());
            }
            if (!key.reset()) {
                break;
            }
        }
    }
}
