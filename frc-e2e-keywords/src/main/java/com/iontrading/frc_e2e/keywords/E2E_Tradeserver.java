package com.iontrading.frc_e2e.keywords;

import org.omg.CORBA.portable.ApplicationException;
import org.robotframework.javalib.annotation.*;

import com.iontrading.frc_e2e.utils.*;
import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;
import com.iontrading.robotframework.keywords2.ChainRepository;

@RobotKeywords
public class E2E_Tradeserver {

    private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Tradeserver.class.getName());
    	
	private final MkvRecordRepository recordRep = new MkvRecordRepository();
	private final ChainRepository chainRep = new ChainRepository();
		
	public String getTSTradeRecordId(String tsTradeId) {
		
		E2E_Utility utility = new E2E_Utility();
		String washedTradeId = utility.washName(tsTradeId);
		return SetServerSourceCurrency.TRADESERVER_SOURCE + "_CM_TRADE_" + SetServerSourceCurrency.TRADESERVER_CURRENCY + "_" + washedTradeId;
	}
	
	/**
	 * Keyword to verify fields' values on trade record in Tradeserver
	 * 
	 * *Parameters:*
	 * 	- _tradeId_: Id of trade record created in Tradeserver
	 * 	- _fieldValuePairs_: List of field name and field value pairs
	 * 
	 * *Example:*
	 * 	| Verify Tradeserver Fields | 278942.0 | Price | 99.25 | Qty | 2 | TradeStatusStr | Open | Yield | value_ret_by_pxe |
	 * 	Here value_ret_by_pxe is the literal string for the fields which value Tradeserver received from PXE
	 */
	@RobotKeyword
	public void verifyTradeserverFields(String tradeId, Object[] fieldValuePairs) throws Exception {

		E2E_Utility utility = new E2E_Utility();
		E2E_PXE pxe = new E2E_PXE();
		String washedTradeId = utility.washName(tradeId);
		
		htmlLogger.info(washedTradeId);
		htmlLogger.info("Length of arg list is " + fieldValuePairs.length);
		htmlLogger.info(SetServerSourceCurrency.TRADESERVER_SOURCE + " " + SetServerSourceCurrency.TRADESERVER_CURRENCY);
		
		String recName1 = recordRep.recordDefine(SetServerSourceCurrency.TRADESERVER_SOURCE, "CM_TRADE", SetServerSourceCurrency.TRADESERVER_CURRENCY, washedTradeId);
		recordRep.recordSetTimeout(recName1, "10s");		
		IReadableRecord readableRecord = recordRep.recordVerifyFields(recName1, new Object[] {"Id", "==", "\"" + tradeId + "\""}); 
		recordRep.recordSubscribe(recName1);
				
		String instrumentId = (String) readableRecord.getFieldValue("InstrumentId");
		Double value = (Double) readableRecord.getFieldValue("Value");
		Integer valueType = (Integer) readableRecord.getFieldValue("ValueType");
		Integer dateSettl = (Integer) readableRecord.getFieldValue("DateSettl");
		recordRep.recordClose(recName1);
		
		for (int i=0; i < fieldValuePairs.length; i++) {
			if (fieldValuePairs[i].equals("value_ret_by_pxe")) {
				fieldValuePairs[i] = pxe.getValueUsingAnlpycalcFunc(fieldValuePairs[i-1].toString(), instrumentId, value, valueType, dateSettl);
			}
		}
				
		String recName2 = recordRep.recordDefine(SetServerSourceCurrency.TRADESERVER_SOURCE, "CM_TRADE", SetServerSourceCurrency.TRADESERVER_CURRENCY, washedTradeId);
		recordRep.recordSetTimeout(recName2, "5s");
		recordRep.recordVerifyFields(recName2, fieldValuePairs);
		recordRep.recordSubscribe(recName2);
		recordRep.recordClose(recName2);
	
	}

	/**
	  * Keyword to reterieve TradeId corresponding to external trade processed by STP
	 * 
	 * *Parameters:*
	 * 	- _extGwySrc: Gateway source which is supplying external trade
	 * 	- _gwyOrderId: OrderId of the trade to be processed by STP 
	 * 
	 * *Example:*
	 * 	|getTSTradeIdFromSTPProcessedTrades | ESPEED | ID_28_13272331 |
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "TradeId"
	 * 		- *For Example:* 4031162.0
	 *
	 * 	 - *On Failure:* "<Error message returned by the function>"
	 *   	- *For Example:* Record does not exist .
	 * @throws Exception 
	 */
	@RobotKeyword
	public String getTSTradeIdFromSTPProcessedTrades(String extGwySrc, String gwyOrderId) throws Exception {
		String recordId=null;
		String tradeId=null;
		String chainName1 = chainRep.chainDefine(SetServerSourceCurrency.TRADESERVER_SOURCE, "CM_TRADE", SetServerSourceCurrency.TRADESERVER_CURRENCY, "TRADE");
		chainRep.chainSetTimeout(chainName1, SetServerSourceCurrency.TIMEOUT_L);
		IReadableRecord recObj = chainRep.chainVerifyRecord(chainName1, new Object[] {"ExternalIdSrc1", "==", extGwySrc, "OrderId", "==", gwyOrderId});
		htmlLogger.info("Full record name is = " + recObj);
		try {
			chainRep.chainSubscribeWaitingSnapshotRecords(chainName1);
			htmlLogger.info("Snapshot subscribed");
			recordId=recObj.getRecordName(); //exception is thrown here if ther is no reccord received
			htmlLogger.info("Full record name is: " + recObj.getRecordName());
			tradeId = (String) recObj.getFieldValue("Id");
		} catch (Exception e) {
			throw new Exception("Record not found in chain : " + e);
		} finally {
		chainRep.chainClose(chainName1);
		}
		return tradeId;
		
	}
}
