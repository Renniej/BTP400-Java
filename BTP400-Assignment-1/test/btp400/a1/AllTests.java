package test.btp400.a1;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Creating a test suite for your convenience 
 * 
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({
	AccountTest.class,
	ChequingTest.class,
	GICTest.class,
	BankTest.class
})

public class AllTests {
	
}
