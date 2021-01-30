package fraudDetect.service;

import java.util.ArrayList;
import java.util.List;

import fraudDetect.model.FraudRecord;
import fraudDetect.model.Transaction;

/*
 * to analysis the data based on given threshold
 *  @author Fudong(Roger)
 */
public class FraudTransactionAnalyser {

	private double thresHold;
	
	public List<FraudRecord> fraudAnalysis(List<Transaction> transactions) {
		List<FraudRecord> records = new ArrayList<FraudRecord>();
		return records;
	}
	
	public double getThresHold() {
		return thresHold;
	}

	public void setThresHold(double thresHold) {
		this.thresHold = thresHold;
	}
}
