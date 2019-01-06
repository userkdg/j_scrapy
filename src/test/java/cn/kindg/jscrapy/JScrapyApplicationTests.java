package cn.kindg.jscrapy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JScrapyApplicationTests {

    @Resource
    private JmsTemplate jmsTemplate;

    @Test
    public void contextLoads() throws InterruptedException {
        while (true) {
            jmsTemplate.send("my-dest", new MsgCreater());
            TimeUnit.SECONDS.sleep(2);
        }
    }

    private class MsgCreater implements MessageCreator {

        @Override
        public Message createMessage(Session session) throws JMSException {
            return session.createTextMessage("hello-mq-message time:" + LocalDateTime.now().toString());
        }
    }
}

@Component
class MqCustomerTest {
    @JmsListener(destination = "my-dest")
    public void receivceMsg(String message) {
        System.out.println("receive msg :" + message);
    }
}

