package cn.kindg.jscrapy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JScrapyApplication.class)
@EnableScheduling
public class JScrapyApplicationKafkaTests {
    public static final String TOPIC = "j_scrapy_topic";
    public static final String TOPIC2 = "kindg20190108";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    private AtomicInteger i = new AtomicInteger(0);

    @Scheduled(fixedDelay = 100L)
    public void send() {
        kafkaTemplate.send(TOPIC, "xxxxxxxxx你好" + i.getAndIncrement());
    }

    @Test
    public void test() {
        while (true) ;
    }

    @KafkaListener(topics = {TOPIC,})
    public void processMessage(String content) {
        // ...
        System.out.println(content);
    }
}
