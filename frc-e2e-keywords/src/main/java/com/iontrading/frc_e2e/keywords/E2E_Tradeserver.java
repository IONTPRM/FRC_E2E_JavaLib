package com.iontrading.frc_e2e.keywords;

import org.robotframework.javalib.annotation.*;

import com.iontrading.frc_e2e.utils.*;

import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Tradeserver {

    public static final String ROBOT_LIBRARY_DOC_FORMAT = "HTML";

    private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Tradeserver.class.getName());
    
	public static String TS_SOURCE;
	public static String TS_CURRENCY;
	
	private final MkvRecordRepository recordRepository = new MkvRecordRepository();  
		
	@RobotKeyword
	public void setTradeserverSourceAndCurrency(String source, String currency) {
		
		E2E_Tradeserver.TS_SOURCE = source;
		E2E_Tradeserver.TS_CURRENCY = currency;	
	}
	
	public String getTSTradeRecordId(String tsTradeId) {
		
		E2E_Utility utility = new E2E_Utility();
		String washedTradeId = utility.washName(tsTradeId);
		return TS_CURRENCY + "_CM_TRADE_" + TS_SOURCE + "_" + washedTradeId;
	}
	
	/**
	 * Keyword to verify fields' values on trade record in Tradeserver
	 * @param tradeId
	 * @param fieldValuePairs
	 * @throws Exception
	 */
	@RobotKeyword
	public void verifyTradeserverFields(String tradeId, Object... fieldValuePairs) throws Exception {

		//String cmTradeRecordName = getTSTradeRecordId(tradeId);
		E2E_Utility utility = new E2E_Utility();
		E2E_PXE pxe = new E2E_PXE();
		String washedTradeId = utility.washName(tradeId);
		
		htmlLogger.info(washedTradeId);
		htmlLogger.info("Length of arg list is " + fieldValuePairs.length);
		htmlLogger.info(TS_SOURCE + " " + TS_CURRENCY);
		
		String recName1 = recordRepository.recordDefine(TS_SOURCE, "CM_TRADEHISTORY", TS_CURRENCY, washedTradeId);
		recordRepository.recordSetTimeout(recName1, "10s");		
		IReadableRecord readableRecord = recordRepository.recordVerifyFields(recName1, new Object[] {"Id", "==", "\"" + tradeId + "\""}); 
		recordRepository.recordSubscribe(recName1);
				
		String instrumentId = (String) readableRecord.getFieldValue("InstrumentId");
		Double value = (Double) readableRecord.getFieldValue("Value");
		Integer valueType = (Integer) readableRecord.getFieldValue("ValueType");
		Integer dateSettl = (Integer) readableRecord.getFieldValue("DateSettl");
		recordRepository.recordClose(recName1);
		
		for (int i=0; i < fieldValuePairs.length; i++) {
			htmlLogger.info("Reached for loop for fieldValuePairs" + fieldValuePairs[i].toString());
			if (fieldValuePairs[i].equals("value_ret_by_pxe")) {
				htmlLogger.info("Inside if condition, " + fieldValuePairs[i].toString());
				fieldValuePairs[i] = pxe.getValueUsingAnlpycalcFunc(fieldValuePairs[i-1].toString(), instrumentId, value, valueType, dateSettl);
			}
		}
		
		
		String recName2 = recordRepository.recordDefine(TS_SOURCE, "CM_TRADEHISTORY", TS_CURRENCY, washedTradeId);
		recordRepository.recordSetTimeout(recName2, "5s");
		recordRepository.recordVerifyFields(recName2, fieldValuePairs);
		recordRepository.recordSubscribe(recName2);
		recordRepository.recordClose(recName2);
	
	}
}
