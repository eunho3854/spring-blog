<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');


::selection{
  background: #ff80bf;

}
.container{
  background: #fff;
  max-width: 350px;
  width: 100%;
  padding: 25px 30px;
  border-radius: 5px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.15);
}
.container form .title{
  font-size: 30px;
  font-weight: 600;
  margin: 20px 0 10px 0;
  position: relative;
}
.container form .title:before{
  content: '';
  position: absolute;
  height: 4px;
  width: 33px;
  left: 0px;
  bottom: 3px;
  border-radius: 5px;
  
}
.container form .input-box{
  width: 100%;
  height: 45px;
  margin-top: 25px;
  position: relative;
}
.container form .input-box input{
  width: 100%;
  height: 100%;
  outline: none;
  font-size: 16px;
  border: none;
}
.container form .underline::before{
  content: '';
  position: absolute;
  height: 2px;
  width: 100%;
  background: #ccc;
  left: 0;
  bottom: 0;
}
.container form .underline::after{
  content: '';
  position: absolute;
  height: 2px;
  width: 100%;
  background: linear-gradient(to right, #8C8C8C 0%, #8C8C8C 100%);
  left: 0;
  bottom: 0;
  transform: scaleX(0);
  transform-origin: left;
  transition: all 0.3s ease;
}
.container form .input-box input:focus ~ .underline::after,
.container form .input-box input:valid ~ .underline::after{
  transform: scaleX(1);
  transform-origin: left;
}
.container form .button{
  margin: 40px 0 20px 0;
}
.container .input-box input[type="submit"]{
  background: linear-gradient(to right, #8C8C8C 0%, #8C8C8C 100%);
  font-size: 17px;
  color: #fff;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}
.container .input-box input[type="submit"]:hover{
  letter-spacing: 1px;
  background: linear-gradient(to left, #8C8C8C 0%, #8C8C8C 100%);
}
.container .option{
  font-size: 14px;
  text-align: center;
}
.container .facebook a,
.container .kakaozz a,
.container .naverzz a,
.container .twitter a{
  display: block;
  height: 45px;
  width: 100%;
  font-size: 15px;
  text-decoration: none;
  padding-left: 20px;
  line-height: 45px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.container .facebook i,
.container .twitter i{
  padding-right: 12px;
  font-size: 20px;
}
.container .twitter a{
  background: linear-gradient(to right,  #00acee 0%, #1abeff 100%);
  margin: 20px 0 15px 0;
}
.container .twitter a:hover{
  background: linear-gradient(to left,  #00acee 0%, #1abeff 100%);
  margin: 20px 0 15px 0;
}
.container .facebook a{
  background: linear-gradient( to right,  #3b5998 0%, #476bb8 100%);
  margin: 20px 0 15px 0;
}
.container .facebook a:hover{
  background: linear-gradient( to left,  #3b5998 0%, #476bb8 100%);
  margin: 20px 0 15px 0;
}

.container .naverzz a{
  background: linear-gradient( to right,  #2F9D27 0%, #19CE60 100%);
  margin: 20px 0 15px 0;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.container .naverzz a:hover{
  background: linear-gradient( to left,  #2F9D27 0%, #19CE60 100%);
  margin: 20px 0 15px 0;
}
.container .kakaozz a{
  background: linear-gradient( to right,  #F7D500 0%, #F7D500 100%);
  margin: 20px 0 15px 0;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.container .kakaozz a:hover{
  background: linear-gradient( to left,  #F7D500 0%, #E5D85C 100%);
  margin: 20px 0 15px 0;
}
.naver {
	margin-right: 10px;
	font-size: 25px;
}
.naverxx {
	margin-left: 20px;
}
.kakao {
	margin-right: 10px;
	font-size: 25px;
}
.kakaoxx {
	margin-left: 20px;
}
.join {
	margin-left: 30%;
}
</style>


  <body>
    <div class="container">
      <form action="/login" method="POST">
        <div class="title">로그인</div>
        <div class="input-box underline">
          <input type="text" name="username" placeholder="Enter Your Username" required>
          <div class="underline"></div>
        </div>
        <div class="input-box">
          <input type="password" name="password" placeholder="Enter Your Password" required>
          <div class="underline"></div>
        </div>
        <div class="input-box button">
          <input type="submit" name="" value="로그인하기">
        </div>
      </form>
        <div class="option">or Connect With Social Media</div>
        <div class="twitter">
          <a href="/oauth2/authorization/google"><i class="fab fa-google"></i>구글 로그인</a>
        </div>
        <div class="facebook">
          <a a href="/oauth2/authorization/facebook"><i class="fab fa-facebook-f"></i>페이스북 로그인</a>
        </div>
        <div class="naverzz">
          <a href="/oauth2/authorization/naver" class="naverxx"><span class="naver">N</span>네이버 로그인</a>
        </div>
        <div class="kakaozz">
          <a href="/oauth2/authorization/kakao" class="kakaoxx"><span class="kakao">K</span>카카오 로그인</a>
        </div>
        <div class="option">회원이 아니신가요?<a href="/joinForm" class="join">회원가입</a></div> 
    </div>





<%@ include file="../layout/footer.jsp"%>