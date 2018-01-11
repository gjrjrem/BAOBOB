<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/resources/setting.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${movie.movie_title}</title>
</head>
<body style="background-color:black; height:700">
	
	<!-- CSS,JavaScript 참조 -->
	<%@ include file="/WEB-INF/views/guest/common/head.jsp" %>
	
	<section style="padding-top:50px;">
		<div class="container">
			<%-- <div class="row">
				<div class="col-md-12" align="left">
					<h3 style="color:white;"><span style="color:blue; font-size:17px; font-weight:bald">[HD]</span>${movie.movie_title}</h3>
				</div>
			</div> --%>
			<div class="row">
				<div class="col-md-10" align="center">
					<div class="embed-responsive embed-responsive-16by9"> <!-- 해상도  -->
						<iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${movie.movie_trailer}"></iframe>
						</iframe>
					</div>
				</div>
				<div class="col-md-2" align="center"><br><br><br>
					<img src="${projectRes}/images/phc/${movie.movie_poster}" width="120px" height="150px"><br>
					<span style="color:white; font-size:15px; font-weight:bold">${movie.movie_title} ${movie.movie_age}</span><br>					
					<span style="color:white; font-size:11px">
						<c:choose>
							<c:when test="${movie.movie_janre==1}">가족</c:when>
							<c:when test="${movie.movie_janre==2}">공포/호러</c:when>
							<c:when test="${movie.movie_janre==3}">드라마</c:when>
							<c:when test="${movie.movie_janre==4}">SF</c:when>
							<c:when test="${movie.movie_janre==5}">멜로/로맨스</c:when>
							<c:when test="${movie.movie_janre==6}">코미디</c:when>
							<c:when test="${movie.movie_janre==7}">애니메이션</c:when>
						</c:choose>
					</span><br>
					<span style="color:white; font-size:11px">20${movie.movie_rel_date}</span><br>
										
				</div>
			</div><br>
			<div class="row">
				<div class="col-md-12" align="left">
					<span style="color:white;">줄거리</span>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10" align="left" style="border:1px solid white; height:200px;">
					<span style="color:white; font-size:13px">${movie.movie_content}</span>
				</div>
			</div>
		</div>
	</section>
	<br>
	<br>
	<br>
		
</body>
</html>