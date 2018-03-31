<template>
  <div class=" login register"><!-- 样式复用 -->
    <div class="conHead">
      <span class="tit">欢迎您注册掌阅小说网</span>
      <span class="ts">已有账号？请 <router-link to="/login">登录</router-link></span>
    </div>
    <div class="srq">
      <div class="difcon">
        <input v-model="name" type="text" placeholder="请输入用户名">
        <p class="ts font_12">
          <i>*</i>
          可用长度为2到15位的中文，英文，数字,注意:每个中文字符等于3个字符长
        </p>
      </div>
      <div class="difcon">
        <input v-model="pw" type="text" placeholder="请输入密码">
        <p class="ts font_12">
          <i>*</i>
          密码由6-15个英文字母（区分大小写）、数字及下划线组成。
        </p>
      </div>
      <div class="difcon">
        <input v-model="conf_pw" type="text" placeholder="请输入确认密码">
        <p class="ts font_12">
          <i>*</i>
        </p>
      </div>
      <div class="difcon">
        <input v-model="email" type="text" placeholder="请输入邮箱地址">
        <p class="ts font_12">
          <i>*</i>
        </p>
      </div>
      <div class="yzm difcon">
        <input type="text" placeholder="输入验证码">
        <span class="yzt"></span>
        <p class="ts">
          <i>*</i>
        </p>
      </div>
      <div class="difcon">
        <span class="dxBtn"><b></b></span>
        <span class="yyd font_12">点击注册即表明您已阅读并同意 <a href="javascript:">《用户协议》</a></span>
      </div>
      <div class="difcon Btn">
        <button @click="register_su" class="tjbtn">免费注册</button>
      </div>
    </div>
  </div>

</template>
<script>
  export default{
    name:'register',
    data(){
      return {name:'',pw:'',conf_pw:'',email:''}
    },
    methods:{
      register_su:function () {
        let _this=this;
        if(_this.name===''||/^[\u4e00-\u9fa5]{2,15}$/u.test(_this.name)){
          console.log("用户名格式错误");
          return false;
        }
        if(_this.pw===''||/^[\w\_]{6,15}$/u.test(_this.pw)){
          console.log("密码格式错误");
          return false;
        }
        if(_this.pw!==_this.conf_pw){
          console.log("两次密码不一致");
          return false;
        }
        if(!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(this.email)){
          console.log("邮箱格式不正确");
          return false;
        }
      }
    }
  }

</script>
<style>
  .register a {
    overflow: auto;
    display: inline;
  }

  /* 注册表单部分 */
  .register .srq {
    margin: 0 0 0 274px;
    width: auto;
  }

  .register .srq input {
    width: 316px;
    height: 42px;
    border: 1px solid #d2d2d2;
    padding: 0 14px;
    float: left;
  }

  /* 注册要求提示文字 */
  .register .srq p {
    width: 230px;
    padding: 4px 0 0 10px;
    float: left;
    color: #ccc;
  }

  .register .srq p i {
    color: #ccc;
  }
  /* 验证码样式 */
  .register .srq .yzm.difcon .yzt {
    float: left;
  }
  /* 用户协议 */
  .register .srq  .difcon .yyd a{
    color: #f26552;
  }

</style>
