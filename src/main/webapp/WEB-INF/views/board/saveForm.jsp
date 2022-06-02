<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>
<div class="container">
    <form>
        <div class="m-3">
            <label for="title">Title</label>
            <input type="text" name="title" class="form-control" placeholder="Enter Title" id="title">
        </div>

        <div class="form-group m-3">
            <label for="content">Content</label>
            <textarea class="form-control summernote" name="content" id="content" cols="30" rows="10"></textarea>
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary mx-3">Save</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>
