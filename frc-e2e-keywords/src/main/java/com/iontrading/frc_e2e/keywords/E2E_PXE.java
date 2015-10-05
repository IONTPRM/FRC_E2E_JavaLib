package com.iontrading.frc_e2e.keywords;

import java.util.HashMap;
import java.util.Map;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.keywords2.FunctionRepository;

@RobotKeywords
public class E2E_PXE {

	public static String PXE_SOURCE;
	public static String PXE_CURRENCY;

	private final FunctionRepository funcRep = new FunctionRepository();

	public Map<String, Integer> anlPYCalcFuncResFieldValuePos = new HashMap<String, Integer>();
	
	public void setANLPYCalcFuncResFieldValuePos() {
		anlPYCalcFuncResFieldValuePos.put("Status", 0);
		anlPYCalcFuncResFieldValuePos.put("Price", 1);
		anlPYCalcFuncResFieldValuePos.put("Yield", 2);
		anlPYCalcFuncResFieldValuePos.put("Discount", 3);
		anlPYCalcFuncResFieldValuePos.put("MMYield", 4);
		anlPYCalcFuncResFieldValuePos.put("DateSettl", 5);
		anlPYCalcFuncResFieldValuePos.put("AccruedInterest", 6);
		anlPYCalcFuncResFieldValuePos.put("AccruedDays", 7);
		anlPYCalcFuncResFieldValuePos.put("DV01", 8);
		anlPYCalcFuncResFieldValuePos.put("IndexRatio", 9);
		anlPYCalcFuncResFieldValuePos.put("RiskPrice", 10);
		anlPYCalcFuncResFieldValuePos.put("DiscountMargin", 11);
		anlPYCalcFuncResFieldValuePos.put("Factor", 12);
	}
	
	/**
	 * 
	 * @param source
	 * @param currency
	 */
	@RobotKeyword	
	public void setPxeSourceAndCurrency(String source, String currency) {
		
		E2E_PXE.PXE_SOURCE = source;
		E2E_PXE.PXE_CURRENCY = currency;	
	}
	
	/**
	 * 
	 * @param fieldNameToGetValue
	 * @param instrumentId
	 * @param value
	 * @param valueType
	 * @param dateSettl
	 * @return
	 * @throws Exception
	 */
	public String getValueUsingAnlpycalcFunc(String fieldNameToGetValue, String instrumentId, String value, String valueType, String dateSettl) throws Exception {
		
		setANLPYCalcFuncResFieldValuePos();
		
		String fieldValueFromPXE = null;
		
		funcRep.functionDefine(PXE_SOURCE, "ANLPYCalc", "InstrumentId", instrumentId, "Value", value, "ValueType", valueType, "DateSettl", dateSettl);
		funcRep.functionSetTimeout("20s");
		funcRep.functionVerifyReturn("0", "OK");
		IFunctionCallResult result = funcRep.functionCall();
		
		if (fieldNameToGetValue.equals("Price") || 
				fieldNameToGetValue.equals("Yield") || 
				fieldNameToGetValue.equals("Discount") || 
				fieldNameToGetValue.equals("MMYield") || 
				fieldNameToGetValue.equals("AccruedInterest") || 
				fieldNameToGetValue.equals("DV01") ||
				fieldNameToGetValue.equals("IndexRatio") ||
				fieldNameToGetValue.equals("RiskPrice") ||
				fieldNameToGetValue.equals("DiscountMargin") ||
				fieldNameToGetValue.equals("Factor")) {
			fieldValueFromPXE = result.getDouble(anlPYCalcFuncResFieldValuePos.get(fieldNameToGetValue)).toString();
		} else if (fieldNameToGetValue.equals("DateSettl") || fieldNameToGetValue.equals("AccruedDays")) {
			fieldValueFromPXE = result.getInteger(anlPYCalcFuncResFieldValuePos.get(fieldNameToGetValue)).toString();
		} else {
			return "";
		}
		
		return fieldValueFromPXE;
	}
}
