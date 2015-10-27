package com.iontrading.frc_e2e.utils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.robotframework.javalib.annotation.*;

import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Utility {
   
    public HashMap<String, Integer> valueTypeEnum = new HashMap<String, Integer>();
    private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Utility.class.getName());
    private static MkvRecordRepository recordRep = new MkvRecordRepository();
    

    public E2E_Utility() {
    	this.valueTypeEnum.put("Price", 1);
    	this.valueTypeEnum.put("Yield", 2);
    	this.valueTypeEnum.put("YDIFF", 4);
    	this.valueTypeEnum.put("Basis", 8);
    	this.valueTypeEnum.put("Discount", 16);
    	this.valueTypeEnum.put("Spread", 32);
    	this.valueTypeEnum.put("DiscountMargin", 64);
    }
    
    public Integer getValueTypeInt(String valueTypeStr) {
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
	
	@RobotKeyword
	public static void verifyRecordDoesnotExist(String source, String currency, String tableName, String recordId)
		throws Exception {
		String recordFullName = recordRep.recordDefine(source, tableName, currency, recordId);
		try
		{
			recordRep.recordSetTimeout(recordFullName, SetServerSourceCurrency.TIMEOUT_M);
			recordRep.recordWaitUnpublish(recordFullName);
			recordRep.recordSubscribe(recordFullName);
		}
		catch (Exception e)
		{
			htmlLogger.info("Exception thrown by keywoard :" + e);
			throw new Exception("Record " + recordFullName + " exist in the " + tableName + " table.");
		}
		finally
		{
			recordRep.recordClose(recordFullName);
		}
	}
		
}
