package cn.kindg.core.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean ok;
    private String message;
    private T data;
    private Map<String, Object> moreData;

    public AppResult() {
    }

    public AppResult(boolean ok, String message, T data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public boolean isOk() {
        return this.ok;
    }

    public AppResult<T> setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public AppResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public AppResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> getMoreData() {
        return this.moreData;
    }

    public AppResult<T> setMoreData(Map<String, Object> extData) {
        this.moreData = extData;
        return this;
    }

    public AppResult<T> addMoreData(String key, Object value) {
        if (this.moreData == null) {
            this.moreData = new LinkedHashMap();
        }

        this.moreData.put(key, value);
        return this;
    }

    public static <T> AppResult<T> ok() {
        return new AppResult(true, (String) null, (Object) null);
    }

    public static <T> AppResult<T> ok(T data) {
        return new AppResult(true, (String) null, data);
    }

    public static <T> AppResult<T> fail() {
        return new AppResult(false, (String) null, (Object) null);
    }

    public static <T> AppResult<T> fail(String message) {
        return new AppResult(false, message, (Object) null);
    }

    public static <T> AppResult<T> of(boolean ok, String message) {
        return new AppResult(ok, message, (Object) null);
    }

    public static <T> AppResult<T> of(boolean ok, T data, String message) {
        return new AppResult(ok, message, data);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
