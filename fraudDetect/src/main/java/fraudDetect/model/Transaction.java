package fraudDetect.model;

import java.time.LocalDateTime;

/*
 * to record a single credit card transaction
 * @author Fudong(Roger)
 */
public class Transaction {
    private String cardNumberHashCode;
    private LocalDateTime transactionTime;
    private double transactionAmount;
    
    public Transaction() {
    	
    }
    public Transaction(String cardNumberHashCode, LocalDateTime transactionTime, double transactionAmount) {
    	    this.cardNumberHashCode = cardNumberHashCode;
    	    this.transactionTime = transactionTime;
    	    this.transactionAmount = transactionAmount;
    }
    
	public String getCardNumberHashCode() {
		return cardNumberHashCode;
	}
	public void setCardNumberHashCode(String cardNumberHashCode) {
		this.cardNumberHashCode = cardNumberHashCode;
	}
	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
}
