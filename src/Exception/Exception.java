package Exception;


public class Exception extends RuntimeException {
	private static final long serialVersionUID = -6717756986647753869L;
    private String erreur;
    
	public Exception() {
		super();
	}

	public Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public Exception(String message) {
		super(message);
		erreur = message;
	}

	public Exception(Throwable cause) {
		super(cause);
	}
	
	public String getMessage() {
		return erreur;
	}
}