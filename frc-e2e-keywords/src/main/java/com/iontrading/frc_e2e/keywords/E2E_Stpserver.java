package com.iontrading.frc_e2e.keywords;

import java.util.Arrays;
import java.util.Date;

import org.robotframework.javalib.annotation.RobotKeyword;

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

public class E2E_Stpserver {
	public static final String ROBOT_LIBRARY_SCOPE = "TEST CASE";
	private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Stpserver.class.getName());
	private static FunctionRepository funRep = new FunctionRepository();
	private static final ILogger logger = new MkvLibraryLogFactory().createLogger();
	private static MkvRecordRepository recordRep = new MkvRecordRepository();
	
	@RobotKeyword
	public IFunctionCallResult InsertGatewaySourceInSTP(String gatewaySource, Object[] fvparams )
	throws Exception {
		IFunctionCallResult fResult = null;
		String response = null;		
		IFunctionCallResult funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertStpSource", SetServerSourceCurrency.TIMEOUT_M,fvparams );
		return fResult;
	}
	
	@RobotKeyword
	public IFunctionCallResult RemoveGatewaySourceInSTP(String gatewaySource, Object[] fvparams )
	throws Exception {
		IFunctionCallResult fResult = null;
		String response = null;		
		IFunctionCallResult funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.STPSERVER_SOURCE, "InsertStpSource", SetServerSourceCurrency.TIMEOUT_M,fvparams );
		return fResult;
	}
	
	@RobotKeyword
	public String configureGatewaySourceInSTP(String gatewaySource, Object[] fvParams)
	throws Exception {
		ITransactionCallResult tResult = null;
		String response = null;
		tResult = TransactionUtils.doTransaction(SetServerSourceCurrency.STPSERVER_CURRENCY, "STP_SOURCES_CONFIG", SetServerSourceCurrency.STPSERVER_SOURCE, gatewaySource, "10s", fvParams);
		response = TransactionUtils.transactionVerifyResult(tResult);
		htmlLogger.info("Result: " + response);
		return response;
	}
	
}
