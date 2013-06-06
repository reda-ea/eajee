package eajee.http.rest;

import java.util.Map;

import eajee.error.IncompatibleObjectException;
import eajee.error.MalformedDataException;
import eajee.error.WrongParametersException;

/**
 * In addition to overriding the default methods, any callable one argument
 * 'format*' named method will be preferred if it matches the object type.
 * 
 * @author Reda El Khattabi
 */
public interface ObjectFormatter {
	String formatObject(Object o) throws IncompatibleObjectException;

	Object buildObject(String s) throws MalformedDataException;

	Object updateObject(Object o, Map<String, String[]> parameters)
			throws WrongParametersException;
}
