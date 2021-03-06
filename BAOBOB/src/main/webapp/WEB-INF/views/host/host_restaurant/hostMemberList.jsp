<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/chg/setting.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${restaurant_css}" rel="stylesheet" type="text/css">
<script src="${restaurant_js}"></script>

</head>
<body class="fixed-nav sticky-footer bg-dark">

	<!-- Navigation -->
	<%@ include file="_navigation.jsp"%>
	<c:if test="${sessionScope.memStep != 4}">
		<div class="content-wrapper">
			<div class="container-fluid" style="width: 1000px;">
				<div class="card mb-3">
					<div class="card-header">
						<i class="fa fa-fw fa-sitemap"></i> 회원 목록
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable">
								<thead>
									<tr>
										<th style="width: 600px; text-align: center;">회원정보</th>
										<th style="width: 100px; text-align: center;">직원 등록</th>
									</tr>
								</thead>
								<tbody>
								<c:if test="${cnt_mem > 0}">
									<c:forEach var="dto" items="${dtos_mem}">
										<tr>
											<td style="text-align: center; vertical-align: middle;">
												<span><b>[${dto.member_name}] 회원</b> - (${dto.member_id})</span>
											</td>
											<td colspan="2" style="text-align: center;">
												<input class="btn btn-secondary" style="background-color: #868e96; color: white;" type="button" value="정보 보기" onclick="window.location='hostEmployeeAddForm?id=${dto.member_id}'">
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${cnt_mem == 0}">
									<tr>
										<td colspan="2" style="text-align: center;">직원으로 등록할 수 있는 회원이 없습니다.</td>
									</tr>
								</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionScope.memStep == 4}">
		<script type="text/javascript">
			errorAlert('이용할 수 없는 메뉴입니다.\n식당 관리자 계정으로 로그인해주세요.');
		</script>
	</c:if>
	
	<!-- Footer -->
	<%@ include file="_footer.jsp"%>
</body>
</html>
