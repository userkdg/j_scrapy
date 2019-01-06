/*
 * This file is generated by jOOQ.
 */
package cn.kindg.jooq.generator.tables.daos;


import cn.kindg.jooq.generator.tables.ScrapyAccount;
import cn.kindg.jooq.generator.tables.records.ScrapyAccountRecord;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ScrapyAccountDao extends DAOImpl<ScrapyAccountRecord, cn.kindg.jooq.generator.tables.pojos.ScrapyAccount, Integer> {

    /**
     * Create a new ScrapyAccountDao without any configuration
     */
    public ScrapyAccountDao() {
        super(ScrapyAccount.SCRAPY_ACCOUNT, cn.kindg.jooq.generator.tables.pojos.ScrapyAccount.class);
    }

    /**
     * Create a new ScrapyAccountDao with an attached configuration
     */
    public ScrapyAccountDao(Configuration configuration) {
        super(ScrapyAccount.SCRAPY_ACCOUNT, cn.kindg.jooq.generator.tables.pojos.ScrapyAccount.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(cn.kindg.jooq.generator.tables.pojos.ScrapyAccount object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<cn.kindg.jooq.generator.tables.pojos.ScrapyAccount> fetchById(Integer... values) {
        return fetch(ScrapyAccount.SCRAPY_ACCOUNT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public cn.kindg.jooq.generator.tables.pojos.ScrapyAccount fetchOneById(Integer value) {
        return fetchOne(ScrapyAccount.SCRAPY_ACCOUNT.ID, value);
    }

    /**
     * Fetch records that have <code>username IN (values)</code>
     */
    public List<cn.kindg.jooq.generator.tables.pojos.ScrapyAccount> fetchByUsername(String... values) {
        return fetch(ScrapyAccount.SCRAPY_ACCOUNT.USERNAME, values);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<cn.kindg.jooq.generator.tables.pojos.ScrapyAccount> fetchByPassword(String... values) {
        return fetch(ScrapyAccount.SCRAPY_ACCOUNT.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<cn.kindg.jooq.generator.tables.pojos.ScrapyAccount> fetchByState(Byte... values) {
        return fetch(ScrapyAccount.SCRAPY_ACCOUNT.STATE, values);
    }

    /**
     * Fetch records that have <code>create_time IN (values)</code>
     */
    public List<cn.kindg.jooq.generator.tables.pojos.ScrapyAccount> fetchByCreateTime(LocalDateTime... values) {
        return fetch(ScrapyAccount.SCRAPY_ACCOUNT.CREATE_TIME, values);
    }
}