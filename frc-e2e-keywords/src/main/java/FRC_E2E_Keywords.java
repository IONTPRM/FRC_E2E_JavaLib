import org.robotframework.javalib.library.AnnotationLibrary;

public class FRC_E2E_Keywords extends AnnotationLibrary {

	/**
	 * Only one instance of this library is created during the whole test execution and it
	 * is shared by all test cases and test suites.
	 */
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";
	public static final String ROBOT_LIBRARY_VERSION = "1.0.0";
	public static final String ROBOT_LIBRARY_DOC_FORMAT = "HTML";

	public FRC_E2E_Keywords() {
  	    super("com/iontrading/frc_e2e/**/**.class");
	}
	
}	
