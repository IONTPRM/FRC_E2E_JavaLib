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
	 * @param bbgSource
	 * @param extInstrId
	 * @return
	 * @throws Exception
	 */
	@RobotKeyword
	public String getInstrumentIdOfImportedImported(String bbgSource, String extInstrId) throws Exception {
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
