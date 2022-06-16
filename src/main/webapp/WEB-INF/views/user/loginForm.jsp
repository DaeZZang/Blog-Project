<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="m-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
        </div>
        <div class="m-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="email">
        </div>

        <button id="btn-login" class="btn btn-primary mx-3">Login</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=	35c25bdac670f486e59ab066a2c4811a&redirect_uri=
http://localhost:8000/auth/kakao/callback&response_type=code"><image src="/image/kakao_login_button.png"></image></a>
    </form>
</div>

<%@include file="../layout/footer.jsp" %>
