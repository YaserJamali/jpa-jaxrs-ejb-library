package tamin.library.model.service.BL;


import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, TYPE, FIELD,PARAMETER})
@Retention(RUNTIME)
@Qualifier
public @interface ThirteenDigit {
}
