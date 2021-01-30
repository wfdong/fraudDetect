package fraudDetect.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/*
 * to provide some utils
 * @author Fudong(Roger)
 */
public class FraudDetectUtils {

	/*
	 * to check whether a specific record is in correct format
	 */
	public static boolean isRightRecord(String record) {
		if (null == record) {
			return false;
		}
		String[] recordItems = record.split(",");
		if (recordItems != null && recordItems.length == 3 && isValidDateFormat(recordItems[1])
				&& parseDateTime(recordItems[1]) != null && parseDateTime(recordItems[1]).isBefore(LocalDateTime.now())
				&& isNumeric(recordItems[2])) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNumeric(String str) {
		try {
			new BigDecimal(str).toString();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/*
	 * parse a valid string into local date time format
	 */
	public static LocalDateTime parseDateTime(String dateTime) {
		if (isValidDateFormat(dateTime)) {
			try {
				return LocalDateTime.parse(dateTime);
			} catch (DateTimeParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static boolean isValidDateFormat(String dateStr) {
		boolean isValidDateFormat = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			sdf.parse(dateStr);
			sdf.setLenient(false);
			isValidDateFormat = true;
		} catch (ParseException e) {
			isValidDateFormat = false;
		}
		return isValidDateFormat;
	}
}
