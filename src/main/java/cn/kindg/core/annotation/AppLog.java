package cn.kindg.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AppLog {
    String name();

    String code();

    String resourceName() default "";

    String resourceCode() default "";
}
