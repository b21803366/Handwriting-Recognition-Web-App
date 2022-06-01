<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" import= "java.text.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		.navbar {
			width: 100%;
			background-color: #555;
			overflow: auto;
		}
		
		.login_div {
			width: 50%;
			position: fixed;
		  	top: 55%;
		  	left: 50%;
		  	transform: translate(-50%, -50%);
		}
		
		.navbar a {
			float: right;
			padding: 12px;
			color: white;
			text-decoration: none;
			font-size: 17px;
		}
		
		.navbar a:hover {
			background-color: #000;
		}
		
		.active {
			background-color: #04AA6D;
		}
		
		@media screen and (max-width: 500px) {
			.navbar a {
				float: none;
				display: block;
			}
		}
		body {
			font-family: Arial, Helvetica, sans-serif;
		}
		
		form {
			border: 3px solid #f1f1f1;
		}
		
		input[type=text], input[type=password], input[type=number]{
			width: 100%;
			padding: 8px 20px;
			margin: 4px 0;
			display: inline-block;
			border: 1px solid #ccc;
			box-sizing: border-box;
		}
		
		button {
			background-color: #04AA6D;
			color: white;
			padding: 14px 20px;
			margin: 8px 0;
			border: none;
			cursor: pointer;
			width: 100%;
		}
		
		button:hover {
			opacity: 0.8;
		}
		
		.cancelbtn {
			width: auto;
			padding: 10px 18px;
			background-color: #f44336;
		}
		
		.imgcontainer {
			text-align: center;
			margin: 24px 0 12px 0;
		}
		
		img.avatar {
			width: 50%;
			<!-- border-radius: 50%; -->
		}
		
		.container {
			padding: 16px;
		}
		
		span.psw {
			float: right;
			padding-top: 16px;
		}
		
		/* Change styles for span and cancel button on extra small screens */
		@media screen and (max-width: 300px) {
			span.psw {
				display: block;
				float: none;
			}
			.cancelbtn {
				width: 100%;
			}
		}
	</style>
	<script src="jquery.min.js"></script>
	<script src="jquery.Jcrop.min.js"></script>
	<link rel="stylesheet" href="jquery.Jcrop.min.css" type="text/css" />
	<script>
		jQuery(function($) {
			$('#target').Jcrop({
				onSelect : setCoordinates
			});
		});
		function setCoordinates(c) {
			//alert("x " + c.x + " y " + c.y);
			//alert("w " + c.w + " h " + c.h);
			document.myForm.x.value = c.x;
			document.myForm.y.value = c.y;
			document.myForm.w.value = c.w;
			document.myForm.h.value = c.h;
		};
		function checkCoordinates() {
			if (document.myForm.x.value == "" || document.myForm.y.value == "") {
				alert("Please select a crop region");
				return false;
			} else {
				return true;
			}
		};
		
	</script>
</head>
<body>
	<div class="navbar">
		<a class="active" href="loadImage.jsp"><i class="fa fa-fw fa-home"></i> Home</a> 	
	</div>
	<div class="login_div">
		<form name="myForm" action="chooseLine" method="post" onsubmit="return checkCoordinates();">
			<div class="container">
				<img src="upload/uploaded.jpg" id="target" width="720" />
				<input type="hidden" name="x" value=""/>
				<input type="hidden" name="y" value=""/>
				<input type="hidden" name="w" value=""/>
				<input type="hidden" name="h" value=""/>
				<input type="submit" value="Crop Image"/>
				<%
					String result = request.getParameter("result");
					if(result != null)
						out.println("Result:	" + result);
				%>
			</div>
		</form>
	</div>
</body>
</html>