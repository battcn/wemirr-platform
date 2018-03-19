<template xmlns: xmlns:>
  <div id="app">
    <div class="header">
      <i v-show="fullWidth<480" @click="show=!show" class="fa fa-navicon"></i>
    </div>
    <transition name="fade">
      <div v-if="show|fullWidth>480" class="left-side">
        <div class="features"> <!-- 功能区 -->
          <ul>
          <!--  <li>
              <router-link to="/">
                <i class="fa  fa-book" aria-hidden="true"></i>我的阅读
              </router-link>
            </li>-->
            <li>
              <router-link   to="/" exact>
                <i class="fa  fa-home" aria-hidden="true"></i>首页
              </router-link>
            </li>
            <li>
              <router-link   to="/ShoppingCart">
                <i class="fa   fa-shopping-cart"  aria-hidden="true"></i>购物车
              </router-link>
            </li>
            <li>
              <router-link  to="/Collection">
                <i class="fa  fa-star" aria-hidden="true"></i>收藏夹
              </router-link>
            </li>
           <!-- <li>
              <router-link to="/"><i class="fa  fa-list-ul" aria-hidden="true"></i>愿望清单</router-link>
            </li>
            <li>
              <router-link to="/"><i class="fa  fa-clock-o" aria-hidden="true"></i>历史记录</router-link>
            </li>-->
          </ul>
        </div>
        <div class="recommend"><!-- 推荐部分 -->
          <ul>
            <li>
              <router-link to="/"><i style="color:#E64963;" class="fa  fa-circle" aria-hidden="true"></i>必读推荐
              </router-link>
            </li>
            <li>
              <router-link to="/"><i style="color:#F9A501;" class="fa  fa-circle" aria-hidden="true"></i>我最喜欢
              </router-link>
            </li>
            <li>
              <router-link to="/"><i style="color:#04BADE;" class="fa  fa-circle" aria-hidden="true"></i>值得一读
              </router-link>
            </li>
            <li>
              <router-link to="/"><i style="color:#7773C8;" class="fa  fa-circle" aria-hidden="true"></i>还未阅读
              </router-link>
            </li>
          </ul>
        </div>
        <div class="recording"><!-- 历史记录 -->
        </div>
      </div>
    </transition>
    <div id="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import {mapState, mapMutations, mapGetters} from 'vuex'
  export default {
    name: 'App',
    data() {
      /* options:'home', */
      return {fullWidth: document.documentElement.clientWidth, show: false, childrenOptions: 'all'}
    },
    computed: {
      /* 使用数组形式将状态变量加载进来,页面使用该变量更简便 */
    },
    mounted() {
      const that = this;
      window.onresize = () => {
        return (() => {
          window.fullWidth = document.documentElement.clientWidth;
          that.fullWidth = window.fullWidth;
          this.fullWidth > 480 && this.show === true ? this.show = false : '';
        })()
      }
    },
    watch: {
      fullWidth(val) {
        if (!this.timer) {
          this.fullWidth = val;
          this.timer = true;
          let that = this;
          setTimeout(function () {
            that.timer = false;
          }, 400)
        }
      }
    }
  }
</script>

<style>
  ul {
    margin: 0;
    padding: 0;
  }

  li {
    list-style: none;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  /* 左侧路由动画 */
  .fade-enter-active, .fade-leave-active { /* fade为transition标签上的name */
    transition: all .5s; /* 在此状态下运用css动画更改opacity属性 */
  }

  .fade-enter, .fade-leave-active {
    opacity: 0;
  }

  /* 头部 */
  .header {
    background-color: #2F3649;
    height: 35px;
    text-align: left;
  }

  .header > i {
    line-height: 35px;
    font-size: 25px;
    cursor: pointer;
    margin-left: 10px;
    color: #fff;
  }

  /* 左侧功能区 */
  .left-side {
    z-index: 99;
    height: 100%;
    width: 250px;
    overflow: hidden;
    position: fixed;
    top: 35px;
    bottom: 0;
    left: 0;
    background-color: #1F2638;
    color: #69728C;
    text-align: left;
  }

  .left-side > div ~ div {
    border-top: 5px solid #191F2B;
  }

  .left-side li > a {
    padding: 0 20px;
  }

  .left-side li > a > i {
    padding-right: 10px;
  }

  /* 功能区 */
  .left-side .features {

  }

  .left-side .features li > a {
    color: #69728C;
    text-decoration: none;
    display: block;
    height: 48px;
    line-height: 48px;
  }

  .left-side .features li > a:hover,
  .left-side .features li > a.active {
    color: #fff;
    background-color: #16A4FA;
  }

  /* 推荐部分 */
  .left-side .recommend {

  }

  .left-side .recommend li {

  }

  .left-side .recommend li > a {
    display: block;
    height: 48px;
    line-height: 48px;
    cursor: pointer;
    color: #69728C;
    text-decoration: none;
  }

  .left-side .recommend li > a:hover {
    color: #fff;
    background-color: #16A4FA;
  }

  /* 历史记录  */
  .left-side .recording {

  }

  .left-side .recording li {
    height: 48px;
    line-height: 48px;
    cursor: pointer;
  }

  .left-side .recording li:hover {
    color: #fff;
    background-color: #16A4FA;
  }

  #content {
    margin-left: 250px;
  }

  /* 响应式样式 */
  /* 超小屏幕（手机，大于等于 630px） */
  @media (max-width: 630px) {
    /* 左侧功能区  */
    .left-side {
      width: 150px;
      font-size: 13px;
      font-weight: bold;
    }

    #content {
      margin-left: 150px;
    }

  }

  @media (max-width: 480px) {
    /* 此分辨率下隐藏左侧功能栏 */
    .left-side {

    }

    #content {
      margin-left: 0;
    }
  }
</style>
