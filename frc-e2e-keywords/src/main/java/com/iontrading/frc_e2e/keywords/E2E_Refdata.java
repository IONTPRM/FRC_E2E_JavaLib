package com.iontrading.frc_e2e.keywords;

import com.iontrading.frc_e2e.utils.*;

import org.robotframework.javalib.annotation.*;

import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

@RobotKeywords
public class E2E_Refdata {
	
	private final MkvRecordRepository recordRepository = new MkvRecordRepository();  
	
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
	
}
