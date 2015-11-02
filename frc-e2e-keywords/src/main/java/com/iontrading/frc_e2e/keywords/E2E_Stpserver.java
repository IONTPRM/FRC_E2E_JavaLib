package com.iontrading.frc_e2e.keywords;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.iontrading.frc_e2e.reusableutils.FunctionUtils;
import com.iontrading.frc_e2e.reusableutils.TransactionUtils;
import com.iontrading.frc_e2e.utils.RobotLogger;
import com.iontrading.frc_e2e.utils.SetServerSourceCurrency;
import com.iontrading.jmix.logging.ILogger;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.base.ITransactionCallResult;
import com.iontrading.robotframework.base.MkvLibraryLogFactory;
import com.iontrading.robotframework.keywords2.FunctionRepository;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Stpserver {
	public static final String ROBOT_LIBRARY_SCOPE = "TEST CASE";
	private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Stpserver.class.getName());

	/**
	 * *Description:* This keyword is required to insert new STP source in stpserver.
	 *
	 *  
	 * *Usage:*
	 * 		| InsertGatewaySourceInSTP | ESPEED|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *   	- *For Example:* Source cannot be blank.
	 *
	 */
	@RobotKeyword
	public void InsertGatewaySourceInSTP(String gatewaySource)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Source",gatewaySource};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertStpSource", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is required to remove existing STP source in stpserver.
	 *
	 *  
	 * *Usage:*
	 * 		| removeGatewaySourceInSTP | ESPEED|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *   	- *For Example:* Source cannot be blank.
	 *
	 */
	@RobotKeyword
	public void removeGatewaySourceInSTP(String gatewaySource)
	throws Exception {
		IFunctionCallResult funcRes = null;
		Object[] args=new Object [] {"Source",gatewaySource};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "RemoveStpSource", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());	
	}
	
	
	/**
	 * *Description:* This keyword is required to set configuration values for the stp source inserted.
	 *
	 *  
	 * *Usage:*
	 * 		| configureGatewaySourceInSTP | ESPEED| DefaultBookId | BookID1 | UseQtyNominal |1 |
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by Transaction call>"
	 *
	 */
	@RobotKeyword
	public String configureGatewaySourceInSTP(String gatewaySource, Object[] fvParams)
	throws Exception {
		ITransactionCallResult tResult = null;
		String response = null;
		Object[] args1=new Object [] {"Status",1};
		Object[] args=ArrayUtils.addAll(args1, fvParams);
		tResult = TransactionUtils.doTransaction(SetServerSourceCurrency.STPSERVER_SOURCE, "STP_SOURCES_CONFIG", SetServerSourceCurrency.STPSERVER_CURRENCY, gatewaySource, SetServerSourceCurrency.TIMEOUT_M, args);
		response = TransactionUtils.transactionVerifyResult(tResult);
		htmlLogger.info("Transaction Result: " + response);
		return response;
	}
	
	/**
	 * *Description:* This keyword is required to set filter expression for the trades to be processed by STP.
	 *
	 *  
	 * *Usage:*
	 * 		| setFilterTradeExprInSTP | ESPEED| IF(Verb=2,"N","Y") |
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public String setFilterTradeExprInSTP(String gatewaySource,String filterExpression)
	throws Exception {
		ITransactionCallResult tResult = null;
		String response = null;
		Object[] args=new Object [] {"FilterExpr",filterExpression};//
		tResult = TransactionUtils.doTransaction(SetServerSourceCurrency.STPSERVER_SOURCE, "STP_SOURCES_CONFIG", SetServerSourceCurrency.STPSERVER_CURRENCY, gatewaySource, SetServerSourceCurrency.TIMEOUT_M, args);
		response = TransactionUtils.transactionVerifyResult(tResult);
		htmlLogger.info("Transaction Result: " + response);
		return response;
	}
	/**
	 * *Description:* This keyword is required to insert field mapping for the trades to be processed by STP.
	 *
	 * *Parameters:*
	 * 	- _fieldName_: Name of the field to be mapped 
	 * 	- _fieldValue_: value with which the field will be override
	 *  - _valueType_: Expression or literal to be used.
	 *  
	 * *Usage:*
	 * 		| setFilterTradeExprInSTP | ESPEED| Date | CM_INSTRUMENT.Date | 2 |
	 *		| setFilterTradeExprInSTP | ESPEED| CPKey | CPKey1 | 1 |
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void insertFieldMappingInSTP(String gatewaySource,String fieldName ,String fieldValue ,int valueType )
			throws Exception {
				IFunctionCallResult funcRes = null;	
				Object[] args=new Object [] {"Source",gatewaySource,"Field",fieldName,"Value",fieldValue,"ValueType",valueType};
				funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertFieldMapping", SetServerSourceCurrency.TIMEOUT_M,args );
				htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
			}
	
	/**
	 * *Description:* This keyword is required to insert new STP source in stpserver.
	 *
	 *  
	 * *Usage:*
	 * 		| InsertGatewaySourceInSTP | ESPEED|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *   	- *For Example:* Source cannot be blank.
	 *
	 */
	@RobotKeyword
	public void configureCounterPartyInSTP(String gatewaySource,int aggressed,int priority,String value,int valueType)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Source",gatewaySource,"Aggressed",aggressed,"Priority",priority,"Value",value,"ValueType",valueType};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertCounterparty", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
}
