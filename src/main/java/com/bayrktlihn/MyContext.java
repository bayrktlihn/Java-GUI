package com.bayrktlihn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bayrktlihn.config.AppConfig;

public class MyContext {
	private static AnnotationConfigApplicationContext context;

	public static <T> T getBean(String beanName, Class<? extends T> classType) {
		if (context == null) {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
		}
		return context.getBean(beanName, classType);
	}

	public static <T> T getBean(Class<? extends T> classType) {
		if (context == null) {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
		}
		return context.getBean(classType);
	}
	
	public static void close() {
		if(context != null)
			context.close();
	}
}
