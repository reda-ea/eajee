package eajee.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

import javax.servlet.ServletRequest;

public final class ServletRequestReader extends BufferedReader {

	private ServletRequest request;

	private BufferedReader reader;

	private BufferedReader getReader() throws IOException {
		if (this.reader == null)
			this.reader = this.request.getReader();
		return this.reader;
	}

	public ServletRequestReader(ServletRequest req) throws IOException {
		super(new StringReader(""));
		this.request = req;
		this.reader = null;
	}

	/**
	 * Reads the whole request as a {@link String}.<br>
	 * Only usable for text (not binary) queries
	 */
	public String readAll(String charsetName) throws IOException {
		// TODO use encoding if available (as HTTP header)
		java.util.Scanner s1 = (charsetName == null) ? new java.util.Scanner(
				this.request.getInputStream()) : new java.util.Scanner(
				this.request.getInputStream(), charsetName);
		java.util.Scanner s = s1.useDelimiter("\\A");
		String ret = s.hasNext() ? s.next() : "";
		s.close();
		s1.close();
		this.reader = new BufferedReader(new StringReader(ret));
		return ret;
	}

	/**
	 * Reads the whole request as a {@link String}.<br>
	 * Only usable for text (not binary) queries
	 */
	public String readAll() throws IOException {
		return this.readAll(null);
	}

	// /////////////////////////////// AUTO GENERATED

	/**
	 * @throws IOException
	 * @see java.io.BufferedReader#close()
	 */
	public void close() throws IOException {
		this.getReader().close();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		try {
			return this.getReader().equals(obj);
		} catch (IOException e) {
			return super.equals(obj);
		}

	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		try {
			return this.getReader().hashCode();
		} catch (IOException e) {
			return super.hashCode();
		}
	}

	/**
	 * @param readAheadLimit
	 * @throws IOException
	 * @see java.io.BufferedReader#mark(int)
	 */
	public void mark(int readAheadLimit) throws IOException {
		this.getReader().mark(readAheadLimit);
	}

	/**
	 * @return
	 * @see java.io.BufferedReader#markSupported()
	 */
	public boolean markSupported() {
		try {
			return this.getReader().markSupported();
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#read()
	 */
	public int read() throws IOException {
		return this.getReader().read();
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
		return this.getReader().read(cbuf, off, len);
	}

	/**
	 * @param cbuf
	 * @return
	 * @throws IOException
	 * @see java.io.Reader#read(char[])
	 */
	public int read(char[] cbuf) throws IOException {
		return this.getReader().read(cbuf);
	}

	/**
	 * @param target
	 * @return
	 * @throws IOException
	 * @see java.io.Reader#read(java.nio.CharBuffer)
	 */
	public int read(CharBuffer target) throws IOException {
		return this.getReader().read(target);
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#readLine()
	 */
	public String readLine() throws IOException {
		return this.getReader().readLine();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#ready()
	 */
	public boolean ready() throws IOException {
		return this.getReader().ready();
	}

	/**
	 * @throws IOException
	 * @see java.io.BufferedReader#reset()
	 */
	public void reset() throws IOException {
		this.getReader().reset();
	}

	/**
	 * @param n
	 * @return
	 * @throws IOException
	 * @see java.io.BufferedReader#skip(long)
	 */
	public long skip(long n) throws IOException {
		return this.getReader().skip(n);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		try {
			return this.getReader().toString();
		} catch (IOException e) {
			return super.toString();
		}
	}
}
