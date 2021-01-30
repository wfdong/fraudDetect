package fraudDetect.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * to record a detected fraud item: card number, how much in totally, the specific time, start/end time and transaction nums
 * @author Fudong(Roger)
 */
public class FraudRecord {
	private String cardNumberHashCode;
	private double totalAmount;
	private LocalDate eventDate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double transactionNums;

	public FraudRecord() {

	}

	public FraudRecord(String cardNumberHashCode, double totalAmount, LocalDate eventDate, LocalDateTime startTime,
			LocalDateTime endTime, double transactionNums) {
		this.cardNumberHashCode = cardNumberHashCode;
		this.totalAmount = totalAmount;
		this.eventDate = eventDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.transactionNums = transactionNums;
	}

	public String getCardNumberHashCode() {
		return cardNumberHashCode;
	}

	public void setCardNumberHashCode(String cardNumberHashCode) {
		this.cardNumberHashCode = cardNumberHashCode;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public double getTransactionNums() {
		return transactionNums;
	}

	public void setTransactionNums(double transactionNums) {
		this.transactionNums = transactionNums;
	}
}
