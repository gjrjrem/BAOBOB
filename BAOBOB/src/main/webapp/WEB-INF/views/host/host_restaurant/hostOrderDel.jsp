<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/chg/setting.jsp"%>
<html>
<body>
	<!-- 주문 삭제 실패 -->
	<c:if test="${cnt == 0}">
		<script type="text/javascript">
			alert("삭제 실패");
		</script>
	</c:if>
	
	<!-- 주문 삭제 성공 -->
	<c:if test="${cnt != 0}">
		<script type="text/javascript">
			alert("예약 정보가 삭제 되었습니다.");
			window.location="hostReservList";
		</script>
	</c:if>
</body>
</html>