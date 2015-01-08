
<!DOCTYPE html>
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
			<h1>Enter Transaction Details</h1>
		</div>
		
	
		<form name='loginform' action = 'placeOrder' method = "post" >
				<ul class="left-form">
					
					<li>
						<input type="text" name ="cardName" id='cardName'  placeholder='Name' />
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" name='cardNo' id='cardNo' placeholder='Enter the valid Card Number' />
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type='text' name='cvv' id='cvv' placeholder='CVV' />
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li> 
					
					<li>
						<input type='text' name='address' id='address' placeholder='Billing Address' />
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li> 
					
					
					<li>
						<input type='password' name='Password' id='txtPassword' placeholder='Transaction Password' />
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li> 
					
					 <li><input name="" type="checkbox" value=""   /> I Agree to the terms and conditions
					 
					 <button class='btn' id='btn1' type='submit' ><b>Verify</button><hr>	       
					
					
					
				</ul>
				<div class="clear"> </div>
					
			</form>
			
		</div>
		
	
</body>
</html>