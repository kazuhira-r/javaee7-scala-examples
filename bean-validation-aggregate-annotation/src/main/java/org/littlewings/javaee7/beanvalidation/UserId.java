package org.littlewings.javaee7.beanvalidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Size(min = 3, max = 5)
@Pattern(regexp = "[A-Z0-9]+")
public @interface UserId {
    String message() default "{message.UserId}";  // ここは使われない

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
