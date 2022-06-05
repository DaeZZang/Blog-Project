<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <button id="btn-update" class="btn btn-warning">수정</button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
        <div class="form-group m-3">
            <h3>${board.title}</h3>
        </div>
        <div class="form-group m-3">
            <div>${board.content}</div>
        </div>
</div>

<%@include file="../layout/footer.jsp"%>