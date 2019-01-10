package cn.kindg.core.base;

import java.util.function.Supplier;


public interface BeanFactory<T> {
    static <T> T create(Supplier<T> supplier) {
        return supplier.get();
    }

    static <T> T defaultValue(Supplier<T> supplier) {
        return create(supplier);
    }

    static <T> T getOrDefault(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }


}
