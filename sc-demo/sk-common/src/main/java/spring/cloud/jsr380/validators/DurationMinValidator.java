package spring.cloud.jsr380.validators;

import spring.cloud.jsr380.constraints.DurationMin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

/**
 * @Description TODO
 * @Author junode
 * @Date 2021/1/25
 */
public class DurationMinValidator implements ConstraintValidator<DurationMin, Duration> {
    private Duration duration;

    @Override
    public void initialize(DurationMin constraintAnnotation) {
        this.duration = Duration.of(constraintAnnotation.value(), constraintAnnotation.units());
    }

    @Override
    public boolean isValid(Duration value, ConstraintValidatorContext context) {
        // null values are valid
        if(value == null) {
            return true;
        }
        return false;
    }
}
