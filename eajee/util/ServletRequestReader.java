package eajee.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;

import javax.servlet.ServletRequest;

public final class ServletRequestReader extends BufferedReader {

	private ServletRequest request;

	private BufferedReader reader;

	private void setRequest(ServletRequest req) throws IOException {
		this.request = req;
		this.reader = req.getReader();
	}

	public ServletRequestReader(ServletRequest req) throws IOException {
		super(req.getReader());
		this.setRequest(req);
	}

	/**
	 * Reads the whole request as a {@link String}.<br>
	 * Only usable for text (not binary) queries
	 */
	public String readString(String charsetName) throws IOException {
		// TODO use encoding if available (as HTTP header)
		@SuppressWarnings("resource")
		java.util.Scanner s1 = (charsetName == null) ? new java.util.Scanner(
				this.request.getInputStream()) : new java.util.Scanner(
				this.request.getInputStream(), charsetName);
		java.util.Scanner s = s1.useDelimiter("\\A");
		String ret = s.hasNext() ? s.next() : "";
		s.close();
		s1.close();
		return ret;
	}

	/**
	 * Reads the whole request as a {@link String}.<br>
	 * Only usable for text (not binary) queries
	 */
	public String readString() throws IOException {
		return this.readString(null);
	}

	// /////////////////////////////// AUTO GENERATED

	/**
	 * @throws IOException
	 * @see java.io.BufferedReader#close()
	 */
	public void close() throws IOException {
		reader.close();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return reader.equals(obj);
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return reader.hashCode();
	}

	/**
	 * @param readAheadLimit
	 * @throws IOException
	 * @see java.io.BufferedReader#mark(int)
	 */
	public void mark(int readAheadLimit) throws IOException {
		reader.mark(readAheadLimit);
	}

	/**
	 * @return
	 * @see java.io.BufferedReader#markSupported()
	 */
	public boolean markSupported() {
		return reader.markSupported();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#read()
	 */
	public int read() throws IOException {
		return reader.read();
	}

	/**
	 * @param cbuf
	 * @param off
	 * @param len
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#read(char[], int, int)
	 */
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}

	/**
	 * @param cbuf
	 * @return
	 * @throws IOException
	 * @see java.io.Reader#read(char[])
	 */
	public int read(char[] cbuf) throws IOException {
		return reader.read(cbuf);
	}

	/**
	 * @param target
	 * @return
	 * @throws IOException
	 * @see java.io.Reader#read(java.nio.CharBuffer)
	 */
	public int read(CharBuffer target) throws IOException {
		return reader.read(target);
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#readLine()
	 */
	public String readLine() throws IOException {
		return reader.readLine();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#ready()
	 */
	public boolean ready() throws IOException {
		return reader.ready();
	}

	/**
	 * @throws IOException
	 * @see java.io.BufferedReader#reset()
	 */
	public void reset() throws IOException {
		reader.reset();
	}

	/**
	 * @param n
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#skip(long)
	 */
	public long skip(long n) throws IOException {
		return reader.skip(n);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return reader.toString();
	}

}
