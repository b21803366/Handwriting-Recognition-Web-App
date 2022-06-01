package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.servlet.annotation.*;

@WebServlet(name = "loadImage", urlPatterns = { "/loadImage" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class loadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("image");
		
	    String savePath = "C:\\Users\\Mali\\eclipse-workspace-web\\Handwriting\\src\\main\\webapp\\upload\\";
	    File fileSaveDir = new File(savePath);
	    if (!fileSaveDir.exists()) {
	      fileSaveDir.mkdir();
	    }
	    File image = new File(savePath + "uploaded.jpg");
	    image.delete();
	    filePart.write(savePath + "uploaded.jpg");
	    //System.out.println(System.getProperty("user.dir"));
	    //filePart.write("uploaded.jpg");
	    
	    response.addHeader("message", "Successfully Added");
	    response.sendRedirect("chooseLine.jsp");
	}

}
