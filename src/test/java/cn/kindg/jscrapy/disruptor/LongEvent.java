package cn.kindg.jscrapy.disruptor;

import java.util.StringJoiner;

public class LongEvent {
    private long value;

    public LongEvent setValue(long value) {
        this.value = value;
        return this;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LongEvent.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
