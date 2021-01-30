package fraudDetect.utils;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FraudDetectUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() {
	}
	
	@Before
	public void setup() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void FraudDetectUtilsTest_isRightRecord() {
		assertEquals(true, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,2014-04-29T13:15:54,10.00"));
		assertEquals(true, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,2014-04-29T13:15:54,10000.00"));
		assertEquals(true, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,2020-04-29T13:15:54,13.54"));
		assertEquals(false, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,2014-04-29T13:15:54,"));
		assertEquals(false, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,10.00"));
		assertEquals(false, FraudDetectUtils.isRightRecord("2014-04-29T13:15:54,10.00"));
		assertEquals(false, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,2048-04-29T13:15:54,10.00"));
		assertEquals(false, FraudDetectUtils.isRightRecord("10d7ce2f43e35fa57d1bbf8b1e2,2014-04-29T33:15:54,10.00"));
	}
	
	@Test
	public void FraudDetectUtilsTest_parseDateTime() {
		assertEquals(LocalDateTime.parse("2014-04-29T13:15:54"), FraudDetectUtils.parseDateTime("2014-04-29T13:15:54"));
		assertEquals(LocalDateTime.parse("2020-04-29T13:15:54"), FraudDetectUtils.parseDateTime("2020-04-29T13:15:54"));
	}
	
	@Test
	public void FraudDetectUtilsTest_isNumeric() {
		assertEquals(true, FraudDetectUtils.isNumeric("10"));
		assertEquals(true, FraudDetectUtils.isNumeric("10.34"));
		assertEquals(false, FraudDetectUtils.isNumeric("10sdf"));
	}
	
	@Test
	public void FraudDetectUtilsTest_isValidDateFormat() {
		assertEquals(true, FraudDetectUtils.isValidDateFormat("2014-04-29T13:15:54"));
		assertEquals(false, FraudDetectUtils.isValidDateFormat("2014/04/29T13:15:54"));
		assertEquals(false, FraudDetectUtils.isValidDateFormat("2014-04-29"));
	}
	
}
