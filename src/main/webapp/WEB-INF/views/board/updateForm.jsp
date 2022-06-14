<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.id}">
        <div class="m-3">
            <input value="${board.title}" type="text" name="title" class="form-control" placeholder="Enter Title" id="title">
        </div>

        <div class="form-group m-3">
            <textarea class="form-control summernote" name="content" id="content" cols="30" rows="10">${board.content}</textarea>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary mx-3">글 수정 완료</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>
