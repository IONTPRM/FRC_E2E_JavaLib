package com.iontrading.frc_e2e.reusableutils;


	import com.iontrading.jmix.logging.ILogger;
	import com.iontrading.jmix.subscribe.function.IFunctionCallResult;
	import com.iontrading.mkv.exceptions.MkvException;
	import com.iontrading.frc_e2e.exception.ResultNotMatchesException;
	import com.iontrading.frc_e2e.utils.RobotLogger;
	import com.iontrading.robotframework.base.MkvLibraryLogFactory;
	import com.iontrading.robotframework.keywords2.FunctionRepository;
	import java.util.Arrays;

	public class FunctionUtils
	{
	  private static final RobotLogger htmlLogger = RobotLogger.getLogger(FunctionUtils.class.getName());
	  private static final ILogger logger = new MkvLibraryLogFactory().createLogger();
	  private static FunctionRepository funRep = new FunctionRepository();
	  
	  public static IFunctionCallResult callFunction(String source, String name, String timeout, Object... args)
	    throws Exception
	  {
	    htmlLogger.info("Calling Function " + source + "_" + name + " with parameters " + Arrays.toString(args) + " with timeout " + timeout);
	    logger.action("Calling function " + source + "_" + name + " with parameters " + Arrays.toString(args) + " with timeout " + timeout).end();
	    funRep.functionDefine(source, name, args);
	    funRep.functionSetTimeout(timeout);
	    IFunctionCallResult fResult = funRep.functionCall();
	    return fResult;
	  }
	  
	  public static IFunctionCallResult callFunctionWithVarArgs(String source, String name, String timeout,Object[] mainArgs,Object... args)
			  throws Exception
			  {
		  htmlLogger.info("Calling Function " + source + "_" + name + " with parameters " + Arrays.toString(args) + " with timeout " + timeout);
		  logger.action("Calling function " + source + "_" + name + " with parameters " + Arrays.toString(args) + " with timeout " + timeout).end();
		  funRep.functionDefine(source, name, mainArgs);
		  funRep.functionSetVarArgs(args);
		  funRep.functionSetTimeout(timeout);
		  IFunctionCallResult fResult = funRep.functionCall();
		  return fResult;
		}
	  
	  public static String functionVerifyCode(IFunctionCallResult fResult, String exceptionMessage, int expectedReturnCode)
	    throws ResultNotMatchesException, MkvException
	  {
	    int actualReturnCode = fResult.getErrorCode().intValue();
	    if (expectedReturnCode != actualReturnCode) {
	      throw new ResultNotMatchesException("Result: " + fResult.getErrorMessage());
	    }
	    return fResult.getErrorMessage();
	  }
	  
	  public static String functionVerifyCode(IFunctionCallResult fResult, String exceptionMessage)
	    throws ResultNotMatchesException, MkvException
	  {
	    String result = functionVerifyCode(fResult, exceptionMessage, 0);
	    return result;
	  }
	  
	  public static String functionVerifyErrMessage(IFunctionCallResult fResult, String exceptionMessage, String expectedMessage)
	    throws ResultNotMatchesException, MkvException
	  {
	    if (!fResult.getErrorMessage().contains(expectedMessage))
	    {
	      htmlLogger.info(exceptionMessage + "Function returned " + fResult.getErrorMessage());
	      throw new ResultNotMatchesException("Result: " + fResult.getErrorMessage());
	    }
	    return fResult.getErrorMessage();
	  }
	  
	  public static IFunctionCallResult callFunctionWithoutLogging(String source, String name, String timeout, Object... args)
	    throws Exception
	  {
	    funRep.functionDefine(source, name, args);
	    funRep.functionSetTimeout(timeout);
	    IFunctionCallResult fResult = funRep.functionCall();
	    return fResult;
	  }
}
