package com.iontrading.frc_e2e.keywords;

import com.iontrading.frc_e2e.reusableutils.TransactionUtils;
import com.iontrading.frc_e2e.utils.RobotLogger;
import com.iontrading.jmix.logging.ILogger;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.base.ITransactionCallResult;
import com.iontrading.robotframework.base.MkvLibraryLogFactory;
import com.iontrading.robotframework.keywords2.FunctionRepository;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

import java.util.Date;
import java.util.Arrays;


import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

@RobotKeywords
public class E2E_GatewayTrades {

	public static final String ROBOT_LIBRARY_SCOPE = "TEST CASE";
	private static final RobotLogger htmlLogger = RobotLogger.getLogger(Date.class.getName());
	private static FunctionRepository funRep = new FunctionRepository();
	private static final ILogger logger = new MkvLibraryLogFactory().createLogger();
	private static MkvRecordRepository recordRep = new MkvRecordRepository();

	
	/**
	 * *Description:* This keyword is required to login in to the gateway
	 *
	 * *Parameters:*
	 *  - *gatewaySource:* gateway source name
	 *  - *userName:* user login name
	 *  - *password:* user login password
	 *   - *fvlist:* list of optional params in field-value pair
	 *
	 * *Usage:*
	 * 		| performVCMILogin | ESPEED	| DEMO1	| DEMO1 |
	 * 		| performVCMILogin | ESPEED	| DEMO1	| DEMO1 | freeText | loginuser |
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "<OK>"
	 * 		- *For Example:* Ok
	 *
	 * 	 - *On Failure:* "<Error message returned by the Gateway>"
	 *   	- *For Example:* Invalid User Name.
	 *
	 */
	@RobotKeyword
	public IFunctionCallResult performVCMILogin(String gatewaySource, String userName, String password,Object[] fvlist )
	throws Exception {

		Object args1[]=new Object[] {"User",userName,"Pwd",password};
		Object args[]=ArrayUtils.addAll(args1, fvlist);
		htmlLogger.info("Calling Function " + gatewaySource + "_" + "VCMILogin" + " with parameters " + Arrays.toString(args) + " with timeout ");
		logger.action("Calling function " +  gatewaySource + "_" + "VCMILogin"+ " with parameters " +  Arrays.toString(args) + " with timeout " + "timeout").end();
		funRep.functionDefine(gatewaySource, "VCMILogin", args);
		funRep.functionSetTimeout("10s");
		funRep.functionVerifyReturn("0", "OK");
		IFunctionCallResult fResult = funRep.functionCall();
		return fResult;
	}

	/**
	 * *Description:* This keyword is required to logout from gateway
	 *
	 * *Parameters:*
	 *  - *gatewaySource:* gateway source name
	 *  - *userName:* user login name
	 *
	 * *Usage:*
	 * 		| performVCMILogout | ESPEED	| DEMO1 |	
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "<OK>"
	 * 		- *For Example:* Ok
	 *
	 * 	 - *On Failure:* "<Error message returned by the Gateway>"
	 *   	- *For Example:* User does not exist.
	 *
	 */
	@RobotKeyword
	public IFunctionCallResult performVCMILogout(String gatewaySource, String password)
	throws Exception {
		Object args[]=new Object[] {"Pwd",password};
		htmlLogger.info("Calling Function " + gatewaySource + "_" + "VCMILogout" + " with parameters " + Arrays.toString(args) + " with timeout ");
		logger.action("Calling function " +  gatewaySource + "_" + "VCMILogout"+ " with parameters " +  Arrays.toString(args) + " with timeout " + "timeout").end();
		funRep.functionDefine(gatewaySource, "VCMILogout", args);
		funRep.functionSetTimeout("10s");
		funRep.functionVerifyReturn("0", "OK");
		IFunctionCallResult fResult = funRep.functionCall();
		return fResult;
	}

	/**
	 * *Description:* This keyword is required to set the trader status for the gateway
	 *
	 * *Parameters:*
	 *  - *gatewaySource:* gateway source name
	 *  - *gatewayCurrency:* gateway currency
	 *   - *userName:* user login name
	 *   - *traderStatus:* traderStatus (On/Off)
	 *  
	 *
	 * *Usage:*
	 * 		| setTraderStatus | ESPEED	| EUR | DEMO1 | On |
	 * 		| setTraderStatus | ESPEED	| EUR | DEMO1 | Off |	
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "0:OK"
	 * 		- *For Example:* Ok
	 *
	 * 	 - *On Failure:* "<Error message returned by the Gateway>"
	 *   	- *For Example:* User not logged in.
	 *
	 */
	@RobotKeyword
	public String setTraderStatus(String gatewaySource,String gatewayCurrency, String userName, String traderStatus)
	throws Exception {
		ITransactionCallResult tResult = null;
		String response = null;
		Object[] args=new Object [] {"TStatusStr",traderStatus};
		tResult = TransactionUtils.doTransaction(gatewaySource, "CM_LOGIN", gatewayCurrency, userName, "10s", args);
		response = TransactionUtils.transactionVerifyResult(tResult);
		htmlLogger.info("Result: " + response);
		return response;
	}

	/**
	 * *Description:* This keyword is required to add order on the gateway and return order id.
	 *
	 *  
	 * *Usage:*
	 * 		| addOrder | ESPEED| DEMO1| T10181| Buy| 10| 100| 100 | LIMIT | FAS | 0 | 0 | ${EMPTY}| ${EMPTY}| 0| ${EMPTY}| 0 | Opt_Params |
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 * 		- *For Example:*  OK -Result {-Id {ID_41_16142193} -OrderTmpId {MKV_T10181_1443607023044} }
	 *
	 * 	 - *On Failure:* "<Error message returned by the Gateway>"
	 *   	- *For Example:* Type not defined.
	 *
	 */
	@RobotKeyword
	public String addOrder(String gatewaySource,String userName,String instrId,String verb,double price,double qtyShown,double qtyTot,String orderType,String timeInForce,int isSoft,int attributeId, String customerInfo,String freeText,int stopCondition,String stopId,double stopPrice,Object[] optParams)
	throws Exception {
		Object args1[]=new Object[] {"User", userName,"InstrumentId",instrId,"Verb",verb,"Price",price,"QtyShown",qtyShown,"QtyTot",qtyTot,"Type",orderType,"TimeInForce",timeInForce,"IsSoft",isSoft,"Attribute",attributeId,"CustomerInfo",customerInfo,"FreeText",freeText,"StopCond",stopCondition,"StopId",stopId,"StopPrice",stopPrice};
		Object args[]=ArrayUtils.addAll(args1, optParams);

		htmlLogger.info("Calling Function " + gatewaySource + "_" + "addOrder" + " with parameters " + Arrays.toString(args) + " with timeout ");
		logger.action("Calling function " +  gatewaySource + "_" + "addOrder"+ " with parameters " +  Arrays.toString(args) + " with timeout " + "timeout").end();

		funRep.functionDefine(gatewaySource, "VCMIOrderAdd181", args);
		funRep.functionSetTimeout("10s");
		funRep.functionVerifyLikeResult("OK");
		IFunctionCallResult fResult = funRep.functionCall();

		if (fResult.getErrorCode() != 0) {
			throw new Exception("OrderAdd Failed: " + fResult.getErrorMessage());
		}

		htmlLogger.info("Calling Function Response : " + fResult.getErrorMessage() + fResult.getErrorCode());
		String fResStr=fResult.getErrorMessage().toString();
		String[] fres_list=StringUtils.split(fResStr);

		String orderId=fres_list[3];
		orderId=fres_list[3].replaceAll("[{}]", "");
		htmlLogger.info( "OrderId generated : " + fres_list[3]);
		return orderId;
	}

	@RobotKeyword
	public static IReadableRecord verifyRecordExists(String source, String currency, String tableName, String recordId)
	{
		IReadableRecord record = null;

		String recordFullName = recordRep.recordDefine(source, tableName, currency, recordId);
		try
		{
			record = recordRep.recordSubscribeWaitingSnapshotRecord(recordFullName);
		}
		catch (Exception e)
		{
			htmlLogger.info("Record " + recordFullName + " doesn't exist in the " + tableName + " table.");
		}
		finally
		{
			recordRep.recordClose(recordFullName);
		}
		return record;
	}

	@RobotKeyword
	public static IReadableRecord verifyRecordFields(String source, String currency, String tableName, String recordId,Object... Fv)
	{
		IReadableRecord record = null;

		String recordFullName = recordRep.recordDefine(source, tableName, currency, recordId);

		try
		{
			recordRep.recordSetTimeout(recordFullName, "10s");
			recordRep.recordVerifyFields(recordFullName, Fv);
			recordRep.recordSubscribe(recordFullName);
		}
		catch (Exception e)
		{
			htmlLogger.info("Record " + recordFullName + " doesn't exist in the " + tableName + " table.");
		}
		finally
		{
			recordRep.recordClose(recordFullName);
		}
		return record;
	}

}



