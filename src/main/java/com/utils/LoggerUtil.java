package com.utils;

import org.apache.logging.log4j.*;
public class LoggerUtil {

	public static Logger getLogger(Class<?> cls) {
		return LogManager.getLogger(cls);
		
	}
}
