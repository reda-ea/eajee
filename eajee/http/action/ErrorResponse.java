package eajee.http.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eajee.http.ActionResponse;

/**
 * Returns a HTTP error.
 * 
 * @author Reda El Khattabi
 */
public class ErrorResponse implements ActionResponse {

	private int error;
	private String message;

	public ErrorResponse(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public ErrorResponse(int error) {
		this(error, null);
	}

	@Override
	public void doResponse(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (this.message == null)
			resp.sendError(this.error);
		else
			resp.sendError(this.error, this.message);
	}
}
