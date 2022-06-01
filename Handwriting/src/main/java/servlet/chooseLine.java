package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class chooseLine
 */
@WebServlet("/chooseLine")
public class chooseLine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chooseLine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String w = request.getParameter("w");
		String h = request.getParameter("h");
		
		File imageFile = new File("C:\\Users\\Mali\\eclipse-workspace-web\\Handwriting\\src\\main\\webapp\\upload\\uploaded.jpg");
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		
		int img_w = bufferedImage.getWidth();
		float rate = (float)img_w/(float)720;
		
		
		int real_x = Math.round(rate * Integer.parseInt(x));
		int real_y = Math.round(rate * Integer.parseInt(y));
		int real_w = Math.round(rate * Integer.parseInt(w));
		int real_h = Math.round(rate * Integer.parseInt(h));
		
		BufferedImage croppedImage = bufferedImage.getSubimage(real_x, real_y, real_w, real_h);
		
		File image = new File("C:\\Users\\Mali\\eclipse-workspace-web\\Handwriting\\src\\main\\webapp\\upload\\input.jpg");
	    image.delete();
	    
		File pathFile = new File("C:\\Users\\Mali\\eclipse-workspace-web\\Handwriting\\src\\main\\webapp\\upload\\input.jpg");
		ImageIO.write(croppedImage,"jpg", pathFile);
		long start = System.nanoTime();    
		String result = javaToPython.line_recognation();
		long end = System.nanoTime();      
	    System.out.println("Elapsed Time in nano seconds: "+ (end-start));
		System.out.println(result);
		response.sendRedirect("chooseLine.jsp?result="+result);
	}
}
