package j2eeproject;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Login
 */
//@WebServlet(description = "Login Servlet", urlPatterns = { "/login" })
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{      
    
		
		
		System.out.println("We are here in the login servlet");

		String quantity = (String)request.getSession().getAttribute("quantity");
		/*String price = (String)request.getSession().getAttribute("price");
		String total = (String)request.getSession().getAttribute("total");
		String description = (String)request.getSession().getAttribute("description");
		*/
		
		HttpSession session = request.getSession();
/*		request.setAttribute("description", description);
		request.setAttribute("price", price);
		request.setAttribute("total", total);*/
		request.setAttribute("quantity", quantity);
		//request.getparameter takes the data what user has entered 
		String inUserName = request.getParameter("un");
		String inPasswd =  request.getParameter("Password");
		String msg = "Hello ";

		System.out.println("username and password " + inUserName + " " + inPasswd);
		if(inUserName.equals(null) || inPasswd.equals(null))
		{
			
			PrintWriter out = response.getWriter();
			out.println("<font size='6' color='red'>" + msg + "</font>");
		}
		
		SHA pass = new SHA();
		String encryptPass = pass.encryptPassword(inPasswd);
		System.out.println("Encrypted password is " + encryptPass);
		database db = new database();
		String firstName = db.verifyLogin(inUserName, encryptPass);
//		System.out.println(firstName);
		String[] nameAndId = firstName.split(",");
		
		if(nameAndId[0] != "" && nameAndId[1] !="")
		{
		//	response.sendRedirect(response.encodeRedirectURL("/j2eeproject/transaction.jsp"));
			session.setAttribute("userName", nameAndId[0]);
			session.setAttribute("userID", Integer.parseInt(nameAndId[1]));
			request.getRequestDispatcher("/Transaction.jsp").forward(request, response);
		}
		else
		{ 
			msg = "Incorrect Username or Password";
			System.out.println(msg);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<font size='6' color='red'>" + msg + "</font>");
			response.sendRedirect(response.encodeRedirectURL("/j2eeproject/index.jsp"));
		}

	
	}
}



