package com.bookshop.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils{
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }
}
