package eajee.http.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eajee.http.ActionResponse;

/**
 * Forward the request to another view, eg. a JSP file.
 * 
 * @author Reda El Khattabi
 */
public class ViewResponse implements ActionResponse {

	private String viewpath;

	public ViewResponse(String path) {
		this.viewpath = path;
	}

	@Override
	public void doResponse(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher(this.viewpath).forward(req, resp);
	}

}
