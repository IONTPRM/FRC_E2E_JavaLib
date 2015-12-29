package com.iontrading.frc_e2e.keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.iontrading.frc_e2e.reusableutils.FunctionUtils;
import com.iontrading.frc_e2e.utils.E2E_Utility;
import com.iontrading.frc_e2e.utils.RobotLogger;
import com.iontrading.frc_e2e.utils.SetServerSourceCurrency;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.ChainRepository;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_RunlistManager {
	public static final String ROBOT_LIBRARY_SCOPE = "TEST CASE";
	private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Stpserver.class.getName());
	
	private final MkvRecordRepository recordRep = new MkvRecordRepository();
	private final ChainRepository chainRep = new ChainRepository();
		
	
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
	public void deleteColumnForRLM(String columnId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnDelete", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
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
	public void deleteTemplateForRLM(String templateId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",templateId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateDelete", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createTemplateColumnForRLM(String columnName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",columnName};
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
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientDelete", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void createRecipientGrpFromRLM(String grpName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",grpName};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupCreate", SetServerSourceCurrency.TIMEOUT_M,args );
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
	
	@RobotKeyword
	public void deleteRecipientGrpFromRLM(String grpName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",grpName};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupDelete", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public void verifyRunListFields(String instrId, Object[] fieldValuePairs) throws Exception {
		htmlLogger.info("Length of arg list is " + fieldValuePairs.length);
		htmlLogger.info(SetServerSourceCurrency.RLM_SOURCE + " " + SetServerSourceCurrency.RLM_CURRENCY);
		
		String recName1 = recordRep.recordDefine(SetServerSourceCurrency.RLM_SOURCE, "RUN_AXE", SetServerSourceCurrency.RLM_CURRENCY, instrId);
		recordRep.recordSetTimeout(recName1, "10s");		
		IReadableRecord readableRecord = recordRep.recordVerifyFields(recName1, new Object[] {"Id", "==", "\"" + instrId + "\""}); 
		recordRep.recordSubscribe(recName1);
		String recName2 = recordRep.recordDefine(SetServerSourceCurrency.RLM_SOURCE, "RUN_AXE", SetServerSourceCurrency.RLM_CURRENCY, instrId);
		recordRep.recordSetTimeout(recName2, "5s");
		recordRep.recordVerifyFields(recName2, fieldValuePairs);
		recordRep.recordSubscribe(recName2);
		recordRep.recordClose(recName2);
	
	}
	
}
