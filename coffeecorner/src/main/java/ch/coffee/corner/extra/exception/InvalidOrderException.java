package ch.coffee.corner.extra.exception;

public class InvalidOrderException  extends Exception {
	
	private static final long serialVersionUID = -1432536535133465112L;

	public InvalidOrderException(String message) {
        super(message);
    }
}
