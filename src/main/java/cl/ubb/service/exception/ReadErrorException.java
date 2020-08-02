package cl.ubb.service.exception;

@SuppressWarnings("serial")
public class ReadErrorException extends Exception{
	private String message;
	
	public ReadErrorException(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public boolean equals(String msg) {
		return message.equalsIgnoreCase(msg);
	}
	
}
