<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="layout/header.jsp" %>
<div class="container-fluid mt-3">
    <c:forEach var="board" items="${boards.content}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${board.title}</h5>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
</div>
<%--<div class="container-fluid row justify-content-center align-items-center">--%>
<div class="container-fluid  row justify-content-center align-items-center">
    <nav aria-label="page">
        <ul class="pagination row justify-content-center">
            <c:choose>
                <c:when test="${boards.first}">
                    <li class="page-item disabled">
                        <a class="page-link" href="?page=${boards.number-1}">Previous</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a href="?page=${boards.number-1}" class="page-link">Previous</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="1" end="${boards.totalPages}">
                <li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
            </c:forEach>
            <c:choose>
                <c:when test="${boards.last}">
                    <li class="page-item disabled">
                        <a class="page-link" href="?page=${boards.number+1}">Next</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a href="?page=${boards.number+1}" class="page-link">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>
<%@include file="layout/footer.jsp" %>





