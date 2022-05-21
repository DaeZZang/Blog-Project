<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>
<div class="container">
    <form>
        <div class="m-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" placeholder="Enter Username" id="username">
        </div>
        <div class="m-3 ">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="email">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="m-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" placeholder="email">
        </div>
    </form>
    <button id="btn-save"  class="btn btn-primary mx-3">Submit</button>
</div>
<script src="/blog/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>
