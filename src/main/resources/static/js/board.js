let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType: "json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((response) => {
            alert("글쓰기 완료");
            location.href = "/";
        }).fail((e) => {
            alert(JSON.stringify(e));
        });
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            data: "json"
        }).done((response) => {
            alert("삭제 완료");
            location.href = "/";
        }).fail((e) => {
            alert(JSON.stringify(e));
        });
    },
    update: function () {
        let id = $('#id').val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType: "json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((response) => {
            alert("글수정 완료");
            location.href = "/";
        }).fail((e) => {
            alert(JSON.stringify(e));
        })
    },
    replySave: function () {
        let data = {
            userId: $("#userId").val(),
            content: $("#reply-content").val(),
            boardId: $('#boardId').val()
        };

        console.log(data);
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType: "json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((response) => {
            alert("댓글쓰기 완료");
            location.href = `/board/${data.boardId}`;
        }).fail((e) => {
            alert(JSON.stringify(e));
        });
    },
    replyDelete: function (boardId,replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            contentType: "application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType: "json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((response) => {
            alert("댓글삭제 완료");
            location.href = `/board/${boardId}`;
        }).fail((e) => {
            alert(JSON.stringify(e));
        });
    }
}

index.init();