package eajee.http.rest;

import java.io.PrintWriter;
import java.util.Map;

import eajee.error.IncompatibleObjectException;
import eajee.error.MalformedDataException;
import eajee.error.WrongParametersException;
import eajee.util.ServletRequestReader;

/**
 * Able to format, build and update objects, usually of a single type/format.
 * 
 * @author Reda El Khattabi
 */
public interface ObjectFormatter {
	void writeObject(Object o, PrintWriter out)
			throws IncompatibleObjectException;

	Object readObject(ServletRequestReader in) throws MalformedDataException;

	/**
	 * Updates the provided object using the given parameters (what this means
	 * is up to the implementation)
	 * 
	 * @throws IncompatibleObjectException
	 *             if the Object can not be handled by this formatter
	 */
	Object updateObject(Object o, Map<String, String[]> parameters)
			throws WrongParametersException, IncompatibleObjectException;

	/**
	 * Provides the HTTP content type to indicate when returning an object
	 * formatted by this formatter.
	 */
	String getContentType();
}
