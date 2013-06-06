package eajee.http.rest;

import java.util.Map;

/**
 * an object container is an object that contains other objects. <br>
 * REST operations on container have different impact than on normal objects.
 * 
 * @author Reda El Khattabi
 */
public interface ObjectContainer {
	Object buildObject(Map<String, String[]> parameters);
}
