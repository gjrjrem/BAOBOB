<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="number" value="1"/>
<li><p class="office_top">영화 인기 순위</p>
<c:forEach var="mov" items="${rank}">
	<li class="li">
		<em class="em">${number}</em>
		<a href="movieDetail?movie_index=${mov.movie_index}">${mov.movie_title}</a>
		<c:set var="number" value="${number + 1}"/>
	</li>
</c:forEach>
