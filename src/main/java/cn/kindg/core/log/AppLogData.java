package cn.kindg.core.log;

import java.time.LocalDateTime;

public class AppLogData {
    private String resourceCode;
    private String operationCode;
    private String content;
    private LocalDateTime createTime;
    private long handlerTime;

    public String getResourceCode() {
        return resourceCode;
    }

    public AppLogData setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
        return this;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public AppLogData setOperationCode(String operationCode) {
        this.operationCode = operationCode;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AppLogData setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public AppLogData setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public long getHandlerTime() {
        return handlerTime;
    }

    public AppLogData setHandlerTime(long handlerTime) {
        this.handlerTime = handlerTime;
        return this;
    }
}
