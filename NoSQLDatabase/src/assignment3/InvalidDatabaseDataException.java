package assignment3;

/**
 * Exception class to throw an Exception when the data is
 * not one of the valid Database Data types. 
 */
@SuppressWarnings("serial")
public class InvalidDatabaseDataException extends Exception {

	public InvalidDatabaseDataException() {
		super("Invalid Database Data Format");
	}

	public InvalidDatabaseDataException(String message) {
		super(message);
	}

	public InvalidDatabaseDataException(Throwable cause) {
		super(cause);
	}

	public InvalidDatabaseDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDatabaseDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
