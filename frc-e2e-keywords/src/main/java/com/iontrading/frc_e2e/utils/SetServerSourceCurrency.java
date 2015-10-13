package com.iontrading.frc_e2e.utils;

import org.robotframework.javalib.annotation.*;

@RobotKeywords
public class SetServerSourceCurrency {

    private static final RobotLogger htmlLogger = RobotLogger.getLogger(SetServerSourceCurrency.class.getName());

	public static String REFDATA_SOURCE;
	public static String REFDATA_CURRENCY;
	public static String PXE_SOURCE;
	public static String PXE_CURRENCY;
	public static String TRADESERVER_SOURCE;
	public static String TRADESERVER_CURRENCY;
	public static String TRADEENTRY_SOURCE;
	public static String TRADEENTRY_CURRENCY;
	public static String POSITION_SOURCE;
	public static String POSITION_CURRENCY;
	public static String RISK_SOURCE;
	public static String RISK_CURRENCY;
	
	public static String TIMEOUT_S;
	public static String TIMEOUT_M;
	public static String TIMEOUT_L;
	public static String TIMEOUT_XL;

	/**
	 * Initializes the timeout variables to be used in keywords.
	 * 
	 * *Parameters:*
	 * 		_timeout_small_: small timeout value, for example 5 seconds 
	 * 		_timeout_medium_: medium timeout value, for example 10 seconds
	 * 		_timeout_large_: large timeout value, for example 20 seconds
	 * 		_timeout_exlarge_: very large timeout value, for example 60 seconds
	 * 
	 * *Example:*
	 * 		| Set Timeout Values | 5s | 10s | 20s | 60s |
	 * 		| Set Timeout Values | 5000ms | 100000ms | 200000ms | 600000ms |
	 */
	@RobotKeyword
	public void setTimeoutValues(String timeout_small, String timeout_medium, String timeout_large, String timeout_exlarge) {
		TIMEOUT_S = timeout_small;
		TIMEOUT_M = timeout_medium;
		TIMEOUT_L = timeout_large;
		TIMEOUT_XL = timeout_exlarge;
		htmlLogger.info("Timeout variables to used in further keywords have been initialized with " 
				+ "TIMEOUT_S=" + timeout_small + " TIMEOUT_M=" + timeout_medium + " TIMEOUT_L=" + timeout_large + " TIMEOUT_XL=" + timeout_exlarge);
	}
	
	/**
	 * 
	 * @param source
	 * @param currency
	 */
	@RobotKeyword
	public void setRefdataSourceCurrency(String source, String currency) {
		
		REFDATA_SOURCE = source;
		REFDATA_CURRENCY = currency;
	}

	/**
	 * 
	 * @param source 
	 * @param currency
	 */
	@RobotKeyword
	public void setPXESourceCurrency(String source, String currency) {

		PXE_SOURCE = source;
		PXE_CURRENCY = currency;
	}

	/**
	 * 
	 * @param source
	 * @param currency
	 */
	@RobotKeyword
	public void setTradeserverSourceCurrency(String source, String currency) {
		
		TRADESERVER_SOURCE = source;
		TRADESERVER_CURRENCY = currency;
	}

	/**
	 * 
	 * @param source
	 * @param currency
	 */
	@RobotKeyword
	public void setTradeentrySourceCurrency(String source, String currency) {

		TRADEENTRY_SOURCE = source;
		TRADEENTRY_CURRENCY = currency;
	}
	
	/**
	 * 
	 * @param source
	 * @param currency
	 */
	@RobotKeyword
	public void setPositionSourceCurrency(String source, String currency) {

		POSITION_SOURCE = source;
		POSITION_CURRENCY = currency;		
	}

	/**
	 * 
	 * @param source
	 * @param currency
	 */
	@RobotKeyword
	public void setRiskSourceCurrency(String source, String currency) {

		RISK_SOURCE = source;
		RISK_CURRENCY = currency;		
	}
}
