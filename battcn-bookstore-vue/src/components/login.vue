<template><!-- 登录页面 -->
  <div class="login">
    <div class="conHead">
      <span class="tit">欢迎您登录掌阅小说网</span>
      <span class="ts"><router-link :to="{path:'/registered',query:{redirect:$route.query.redirect}}">注册掌阅小说网</router-link></span>
    </div>
    <div class="srq">
      <div class="difcon"><input  v-model="username" placeholder="输入用户名" type="text"></div>
      <div class="difcon"><input  v-model="password" placeholder="输入密码" type="password"></div>
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
      return {username: '15386022233', password: 'z1233210', v_code: ''}
    },
    computed: {
    },
    methods: {
      ...mapMutations([
         'login'
      ]),
      /* 登录按钮提交 */
      login_su: function () {
        let _this = this;
        if (_this.username === '' || _this.password === '') {
          return false;
        }
        let data = {
          username: _this.username,
          password: _this.password
        }
        this.login({context:data,Authorization:123,$router:_this.$router,$route:_this.$route});
      }
    },
    beforeMount() {

    }
  }

</script>
<style>

</style>
