package com.bingo.anno;

import java.lang.annotation.*;

/**
 * @author 徐志斌
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequire {

}
