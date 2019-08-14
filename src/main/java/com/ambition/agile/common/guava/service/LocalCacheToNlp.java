package com.ambition.agile.common.guava.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

//mport com.adks.dubbo.api.data.Title;
import com.ambition.agile.common.guava.GuavaAbstractLoadingCache;
import com.ambition.agile.common.guava.ILocalCache;

/**
 * 本地缓存：parent -> Title
 * @author yy
 */
@Component
public class LocalCacheToNlp extends GuavaAbstractLoadingCache<String, String> 
					implements ILocalCache<String, String> { 
	
	private LocalCacheToNlp(){    
        setMaximumSize(300); // 最大缓存条数    
        setExpireAfterWriteDuration(30 * 1); // 数据存在时长 30秒
    }
	
	public String get(String key) {
        try {    
            return getValue(key);
        } catch (Exception e) {    
            logger.error("无法根据parentId={}获取nlp，可能是数据库中无该记录。", key ,e);    
            return null;    
        }
    }
	
	/* (non-Javadoc) 从数据库中获取数据
	 * @see com.adks.gclc.webcore.guava.GuavaAbstractLoadingCache#fetchData(java.lang.Object)
	 */
	protected String fetchData(String key,String value) {
        logger.debug("正在从数据库中获取title，parentId={}", key);
        return null;//titlePlatformWebApi.getTitleListByParent(key);   
    } 

}
