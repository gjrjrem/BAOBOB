<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/resources/setting.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Insert title here</title>
	
	<style type="text/css">
		body .card{max-width:700px;margin:0 auto;margin-bottom:20px;margin-top:13%;}
		.cnt{font-size:50px;display:block;text-align:center;margin:20px;}
		.clear{clear:both;}
		.item{float:left;}
		.u{width:209px;text-align:center;}
		.u > li{padding:10px;}
		body .l_h{border-bottom:1px solid #e9ecef;font-weight:bold;padding-bottom:20px;}
	</style>
</head>
<body>
	<%@ include file="../common/head.jsp" %>

	<%@ include file="guestParkingHeader.jsp" %>
	
	<section>
	  <div class="container">
	  		<div class="card">
		  		<input class="input" type="text" id="key" placeholder="발급받은 번호를 입력하세요">
			  	<button class="btn btn-block" onclick="myParking();">확인</button>
	  		</div>
	  
	  		<div id="result">
			</div>
	  </div>
	</section>
	
	<!-- footerCopyright -->
	<%@ include file="../common/footerCopyright.jsp" %>
	
	<script src="${projectRes}ymk/js/ajax.js"></script>
	<script type="text/javascript">
		function myParking() {
			var param = 'key=' + document.getElementById('key').value;
			sendRequest(parking_callback, 'guestParkingMy', 'GET', param);
		}
		
		function parking_callback() {
			if(httpRequest.readyState == 4) {
				if(httpRequest.status == 200) {
					var data = httpRequest.responseText;
					
					document.getElementById('result').innerHTML = data;
					console.log(data);
				}
			}
		}
	</script>
</body>
</html>