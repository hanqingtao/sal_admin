package com.ambition.agile.test;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ?grant_type=client_credential&appid=APPID&secret=APPSECRET
		 // Cache<String, String> cache = 
			//	  CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();
		  Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(3, TimeUnit.SECONDS).build();

	        cache.put("1","1abc2");
	        System.out.println(cache.asMap());
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        //System.out.println(cache.getIfPresent("1"));
	        System.out.println(cache.asMap());
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println(cache.asMap());
	    
	}

}
