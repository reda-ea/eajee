package eajee.http.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eajee.http.ActionResponse;

// TODO infer mime type from file extension
public class FileResponse implements ActionResponse {

	private static final int BUFSIZE = 4096;
	public static final String DEFAULT_MIMETYPE = "application/octet-stream";

	private String contentType;
	private String filePath;
	private String fileName;

	public FileResponse(String mimeType, String path, String fileName) {
		this.contentType = mimeType;
		this.filePath = path;
		this.fileName = fileName;
	}

	private static String basename(String path) {
		int pos = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
		return path.substring(pos + 1);
	}

	public FileResponse(String mimeType, String path) {
		this(mimeType, path, FileResponse.basename(path));
	}

	public FileResponse(String path) {
		this(FileResponse.DEFAULT_MIMETYPE, path, FileResponse.basename(path));
	}

	@Override
	public void doResponse(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		File file = new File(this.filePath);
		int length = 0;
		ServletOutputStream outStream = resp.getOutputStream();

		// ServletContext context = this.getServletConfig().getServletContext();
		// String mimetype = context.getMimeType(filePath);
		//
		// // sets response content type
		// if (mimetype == null) {
		// mimetype = "application/octet-stream";
		// }

		resp.setContentType(this.contentType);
		resp.setContentLength((int) file.length());

		// String fileName = (new File(filePath)).getName();
		// resp.setHeader("Content-Disposition", "attachment; filename=\""
		// + this.fileName + "\"");

		resp.setHeader("Content-Disposition", "filename=\"" + this.fileName
				+ "\"");

		byte[] byteBuffer = new byte[FileResponse.BUFSIZE];
		DataInputStream in = new DataInputStream(new FileInputStream(file));

		// reads the file's bytes and writes them to the response stream
		while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
			outStream.write(byteBuffer, 0, length);
		}

		in.close();
		outStream.close();
	}

}
