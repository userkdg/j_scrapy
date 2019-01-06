package cn.kindg.jscrapy;


import cn.kindg.jooq.generator.tables.ScrapyAccount;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.Driver;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJooq {
    @Resource
    private DSLContext context;

    private ScrapyAccount account = ScrapyAccount.SCRAPY_ACCOUNT;

    @Test
    public void tjooq() throws ClassNotFoundException {
        cn.kindg.jooq.generator.tables.pojos.ScrapyAccount scrapyAccount = new cn.kindg.jooq.generator.tables.pojos.ScrapyAccount ();
        scrapyAccount.setPassword("j_scrapy");
        scrapyAccount.setUsername("j_scrapy");
        scrapyAccount.setCreateTime(LocalDateTime.now());
        scrapyAccount.setState((byte) 1);
        // insert into aa as a(xx)values(?) is error ，due to "as a"
        context.insertInto(account)
                .columns(account.USERNAME,account.PASSWORD,account.STATE,account.CREATE_TIME)
                .values(scrapyAccount.getUsername(),scrapyAccount.getPassword(),scrapyAccount.getState(),scrapyAccount.getCreateTime())
                .execute();
    }
}
