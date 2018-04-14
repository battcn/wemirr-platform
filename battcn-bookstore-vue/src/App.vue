<template xmlns: xmlns:>
  <div id="app">
    <div class="header">
      <div class="headWrap">
        <h1>
          <a href="javascipt:">
            <img src="./../static/img/logo@2x.png" alt="">
          </a>
        </h1>
        <nav class="nav_header">
          <ul class="nav_ul">
            <li>
              <router-link to="/" exact>首页</router-link>
            </li>
            <li>
              <router-link to="/library" href="javascipt:">书库</router-link>
            </li>
            <li>
              <router-link to="/ranking" href="javascipt:">排行</router-link>
            </li>
            <li><a href="javascipt:">男频</a></li>
            <li><a href="javascipt:">女频</a></li>
            <li><a href="javascipt:">充值</a></li>
            <li><a href="javascipt:">福利</a></li>
          </ul>
          <div class="headWrap_r">
            <div class="search" :class="{show:search_show}">
              <div class="search_sel">
                <input v-model="keyWords" placeholder="我要搜.. " type="text" class="search_val"/>
              </div>
              <a @click="keyWords==''?search_show=!search_show:''" class="search_btn" href="javascript:">
                <s></s>
              </a>
            </div>
            <div class="login">
            <span v-if="infoData==null" class="login_before">
              <router-link to="/login">
              <i class="fa fa-user-o"></i>
               登录
                </router-link>
            </span>
              <div v-if="infoData!=null" class="login_after"><!--已经登录状态下显示头像信息 -->
                <span class="yh">
                  <img src="./../static/img/middle.jpg" alt="">
                  <a href="">Rock1313</a>
                </span>
              </div>
            </div>
          </div>
        </nav>
        <nav class="nav_header_menu">
          <div class="headWrap_r">
            <div class="search" :class="{show:search_show}">
              <div class="search_sel">
                <input v-model="keyWords" placeholder="我要搜.. " type="text" class="search_val"/>
              </div>
              <a @click="keyWords==''?search_show=!search_show:''" class="search_btn" href="javascript:">
                <s></s>
              </a>
            </div>
            <div class="login">
            <span v-if="infoData==null" class="login_before">
              <router-link to="/login">
              <i class="fa fa-user-o"></i>
               登录
                </router-link>
            </span>
              <div v-if="infoData!=null" class="login_after"><!--已经登录状态下显示头像信息 -->
                <span class="yh">
                  <img src="./../static/img/middle.jpg" alt="">
                  <a href="">{{infoData}}</a>
                </span>
              </div>
            </div>
            <a @click="menu_show=!menu_show" class="menu_btn" href="javascript:"><span class="fa fa-bars"></span></a>
          </div>
          <ul :class="{show:menu_show}" class="nav_ul">
            <li>
              <div class="nav_ul_input">
                <input v-model="keyWords" placeholder="我要搜.. " type="text" />
              <s></s>
              </div>
            </li>
            <li>
              <router-link to="/" exact>首页</router-link>
            </li>
            <li>
              <router-link to="/library" href="javascipt:">书库</router-link>
            </li>
            <li>
              <router-link to="/ranking" href="javascipt:">排行</router-link>
            </li>
            <li><a href="javascipt:">男频</a></li>
            <li><a href="javascipt:">女频</a></li>
            <li><a href="javascipt:">充值</a></li>
            <li><a href="javascipt:">福利</a></li>
          </ul>
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
      return {search_show: false, keyWords: '', menu_show: false}
    },
    computed: {
      /* 使用数组形式将状态变量加载进来,页面使用该变量更简便 */
      ...mapState({
        infoData: state => state.Account.infoData,
      })
    },
    methods:{
      ...mapMutations([
        'getUserinfo'/* 各个触发函数名(不区分模块名)，相同函数名则同时引入 */
      ]),
    },
    beforeMount() {
      this.getUserinfo();
    }
  }
</script>
<style scoped>
  /* 导航头 */
  .header {
    width: 100%;
    height: 80px;
    background: #fff;
    margin-bottom: 20px;
  }

  /* 导航条 */
  .headWrap {
    width: 1100px;
    margin: 0 auto;
    height: 80px;
    background: #fff;
  }

  .headWrap h1 {
    margin: 0;
    width: 170px;
    height: 50px;
    padding-top: 15px;
    float: left;
  }

  .nav_header {
    overflow: hidden;
    height: 80px;
    background: #fff;
    margin-bottom: 20px;
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
    float: right;overflow: hidden;
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

  .headWrap_r .search .search_btn s {
    width: 24px;
    height: 24px;
    background-position: 0 0;
    margin-top: 2px;
  }

  .headWrap_r .search .search_btn:hover s,
  .headWrap_r .search.show .search_btn s {
    background-position: -30px 0;
  }

  /* 登录人形图标 */
  .headWrap_r .login {
    font-size: 14px;
    float: right;
    position: relative;
    background: #fff;
    padding-top: 30px;
    margin-bottom: 0;
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
  /* 登录状态下 */
  .headWrap_r .login .login_after{
    width: 146px;
    margin: 0 0 0 10px;
    position: relative;
  }
  .headWrap_r .login .login_after .yh{
    padding: 0 10px 0 15px;
    position: relative;
    z-index: 100;
  }
  .headWrap_r .login .login_after img{
    float: left;
    width: 22px;
    height: 22px;
    border-radius: 50%;
    margin-right: 10px;
  }
  .headWrap_r .login .login_after a{
    font-size: 12px;
    color: #6a6a6a;
    max-width: 84px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .headWrap_r .login .login_after .yh a{
    margin: 4px 0 0 0;
  }

  .headWrap_r .login a {
    float: left;
    color: #9e9e9e;
  }

  .headWrap_r .login a i {
    font-size: 17px;
  }

  /* 小屏下菜单样式 */
  .nav_header_menu {
    position: relative;
    display: none;
  }

  .nav_header_menu .headWrap_r {

  }

  .nav_header_menu .headWrap_r .login {
    float: left;
  }

  .nav_header_menu .headWrap_r .menu_btn {
    font-size: 30px;
    display: block;
    margin-right: 8px;
    margin-top: 23px;
  }

  .nav_header_menu .nav_ul {
    transition: height .1s ease .4s,opacity,margin .3s ease  ;
    position: absolute;
    margin-top: 12px;
    top: 80px;
    right: 0;overflow: hidden;
height:0;
    background: #fff;
    z-index: 9;
    opacity: 0;width: 100%;
  }

  .nav_header_menu .nav_ul.show {
    margin-top: 0;
    opacity: .9;height:auto;
  }

  .nav_header_menu .nav_ul li {
    border-bottom: 1px solid #d2d2d2;
  }

  .nav_header_menu .nav_ul li a {
    display: block;
    position: relative;
    padding: 15px 20px;
    font-size: 16px;
    line-height: 20px;
    font-weight: 300;
    outline: none;
  }
  /* 小屏显示缩放栏中的搜索框 */
  .nav_header_menu .nav_ul li .nav_ul_input{display: none;
text-align: center;    padding: 10px 0;width:88%;margin:0 auto;position: relative;
  }
  /* 输入框 */

  .nav_header_menu .nav_ul li .nav_ul_input input{
    height: 30px;width: 100%;
    border: 1px solid #d2d2d2;
    border-radius: 5px;
    padding: 0 35px 0 10px;    box-sizing: border-box;
  }
  /* 搜索图标 */
  .nav_header_menu .nav_ul li .nav_ul_input s{
    width: 24px;
    height: 24px;position: absolute;cursor: pointer;
    background-position: 0 0;right: 7px;
    top: 13px;
  }
  .nav_header_menu .nav_ul li .nav_ul_input s:hover{
    background-position: -30px 0;
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

  /* 路由渲染范围 */
  .content {
    width: 1100px;
    margin: 0 auto;
    overflow: hidden;
  }

  /*响应式样式部分 */
  @media screen and (max-width: 1100px)  {
    .headWrap .nav_header {
      display: none;
    }

    /* 头部导航条样式 */
    .headWrap {
      width: 100%;
    }

    .headWrap h1 {
      margin-left: 15px;
    }

    .headWrap .nav_header_menu {
      display: block;
    }
    /* 组件容器响应式 */
    .content{
      width: 100%;
    }
  }

  @media screen and (max-width: 1100px) and (min-width: 800px) {
    .nav_header_menu .nav_ul {
      max-width: 163px;
    }
  }
  /* 该分辨率下，隐藏上方搜索栏，显示缩放中的搜索框 */
  @media screen and (max-width: 570px){
    .headWrap_r .search{
      display: none;
    }
    .nav_header_menu .nav_ul li .nav_ul_input{
      display: block;
    }
    .headWrap_r .login .login_after{
      width:auto;
    }
  }
</style>
