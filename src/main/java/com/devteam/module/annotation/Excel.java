package com.devteam.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * @author ddthien
 * vion version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {
    public int sort() default Integer.MAX_VALUE;

    public String name() default "";
    public String dateFormat() default "";
    public String dictType() default "";
    public String readConverterExp()  default "";
    public String separator() default "";
    public  int scale() default -1;
    public  int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    public ColumnType cellType() default ColumnType.STRING;

    public double height() default 14;

    public double width() default 16;

    public String suffix() default "";

    public String defaultValue() default "";

    public String prompt() default "";

    public String[] combo() default {};

    public boolean isExport() default true;

    public String targetAttr() default "";

    public boolean isStatistics() default false;

    Align align() default Align.AUTO;

    public enum Align
    {
        AUTO(0), LEFT(1), CENTER(2), RIGHT(3);
        private final int value;

        Align(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    Type type() default Type.ALL;

    public enum Type
    {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    public enum ColumnType
    {
        NUMERIC(0), STRING(1), IMAGE(2);
        private final int value;

        ColumnType(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }
}

