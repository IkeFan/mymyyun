package com.mmy.maimaiyun.base.scoped;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @创建者 lucas
 * @创建时间 2017/8/12 0012 16:57
 * @描述 TODO
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
