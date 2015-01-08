package j2eeproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		//database db = new database();
		
		
		database db= new database();
		db.updateStatus();
		String productId = request.getParameter("productId");
		System.out.println("product Id " + productId);
		String productData = db.getProductDetails(productId);
	
	//	System.out.println("db data " + productData);
		String[] pData = productData.split(",");
		String quantity = request.getParameter("quantity");
		
		
		int total  = Integer.parseInt(quantity) * Integer.parseInt(pData[2]);
		
		
	//	String image = request.getParameter("image");
		HttpSession session = request.getSession();
		request.setAttribute("productID", productId);
		request.setAttribute("quantity", quantity);
//		request.setAttribute("image", image);
		request.setAttribute("price", pData[2]);
		request.setAttribute("quantityAvl", pData[1]);
		request.setAttribute("description", pData[0]);
		request.setAttribute("total", total);
		
		
		session.setAttribute("ProductID", productId);
		session.setAttribute("quantity", quantity);
//		request.setAttribute("image", image);
		session.setAttribute("price", pData[2]);
		session.setAttribute("quantityAvl", pData[1]);
		session.setAttribute("description", pData[0]);
		session.setAttribute("total", total);
		
		//db.updateStatus();
		request.getRequestDispatcher("/orderSummary.jsp").forward(request, response);
		//response.sendRedirect(response.encodeRedirectURL("/j2eeproject/orderSummary.jsp"));
	//	out.println("Session product Id is :" + (String)session.getAttribute("ProductID"));

	
	
	}
	

}
