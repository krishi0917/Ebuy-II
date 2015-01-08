package j2eeproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	
	
		//variables for Transaction table
		String name, cardNo, cvv, billingAdd, password;
		name = cardNo = cvv = billingAdd = password = "";
		
		//data for order table
		int userId = (Integer) request.getSession().getAttribute("userID");
		System.out.println("User id is " + userId);
	//	int intUserId = Integer.parseInt(userId);
		String productId = (String) request.getSession().getAttribute("ProductID");
		String quantity = (String) request.getSession().getAttribute("quantity");
		int intQuantity = Integer.parseInt(quantity);
//		HttpSession session = request.getSession();
		name = request.getParameter("cardName");
		billingAdd = request.getParameter("address");
		
	//session.setAttribute("ProductID", productId);
	//	session.setAttribute("quantity", quantity);
		
		database db = new database();
		int status = db.placeOrder(userId, productId, intQuantity, name, billingAdd, cardNo);
		//here status is string
		if (status == 1)
		{	
			request.getRequestDispatcher("/orderFinal.jsp").forward(request, response);
		}
		
	}

	
}


