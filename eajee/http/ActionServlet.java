package eajee.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = -5436397904404462808L;

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] actionpath = req
				.getRequestURI()
				.substring(
						req.getContextPath().length()
								+ req.getServletPath().length() + 1).split("/");
		Method m = this.find_action_method(actionpath);
		ActionResponse aResp = this.call_action_method(m, actionpath);
		aResp.doResponse(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	private static final String ACTION_METHOD_PREFIX = "do";

	// TODO support annotations
	private Method find_action_method(String[] actionpath) {
		for (Method m : this.getClass().getMethods()) {
			if (!m.getName().equalsIgnoreCase(
					ActionServlet.ACTION_METHOD_PREFIX + actionpath[0]))
				continue;
			if (!ActionResponse.class.isAssignableFrom(m.getReturnType()))
				continue;
			if (!this.check_action_method_parameters(m, actionpath))
				continue;
			return m;
		}
		throw new NoSuchMethodError(actionpath[0]);
	}

	// TODO allow integer parameters
	// TODO last parameter can be an array
	private boolean check_action_method_parameters(Method m, String[] actionpath) {
		// if (actionpath.length <= m.getParameterTypes().length)
		// return false;
		for (Class<?> c : m.getParameterTypes())
			if (!c.equals(String.class))
				return false;
		return true;
	}

	private ActionResponse call_action_method(Method m, String[] actionpath) {
		try {
			return (ActionResponse) m.invoke(this,
					this.prepare_action_method_parameters(m, actionpath));
		} catch (IllegalArgumentException e) {
			throw new Error();
		} catch (IllegalAccessException e) {
			throw new Error();
		} catch (InvocationTargetException e) {
			throw new Error();
		}
	}

	// TODO parse integers once allowed
	private Object[] prepare_action_method_parameters(Method m,
			String[] actionpath) {
		return (Object[]) Arrays.copyOfRange(actionpath, 1,
				m.getParameterTypes().length + 1);
	}

}
