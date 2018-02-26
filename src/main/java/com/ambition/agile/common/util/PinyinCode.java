package com.ambition.agile.common.util;

import java.io.Serializable;


/** pinyin
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class PinyinCode implements Serializable {  
     public PinyinCode(String py, int cd) {  
         pinyin = py;  
         code = cd;  
     }  
      public String pinyin = null;  
      public int code = 0;  
 } 