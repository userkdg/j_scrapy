package cn.kindg.jscrapy.disruptor;

@FunctionalInterface
public interface BaseFactory<T> {
    T newInstance();
}
