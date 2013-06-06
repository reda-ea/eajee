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
import eajee.http.rest.ObjectFormatter;
import eajee.http.rest.ObjectStore;
import eajee.util.ServletRequestReader;

//TODO support HTTP attributes (for output)
public abstract class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 8545310446563831428L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resource = req.getRequestURI().substring(
				req.getContextPath().length() + req.getServletPath().length());
		ObjectStore os = this.getStore(req);
		ObjectFormatter of = this.getFormatter(req);
		try {
			resp.setContentType(of.getContentType());
			of.writeObject(os.retrieveObject(resource), resp.getWriter());
		} catch (NoSuchResourceException e) {
			resp.sendError(404, resource);
		} catch (IncompatibleObjectException e) {
			throw new ServletException(
					"Chosen formatter unable to format the retreived object", e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resource = req.getRequestURI().substring(
				req.getContextPath().length() + req.getServletPath().length());
		ObjectStore os = this.getStore(req);
		ObjectFormatter of = this.getFormatter(req);
		try {
			os.storeObject(resource,
					of.readObject(new ServletRequestReader(req)));
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
		ObjectStore os = this.getStore(req);
		ObjectFormatter of = this.getFormatter(req);
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = req.getParameterMap();
		try {
			os.storeObject(resource,
					of.updateObject(os.retrieveObject(resource), parameters));
			// this.storeObject(resource, this.updateObject(
			// this.retrieveObject(resource), parameters));
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
			// this.deleteObject(resource);
			this.getStore(req).deleteObject(resource);
		} catch (NoSuchResourceException e) {
			resp.sendError(404, resource);
		} catch (IncompatibleObjectException e) {
			resp.sendError(403, resource);
		}
	}

	protected abstract ObjectStore getStore(HttpServletRequest req);

	protected abstract ObjectFormatter getFormatter(HttpServletRequest req);

}
