package com.iontrading.frc_e2e.keywords;

import org.robotframework.javalib.annotation.*;

import com.iontrading.frc_e2e.utils.*;

import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Position {

	private final MkvRecordRepository recordRepository = new MkvRecordRepository();
	
	/**
	 * This keyword verifies POSITIONDETAILS record fields, we pass instr_id and paired list of fields and values.
	 * 
	 * *Parameters:*
	 * 	- _bookId_: Refdata book id in which the trade has been created
	 * 	- _instrId_: Instrument Id on which the trade has been created
	 * 	- _tradeId_: Trade Id record in created Tradeserver
	 * 	- _fieldValuePairs_: List of field name and value pairs to verify on POSITIONDETAILS record of Position server 
	 * 
	 * @throws Exception
	 */
	@RobotKeyword	
	public void verifyPositionFields(String bookId, String instrId, String tradeId, Object[] fieldValuePairs) throws Exception {

		E2E_Utility utility = new E2E_Utility();
		String washedTradeId = utility.washName(tradeId);
		
		// to get the value of DateSettl field from Tradeserver trade record
		String recName1 = recordRepository.recordDefine(SetServerSourceCurrency.TRADESERVER_SOURCE, "CM_TRADE", SetServerSourceCurrency.TRADESERVER_CURRENCY, washedTradeId);
		recordRepository.recordSetTimeout(recName1, "5s");		
		IReadableRecord readableRecord = recordRepository.recordVerifyFields(recName1, new Object[] {"Id", "==", "\"" + tradeId + "\""}); 
		recordRepository.recordSubscribe(recName1);
		Integer dateSettl = (Integer) readableRecord.getFieldValue("DateSettl");
		recordRepository.recordClose(recName1);

		// verify the given fieldvaluepairs on the POSITIONDETAILS record of Risk server
		String posDetailsRecrodId = bookId + "_" + instrId + "_" + dateSettl + "____";
		String recName2 = recordRepository.recordDefine(SetServerSourceCurrency.POSITION_SOURCE, "POSITIONDETAILS", SetServerSourceCurrency.POSITION_CURRENCY, posDetailsRecrodId);
		recordRepository.recordSetTimeout(recName1, "5s");		
		recordRepository.recordVerifyFields(recName2, fieldValuePairs); 
		recordRepository.recordSubscribe(recName2);
		recordRepository.recordClose(recName2);
	}
}
