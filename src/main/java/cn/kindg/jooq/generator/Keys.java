/*
 * This file is generated by jOOQ.
 */
package cn.kindg.jooq.generator;


import cn.kindg.jooq.generator.tables.ScrapyAccount;
import cn.kindg.jooq.generator.tables.ScrapyData;
import cn.kindg.jooq.generator.tables.ScrapyDataDetail;
import cn.kindg.jooq.generator.tables.records.ScrapyAccountRecord;
import cn.kindg.jooq.generator.tables.records.ScrapyDataDetailRecord;
import cn.kindg.jooq.generator.tables.records.ScrapyDataRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>j_scrapy</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ScrapyAccountRecord, Integer> IDENTITY_SCRAPY_ACCOUNT = Identities0.IDENTITY_SCRAPY_ACCOUNT;
    public static final Identity<ScrapyDataRecord, Integer> IDENTITY_SCRAPY_DATA = Identities0.IDENTITY_SCRAPY_DATA;
    public static final Identity<ScrapyDataDetailRecord, Integer> IDENTITY_SCRAPY_DATA_DETAIL = Identities0.IDENTITY_SCRAPY_DATA_DETAIL;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ScrapyAccountRecord> KEY_SCRAPY_ACCOUNT_PRIMARY = UniqueKeys0.KEY_SCRAPY_ACCOUNT_PRIMARY;
    public static final UniqueKey<ScrapyDataRecord> KEY_SCRAPY_DATA_PRIMARY = UniqueKeys0.KEY_SCRAPY_DATA_PRIMARY;
    public static final UniqueKey<ScrapyDataDetailRecord> KEY_SCRAPY_DATA_DETAIL_PRIMARY = UniqueKeys0.KEY_SCRAPY_DATA_DETAIL_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<ScrapyAccountRecord, Integer> IDENTITY_SCRAPY_ACCOUNT = Internal.createIdentity(ScrapyAccount.SCRAPY_ACCOUNT, ScrapyAccount.SCRAPY_ACCOUNT.ID);
        public static Identity<ScrapyDataRecord, Integer> IDENTITY_SCRAPY_DATA = Internal.createIdentity(ScrapyData.SCRAPY_DATA, ScrapyData.SCRAPY_DATA.ID);
        public static Identity<ScrapyDataDetailRecord, Integer> IDENTITY_SCRAPY_DATA_DETAIL = Internal.createIdentity(ScrapyDataDetail.SCRAPY_DATA_DETAIL, ScrapyDataDetail.SCRAPY_DATA_DETAIL.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ScrapyAccountRecord> KEY_SCRAPY_ACCOUNT_PRIMARY = Internal.createUniqueKey(ScrapyAccount.SCRAPY_ACCOUNT, "KEY_scrapy_account_PRIMARY", ScrapyAccount.SCRAPY_ACCOUNT.ID);
        public static final UniqueKey<ScrapyDataRecord> KEY_SCRAPY_DATA_PRIMARY = Internal.createUniqueKey(ScrapyData.SCRAPY_DATA, "KEY_scrapy_data_PRIMARY", ScrapyData.SCRAPY_DATA.ID);
        public static final UniqueKey<ScrapyDataDetailRecord> KEY_SCRAPY_DATA_DETAIL_PRIMARY = Internal.createUniqueKey(ScrapyDataDetail.SCRAPY_DATA_DETAIL, "KEY_scrapy_data_detail_PRIMARY", ScrapyDataDetail.SCRAPY_DATA_DETAIL.ID);
    }
}
