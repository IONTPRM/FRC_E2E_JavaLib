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
	
	@RobotKeyword
	public void updateColumnForRLM(String columnId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createColumnSourceForRLM(String columnName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",columnName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnSourceCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void updateColumnSourceForRLM(String columnSourceId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnSourceId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnSourceUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void deleteColumnSourceForRLM(String columnSourceId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnSourceId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "ColumnSourceDelete", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createTemplateForRLM(String templateName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",templateName};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateCreate", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createTemplateColumnForRLM(String columnName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",columnName};
		//funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateColumnCreate", SetServerSourceCurrency.TIMEOUT_M,args);
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "TemplateColumnCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createRunListForRLM(String runlistName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",runlistName};
		//funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateColumnCreate", SetServerSourceCurrency.TIMEOUT_M,args);
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RunListCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void updateRunListForRLM(String runlistId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",runlistId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RunListUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void deleteRunListForRLM(String runlistId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",runlistId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "RunListDelete", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createRecipientForRLM(String recipientName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",recipientName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void updateRecipientForRLM(String recipientName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",recipientName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void deleteRecipientFromRLM(String recipientName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",recipientName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientCreate", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createRecipientGrpFromRLM(String grpName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",grpName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupCreate", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	
	@RobotKeyword
	public void updateRecipientGroupForRLM(String groupId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",groupId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
}
