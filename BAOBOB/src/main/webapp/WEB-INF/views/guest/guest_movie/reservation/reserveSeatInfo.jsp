<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="/resources/setting.jsp"%>
<!-- CSS,JavaScript 참조 -->
<%@ include file="/WEB-INF/views/guest/common/head.jsp" %>
<br>
<!-- 좌석도  -->
<div style="font-size:13px; font-weight:bold;">
	일시:&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${schedule.schedule_startTime}" pattern="YYYY.MM.dd(E) HH:mm" />~
						<fmt:formatDate value="${schedule.schedule_endTime}" pattern="HH:mm" /><br><br>
	상영관:&nbsp;&nbsp;&nbsp;${schedule.theater_index}관
</div>