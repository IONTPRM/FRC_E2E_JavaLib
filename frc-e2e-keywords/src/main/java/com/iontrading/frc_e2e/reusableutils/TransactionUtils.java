package com.iontrading.frc_e2e.reusableutils;

import java.util.Arrays;

import com.iontrading.frc_e2e.exception.ResultNotMatchesException;
import com.iontrading.frc_e2e.utils.RobotLogger;
import com.iontrading.jmix.logging.ILogger;
import com.iontrading.mkv.exceptions.MkvException;
import com.iontrading.robotframework.base.ITransactionCallResult;
import com.iontrading.robotframework.base.MkvLibraryLogFactory;
import com.iontrading.robotframework.keywords2.TransactionRepository;

public class TransactionUtils
{
  private static TransactionRepository transRep = new TransactionRepository();
  private static final RobotLogger htmlLogger = RobotLogger.getLogger(TransactionUtils.class.getName());
  private static final ILogger logger = new MkvLibraryLogFactory()
    .createLogger();
  
  public static ITransactionCallResult doTransaction(String source, String tableName, String currency, String recName, String timeout, Object... args)
    throws Exception
  {
    String recordFullName = currency + "." + tableName + "." + source + "." + 
      recName;
    
    htmlLogger.info("Transacting record " + recordFullName + 
      " with parameters " + Arrays.toString(args) + " with timeout " + timeout);
    logger.action(
      "Transacting record " + recordFullName + " with parameters " + 
      Arrays.toString(args) + " with timeout " + timeout).end();
    transRep.transactionDefine(source, tableName, currency, recName);
    transRep.transactionSetFieldsValues(args);
    transRep.transactionSetTimeout(timeout);
    ITransactionCallResult transRes = transRep.transactionCall();
    return transRes;
  }
  
 public static String transactionVerifyResult(ITransactionCallResult tResult)
    throws ResultNotMatchesException, MkvException
  {
	 htmlLogger.info("error code :"+tResult.getErrCode());
	 
	 htmlLogger.info("error msg :"+tResult.getErrMessage().contains("OK"));
	 
    if ((tResult.getErrCode() != 0) || (!tResult.getErrMessage().contains("OK"))) {
      throw new ResultNotMatchesException("Result: " + tResult.getErrMessage());
    }
    return tResult.getErrMessage();
  }
  
  public static boolean verifyTransactionParamIsNotEmpty(Object... params)
  {
    boolean isNotEmpty = false;
    if (params.length >= 2) {
      isNotEmpty = true;
    }
    return isNotEmpty;
  }
}
