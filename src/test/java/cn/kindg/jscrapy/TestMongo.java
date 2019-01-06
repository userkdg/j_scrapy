package cn.kindg.jscrapy;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestMongo {
    private static final String mongoHost = "localhost";
    private static final int mongoPort = 27017;
    public static final String DEFAULT_MONGO_DB = "j_scrapy";

    private static class Holder {
        private static MongoClient mongoClient = new MongoClient(mongoHost, mongoPort);
    }

    public static MongoClient getMongoClientInstance(){
        return Holder.mongoClient;
    }

    private MongoClient mongoClient;

    public void init() {
        mongoClient = new MongoClient(mongoHost, mongoPort);
    }

    @Test
    public void test(){
        init();
        MongoDatabase database = mongoClient.getDatabase(DEFAULT_MONGO_DB);
        MongoCollection<Document> scrapyAccount = database.getCollection("scrapy_account");
        FindIterable<Document> documents = scrapyAccount.find();
        for (Document document : documents) {
            System.out.println(document);
        }
    }
}
