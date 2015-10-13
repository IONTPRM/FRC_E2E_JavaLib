package com.iontrading.frc_e2e.keywords;

import java.util.HashMap;
import java.util.Map;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.iontrading.frc_e2e.utils.SetServerSourceCurrency;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.keywords2.FunctionRepository;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;
import com.iontrading.robotframework.keywords2.TransactionRepository;

@RobotKeywords
public class E2E_PXE {

	private final FunctionRepository funcRep = new FunctionRepository();
	private final MkvRecordRepository recordRepository = new MkvRecordRepository();  
	private final TransactionRepository transRep = new TransactionRepository();
	
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
	 * This keyword does login to PXE and verify that trader is ON
	 * @param user
	 * @param password
	 * @throws Exception 
	 */
	@RobotKeyword
	public void loginToPXE(String user, String password) throws Exception {
		
		Object[] args = new Object[] {"User", user, "Pwd", password};
		funcRep.functionDefine(SetServerSourceCurrency.PXE_SOURCE, "VCMILogin", args);
		funcRep.functionSetTimeout("5s");
		funcRep.functionVerifyReturn("0", "OK");
		funcRep.functionCall();
		
		String recName = recordRepository.recordDefine(SetServerSourceCurrency.PXE_SOURCE, "CM_LOGIN", SetServerSourceCurrency.PXE_CURRENCY, user);
		recordRepository.recordSetTimeout(recName, "5s");
		recordRepository.recordVerifyFields(recName, new Object[] {"TStatusStr", "==", "On"});
		recordRepository.recordSubscribe(recName);
	}
	
	/**
	 * This keyword does logout from PXE and verify that trader is logged out
	 * @param user
	 * @throws Exception
	 */
	@RobotKeyword
	public void logoutFromPXE(String user) throws Exception {
		
		Object[] args = new Object[] {"User", user};
		funcRep.functionDefine(SetServerSourceCurrency.PXE_SOURCE, "VCMILogout", args);
		funcRep.functionSetTimeout("5s");
		funcRep.functionVerifyReturn("0", "OK");
		funcRep.functionCall();

		String recName = recordRepository.recordDefine(SetServerSourceCurrency.PXE_SOURCE, "CM_LOGIN", SetServerSourceCurrency.PXE_CURRENCY, user);
		recordRepository.recordSetTimeout(recName, "5s");
		recordRepository.recordWaitUnpublish(recName);
	}
	
	/**
	 * This keyword performs transaction on FeedToPXE1 field of imported instrument record in Refdata.
	 * @param instrId
	 * @throws Exception
	 */
	@RobotKeyword
	public void feedRefdataInstrumentToPXE(String instrId) throws Exception {
		// transacting on FeedToPXE1 field of Refdata's CM_INSTRUMENT record of imported intrument
		transRep.transactionDefine(SetServerSourceCurrency.REFDATA_SOURCE, "CM_INSTRUMENT", SetServerSourceCurrency.REFDATA_CURRENCY, instrId);
		transRep.transactionSetFieldsValues(new Object[] {"FeedToPXE1", "1"});
		transRep.transactionSetTimeout("5s");
		transRep.transactionVerifyReturn("0", "Ok");
		transRep.transactionCall();
		
		// To wait till instrument is published by CM_INSTRUMENT of PXE
		String instrRec = recordRepository.recordDefine(SetServerSourceCurrency.PXE_SOURCE, "CM_INSTRUMENT", SetServerSourceCurrency.PXE_CURRENCY, instrId);
		recordRepository.recordSetTimeout(instrRec, "20s");
		recordRepository.recordVerifyFields(instrRec, new Object[] {"Id", "==", instrId});
		recordRepository.recordSubscribe(instrRec);
		
		// to transact on CM_INSTRUMENT of PXE in order to publish it
		transRep.transactionDefine(SetServerSourceCurrency.PXE_SOURCE, "CM_INSTRUMENT", SetServerSourceCurrency.PXE_CURRENCY, instrId);
		transRep.transactionSetFieldsValues(new Object[] {"Publish", "true", "IndicatorProfile", "EUR"});
		transRep.transactionSetTimeout("5s");
		transRep.transactionVerifyReturn("0", "0:OK");
		transRep.transactionCall();
		
		
	}
	
	/**
	 * It generate pricing in PXE, call 'CreatePricing' function of PXE passing instrument id to create record in PRICINGS table. 
	Then it transacts on ModelStr, MidPrice fields of PRICING_MAIN table to price this instrument.
	 * @param instrId
	 * @param value
	 * @param valueType
	 * @throws Exception 
	 */
	@RobotKeyword
	public void priceInstrumentInPXE(String instrId, Double value, String valueType) throws Exception {
		
		// to create record in PRICINGS table
		Object[] args = new Object[] {"BondID", instrId};
		funcRep.functionDefine(SetServerSourceCurrency.PXE_SOURCE, "CreatePricing", args);
		funcRep.functionSetTimeout("10s");
		funcRep.functionVerifyRetCode(0);
		funcRep.functionCall();
		
		// set the pricing model
		transRep.transactionDefine(SetServerSourceCurrency.PXE_SOURCE, "PRICING_MAIN", SetServerSourceCurrency.PXE_CURRENCY, instrId);
		transRep.transactionSetFieldsValues(new Object[] {"ModelStr", "Manual Price"});
		transRep.transactionSetTimeout("5s");
		transRep.transactionVerifyReturn("0", "0:OK");
		transRep.transactionCall();
		
		String fieldNameToTransOn = "";
		if (valueType.equals("Price")) {
			fieldNameToTransOn = "MidPrice";
		} else if (valueType.equals("Discount")) {
			fieldNameToTransOn = "MidDiscount";
		}
		// price the PXE record with value given
		transRep.transactionDefine(SetServerSourceCurrency.PXE_SOURCE, "PRICING_MAIN", SetServerSourceCurrency.PXE_CURRENCY, instrId);
		transRep.transactionSetFieldsValues(new Object[] {fieldNameToTransOn, value});
		transRep.transactionSetTimeout("5s");
		transRep.transactionVerifyReturn("0", "0:OK");
		transRep.transactionCall();
	}
	
	/**
	 * This keyword calls ANLPYCalc function of PXE and return the value for the field you passed as first param 'fieldname_to_get_value'.
	 * 
	 * @param fieldNameToGetValue
	 * @param instrumentId
	 * @param value
	 * @param valueType
	 * @param dateSettl
	 * @return
	 * @throws Exception
	 */
	public String getValueUsingAnlpycalcFunc(String fieldNameToGetValue, String instrumentId, Double value, Integer valueType, Integer dateSettl) throws Exception {
		
		setANLPYCalcFuncResFieldValuePos();
		
		String fieldValueFromPXE = null;
		Object[] args = new Object[] {"InstrumentId", instrumentId, "Value", value, "ValueType", valueType, "DateSettl", dateSettl};
		funcRep.functionDefine(SetServerSourceCurrency.PXE_SOURCE, "ANLPYCalc", args);
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
		}
		
		if (fieldValueFromPXE.equals("NaN")) {
			fieldValueFromPXE = "0";
		}
		
		return fieldValueFromPXE;
	}
	
	/**
	 * This keyword perform transaction on FeedToPXE1 field of Refdata instrument record which was fed to PXE earlier. 
	By setting FeedToPXE1 to 0, this instrument is removed from PXE tables.
	 * @param instrIdList
	 * @throws Exception 
	 */
	@RobotKeyword
	public void removeInstrumentsFromPXE(String... instrIdList) throws Exception {
		
		for (int i=0; i<instrIdList.length; i++) {
			transRep.transactionDefine(SetServerSourceCurrency.REFDATA_SOURCE, "CM_INSTRUMENT", SetServerSourceCurrency.REFDATA_CURRENCY, instrIdList[i]);
			transRep.transactionSetFieldsValues(new Object[] {"FeedToPXE1", "0"});
			transRep.transactionSetTimeout("5s");
			transRep.transactionVerifyReturn("0", "Ok");
			transRep.transactionCall();
		}
	}
}

