package com.iontrading.frc_e2e.keywords;

import org.robotframework.javalib.annotation.*;

import com.iontrading.frc_e2e.utils.*;

import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Tradeserver {

    public static final String ROBOT_LIBRARY_DOC_FORMAT = "HTML";

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
	public void verifyTradeserverFields(String tradeId, Object[] fieldValuePairs) throws Exception {

		//String cmTradeRecordName = getTSTradeRecordId(tradeId);
		E2E_Utility utility = new E2E_Utility();
		E2E_PXE pxe = new E2E_PXE();
		String washedTradeId = utility.washName(tradeId);
		
		String recName1 = recordRepository.recordDefine(TS_SOURCE, "CM_TRADE", TS_CURRENCY, washedTradeId);
		recordRepository.recordSetTimeout(recName1, "5s");
		IReadableRecord readableRecord = recordRepository.recordSubscribe(recName1);
		recordRepository.recordClose(recName1);
		
		String instrumentId = readableRecord.getFieldValue("InstrumentId").toString();
		String value = readableRecord.getFieldValue("Value").toString();
		String valueType = readableRecord.getFieldValue("ValueType").toString();
		String dateSettl = readableRecord.getFieldValue("DateSettl").toString();
		
		for (int i=0; i < fieldValuePairs.length; i=i+2) {
			if (fieldValuePairs[i+1] == "value_ret_by_pxe") {
				fieldValuePairs[i+1] = pxe.getValueUsingAnlpycalcFunc(fieldValuePairs[i].toString(), instrumentId, value, valueType, dateSettl);
			}
		}
		
		String recName2 = recordRepository.recordDefine(TS_SOURCE, "CM_TRADE", TS_CURRENCY, washedTradeId);
		recordRepository.recordSetTimeout(recName2, "5s");
		recordRepository.recordVerifyFields(recName2, fieldValuePairs);
		recordRepository.recordClose(recName2);
	
	}
}
