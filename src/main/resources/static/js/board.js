let index={
    init: function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },
    save:function(){
        let data = {
            title:$("#title").val(),
            content:$("#content").val()
        }
        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType:"json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((reponse)=>{
            alert("글쓰기 완료");
            location.href = "/";
        }).fail((e)=>{
            alert(JSON.stringify(e));
        });

    }
}

index.init();