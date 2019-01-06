package cn.kindg.jscrapy;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestJsoup {
    public static final String url = "https://www.jianshu.com/trending/weekly?utm_medium=index-banner-s&utm_source=desktop";

    private static String redis_prefix = "jscrapy:kindg:";
    private static String key = "jscrapy:jianshu:key2";

    static class A {
        public static void main(String[] args) {
            MongoDatabase db = TestMongo.getMongoClientInstance().getDatabase(TestMongo.DEFAULT_MONGO_DB);
            String j_scrapy_jisnshu_data = "j_scrapy_jisnshu_data";
            try (Jedis jedis = RedisClientTest.getJedisPoolInstance().getResource()) {
                jedis.select(2);
                String htmlBodyJSON = jedis.get(key);
                String htmlBodyStr = String.valueOf(JSON.parse(htmlBodyJSON));
                Element element = Jsoup.parseBodyFragment(htmlBodyStr).body();
                Element articleList = element.getElementById("list-container");
                Elements ulTags = articleList.getElementsByTag("ul");
                for (Element ulTag : ulTags) {
                    Elements liTags = ulTag.getElementsByTag("li");
                    for (Element liTag : liTags) {
                        Elements articleHeadImgEle = liTag.getElementsByClass("wrap-img");
                        String hrefValue = articleHeadImgEle.attr("href");
                        if (StringUtils.isNotBlank(hrefValue)
                                && !hrefValue.contains("https:")
                                && !hrefValue.contains("http:")) {
                            hrefValue = "https:" + hrefValue;
                        }
                        org.bson.Document document = new org.bson.Document();
                        document.put("li_head_img_href", hrefValue);

                        Elements liContentEle = liTag.getElementsByClass("content");
                        for (Element contentEle : liContentEle) {
                            Elements titleEle = contentEle.getElementsByClass("title");
                            String titleHrefValue = titleEle.attr("href");
                            if (StringUtils.isNotBlank(titleHrefValue)
                                    && !titleHrefValue.contains("https:")
                                    && !titleHrefValue.contains("http:")) {
                                titleHrefValue = "https:" + titleHrefValue;
                            }
                            document.put("li_content_title_href", titleHrefValue);

                            String titleText = titleEle.text();
                            document.put("li_content_title_text", titleText);

                            Elements anAbstractEle = contentEle.getElementsByClass("abstract");
                            String anAbstractText = anAbstractEle.text();
                            document.put("li_content_abstract_text", anAbstractText);

                            Elements nicknameEle = contentEle.getElementsByClass("nickname");
                            String articlAuthorName = nicknameEle.text();
                            document.put("li_content_article_nickname", articlAuthorName);

                            db.getCollection(j_scrapy_jisnshu_data).insertOne(document);
                        }

                        String text = liTag.text();
                        System.out.println(text);
                        Elements liTagId = liTag.getElementsByAttribute("id");
                        String liTagIdValue = liTagId.attr("id");
                        jedis.set(redis_prefix + liTagIdValue, text);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(url)
                .timeout(400000)
                .ignoreContentType(true)
                .userAgent("Mozilla\" to \"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0)")
                .get();
        Element body = document.body();

//        System.out.println(body);

        String key = "jscrapy:jianshu:key2";
        String toJSONString = JSON.toJSONString(body.toString());
        try (JedisPool jedisPool = RedisClientTest.getJedisPoolInstance()) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.select(2);
                String s = jedis.get(key);
                Object parse = JSON.parse(s);
                System.out.println(parse);
            }
        }

        // <li id="note-39440883" data-note-id="39440883" class="have-img"> <a class="wrap-img" href="/p/79848a24fe3f" target="_blank"> <img data-echo="//upload-images.jianshu.io/upload_images/15524861-ce9ca92037221db0?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240" class="img-blur" src="//upload-images.jianshu.io/upload_images/15524861-ce9ca92037221db0?imageMogr2/auto-orient/strip|imageView2/1/w/150/h/120" alt="120"> </a>
        //       <div class="content">
        //        <a class="title" target="_blank" href="/p/79848a24fe3f">越是活的“低级”的女人，越是喜欢用“这种”微信头像，别不承认</a>
        //        <p class="abstract"> 01 信息时代，微信已经成了大部分人的交流方式，而微信头像，也成为了我们对陌生人的第一印象。 因此，你用的微信头像，很多时候也能说明你是一个什么... </p>
        //        <div class="meta">
        //         <a class="nickname" target="_blank" href="/u/14e7cf481fd9">红尘弦影</a>
        //         <a target="_blank" href="/p/79848a24fe3f#comments"> <i class="iconfont ic-list-comments"></i> 29 </a>
        //         <span><i class="iconfont ic-list-like"></i> 89</span>
        //        </div>
        //       </div>
        // </li>

        // href="/p/xxx"
        // https://www.jianshu.com/p/xxx
        // src="//upload-images.jianshu.io/upload_images/x.jpg"
        // src="https://upload-images.jianshu.io/upload_images/x.jpg"

        try (JedisPool jedisPoolInstance = RedisClientTest.getJedisPoolInstance()) {
            try (Jedis jedis = jedisPoolInstance.getResource()) {
                jedis.select(2);
//                Transaction multi = jedis.multi();
                String set = jedis.set(key.getBytes(StandardCharsets.UTF_8), toJSONString.getBytes(StandardCharsets.UTF_8));
//                String set = jedis.set(key, toJSONString);
                System.out.println(set);
                Long persist = jedis.persist(key);
                System.out.println(persist);
//                List<Object> responses = multi.exec();
//                for (Object respons : responses) {
//                    System.out.println(respons);
//                }
                List<String> strings = jedis.configGet("CONFIG GET *");
                for (String string : strings) {
                    System.out.println(string);
                }
            }
        }
    }
}
