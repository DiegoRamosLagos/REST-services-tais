package cl.ubb.service.exception;

@SuppressWarnings("serial")
public class CreateErrorException extends Exception {
	private String message;
	
	public CreateErrorException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public boolean equals(String msg) {
		return message.equalsIgnoreCase(msg);
	}

}
