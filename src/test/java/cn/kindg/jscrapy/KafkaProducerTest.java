package cn.kindg.jscrapy;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * 这是一个简单的Kafka producer代码
 * 包含两个功能:
 * 1、数据发送
 * 2、数据按照自定义的partition策略进行发送
 *
 *
 * KafkaSpout的类
 */

public class KafkaProducerTest {
    public static void main(String[] args) {
        /**
         * 1、指定当前kafka producer生产的数据的目的地
         *  创建topic可以输入以下命令，在kafka集群的任一节点进行创建。
         *  bin/kafka-topics.sh --create --zookeeper master:2181
         *  --replication-factor 1 --partitions 1 --topic orderMq
         */
        String TOPIC = JScrapyApplicationKafkaTests.TOPIC;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "Producer_demo1");

        //开始的时候下面5个参数未设置，导致消费时取不到数据，需要注意
       /* acks=0时，producer不会等待确认，直接添加到socket等待发送；
        acks=1时，等待leader写到local log就行；
        acks=all或acks=-1时，等待isr中所有副本确认
        */
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        //發送失敗重試
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        //批次发送，不会尝试大于此值的容量
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //默认设置为0，
        // 具体参数参考：http://kafka.apache.org/0102/documentation.html#producerconfigs
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        /**
         * 3、通过配置文件，创建生产者
         */
        Producer<String, String> producer = new KafkaProducer<>(props);
        /**
         * 4、通过for循环生产数据
         */
        int messageNo = 1;
        while (true){
            messageNo++;
            String messageStr = messageNo + "注意：xx";
            /**
             * 5、调用producer的send方法发送数据
             * 注意：这里需要指定 partitionKey，用来配合自定义的MyLogPartitioner进行数据分发
             */
            System.out.println("发送："+messageStr);
            producer.send(new ProducerRecord<>(TOPIC, messageNo + "", "appid" + UUID.randomUUID() + messageStr));

            //producer.send(new KeyedMessage<String, String>(TOPIC, messageNo + "", "appid" + UUID.randomUUID() + "biexiansheng"));
        }
    }
}
class PartitionerTest implements Partitioner{
    private static final Logger log = LoggerFactory.getLogger(PartitionerTest.class);

    public int partition(Object obj, int numPartitions) {
        return Integer.parseInt(obj.toString())%numPartitions;
    }

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {
    }
}
