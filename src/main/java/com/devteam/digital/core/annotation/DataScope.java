package com.devteam.digital.core.annotation;

import java.lang.annotation.*;

//Nó sẽ được dùng chú thích tren mot method
@Target(ElementType.METHOD)
//Mức tồn tại lớn nhất, được trình biên dịch nhận biết,
// và máy ảo (JVM) cũng nhận ra khi chạy chương trình.
@Retention(RetentionPolicy.RUNTIME)
//@Documented: Chú thích chỉ ra rằng loại chú thích có thể được kế thừa từ lớp cha và có giá trị mặc định là false.
@Documented
//annotation tự định nghĩa
public @interface DataScope {
    public String deptAlias() default "";
    public String userAlias() default "";
}
