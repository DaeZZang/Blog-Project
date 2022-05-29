<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <form action="#" method="post">
        <div class="m-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
        </div>
        <div class="m-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="email">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input name="remember" type="checkbox" class="form-check-input"> Remember me
            </label>
        </div>
        <button id="btn-login" class="btn btn-primary mx-3">Login</button>
    </form>
</div>

<%@include file="../layout/footer.jsp" %>
