<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/resources/lgt/setting.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 글쓰기 성공 -->
	<c:if test="${deleteCnt == 1 }">
		<script type="text/javascript">
			alert("삭제 하였습니다.");
			window.location="MovieLog";
		</script>
	</c:if>
	
	<!-- 글쓰기 실패 -->
	<c:if test="${deleteCnt != 1 }">
		<script type="text/javascript">
			alert("삭제에 실패하였습니다.");
			window.history.back();
		</script>
	</c:if>
</body>
</html>