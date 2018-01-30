package org.consume.com.crew.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

@Min(0)
@Constraint(validatedBy = {})
@Documented
@Target( { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "错误的面积";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
