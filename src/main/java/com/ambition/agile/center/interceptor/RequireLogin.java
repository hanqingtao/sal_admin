package com.ambition.agile.center.interceptor;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import com.ambition.agile.center.interceptor.ResultTypeEnum;

/**
 * 用户登录状态
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  RequireLogin {
	

    /**
     * @author harry
     * @time 2017 2017年11月20日 下午2:02:39
     * @see  
     * @return 
     */
    ResultTypeEnum value()
            default ResultTypeEnum.json;
    
    
}
