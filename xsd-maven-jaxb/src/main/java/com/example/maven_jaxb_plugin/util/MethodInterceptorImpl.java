package com.example.maven_jaxb_plugin.util;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorImpl implements MethodInterceptor {

    private String lastMethodName;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        lastMethodName = method.getName();
        return proxy.invokeSuper(obj, args);
    }

    public String getLastMethodName() {
        return lastMethodName;
    }

    public static <T> T createProxy(Class<T> clazz, MethodInterceptorImpl interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }
}

