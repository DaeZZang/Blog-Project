let index={
    init: function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        })
    },
    save:function(){
        //alert('user의save함수가 호출됨');
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        }
        //ajax호출시 default가 비동기 호출
        //ajax통신을 통해 3개의 데이터를 json으로 변경하여 insert 요청
        //ajax가 통신에 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        $.ajax({
            type:"POST",
            url:"/auth/joinProc",
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType:"json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((reponse)=>{
            if(reponse.status==500){
                alert("회원가입에 실패하셨습니다");
            }else{
                alert("회원가입 완료");
                location.href = "/";
            }
        }).fail((e)=>{
            alert(JSON.stringify(e));
        });//통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert요청을 할 것
    },
    update:function (){
        let data = {
            id:$("#id").val(),
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        }
        $.ajax({
            type:"PUT",
            url:"/user",
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
            dataType:"json" //서버에게 요청하여 응답 받은 데이터의 종류는 기본적으로 응답은 버퍼, 즉 string으로 온다 근데 생긴게 json이면 javaScript 객체로 바꿔준다
        }).done((reponse)=>{
            alert("회원수정 완료");
            location.href = "/";
        }).fail((e)=>{
            alert(JSON.stringify(e));
        });
    }
}

index.init();