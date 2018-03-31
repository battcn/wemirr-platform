<template>
  <div class="login">
    <div class="conHead">
      <span class="tit">欢迎您登录掌阅小说网</span>
      <span class="ts"><router-link to="/registered">注册掌阅小说网</router-link></span>
    </div>
    <div class="srq">
      <div class="difcon"><input v-model="name" placeholder="输入用户名" type="text"></div>
      <div class="difcon"><input v-model="pw" placeholder="输入密码" type="password"></div>
      <div class="yzm difcon"><input v-model="v_code" placeholder="输入验证码" type="password"><span class="yzt"><img src="" alt=""></span></div>
      <div class="difcon Btn">
        <button @click="login_su" class="tjbtn">登录</button>
      </div>
      <div class="difcon end">
        <span class="for font_12"><a href="" class="for">忘记密码？</a></span>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name:'login',
    data(){
      return {name:'',pw:'',v_code:''}
    },
    methods:{
      /* 登录按钮提交 */
      login_su:function () {
        let _this=this;
        if(_this.name===''||_this.pw===''){
          return false;
        }
        let info ={
          name:_this.name,
          password:_this.pw
        }
        console.log(info);
      }
    }
  }

</script>
<style>

</style>
