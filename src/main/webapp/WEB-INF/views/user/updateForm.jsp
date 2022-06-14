<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>
<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="m-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" placeholder="Enter Username" id="username" value="${principal.user.username}" readonly>
        </div>
        <div class="m-3 ">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="email" value="${principal.user.email}">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="m-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" placeholder="password">
        </div>
    </form>
    <button id="btn-update"  class="btn btn-primary mx-3">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>
