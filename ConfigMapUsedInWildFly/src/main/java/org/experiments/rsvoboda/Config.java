package org.experiments.rsvoboda;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * Created by rsvoboda on 5/1/17.
 */
@Qualifier
@Retention(RUNTIME)
@Documented
public @interface Config {

    @Nonbinding
    String value();

}
