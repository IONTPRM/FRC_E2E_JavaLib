package com.iontrading.frc_e2e.keywords;

import org.apache.commons.lang.ArrayUtils;
import org.robotframework.javalib.annotation.*;

import com.iontrading.frc_e2e.utils.*;

import com.iontrading.robot.DPlayerKey;

import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.keywords2.FunctionRepository;
import com.iontrading.robotframework.keywords2.TransactionRepository;

@RobotKeywords
public class E2E_Tradeentry {
	
    private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Tradeentry.class.getName());

	private final FunctionRepository funcRep = new FunctionRepository();
	private final TransactionRepository transRep = new TransactionRepository();
	private final DPlayerKey dplayerKey;

	public E2E_Tradeentry() throws Exception {
		dplayerKey = new DPlayerKey("0", "AUTOROBOT");
	}
	
	
	/**
	 * Creates voice/manual trade using Tradeentry component
	 * 
	 * *Parameters:*
	 * 	- _createUserId_: User id to create the trade
	 * 	- _instrId_: Refdata Instrument id
	 * 	- _bookId_: A valid book id from Refdata 
	 * 	- _value_: Value to create trade with
	 * 	- _valueType_: Value Type, for example Price, Yield, Discount, DiscountMargin etc
	 * 	- _verbStr_: Verb for trade, Buy/Sell
	 * 	- _qty_: Qty for trade
	 * 	- _fieldValuePairs_: List of optional field name and value pairs
	 * 
	 * *Returns:* It returns the trade record id created in Tradeserver
	 *  	
	 */
	@RobotKeyword
	public String createManualTrade(String createUserId, String instrId, String bookId, Double value, String valueType, String verbStr, Double qty, Object[] fieldValuePairs) throws Exception {
		
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
		
		// Just to wait for 1 second in case ticket is not initialized correctly yet
		dplayerKey.DPlayer("Wait For Sec", new Object[] {1});
		
		transRep.transactionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "TRADEENTRYACTION", SetServerSourceCurrency.TRADEENTRY_CURRENCY, teActionRecId);
		Object[] transFieldValuePairs2 = new Object[] {"Value", value, "ValueType", utility.getValueTypeInt(valueType), "Verb", verb, "Qty", qty, "BookId", bookId, "RecalcFlag", "1"};
		Object[] args = ArrayUtils.addAll(transFieldValuePairs2, fieldValuePairs);
		transRep.transactionSetFieldsValues(args);
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
	
	@RobotKeyword
	public String matchSalesTrade(String salesTradeId, String bookId) throws Exception {
		
		funcRep.functionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "MatchTrade");
		funcRep.functionSetTimeout(SetServerSourceCurrency.TIMEOUT_M);
		funcRep.functionVerifyRetCode(0);
		IFunctionCallResult createTradeFuncResult = funcRep.functionCall();
		String teActionRecId = createTradeFuncResult.getErrorMessage();

		htmlLogger.info("TradeentryAction record id is " + teActionRecId);
		
		transRep.transactionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "TRADEENTRYACTION", SetServerSourceCurrency.TRADEENTRY_CURRENCY, teActionRecId);
		Object[] transFieldValuePairs1 = new Object[] {"BookId", bookId};
		transRep.transactionSetFieldsValues(transFieldValuePairs1);
		transRep.transactionSetTimeout(SetServerSourceCurrency.TIMEOUT_S);
		transRep.transactionVerifyReturn("0", "OK");
		transRep.transactionCall();

		String tradeId;
		funcRep.functionDefine(SetServerSourceCurrency.TRADEENTRY_SOURCE, "SaveTrade", new Object[] {"RecordId", teActionRecId});
		funcRep.functionSetTimeout(SetServerSourceCurrency.TIMEOUT_M);
		IFunctionCallResult saveTradeFuncResult = funcRep.functionCall();
		
		if (saveTradeFuncResult.getErrorCode() != 0) {
			throw new Exception("Trade could not be created, SaveTrade returned error message: " + saveTradeFuncResult.getErrorMessage()); 
		}
		
		tradeId = saveTradeFuncResult.getErrorMessage();
		
		htmlLogger.info("Tradeserver trade record id " + tradeId);
		
		return tradeId;
	}
	
}
