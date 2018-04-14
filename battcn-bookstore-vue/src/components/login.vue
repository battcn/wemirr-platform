<template>
  <div class="login">
    <div class="conHead">
      <span class="tit">欢迎您登录掌阅小说网</span>
      <span class="ts"><router-link to="/registered">注册掌阅小说网</router-link></span>
    </div>
    <div class="srq">
      <div class="difcon"><input v-model="name" placeholder="输入用户名" type="text"></div>
      <div class="difcon"><input v-model="pw" placeholder="输入密码" type="password"></div>
      <div class="yzm difcon"><input v-model="v_code" placeholder="输入验证码" type="password"><span class="yzt"><img
        @click="getVerificatCode()" :src="codeImg" alt="验证码"></span></div>
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
  import {mapState, mapMutations} from 'vuex'
  import axios from 'axios';
  export default {
    name: 'login',
    data() {
      return {name: '', pw: '', v_code: ''}
    },
    computed: {
      codeImg(){
        let _this =this;
        return('data:image/png;base64,' + btoa(new Uint8Array(_this.$store.state.Account.codeImg)
          .reduce((data, byte) => data + String.fromCharCode(byte), '')));
      }
    },
    methods: {
      ...mapMutations([
        'getVerificatCode','judgeVerificatCode'
      ]),
      /* 登录按钮提交 */
      login_su: function () {
        let _this = this;
        if (_this.name === '' || _this.pw === '') {
          return false;
        }
        let info = {
          name: _this.name,
          password: _this.pw
        }
      }
    },
    beforeMount() {
      this.getVerificatCode();
    }
  }

</script>
<style>

</style>
