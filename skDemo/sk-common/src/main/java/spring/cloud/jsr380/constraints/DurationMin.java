package spring.cloud.jsr380.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Constraint(validatedBy = {})
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(List.class)
@ReportAsSingleViolation
public @interface DurationMin {
    String message() default "{spring.cloud.jsr380.DurationMin.message}";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    long value() default 0;
    ChronoUnit units() default ChronoUnit.NANOS;

}
/**
 * Defines several {@code @DurationMin} annotations on the same element.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface List {
    DurationMin[] value();
}
