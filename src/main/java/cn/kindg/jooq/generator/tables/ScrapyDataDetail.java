/*
 * This file is generated by jOOQ.
 */
package cn.kindg.jooq.generator.tables;


import cn.kindg.jooq.generator.Indexes;
import cn.kindg.jooq.generator.JScrapy;
import cn.kindg.jooq.generator.Keys;
import cn.kindg.jooq.generator.tables.records.ScrapyDataDetailRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class ScrapyDataDetail extends TableImpl<ScrapyDataDetailRecord> {

    private static final long serialVersionUID = -1459731336;

    /**
     * The reference instance of <code>j_scrapy.scrapy_data_detail</code>
     */
    public static final ScrapyDataDetail SCRAPY_DATA_DETAIL = new ScrapyDataDetail();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ScrapyDataDetailRecord> getRecordType() {
        return ScrapyDataDetailRecord.class;
    }

    /**
     * The column <code>j_scrapy.scrapy_data_detail.id</code>.
     */
    public final TableField<ScrapyDataDetailRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>j_scrapy.scrapy_data_detail.title</code>.
     */
    public final TableField<ScrapyDataDetailRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>j_scrapy.scrapy_data_detail.content</code>.
     */
    public final TableField<ScrapyDataDetailRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>j_scrapy.scrapy_data_detail.create_time</code>.
     */
    public final TableField<ScrapyDataDetailRecord, LocalDateTime> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>j_scrapy.scrapy_data_detail.remark</code>.
     */
    public final TableField<ScrapyDataDetailRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>j_scrapy.scrapy_data_detail.source</code>.
     */
    public final TableField<ScrapyDataDetailRecord, String> SOURCE = createField("source", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>j_scrapy.scrapy_data_detail</code> table reference
     */
    public ScrapyDataDetail() {
        this(DSL.name("scrapy_data_detail"), null);
    }

    /**
     * Create an aliased <code>j_scrapy.scrapy_data_detail</code> table reference
     */
    public ScrapyDataDetail(String alias) {
        this(DSL.name(alias), SCRAPY_DATA_DETAIL);
    }

    /**
     * Create an aliased <code>j_scrapy.scrapy_data_detail</code> table reference
     */
    public ScrapyDataDetail(Name alias) {
        this(alias, SCRAPY_DATA_DETAIL);
    }

    private ScrapyDataDetail(Name alias, Table<ScrapyDataDetailRecord> aliased) {
        this(alias, aliased, null);
    }

    private ScrapyDataDetail(Name alias, Table<ScrapyDataDetailRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ScrapyDataDetail(Table<O> child, ForeignKey<O, ScrapyDataDetailRecord> key) {
        super(child, key, SCRAPY_DATA_DETAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return JScrapy.J_SCRAPY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SCRAPY_DATA_DETAIL_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ScrapyDataDetailRecord> getPrimaryKey() {
        return Keys.KEY_SCRAPY_DATA_DETAIL_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ScrapyDataDetailRecord>> getKeys() {
        return Arrays.<UniqueKey<ScrapyDataDetailRecord>>asList(Keys.KEY_SCRAPY_DATA_DETAIL_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataDetail as(String alias) {
        return new ScrapyDataDetail(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataDetail as(Name alias) {
        return new ScrapyDataDetail(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ScrapyDataDetail rename(String name) {
        return new ScrapyDataDetail(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ScrapyDataDetail rename(Name name) {
        return new ScrapyDataDetail(name, null);
    }
}
