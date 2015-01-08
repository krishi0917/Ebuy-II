package j2eeproject;
import java.sql.*;
public class database {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private String query;
	private String username="";
	private String password="";
	private Statement st1;
	public database()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root", "");
			st= con.createStatement();
		}catch(Exception ex){
			System.out.println("Error" + ex);
		}
	}

	protected int placeOrder(int intUserId, String productId, int intQuantity,
			String name, String billingAdd, String cardNo) {
		//select max order number from order table
		int orderNo = 100;
		
		try{
			query = "select max(ORDERNO) as ORDERNO from ORDERDETAILS";
			rs = st.executeQuery(query);
			while(rs.next()){
				orderNo = rs.getInt("ORDERNO") + 1;
			}
			
			//Insert into the Order Details
			try{
				query = "insert into ORDERDETAILS (ORDERNO,USERID,PRODUCTID,QUANTITY) values(" + orderNo + "," + intUserId +"," + "'" +productId+ "'," + intQuantity +")";
				System.out.println(query);
				int orderStatus = st.executeUpdate(query);
				//Insert the transaction for the above order if success
				if(orderStatus == 1){
					int txnStatus = transaction(orderNo, name, productId, intQuantity);
					
					return txnStatus;
				
				}
			}catch(Exception ex){
				System.out.println("Insertion to Order table failed " +ex);
			}
			
		}catch(Exception ee){
			System.out.println("Unable to fetch Order Number" + ee);
		}
	return 0;
	//return ""; //when it becomes string
	
	}

	
private int transaction(int orderNo, String name, String productID, int quantity){
		
		try{
			query = "insert into transactiondetails (ORDERID, NAME, STATUS) values (" + orderNo +",'" +name + "','INPROGRESS')" ;
			System.out.println(query);
			int status = st.executeUpdate(query);	
			if (status == 1){
				query = "update PRODUCTDETAILS set quantity = quantity - " + quantity + " where PRODUCTID = " + "'" + productID + "'";
				System.out.println(query);
				status = st.executeUpdate(query);
				return status;
			}
	
		}catch(Exception ex){
			System.out.println("Error in transaction " + ex);
		}	
		return 0;
	}
	
	protected String verifyLogin(String inUserName, String inPassword){
		String outPassword = "";
		username = inUserName;
		password = inPassword;
		
		try{
			query = "select PASSWORD from LOGINDETAILS where USERNAME = " + "'" + username + "'";
			System.out.println(query);
			rs = st.executeQuery(query);
			if(!rs.next()){
				System.out.println("User Does not exist");
				return "";
			}
			else{
				//taking the password from the database 
				outPassword = rs.getString("PASSWORD");
				System.out.println("The passsword is " + outPassword);
				if(outPassword.isEmpty() || outPassword.equals(null)){
					return "";
				}
				if(outPassword.equals(password)){
					try{
						String firstName = "";
						int userId = 0;
						
					//	System.out.println("currently struck here as we dont have the table userdetails");
						
						query = "select u.FIRSTNAME, l.USERID from USERDETAILS u, LOGINDETAILS l where l.USERNAME = " + "'" + username +"'" + " and u.USERID = l.USERID";
						rs = st.executeQuery(query);
						while(rs.next()){
						firstName = rs.getString("FIRSTNAME");
						userId = rs.getInt("USERID");
						System.out.println("The user id is " + userId);
						}
						return firstName + "," + userId;
					}catch(Exception ee){
						System.out.println("Error in fetching first name from userdetails " + ee);
					}
					
				}
				else{
					return "";
				}
			}
			
		}catch(Exception ex){
			System.out.println("Database Error: " + ex);
		}
		return "";
	}

	protected String getProductDetails(String productId){
		
		String pId = productId;
		String pName = "";
		int qty = 0;
		int price = 0;
		String concat = "";
		
		try{
			String query = "select PRODUCTNAME, QUANTITY, PRICE from PRODUCTDETAILS where PRODUCTID = " + "'" + pId + "'";
			rs =st.executeQuery(query);
			while(rs.next()){
				 pName = rs.getString("PRODUCTNAME");
				 qty = rs.getInt("QUANTITY");
				 price = rs.getInt("PRICE");
				 concat = pName + "," + qty +"," +price;
				 System.out.println("product data : " + concat);
		}
		}catch(Exception ex){
			System.out.println("Error Database Exception while fetching product Record : " + ex);
		}
		
		return concat;
	}


	//from here the new part starts....
	private String getLastupdateTime(){
		String diff = "";
		try {
			query = "select DateDiff(curDate(),UPDATETIME) as DATEDIFF, COUNT from LASTUPDATE";
			rs =st.executeQuery(query);
			while(rs.next()){
				diff = rs.getString("DATEDIFF");
				
			}
			return diff;
		}catch(Exception ex){
			System.out.println("Unable to fetch the last update time "+ ex);
		}
		
		return "";
	}
	
	protected void updateStatus(){
		try{
			st1 =con.createStatement();
		}catch(Exception st){
			System.out.println("Unable to initialize statement " + st1);
		}
		String query1 = "";
		String orderId = "";
		String diff = getLastupdateTime();
		int lastUpdate = Integer.parseInt(diff);
		int status = 0;
		System.out.println("Last update variable " + lastUpdate);
		if (lastUpdate >= 1)
		try{			
			query = "select DateDIFF(curDate(),TIME) as DIFF, ORDERID from Transactiondetails group by ORDERID having DIFF > 3";
			rs = st.executeQuery(query);
			
			while(rs.next()){
				orderId = orderId + "," + rs.getString("ORDERID");
				System.out.println("ORder Id's " + orderId);
				
			}
			try{
				query = "update TRANSACTIONDETAILS set STATUS = 'COMPLETED' WHERE ORDERID in (0" + orderId + ")";
				status = st.executeUpdate(query);
			}catch(Exception eex){
				
				System.out.println("unable to update the status for the orders " );
			}
			System.out.println("value of status = " + status);
			
		}catch (Exception ex){
			System.out.println("Unable to run updates for orders "+ ex);
		}
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getData(){
		try{
			String query="select * from logindetails";
			rs=st.executeQuery(query);
			System.out.println("Results from database");
			while (rs.next()){
				int userid= rs.getInt("userid");
				String username=rs.getString("username");
				String password=rs.getString("password");
				System.out.println("User Id : " + userid  );
				System.out.println("Username : " + username  );
				System.out.println("Password : " + password  );
			}
		}catch(Exception ex){
			System.out.println("Error : " + ex);
		}
	}
}
