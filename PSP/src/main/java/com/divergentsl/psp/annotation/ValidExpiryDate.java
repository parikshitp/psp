package com.divergentsl.psp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.divergentsl.psp.annotation.impl.ExpiryDateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ExpiryDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExpiryDate {
    String message() default "Invalid Expiry Date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}