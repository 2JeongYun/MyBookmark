package com.github.neukrang.mybookmark.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어디에 적용될 것인가
@Target(ElementType.PARAMETER)
// 언제까지 살려둘건가
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
