<template xmlns: xmlns:>
  <div id="app">
    <div class="header">
    <div class="headWrap">
      <nav class="nav_header">
        <h1>
          <a href="javascipt:">
            <img src="./../static/img/logo@2x.png" alt="">
          </a>
        </h1>
        <ul class="nav_ul">
          <li><router-link to="/" exact>首页</router-link></li>
          <li><router-link to="/library" href="javascipt:">书库</router-link></li>
          <li><router-link to="/ranking" href="javascipt:">排行</router-link></li>
          <li><a href="javascipt:">男频</a></li>
          <li><a href="javascipt:">女频</a></li>
          <li><a href="javascipt:">充值</a></li>
          <li><a href="javascipt:">福利</a></li>
        </ul>
        <div class="headWrap_r">
          <div class="search" :class="{show:search_show}">
            <div class="search_sel">
              <input placeholder="我要搜.. " type="text" class="search_val"/>
            </div>
            <a @click="search_show=!search_show" class="search_btn" href="javascript:">
              <i class="fa fa-search"></i>
            </a>
          </div>
          <div class="login">
            <span class="login_before" >
              <router-link to="/login" >
              <i class="fa fa-user-o"></i>
               登录
                </router-link>
            </span>
          </div>
        </div>
      </nav>
    </div>
    </div>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import {mapState, mapMutations, mapGetters} from 'vuex'

  export default {
    name: 'App',
    data() {
      return {search_show: false,}
    },
    computed: {
      /* 使用数组形式将状态变量加载进来,页面使用该变量更简便 */
    }
  }
</script>

<style>
  .content{
    width: 1100px;
    margin: 0 auto;
    overflow: hidden;
  }

  /* 导航条 */
  .headWrap {
    width: 1100px;
    margin: 0 auto;
    height: 80px;
    background: #fff;
  }

  .nav_header {
    width: 100%;
    height: 80px;
    background: #fff;
    margin-bottom: 20px;
  }

  .nav_header h1 {
    margin: 0;
    width: 170px;
    height: 50px;
    padding-top: 15px;
    float: left;
  }

  .nav_header h1 a {
    width: 170px;
    height: 50px;
  }

  .nav_header h1 a img {
    width: 100%;
    height: 100%;
  }

  .nav_header .nav_ul {
    float: left;
    margin: 24px 0 0 30px;
  }

  .nav_header .nav_ul li {
    float: left;
    margin: 0 2px;
  }

  .nav_header .nav_ul li a {
    width: 64px;
    height: 32px;
    line-height: 32px;
    text-align: center;
    font-size: 16px;
    color: #6a6a6a;
    border-radius: 4px;
    display: inline-block;
  }

  .nav_header .nav_ul li a:hover {
    color: #f26552;
  }

  .nav_header .nav_ul li a.active {
    background: #f26552;
    color: #fff;
  }

  .nav_header .nav_ul li a {
    width: 64px;
    height: 32px;
    line-height: 32px;
    text-align: center;
    font-size: 16px;
    color: #6a6a6a;
    border-radius: 4px;
  }

  .headWrap_r {
    float: right;
  }

  .headWrap_r .search {
    float: left;
    position: relative;
    width: 48px;
    height: 28px;
    margin-top: 26px;
    border-left: 1px solid #d2d2d2;
    transition: width 0.5s;
  }

  .headWrap_r .search.show {
    width: 238px;
  }

  .headWrap_r .search.show .search_sel {
    visibility: visible;
    opacity: 1;
    border-right: 1px solid #d2d2d2;
  }

  .search_sel {
    height: 28px;
    position: absolute;
    left: 0;
    padding: 0 16px;
    visibility: hidden;
    opacity: 0;
    transition: opacity 0.5s;
  }

  .search_sel input {
    width: 156px;
    height: 28px;
    line-height: 28px;
    font-size: 14px;
  }

  /* 搜索放大镜*/
  .headWrap_r .search .search_btn {
    width: 47px;
    height: 28px;
    text-align: center;
    position: absolute;
    right: 0;
    border-right: 1px solid #d2d2d2;
    background: #fff;
  }

  .headWrap_r .search .search_btn:hover i {
    color: #f26552;
  }

  .headWrap_r .search .search_btn i {
    width: 24px;
    height: 24px;
    background-position: 0 0;
    margin-top: 2px;
    font-size: 19px;
    color: #D2D2D2;
  }

  /* 登录人形图标 */
  .login {
    font-size: 14px;
    float: right;
    position: relative;
    background: #fff;
    padding-top: 30px;
  }

  .headWrap_r .login .login_before {
    width: 60px;
    height: 18px;
    padding-left: 20px;
    cursor: pointer;
    display: block;
  }

  .headWrap_r .login .login_before:hover a,
  .headWrap_r .login .login_before:hover a i {
    color: #f26552;
  }

  .headWrap_r .login a {
    float: left;
    color: #9e9e9e;
  }

  .headWrap_r .login a i {
    font-size: 17px;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #9e9e9e;
  }
  .header > i {
    line-height: 35px;
    font-size: 25px;
    cursor: pointer;
    margin-left: 10px;
    color: #fff;
  }
</style>
