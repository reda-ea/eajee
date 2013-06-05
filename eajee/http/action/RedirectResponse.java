package eajee.http.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eajee.http.ActionResponse;

/**
 * Sends an HTTP redirect to the given URL.
 * 
 * @author Reda El Khattabi
 */
public class RedirectResponse implements ActionResponse {

	private String redirecturl;

	public RedirectResponse(String url) {
		this.redirecturl = url;
	}

	@Override
	public void doResponse(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect(this.redirecturl);
	}

}
