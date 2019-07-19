/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.ambition.agile.common.io;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 配置文件加载（Boot）
 * @author ThinkGem
 * @version 2018-10-16
 */
public class PropertyLoader {
	
	private static boolean isLoadPropertySource = false;
	
	public String[] getFileExtensions() {
		return new String[] { "properties", "yml" };
	}
	
	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
		if (!isLoadPropertySource){
			isLoadPropertySource = true;
			Properties properties = PropertiesUtils.getInstance().getProperties();
			return null;
		}
		return Collections.emptyList();
	}
	
}
