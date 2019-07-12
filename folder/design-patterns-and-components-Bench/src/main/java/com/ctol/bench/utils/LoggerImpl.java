package com.ctol.bench.utils;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoggerImpl {
	private static final Logger LOGGER = Logger.getGlobal();

	public static void logInfo(String message) {
		LOGGER.log(Level.INFO, message);
	}

	public static void logSevere(String message) {
		LOGGER.log(Level.SEVERE, message);
	}

	public static void logWarning(String message) {
		LOGGER.log(Level.WARNING, message);
	}

	private LoggerImpl() {

	}
}
