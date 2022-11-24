package exceptions;

public class EntityIsNotExist extends Exception {
	
	public EntityIsNotExist (String errorMessage) {
		super(errorMessage);
	}
}
