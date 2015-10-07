package com.iontrading.frc_e2e.utils;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

@RobotKeywords
public class SetServerSourceCurrency {

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
	
	@RobotKeyword
	public void setRefdataSourceCurrency(String source, String currency) {
		
		REFDATA_SOURCE = source;
		REFDATA_CURRENCY = currency;
	}

	@RobotKeyword
	public void setPXESourceCurrency(String source, String currency) {

		PXE_SOURCE = source;
		PXE_CURRENCY = currency;
	}

	@RobotKeyword
	public void setTradeserverSourceCurrency(String source, String currency) {
		
		TRADESERVER_SOURCE = source;
		TRADESERVER_CURRENCY = currency;
	}

	@RobotKeyword
	public void setTradeentrySourceCurrency(String source, String currency) {

		TRADEENTRY_SOURCE = source;
		TRADEENTRY_CURRENCY = currency;

	}
	
	@RobotKeyword
	public void setPositionSourceCurrency(String source, String currency) {

		POSITION_SOURCE = source;
		POSITION_CURRENCY = currency;		
	}

	@RobotKeyword
	public void setRiskSourceCurrency(String source, String currency) {

		RISK_SOURCE = source;
		RISK_CURRENCY = currency;		
	}
}
