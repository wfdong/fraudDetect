package fraudDetect;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import fraudDetect.FraudDetectApp;
import fraudDetect.model.FraudRecord;

public class FraudDectectAppTest {

	static FraudDetectApp fraudDetectApp;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		fraudDetectApp = new FraudDetectApp();
	}
	
	@Before
	public void setup() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void FraudDectectAppTest1() {
		List<FraudRecord> fraudRecords = fraudDetectApp.process(140, "./src/test/creditCardTransactionsTestFile1.csv");
		assertEquals(1, fraudRecords.size());
		assertEquals("10d7ce2f43e35fa57d1bbf8b1e5", fraudRecords.get(0).getCardNumberHashCode());
		assertEquals(LocalDate.parse("2014-04-29"), fraudRecords.get(0).getEventDate());
	}
	
	@Test
	public void FraudDectectAppTest2() {
		List<FraudRecord> fraudRecords = fraudDetectApp.process(200, "./src/test/creditCardTransactionsTestFile1.csv");
		assertEquals(0, fraudRecords.size());
	}
	
	@Test
	public void FraudDectectAppTest3() {
		List<FraudRecord> fraudRecords = fraudDetectApp.process(10, "./src/test/creditCardTransactionsTestFile1.csv");
		assertEquals(4, fraudRecords.size());
	}
	
	/*
	 * file2 including invalid records
	 */
	@Test
	public void FraudDectectAppTest4() {
		List<FraudRecord> fraudRecords = fraudDetectApp.process(30, "./src/test/creditCardTransactionsTestFile2.csv");
		assertEquals(1, fraudRecords.size());
		assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", fraudRecords.get(0).getCardNumberHashCode());
		assertEquals(LocalDate.parse("2014-04-29"), fraudRecords.get(0).getEventDate());
	}
	
	/*
	 * file2 including invalid records
	 */
	@Test
	public void FraudDectectAppTest5() {
		List<FraudRecord> fraudRecords = fraudDetectApp.process(5, "./src/test/creditCardTransactionsTestFile2.csv");
		assertEquals(2, fraudRecords.size());
	}
}
