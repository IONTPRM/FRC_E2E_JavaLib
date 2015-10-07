package com.iontrading.frc_e2e.utils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.robotframework.javalib.annotation.*;

@RobotKeywords
public class E2E_Utility {
	/**
    * Only one instance of this library is created during the whole test execution and it
    * is shared by all test cases and test suites.
    */
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";
    public static final String ROBOT_LIBRARY_VERSION = "1.0.0";
    public static final String ROBOT_LIBRARY_DOC_FORMAT = "HTML";
    
    public HashMap<String, Integer> valueTypeEnum = new HashMap<String, Integer>();

    public E2E_Utility() {
    	this.valueTypeEnum.put("Price", 1);
    	this.valueTypeEnum.put("Yield", 2);
    	this.valueTypeEnum.put("YDIFF", 4);
    	this.valueTypeEnum.put("Basis", 8);
    	this.valueTypeEnum.put("Discount", 16);
    	this.valueTypeEnum.put("Spread", 32);
    	this.valueTypeEnum.put("DiscountMargin", 64);
    }
    
    public Integer getValueTypeInt(Integer valueTypeStr) {
    	return this.valueTypeEnum.get(valueTypeStr);
    }

    static final Pattern WASH_NAME_REGEX = Pattern.compile("[^a-zA-Z0-9@#_]");
	public String washName(String stringToWash) {
		final Matcher m = WASH_NAME_REGEX.matcher(stringToWash);
		return m.replaceAll("_");
	}    

	@RobotKeyword
	public String selectCorrectInstrumentToImport(String instrType, String couponType) {
		String extInstrId = null;
		
		if (instrType.equals("T Note") && couponType.equals("Fixed")) {
			extInstrId = "Govt_Fixed_1";
		} else if (instrType.equals("Corp") && couponType.equals("Floater")) {
			extInstrId = "Corp_Floater_1";			
		} else if (instrType.equals("Govt") && couponType.equals("Index Linked")) {
			extInstrId = "Govt_IndexLinked_1";			
		} else if (instrType.equals("Govt") && couponType.equals("Sinkable")) {
			extInstrId = "Govt_Sinakble_1";			
		} else if (instrType.equals("Govt") && couponType.equals("Putable")) {
			extInstrId = "Govt_Putable_1";			
		} else if (instrType.equals("Corp") && couponType.equals("Zero Coupon")) {
			extInstrId = "Corp_ZeroCoupon_1";			
		} else if (instrType.equals("Govt") && couponType.equals("Zero Coupon")) {
			extInstrId = "Govt_ZeroCoupon_1";			
		} else if (instrType.equals("Agency") && couponType.equals("Fixed")) {
			extInstrId = "Agency_Fixed_1";			
		} else if (instrType.equals("Agency") && couponType.equals("Callable")) {
			extInstrId = "Agency_Callable_1";			
		} else if (instrType.equals("Agency") && couponType.equals("Zero Coupon")) {
			extInstrId = "Agency_ZeroCoupon_1";			
		}
		
		return extInstrId;
	}
	
}
