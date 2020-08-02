package cl.ubb.service.exception;

@SuppressWarnings("serial")
public class EmptyListException extends Exception {
	private String message;
	
	public EmptyListException(String message) {
		this.message= message;
	}

	public String getMessage() {
		return message;
	}
	
	public boolean equals(String msg) {
		return message.equalsIgnoreCase(msg);
	}
}
