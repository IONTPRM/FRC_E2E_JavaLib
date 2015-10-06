package com.iontrading.frc_e2e.keywords;

import com.iontrading.frc_e2e.utils.RobotLogger;
import com.iontrading.jmix.logging.ILogger;
import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
import com.iontrading.robotframework.base.IReadableRecord;
import com.iontrading.robotframework.base.MkvLibraryLogFactory;
import com.iontrading.robotframework.keywords2.FunctionRepository;
import com.iontrading.robotframework.keywords2.MkvRecordRepository;

import java.util.Date;
import java.util.Arrays;

import com.iontrading.robotframework.keywords2.FunctionRepository;

import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

@RobotKeywords
public class E2E_GatewayTrades {

 public static final String ROBOT_LIBRARY_SCOPE = "TEST CASE";
 private static final RobotLogger htmlLogger = RobotLogger.getLogger(Date.class.getName());
 private static FunctionRepository funRep = new FunctionRepository();
 private static final ILogger logger = new MkvLibraryLogFactory().createLogger();
 private static MkvRecordRepository recordRep = new MkvRecordRepository();
 
 @RobotKeyword
public void performVCMILogin (String gatewaySource, String userName, String password,Object... fvlist )
		throws Exception {
	 Object args[]=new Object[] {"User",userName,"Pwd",password};
	htmlLogger.info("Calling Function " + gatewaySource + "_" + "VCMILogin" + " with parameters " + Arrays.toString(args) + " with timeout ");
	logger.action("Calling function " +  gatewaySource + "_" + "VCMILogin"+ " with parameters " +  Arrays.toString(args) + " with timeout " + "timeout").end();
	funRep.functionDefine(gatewaySource, "VCMILogin", args);
	funRep.functionSetTimeout("10s");
IFunctionCallResult fResult = funRep.functionCall();
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



