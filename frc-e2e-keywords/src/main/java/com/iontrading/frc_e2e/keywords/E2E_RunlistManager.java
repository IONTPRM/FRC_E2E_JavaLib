package com.iontrading.frc_e2e.keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.iontrading.frc_e2e.reusableutils.FunctionUtils;
import com.iontrading.frc_e2e.utils.RobotLogger;
import com.iontrading.frc_e2e.utils.SetServerSourceCurrency;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;

@RobotKeywords
public class E2E_RunlistManager {
	public static final String ROBOT_LIBRARY_SCOPE = "TEST CASE";
	private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Stpserver.class.getName());
	
	/**
	 * *Description:* This keyword is required to insert counterparty to be used by STP source in stpserver.
	 *
	 *  
	 * *Usage:*
	 * 		| insertCounterPartyInSTP | ESPEED| 0 | 0| CP1000|1|
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
	public void createColumnForRLM(String columnName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",columnName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
}