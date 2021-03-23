<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<style>

@keyframes intro {
  0% {
    opacity: 0;
    transform: translateY(-10px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
.entire {
  width: 700px;
  height: 500px;
  
  display: flex;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  margin: auto;
  background: #eee;
  box-shadow: 0 0px 34px -2px black;
}
.in-entire {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}
.left-cov, .right-cov {
  height: 100%;
}
.left-cov {
  width: 35%;
  background: #3bd881;
}
.right-cov {
  width: 65%;
  background: #fff;
  overflow: auto;
}
@media (max-width: 700px) {
  .entire {
    width: auto;
    height: auto;
  }
  .left-cov, .right-cov {
    width: 100%;
  }
}
.profile {
  margin: 100px auto 50px auto;
  width: 100px;
  padding: 10px;
}
.human {
  width: 50px;
  height: 50px;
  margin: 0 auto;
  border: 9px solid #fff;
  border-radius: 50%;
  position: relative;
}
.human:before {
  content: '';
  width: 108px;
  height: 100px;
  position: absolute;
  border: 3px solid #fff;
  border-width: 9px 0 0 0;
  right: -110%;
  bottom: -112px;
  border-radius: 50%;
}
.basic {
  text-align: center;
  font-size: 26px;
  color: #fff;
}
.basic span {
  display: block;
  text-align: center;
  font-size: 22px;
}
.detail {
  margin-top: 20px;
  padding: 1px;
}
.full-detail {
  padding: 20px 30px;
  overflow: auto;
}
h3 {
  text-align: center;
  position: relative;
  padding: 1px;
}
h3:before {
  content: '';
  position: absolute;
  left: 0;
  right: 0;
  margin: 0 auto;
  bottom: -10px;
  width: 30px;
  height: 3px;
  background: #3bd881;
  
}
h4 {
  position: relative;
  padding: 1px;
  margin-top: 20px;
}
h4:before {
  content: '';
  position: absolute;
  left: 0;
  bottom: 0;
  width: 10px;
  height: 2px;
  background: #3bd881;
}
p a {
  text-decoration: none;
  color: #000;
  font-size: 16px;
}
p a:hover {
  color: #888;
}
.ball {
  display: block;
  width: 10px;
  height: 10px;
  background: #fff;
  border-radius: 50%;
  margin: 0 auto;
  opacity: 1;
  animation: jump 1s infinite alternate;
}
@keyframes jump {
  0% {
    transform: translatex(-10px);
  }
  50% {
    transform: translatex(10px);
  }
}
#btn-update {
	border-radius: 10px;
}
</style>

<form>
	<input type="hidden" id="id" value="${id}" />
	
	
	
	
<div class="entire">
    <div class="in-entire">
        <div class="left-cov">
            <div class="profile">
                <div class="human"></div>
            </div>
            <div class="basic">Blog<span>User</span><span class="ball"></span></div>
        </div>
        <div class="right-cov">
            <div class="detail">
                <h3>내 정보</h3>
            </div>
            <div class="full-detail">
                <h4>이름</h4>
                <input type="text" value="${principal.user.username}" placeholder="Username" id="username"  readonly="readonly"/> <br/>
                <h4>비밀번호</h4>
                <input type="password" value="" placeholder="Password" id="password" /> <br/>
                <h4>이메일</h4>
                <input type="email" value="${principal.user.email}" placeholder="Email" id="email" /> <br/>
                <h4>회원수정</h4>
                <button id="btn-update">회원수정</button>
            </div>
        </div>
    </div>
</div>
</form>

<script>
	$("#btn-update").on("click", (e)=> {
		e.preventDefault();
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		let id = $("#id").val();
		console.log(data);
		$.ajax({
			type: "PUT",
			url: "/user/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done((res)=>{
			console.log(res);
			if(res.statusCode === 1){
				alert("수정에 성공하였습니다.");
				location.href = "/";
			}else{
				alert("수정에 실패하였습니다.");
			}
		});
	});
</script>
<%@ include file="../layout/footer.jsp"%>