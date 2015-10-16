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
	private static FunctionRepository funRep = new FunctionRepository();
	private static final ILogger logger = new MkvLibraryLogFactory().createLogger();
	private static MkvRecordRepository recordRep = new MkvRecordRepository();
	
	@RobotKeyword
	public void InsertGatewaySourceInSTP(String gatewaySource)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Source",gatewaySource};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertStpSource", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void removeGatewaySourceInSTP(String gatewaySource)
	throws Exception {
		IFunctionCallResult funcRes = null;
		String response = null;		
		Object[] args=new Object [] {"Source",gatewaySource};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertStpSource", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());	
	}
	
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
	
	@RobotKeyword
	public String setFilterTradeExprInSTP(String gatewaySource,String filterExpression)
	throws Exception {
		ITransactionCallResult tResult = null;
		String response = null;
		Object[] args=new Object [] {"FilterExpr",filterExpression};//IF(Verb=2,"N","Y")
		tResult = TransactionUtils.doTransaction(SetServerSourceCurrency.STPSERVER_SOURCE, "STP_SOURCES_CONFIG", SetServerSourceCurrency.STPSERVER_CURRENCY, gatewaySource, SetServerSourceCurrency.TIMEOUT_M, args);
		response = TransactionUtils.transactionVerifyResult(tResult);
		htmlLogger.info("Transaction Result: " + response);
		return response;
		//DefaultBookId	${_BOOKID_1_}	InstrumentMapSource	${GLOBAL_MAPPING_SOURCE}	UseQtyNominal	1
	}
	
}
