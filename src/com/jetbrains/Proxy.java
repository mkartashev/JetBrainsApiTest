package com.jetbrains;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks JBR API proxy (including 2-way proxies).
 * Proxies are implemented by JBR API backend.
 * Proxy interfaces must not be implemented by client code unless explicitly marked with {@link Client}.
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@interface Proxy {
}
