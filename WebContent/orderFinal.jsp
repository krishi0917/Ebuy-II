<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
		<meta charset="utf-8">
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!--webfonts-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text.css'/>
		<!--//webfonts-->
</head>
<body>
	<div class="main">
		<div class="header" >
			<h1>Confirming the order</h1>
		</div>
	
			<form name = 'loginform' method='post' action='http://localhost:8080/j2eeproject/final.jsp' id='form-control'>
		       				<ul>
		       				
		                    <li><label><b>Product           :</b>   </label> <%= session.getAttribute("description") %><li>
		                    <li><label><b>Quantity        :   </b></label> <%= session.getAttribute("quantity") %><li>
		                    <li><label><b>Available Quantity :  </b> </label> <%= session.getAttribute("quantityAvl") %><li>
		                    <li><label><b>Price              :  </b> </label> $<%= session.getAttribute("price") %><li>
		                    <li><label><b>Total Amount       :  </b> </label> $<%= session.getAttribute("total") %><li>
		                    <button class='btn' id='btn1' type='submit' ><b>Check Out?</b> </button><hr>	                    
		                    </li></ul>
		                    
		       
		 	         
		               
		                </form>
			
			
		</div>
		
	
</body>
</html>