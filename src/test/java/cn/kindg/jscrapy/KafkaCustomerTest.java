package cn.kindg.jscrapy;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.StreamsMetadata;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

public class KafkaCustomerTest implements Runnable {
    public String title;
    public KafkaStreams stream;

    public KafkaCustomerTest(String title, KafkaStreams stream) {
        this.title = title;
        this.stream = stream;
    }

    @Override
    public void run() {
        System.out.println("开始运行 " + title);
        stream.allMetadata().stream()
                .filter(Objects::nonNull)
                .map(StreamsMetadata::topicPartitions)
                .flatMap(Collection::stream)
                .forEach(topicPartition -> {
                    int partition = topicPartition.partition();
                    String topic = topicPartition.topic();
                    System.out.println(String.format(" customer : topic:[%s], Partition:[%d]", topic, partition));
                });
        System.out.println(String.format("Consumer: [%s] exiting ...", title));
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        //bootstrap.servers   必要
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //group id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "producer-consumer-demo1");
        //是否后台自动提交offset 到kafka
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //消费者偏移自动提交到Kafka的频率（以毫秒为单位enable.auto.commit）设置为true
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //故障检测，心跳检测机制 的间隔时间，，在该值范围内，没有接收到心跳，则会删除该消费者
        //并启动再平衡（rebanlance）,值必须在group.min.session.timeout 和 group.max.session.timeout.ms之间
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//从头条开始处理消费
        //key - value 的序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(JScrapyApplicationKafkaTests.TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(2));
            for (ConsumerRecord<String, String> record : records) {
                String topic = record.topic();
                String value = record.value();
                long offset = record.offset();
                System.out.println(String.format("topic:[%s], value:[%s] , offset : [%d]", topic, value, offset));
            }
        }
        //定义一个map
//        Map<String, Integer> topicCountMap = new HashMap<>();
//        topicCountMap.put(topic1, 3);
//        //Map<String, List<KafkaStream<byte[], byte[]>> 中String是topic， List<KafkaStream<byte[], byte[]>是对应的流
//        Map<String, List<KafkaStreams>> topicStreamsMap = consumerConn.createMessageStreams(topicCountMap);
//        //取出 `kafkaTest` 对应的 streams
//        List<KafkaStreams> streams = topicStreamsMap.get(topic1);
//        //创建一个容量为4的线程池
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        //创建20个consumer threads
//        for (int i = 0; i < streams.size(); i++) {
//            executor.execute(new KafkaCustomerTest("消费者" + (i + 1), streams.get(i)));
//        }
    }
}
