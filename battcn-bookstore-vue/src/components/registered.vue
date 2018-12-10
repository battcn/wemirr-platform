<template><!-- 注册页面 -->
  <div class=" login register"><!-- 样式复用 -->
    <div class="conHead">
      <span class="tit">欢迎您注册掌阅小说网</span>
      <span class="ts">已有账号？请 <router-link to="/login">登录</router-link></span>
    </div>
    <div class="srq">
      <div class="difcon">
        <input  :class="{input_meets:meetsName}"  v-model="name" type="text" placeholder="请输入手机号">
        <p class="ts font_12">
          <i>*</i>
          使用手机号登录
        </p>
      </div>
      <div class="difcon">
        <input  v-model="pw" :class="{input_meets:meetsPw}"  type="text" placeholder="请输入密码">
        <p class="ts font_12">
          <i>*</i>
          密码由6-32个英文字母（区分大小写）、数字及下划线组成。
        </p>
      </div>
      <div class="difcon">
        <input  :class="{input_meets:meetsConf_pw}"  v-model="conf_pw" type="text" placeholder="请输入确认密码">
        <p class="ts font_12">
          <i>*</i>
        </p>
      </div>
      <div class="yzm difcon">
        <input type="text" v-model="Vecode" placeholder="输入验证码">
        <span class="yzt"><img @click="getVerificatCode()" :src="codeImg" alt="验证码" /></span>
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
  import {verificat} from './../util/fromVerificat';
  import {mapState, mapMutations} from 'vuex';
  import axios from 'axios';

  export default {
    name: 'register',
    data() {
      return {name: '15386025588',meetsName:false, pw: 'z1233210',meetsPw:false, conf_pw: 'z1233210',meetsConf_pw:false, email: '',meetsEmail:false, Vecode: '',token: ''}
    },
    computed: {
      codeImg() {
        let _this = this;
        return ('data:image/png;base64,' + btoa(new Uint8Array(_this.$store.state.Account.codeImg)
          .reduce((data, byte) => data + String.fromCharCode(byte), '')));
      },
    },
    methods: {
      ...mapMutations([
         'getVerificatCode','judgeVerificatCode'
      ]),
      isMeetsName:function () {/* 验证手机号 */
        verificat.username(this.name)?this.meetsName=false:this.meetsName=true;
      },
      isMeetsPw:function () {/* 验证密码 */
        verificat.password(this.pw)?this.meetsPw=false:this.meetsPw=true;
      },
      isMeetsConf_pw:function () { /* 验证确认密码一致 */
        verificat.password(this.conf_pw)&&this.pw===this.conf_pw?this.meetsConf_pw=false:this.meetsConf_pw=true;
      },
      register_su: function () {
        if (verificat.trim(this.Vecode)&&verificat.username(this.name) && verificat.password(this.pw) && verificat.same('密码', this.pw, this.conf_pw)) {
          let account = {
            "username":this.name,
            "password":this.pw
          };
          this.judgeVerificatCode({code: this.Vecode,context:account,$router:this.$router,$route:this.$route},);
          /* 验证码是否正确 */
        }
      },
    },
    beforeMount() {
      this.getVerificatCode();
    }
  }

</script>
<style>
  input.input_meets {
    border-color: #A41E22!important;
  }

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
  .register .srq .difcon .yyd a {
    color: #f26552;
  }

</style>
