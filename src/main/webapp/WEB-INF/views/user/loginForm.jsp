<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <div class="m-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" placeholder="Enter Username" id="username">
        </div>
        <div class="m-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" placeholder="email">
        </div>
    </form>
    <button id="btn-login" class="btn btn-primary mx-3">Login</button>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>
