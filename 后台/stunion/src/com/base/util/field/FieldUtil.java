package com.base.util.field;

 
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.base.util.DateUtil;
import com.base.util.StringUtil;

import net.sf.json.JSONObject;

public class FieldUtil {
	
	/**
     * 判断一个类是否存在某个属性（字段）
     *
     * @param field 字段
     * @param obj   类对象
     * @return true:存在，false:不存在,
     */
    public static Boolean isExistField(String field, Object obj) {
        if (obj == null || StringUtil.isEmpty(field)) {
            return false;
        }
        //将java对象转换为JSON对象
        JSONObject jsonObj = JSONObject.fromObject(obj);
        return jsonObj.containsKey(field);
    }


    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
    
    public static void setCreatedAt(Object obj){
    	try {
			Field f1 = obj.getClass().getDeclaredField("createdAt");
			f1.setAccessible(true);
			f1.set(obj, DateUtil.getNow());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void setUpdatedAt(Object obj){
    	try {
			Field f1 = obj.getClass().getDeclaredField("updatedAt");
			f1.setAccessible(true);
			f1.set(obj, DateUtil.getNow());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static Object getFieldValueByName(String field, Object obj) {
        try {  
            /*String firstLetter = fieldName.substring(0, 1).toUpperCase();  
            String getter = "get" + firstLetter + fieldName.substring(1);  
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  */
        	Field f1 = obj.getClass().getDeclaredField(field);
        	f1.setAccessible(true);
            return f1.get(obj);  
        } catch (Exception e) {  
            return null;  
        }  
    } 
	
}
