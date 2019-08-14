package com.ambition.agile.common.guava;

/**
 * @author yy
 * @param <K>  Key的类型   
 * @param <V>  Value的类型  
 */
public interface ILocalCache <K, V> {
	
	/**   
     * 从缓存中获取数据   
     * @param key   
     * @return value   
     */    
    public V get(K key); 
    
}
