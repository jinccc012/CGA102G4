package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;


public class Webutils {
	
	

	public static void copyParamToBean(HttpServletRequest req,Object bean) {
		try {
			//獲取所有列表名稱
			Enumeration<String> enums =req.getParameterNames();	
			//register if sql.date is null is ok
			BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null),Date.class);
			BeanUtilsBean.getInstance().getConvertUtils().register(new SqlTimestampConverter(null),Timestamp.class);
			//便利
			while(enums.hasMoreElements()) {
				//獲取表單元素名稱
				String name = enums.nextElement();
				//獲取相對值
				String value = req.getParameter(name);
		
			BeanUtils.copyProperty(bean, name, value);
			}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
