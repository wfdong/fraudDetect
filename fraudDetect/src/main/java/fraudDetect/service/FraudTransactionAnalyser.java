package fraudDetect.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		Set<String> alreadyFoundCards = new HashSet<String>();
		Map<String, Double> cardToAmoutSum = new HashMap<String, Double>();
		if (transactions != null && !transactions.isEmpty()) {
			int index = 0;
			for (Transaction transaction : transactions) {
				index++;
				// start with this record, to find the fraud items within 24-hour sliding window
				if (transaction.getTransactionTime() != null
						&& !alreadyFoundCards.contains(transaction.getCardNumberHashCode())) {
					if (transaction.getTransactionAmount() > this.thresHold) {
						alreadyFoundCards.add(transaction.getCardNumberHashCode());
						FraudRecord fraudRecord = new FraudRecord();
						fraudRecord.setCardNumberHashCode(transaction.getCardNumberHashCode());
						fraudRecord.setEventDate(transaction.getTransactionTime().toLocalDate());
						records.add(fraudRecord);
					} else {
						cardToAmoutSum.clear();
						cardToAmoutSum.put(transaction.getCardNumberHashCode(), transaction.getTransactionAmount());
						LocalDateTime currentSearchTargetTime = transaction.getTransactionTime().plusHours(24);
						for (int i = index; i < transactions.size(); i++) {
							Transaction currentTransac = transactions.get(i);
							if (alreadyFoundCards.contains(currentTransac.getCardNumberHashCode())) {
								continue;
							}
							if (currentTransac.getTransactionAmount() >= this.thresHold) {
								FraudRecord fraudRecord = new FraudRecord();
								fraudRecord.setCardNumberHashCode(currentTransac.getCardNumberHashCode());
								fraudRecord.setEventDate(currentTransac.getTransactionTime().toLocalDate());
								records.add(fraudRecord);
								alreadyFoundCards.add(currentTransac.getCardNumberHashCode());
							} else {
								if (currentTransac.getTransactionTime().isBefore(currentSearchTargetTime)) {
									if (cardToAmoutSum.containsKey(currentTransac.getCardNumberHashCode())) {
										if (cardToAmoutSum.get(currentTransac.getCardNumberHashCode())
												+ currentTransac.getTransactionAmount() >= this.thresHold) {
											FraudRecord fraudRecord = new FraudRecord();
											fraudRecord.setCardNumberHashCode(currentTransac.getCardNumberHashCode());
											fraudRecord.setEventDate(currentTransac.getTransactionTime().toLocalDate());
											records.add(fraudRecord);
											alreadyFoundCards.add(currentTransac.getCardNumberHashCode());
										} else {
											cardToAmoutSum.put(currentTransac.getCardNumberHashCode(),
													cardToAmoutSum.get(currentTransac.getCardNumberHashCode())
															+ currentTransac.getTransactionAmount());
										}
									} else {
										cardToAmoutSum.put(currentTransac.getCardNumberHashCode(),
												currentTransac.getTransactionAmount());
									}
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}
		return records;
	}

	public double getThresHold() {
		return thresHold;
	}

	public void setThresHold(double thresHold) {
		this.thresHold = thresHold;
	}
}
