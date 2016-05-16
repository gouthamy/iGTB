package com.igtb.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class IGTBServlet
 */
@WebServlet("/createFile")
@MultipartConfig
public class IGTBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String serverPath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;
	
	 private final static Logger LOGGER = 
	            Logger.getLogger(IGTBServlet.class.getCanonicalName());
	

	/**
	 * Default constructor. 
	 */
	public IGTBServlet() {
		// TODO Auto-generated constructor stub
	}
	
	

	public void init(){
		serverPath = getServletContext().getInitParameter("file-upload");
		 System.out.println("file path "+serverPath);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		System.out.println("File type "+fileType);
		final Part filePart = request.getPart("file");
	    final String fileName = getFileName(filePart);
	    System.out.println("fileName "+fileName);
	    
	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(serverPath + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();
	        int read = 0;
	        final byte[] bytes = new byte[1024];
	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + serverPath);
	        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
	                new Object[]{fileName, serverPath});
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());
	        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
	                new Object[]{fne.getMessage()});
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
		System.out.println("success");
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            String path =  content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	            File file = new File(path);
	            return file.getName();
	            
	        }
	    }
	    return null;
	}

}
