package fraudDetect.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fraudDetect.model.FraudRecord;
import fraudDetect.model.Transaction;

public class FraudTransactionAnalyserTest {

	static List<Transaction> trasactions;
	static FraudTransactionAnalyser fraudTransactionAnalyser;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		fraudTransactionAnalyser = new FraudTransactionAnalyser();
		trasactions = new ArrayList<Transaction>();
	}
	
	@Before
	public void setup() {
		/* set some default values
		 * 10d7ce2f43e35fa57d1bbf8b1e2 ---  50
		 * 10d7ce2f43e35fa57d1bbf8b1e3 ---  40
		 * 10d7ce2f43e35fa57d1bbf8b1e4 ---  10
		 */
		Transaction tran1 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e2", LocalDateTime.parse("2014-04-29T13:15:54"), 10);
		Transaction tran2 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e2", LocalDateTime.parse("2014-04-29T13:16:54"), 10);
		Transaction tran3 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e3", LocalDateTime.parse("2014-04-29T13:16:59"), 10);
		Transaction tran4 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e3", LocalDateTime.parse("2014-04-29T13:17:09"), 10);
		Transaction tran5 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e2", LocalDateTime.parse("2014-04-29T13:18:54"), 10);
		Transaction tran6 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e3", LocalDateTime.parse("2014-04-29T13:19:54"), 10);
		Transaction tran7 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e2", LocalDateTime.parse("2014-04-29T13:20:54"), 10);
		Transaction tran8 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e3", LocalDateTime.parse("2014-04-29T13:21:54"), 10);
		Transaction tran9 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e2", LocalDateTime.parse("2014-04-29T13:22:54"), 10);
		Transaction tran10 = new Transaction("10d7ce2f43e35fa57d1bbf8b1e4", LocalDateTime.parse("2014-04-29T13:23:54"), 10);
		trasactions.add(tran1);
		trasactions.add(tran2);
		trasactions.add(tran3);
		trasactions.add(tran4);
		trasactions.add(tran5);
		trasactions.add(tran6);
		trasactions.add(tran7);
		trasactions.add(tran8);
		trasactions.add(tran9);
		trasactions.add(tran10);
	}
	
	@After
	public void tearDown() {
		trasactions.clear();
	}
	
	@Test
	public void FraudTransactionAnalyserTest_normal1() {
		fraudTransactionAnalyser.setThresHold(5);
		List<FraudRecord> fraudRecords1 = fraudTransactionAnalyser.fraudAnalysis(trasactions);
		assertEquals(3, fraudRecords1.size());
	}
	
	@Test
	public void FraudTransactionAnalyserTest_normal2() {
		fraudTransactionAnalyser.setThresHold(35);
		List<FraudRecord> fraudRecords2 = fraudTransactionAnalyser.fraudAnalysis(trasactions);
		assertEquals(2, fraudRecords2.size());
	}
	
	@Test
	public void FraudTransactionAnalyserTest_normal3() {
		fraudTransactionAnalyser.setThresHold(45);
		List<FraudRecord> fraudRecords3 = fraudTransactionAnalyser.fraudAnalysis(trasactions);
		assertEquals(1, fraudRecords3.size());
		assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", fraudRecords3.get(0).getCardNumberHashCode());
		assertEquals(50, fraudRecords3.get(0).getTotalAmount());
		assertEquals(5, fraudRecords3.get(0).getTransactionNums());
		assertEquals(LocalDate.parse("2014-04-29"), fraudRecords3.get(0).getEventDate());
		assertEquals(LocalDateTime.parse("2014-04-29T13:15:54"), fraudRecords3.get(0).getStartTime());
		assertEquals(LocalDateTime.parse("2014-04-29T13:22:54"), fraudRecords3.get(0).getEndTime());
	}
	
	@Test
	public void FraudTransactionAnalyserTest_normal4() {
		fraudTransactionAnalyser.setThresHold(55);
		List<FraudRecord> fraudRecords4 = fraudTransactionAnalyser.fraudAnalysis(trasactions);
		assertEquals(0, fraudRecords4.size());
	}
	
	@Test
	public void FraudTransactionAnalyserTest_abnormal5() {
		fraudTransactionAnalyser.setThresHold(55);
		List<FraudRecord> fraudRecords4 = fraudTransactionAnalyser.fraudAnalysis(null);
		assertEquals(null, fraudRecords4);
	}
}
