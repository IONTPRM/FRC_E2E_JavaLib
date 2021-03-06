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
	 * *Description:* This keyword is used to create column in RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| createColumnForRLM | TestBidPrice| Alias| TBpc|DefaultColumnFormatId|String_Right_2|FieldType|REAL|FieldValueType|PRICE|Verb|1|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
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
	
	/**
	 * *Description:* This keyword is used to update column fields in RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| updateColumnForRLM | columnId| Alias| TBpc_updated|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void updateColumnForRLM(String columnId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to delete column from RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| deleteColumnForRLM | columnId|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void deleteColumnForRLM(String columnId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnDelete", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to Create column source from RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 	 |createColumnSourceForRLM| TestBidPrice | RunColumnId| TestBidPrice_01| RunSourceId| PXE| SourceFields| BidPrice|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void createColumnSourceForRLM(String columnName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",columnName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnSourceCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to Update column source from RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 	 |updateColumnSourceForRLM| TestBidPrice_1 |SourceFields| AskPrice|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void updateColumnSourceForRLM(String columnSourceId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnSourceId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "ColumnSourceUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	/**
	 * *Description:* This keyword is used to delete columnSource from RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| deleteColumnForRLM | columnSourceId|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void deleteColumnSourceForRLM(String columnSourceId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",columnSourceId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "ColumnSourceDelete", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to Create template in RunListManager
	 *
	 *  
	 * *Usage:*
	 * 		| createTemplateForRLM | templateName|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void createTemplateForRLM(String templateName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",templateName};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateCreate", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	

	/**
	 * *Description:* This keyword is used to delete template in RunListManager
	 *
	 *  
	 * *Usage:*
	 * 		| deleteTemplateForRLM | templateId|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void deleteTemplateForRLM(String templateId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",templateId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateDelete", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to Create templateColumn in RunListManager
	 *
	 *  
	 * *Usage:*
	 * 		| createTemplateColumnForRLM | RunColumnId|TemplateId|TestTemplate|ColumnSourceId|TestBidPrce_1|ColumnFormatId|StringRight_2|IsHidden|False|Position|1|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void createTemplateColumnForRLM(String columnName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",columnName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "TemplateColumnCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	/**
	 * *Description:* This keyword is used to Create New runList in RunListManager
	 *
	 *  
	 * *Usage:*
	 * 		|createRunListForRLM|TestRunList| InstrumentGroup| TestInstrGrp| MessageSubject| TestRunList| SenderName| ankit | SenderAddress|ankit.jain@iontrading.com| RecipientGroups|MyGrp_1| ListType|RUN| TemplateId| test_template_1|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void createRunListForRLM(String runlistName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",runlistName};
		//funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "TemplateColumnCreate", SetServerSourceCurrency.TIMEOUT_M,args);
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RunListCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to update existing runList in RunListManager
	 *
	 *  
	 * *Usage:*
	 * 		|createRunListForRLM|TestRunList_Id| InstrumentGroup| TestInstrGrp1|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void updateRunListForRLM(String runlistId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",runlistId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RunListUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	/**
	 * *Description:* This keyword is used to delete runList from RunListManager
	 *
	 *  
	 * *Usage:*
	 * 		|deleteRunListForRLM|TestRunList|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void deleteRunListForRLM(String runlistId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",runlistId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "RunListDelete", SetServerSourceCurrency.TIMEOUT_M,args);
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	/**
	 * *Description:* This keyword is used to add recipient in RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		|createRecipientForRLM|ankit|Address|ankit.jain@iontrading.com|SpeedDial||UUID|Test_uuid_01|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void createRecipientForRLM(String recipientName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",recipientName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientCreate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to update recipient information in RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		|updateRecipientForRLM|recipientid|Address|tilak.raj@iontrading.com|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void updateRecipientForRLM(String recipientName, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",recipientName};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	/**
	 * *Description:* This keyword is used to delete recipient from RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| RecipientDelete | recipientId|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void deleteRecipientFromRLM(String recipientId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",recipientId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientDelete", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to create recipient group in RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| RecipientGroupCreate | grpName|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void createRecipientGrpFromRLM(String grpName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Name",grpName};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupCreate", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to update recipient group in RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| RecipientGroupUpdate | groupId|fvparams|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	@RobotKeyword
	public void updateRecipientGroupForRLM(String groupId, Object[] fvParams)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",groupId};
		funcRes = FunctionUtils.callFunctionWithVarArgs(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupUpdate", SetServerSourceCurrency.TIMEOUT_M,args,fvParams );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to delete columnSource from RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| deleteColumnForRLM | columnSourceId|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
	
	@RobotKeyword
	public void deleteRecipientGrpFromRLM(String grpId)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",grpId};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.RLM_SOURCE, "RecipientGroupDelete", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	/**
	 * *Description:* This keyword is used to verify fields of RUN_AXE chain record of RunlistManager.
	 *
	 *  
	 * *Usage:*
	 * 		| verifyRunListFields | INSTR1|NetPosition|100|SubscriptionStatus|Yes|
	 *
	 * *Return Values:*
	 *
	 * 	 - *On Success:* "OK"
	 *
	 * 	 - *On Failure:* "<Error message returned by function call>"
	 *
	 */
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
