package eajee.http;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eajee.error.IncompatibleObjectException;
import eajee.error.MalformedDataException;
import eajee.error.NoSuchResourceException;
import eajee.error.WrongParametersException;

//TODO support HTTP attributes
//TODO use file extensions for types
public abstract class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 8545310446563831428L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resource = req.getRequestURI().substring(
				req.getContextPath().length() + req.getServletPath().length());
		try {
			Object output = this.retrieveObject(resource);
			String contentType = this.getContentType(resource);
			resp.setContentType(contentType);
			resp.getWriter().print(this.formatObject(output, contentType));
		} catch (NoSuchResourceException e) {
			resp.sendError(404, resource);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resource = req.getRequestURI().substring(
				req.getContextPath().length() + req.getServletPath().length());
		// TODO use encoding if available (as HTTP header)
		java.util.Scanner s1 = new java.util.Scanner(req.getInputStream());
		java.util.Scanner s = s1.useDelimiter("\\A");
		String requestBody = s.hasNext() ? s.next() : "";
		s.close();
		s1.close();
		try {
			this.storeObject(resource,
					this.buildObject(this.getObjectType(resource), requestBody));
		} catch (IncompatibleObjectException e) {
			resp.sendError(409, resource);
		} catch (MalformedDataException e) {
			resp.sendError(400, resource);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resource = req.getRequestURI().substring(
				req.getContextPath().length() + req.getServletPath().length());
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = req.getParameterMap();
		try {
			this.storeObject(resource, this.updateObject(
					this.retrieveObject(resource), parameters));
		} catch (NoSuchResourceException e) {
			resp.sendError(404, resource);
		} catch (WrongParametersException e) {
			resp.sendError(400, "updating '" + resource + "'");
		} catch (IncompatibleObjectException e) {
			resp.sendError(400, "storing '" + resource + "'");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resource = req.getRequestURI().substring(
				req.getContextPath().length() + req.getServletPath().length());
		try {
			this.deleteObject(resource);
		} catch (NoSuchResourceException e) {
			resp.sendError(404, resource);
		}
	}

	/**
	 * the HTTP content type for a resource
	 */
	protected abstract String getContentType(String resource);

	/**
	 * formats the given object for output in a specific content type
	 */
	protected abstract String formatObject(Object o, String contentType);

	/**
	 * the Java type for a resource
	 */
	protected abstract Class<?> getObjectType(String resource);

	/**
	 * Builds a new object using the provided data
	 */
	protected abstract Object buildObject(Class<?> type, String data)
			throws MalformedDataException;

	/**
	 * Retrieves an object based on its resource path
	 */
	protected abstract Object retrieveObject(String resource)
			throws NoSuchResourceException;

	/**
	 * Replaces specified resource path with the given object (or creates it if
	 * there is no such object)
	 */
	protected abstract void storeObject(String resource, Object o)
			throws IncompatibleObjectException;

	/**
	 * Deletes the provided resource
	 */
	protected abstract void deleteObject(String resource)
			throws NoSuchResourceException;

	/**
	 * Updates the provided object with the given data
	 * 
	 * @return
	 *         the updated object, which may be the same, or a newly created one
	 *         (for immutable types)
	 */
	protected abstract Object updateObject(Object o,
			Map<String, String[]> parameters) throws WrongParametersException;
}
