package com.iontrading.frc_e2e.utils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.robotframework.javalib.annotation.*;

@SuppressWarnings("unused")
public class E2E_Utility {
	/**
    * Only one instance of this library is created during the whole test execution and it
    * is shared by all test cases and test suites.
    */
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    public static final String ROBOT_LIBRARY_VERSION = "1.0.0";
    
    static final Pattern WASH_NAME_REGEX = Pattern.compile("[^a-zA-Z0-9@#_]");
    
    public HashMap<String, Integer> value_type_enum = new HashMap<String, Integer>();

    public E2E_Utility() {
    	this.value_type_enum.put("Price", 1);
    	this.value_type_enum.put("Yield", 2);
    	this.value_type_enum.put("YDIFF", 4);
    	this.value_type_enum.put("Basis", 8);
    	this.value_type_enum.put("Discount", 16);
    	this.value_type_enum.put("Spread", 32);
    	this.value_type_enum.put("DiscountMargin", 64);
    }
    
    public Integer getValueTypeInt(Integer valueTypeStr) {
    	return this.value_type_enum.get(valueTypeStr);
    }
    
	@RobotKeyword
	public String selectCorrectInstrumentToImport(String instr_type, String coupon_type) {
		String ext_instr_id = null;
		
		if (instr_type.equals("T Note") && coupon_type.equals("Fixed")) {
			ext_instr_id = "Govt_Fixed_1";
		} else if (instr_type.equals("Corp") && coupon_type.equals("Floater")) {
			ext_instr_id = "Corp_Floater_1";			
		} else if (instr_type.equals("Govt") && coupon_type.equals("Index Linked")) {
			ext_instr_id = "Govt_IndexLinked_1";			
		} else if (instr_type.equals("Govt") && coupon_type.equals("Sinkable")) {
			ext_instr_id = "Govt_Sinakble_1";			
		} else if (instr_type.equals("Govt") && coupon_type.equals("Putable")) {
			ext_instr_id = "Govt_Putable_1";			
		} else if (instr_type.equals("Corp") && coupon_type.equals("Zero Coupon")) {
			ext_instr_id = "Corp_ZeroCoupon_1";			
		} else if (instr_type.equals("Govt") && coupon_type.equals("Zero Coupon")) {
			ext_instr_id = "Govt_ZeroCoupon_1";			
		} else if (instr_type.equals("Agency") && coupon_type.equals("Fixed")) {
			ext_instr_id = "Agency_Fixed_1";			
		} else if (instr_type.equals("Agency") && coupon_type.equals("Callable")) {
			ext_instr_id = "Agency_Callable_1";			
		} else if (instr_type.equals("Agency") && coupon_type.equals("Zero Coupon")) {
			ext_instr_id = "Agency_ZeroCoupon_1";			
		}
		
		return ext_instr_id;
	}
	
	public String washName(String stringToWash) {
		
	}
}
