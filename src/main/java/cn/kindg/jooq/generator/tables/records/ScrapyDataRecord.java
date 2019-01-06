/*
 * This file is generated by jOOQ.
 */
package cn.kindg.jooq.generator.tables.records;


import cn.kindg.jooq.generator.tables.ScrapyData;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ScrapyDataRecord extends UpdatableRecordImpl<ScrapyDataRecord> implements Record6<Integer, String, String, LocalDateTime, String, Long> {

    private static final long serialVersionUID = 1443911719;

    /**
     * Setter for <code>j_scrapy.scrapy_data.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>j_scrapy.scrapy_data.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>j_scrapy.scrapy_data.data_source</code>.
     */
    public void setDataSource(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>j_scrapy.scrapy_data.data_source</code>.
     */
    public String getDataSource() {
        return (String) get(1);
    }

    /**
     * Setter for <code>j_scrapy.scrapy_data.data_type</code>.
     */
    public void setDataType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>j_scrapy.scrapy_data.data_type</code>.
     */
    public String getDataType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>j_scrapy.scrapy_data.create_time</code>.
     */
    public void setCreateTime(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>j_scrapy.scrapy_data.create_time</code>.
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>j_scrapy.scrapy_data.remark</code>.
     */
    public void setRemark(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>j_scrapy.scrapy_data.remark</code>.
     */
    public String getRemark() {
        return (String) get(4);
    }

    /**
     * Setter for <code>j_scrapy.scrapy_data.data_count</code>.
     */
    public void setDataCount(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>j_scrapy.scrapy_data.data_count</code>.
     */
    public Long getDataCount() {
        return (Long) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, LocalDateTime, String, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, LocalDateTime, String, Long> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ScrapyData.SCRAPY_DATA.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return ScrapyData.SCRAPY_DATA.DATA_SOURCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return ScrapyData.SCRAPY_DATA.DATA_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field4() {
        return ScrapyData.SCRAPY_DATA.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return ScrapyData.SCRAPY_DATA.REMARK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field6() {
        return ScrapyData.SCRAPY_DATA.DATA_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getDataSource();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDataType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component4() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component6() {
        return getDataCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getDataSource();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDataType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value4() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value6() {
        return getDataCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord value2(String value) {
        setDataSource(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord value3(String value) {
        setDataType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord value4(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord value5(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord value6(Long value) {
        setDataCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScrapyDataRecord values(Integer value1, String value2, String value3, LocalDateTime value4, String value5, Long value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ScrapyDataRecord
     */
    public ScrapyDataRecord() {
        super(ScrapyData.SCRAPY_DATA);
    }

    /**
     * Create a detached, initialised ScrapyDataRecord
     */
    public ScrapyDataRecord(Integer id, String dataSource, String dataType, LocalDateTime createTime, String remark, Long dataCount) {
        super(ScrapyData.SCRAPY_DATA);

        set(0, id);
        set(1, dataSource);
        set(2, dataType);
        set(3, createTime);
        set(4, remark);
        set(5, dataCount);
    }
}
