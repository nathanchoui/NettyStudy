package personal.nathan.niobase.anno;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Tanscation {
    String value() default "true";
}
