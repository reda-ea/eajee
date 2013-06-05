package eajee.http.action;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eajee.http.ActionResponse;

public class TextResponse implements ActionResponse {

	private String contentType;
	private String responseText;

	public TextResponse(String contentType, String text) {
		this.contentType = contentType;
		this.responseText = text;
	}

	public TextResponse(String text) {
		this("text/plain", text);
	}

	@Override
	public void doResponse(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType(this.contentType);
		resp.setStatus(200);
		PrintStream ps = new PrintStream(resp.getOutputStream());
		ps.print(this.responseText);
	}

}
