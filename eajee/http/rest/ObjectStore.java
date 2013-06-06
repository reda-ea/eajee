package eajee.http.rest;

import eajee.error.IncompatibleObjectException;
import eajee.error.NoSuchResourceException;

// TODO handle the difference between containers and items
public interface ObjectStore {
	/**
	 * Retrieves an object based on its resource path
	 * 
	 * @throws NoSuchResourceException
	 *             if there is no resource with the given URI
	 */
	Object retrieveObject(String resource) throws NoSuchResourceException;

	/**
	 * Replaces specified resource path with the given object (or creates it if
	 * there is no such object)
	 * 
	 * @throws IncompatibleObjectException
	 *             if the object can't be stored at the given URI
	 */
	void storeObject(String resource, Object o)
			throws IncompatibleObjectException;

	/**
	 * Deletes the provided resource
	 * 
	 * @throws NoSuchResourceException
	 *             if there is no resource with the given URI
	 * @throws IncompatibleObjectException
	 *             if the object at the given URI can't be deleted (eg. still
	 *             has children, is a "root" container)
	 */
	void deleteObject(String resource) throws NoSuchResourceException,
			IncompatibleObjectException;
}
