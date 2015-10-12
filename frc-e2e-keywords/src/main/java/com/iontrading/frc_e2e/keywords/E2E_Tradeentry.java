package com.iontrading.frc_e2e.keywords;

import org.robotframework.javalib.annotation.*;

import com.iontrading.frc_e2e.utils.*;

import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.keywords2.FunctionRepository;
import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;
import com.iontrading.robotframework.keywords2.TransactionRepository;

@RobotKeywords
public class E2E_Tradeentry {

    private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Tradeserver.class.getName());

	private final FunctionRepository funcRep = new FunctionRepository();
	private final MkvRecordRepository recordRepository = new MkvRecordRepository();  
	private final TransactionRepository transRep = new TransactionRepository();
	
	/**
	 * 
	 * @param createUserId
	 * @param instrId
	 * @param bookId
	 * @param value
	 * @param valueType
	 * @param verbStr
	 * @param qty
	 * @return
	 * @throws Exception
	 */
	@RobotKeyword
	public String createManualTrade(String createUserId, String instrId, String bookId, Double value, String valueType, String verbStr, Double qty) throws Exception {
		
		E2E_Utility utility = new E2E_Utility();
		
		int verb = 1;
		if (verbStr.equals("Sell")) {
			verb = 2;
		}
		
		funcRep.functionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "CreateTrade");
		funcRep.functionSetTimeout("5s");
		funcRep.functionVerifyRetCode(0);
		IFunctionCallResult createTradeFuncResult = funcRep.functionCall();
		String teActionRecId = createTradeFuncResult.getErrorMessage();

		htmlLogger.info("TradeentryAction record id is " + teActionRecId);
		
		transRep.transactionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "TRADEENTRYACTION", SetServerSourceCurrency.TRADEENTRY_CURRENCY, teActionRecId);
		Object[] transFieldValuePairs1 = new Object[] {"CreateUserId", createUserId, "InstrumentId", instrId};
		transRep.transactionSetFieldsValues(transFieldValuePairs1);
		transRep.transactionSetTimeout("5s");
		transRep.transactionVerifyReturn("0", "OK");
		transRep.transactionCall();
		
		transRep.transactionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "TRADEENTRYACTION", SetServerSourceCurrency.TRADEENTRY_CURRENCY, teActionRecId);
		Object[] transFieldValuePairs2 = new Object[] {"Value", value, "ValueType", utility.getValueTypeInt(valueType), "Verb", verb, "Qty", qty, "BookId", bookId, "RecalcFlag", "1"};
		transRep.transactionSetFieldsValues(transFieldValuePairs2);
		transRep.transactionSetTimeout("5s");
		transRep.transactionVerifyReturn("0", "OK");
		transRep.transactionCall();
		
		String tradeId;
		funcRep.functionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "SaveTrade", new Object[] {"RecordId", teActionRecId});
		funcRep.functionSetTimeout("5s");
		IFunctionCallResult saveTradeFuncResult = funcRep.functionCall();
		
		if (saveTradeFuncResult.getErrorCode() != 0) {
			throw new Exception("Trade could not be created, SaveTrade returned error message: " + saveTradeFuncResult.getErrorMessage()); 
		}
		
		tradeId = saveTradeFuncResult.getErrorMessage();
		
		htmlLogger.info("Tradeserver trade record id " + tradeId);
		
		return tradeId;
	}
}
