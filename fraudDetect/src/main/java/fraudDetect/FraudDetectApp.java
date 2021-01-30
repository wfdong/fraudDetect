package fraudDetect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fraudDetect.model.FraudRecord;
import fraudDetect.model.Transaction;
import fraudDetect.service.FraudTransactionAnalyser;
import fraudDetect.utils.FraudDetectUtils;

/*
 *  application entrance
 *  @author Fudong(Roger)
 */
public class FraudDetectApp {

	public static void main(String[] args) {
		// System.out.println("application start!");
		if (args.length != 2) {
			System.err.println(
					"input arguments invalid. Please input the threshold and the input file with a space between them (eg: 150 ./creditCardTransactions.csv)");
			System.exit(1);
		} else {
			String thresholdStr = args[0];
			String inputFile = args[1];
			if (FraudDetectUtils.isNumeric(thresholdStr)) {
				FraudDetectApp fraudDetectApp = new FraudDetectApp();
				double thresHold = Double.valueOf(thresholdStr);
				List<FraudRecord> fraudRecords = fraudDetectApp.process(thresHold, inputFile);
				if (!fraudRecords.isEmpty()) {
					System.out.println("These hashed credit cards are identified as fraudulent:");
					for (FraudRecord record : fraudRecords) {
						System.out.println(record.getCardNumberHashCode());
					}
				} else {
					System.out.println("No fraudulent hashed credit cards found.");
				}
			} else {
				System.err.println("input error, the first argument should be a number.");
				System.exit(1);
			}
		}
	}
	
	public List<FraudRecord> process(double thresHold, String inputFile) {
		List<Transaction> transactions = this.readDataFromFile(inputFile);
		FraudTransactionAnalyser fraudTransactionAnalyser = new FraudTransactionAnalyser();
		fraudTransactionAnalyser.setThresHold(thresHold);
		List<FraudRecord> fraudRecords = fraudTransactionAnalyser.fraudAnalysis(transactions);
		return fraudRecords;
	}
	
	/*
	 * read file content
	 */
	public List<Transaction> readDataFromFile(String filePath) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		if (null == filePath) {
			return transactions;
		}
		File file = new File(filePath);
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String currentLine = sc.nextLine();
				if (FraudDetectUtils.isRightRecord(currentLine)) {
					String[] recordItems = currentLine.split(", ");
					String cardNumberHashCode = recordItems[0];
					String timeStr = recordItems[1];
					String amount = recordItems[2];
					transactions.add(new Transaction(cardNumberHashCode, FraudDetectUtils.parseDateTime(timeStr),
							Double.valueOf(amount)));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return transactions;
	}
}
