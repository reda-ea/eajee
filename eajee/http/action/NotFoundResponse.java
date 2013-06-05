package eajee.http.action;

/**
 * A 404 Error.
 * 
 * @author Reda El Khattabi
 */
public class NotFoundResponse extends ErrorResponse {
	public NotFoundResponse() {
		super(404);
	}

	public NotFoundResponse(String resource) {
		super(404, resource);
	}
}
