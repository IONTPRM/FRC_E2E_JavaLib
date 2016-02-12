package com.iontrading.frc_e2e.keywords;

import com.iontrading.frc_e2e.reusableutils.FunctionUtils;
import com.iontrading.frc_e2e.reusableutils.TransactionUtils;
import com.iontrading.frc_e2e.utils.*;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;

import org.robotframework.javalib.annotation.*;

import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.base.ITransactionCallResult;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Refdata {
	
	private final MkvRecordRepository recordRepository = new MkvRecordRepository();  
	private final MkvRecordRepository recordRep = new MkvRecordRepository();
	private static final RobotLogger htmlLogger = RobotLogger.getLogger(E2E_Stpserver.class.getName());
	
	/**
	 * It returns the Refdata instrument id of the imported instrument.
	 * It requires id of external instrument published by external source.
	 * 
	 * *Parameters:*
	 * 	- _bbgSource_: Source of BloombergMData component (it can be a custom publisher of Dplayer playing BBGMData recording)
	 * 	- _extInstrId_: Id of external instrument being published by BBGMData component
	 * 
	 * *Returns:*
	 * 	- _wfInstrId_: Id of instrument record created in Refdata on importing the BBGMData instrument
	 * 
	 */
	@RobotKeyword
	public String getInstrumentIdOfImportedInstrument(String bbgSource, String extInstrId) throws Exception {
		String instrMappingRecName = bbgSource+"_"+extInstrId; 
		String recName1 = recordRepository.recordDefine(SetServerSourceCurrency.REFDATA_SOURCE, "INSTRUMENTMAPPING", SetServerSourceCurrency.REFDATA_CURRENCY, instrMappingRecName);
		recordRepository.recordSetTimeout(recName1, "5s");
		IReadableRecord readableRecord = recordRepository.recordVerifyFields(recName1, new Object[] {"SourceId", "==", extInstrId});
		recordRepository.recordSubscribe(recName1);
		String wfInstrId = (String) readableRecord.getFieldValue("TargetId");
		recordRepository.recordClose(recName1);
		
		return wfInstrId;
	}
	
	@RobotKeyword
	public void addInstrumentGrp(String grpName,String desc,String rule,int isSmart)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",grpName,"Desc",desc,"Rule",rule,"IsSmart",isSmart};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.REFDATA_SOURCE, "CreateInstrumentGroup", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
	
	@RobotKeyword
	public String addInstrumentInInstrumentGrp(String grpName,String instrIdList)
	throws Exception {
		ITransactionCallResult tResult = null;
		String response = null;
		Object[] args=new Object [] {"InstrumentIdInclude",instrIdList};
		tResult = TransactionUtils.doTransaction(SetServerSourceCurrency.REFDATA_SOURCE, "INSTRUMENTGROUP", SetServerSourceCurrency.REFDATA_CURRENCY, grpName, SetServerSourceCurrency.TIMEOUT_M, args);
		response = TransactionUtils.transactionVerifyResult(tResult);
		htmlLogger.info("Transaction Result: " + response);
		return response;
	}
	
	@RobotKeyword
	public void deleteInstrumentGrp(String grpName)
	throws Exception {
		IFunctionCallResult funcRes = null;	
		Object[] args=new Object [] {"Id",grpName};
		funcRes = FunctionUtils.callFunction(SetServerSourceCurrency.REFDATA_SOURCE, "DeleteInstrumentGroup", SetServerSourceCurrency.TIMEOUT_M,args );
		htmlLogger.info("Function Result: "+ funcRes.getErrorMessage());
	}
		
}
