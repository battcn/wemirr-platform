<template xmlns: xmlns:>
  <div id="app">
    <div class="left-side">
      <div class="features"> <!-- 功能区 -->
        <ul>
          <li><router-link to="/"><i class="fa  fa-book" aria-hidden="true"></i>我的阅读</router-link></li>
          <li @click="options='home'"><router-link  :class="{active:options =='home'}" to="/"><i class="fa  fa-home" aria-hidden="true"></i>首页</router-link></li>
          <li @click="options='shoppingCart'"><router-link  :class="{active:options =='shoppingCart'}" to="/ShoppingCart"><i class="fa  fa-shopping-cart" aria-hidden="true"></i>购物车</router-link></li>
          <li @click="options='collection'"><router-link  :class="{active:options =='collection'}" to="/Collection"><i class="fa  fa-star" aria-hidden="true"></i>收藏夹</router-link></li>
          <li><router-link to="/"><i class="fa  fa-list-ul" aria-hidden="true"></i>愿望清单</router-link></li>
          <li><router-link to="/"><i class="fa  fa-clock-o" aria-hidden="true"></i>历史记录</router-link></li>
        </ul>
      </div>
      <div  class="recommend"><!-- 推荐部分 -->
        <ul>
          <li><router-link to="/"><i style="color:#E64963;" class="fa  fa-circle" aria-hidden="true"></i>必读推荐</router-link></li>
          <li><router-link to="/"><i style="color:#F9A501;" class="fa  fa-circle" aria-hidden="true"></i>我最喜欢</router-link></li>
          <li><router-link to="/"><i style="color:#04BADE;" class="fa  fa-circle" aria-hidden="true"></i>值得一读</router-link></li>
          <li><router-link to="/"><i style="color:#7773C8;" class="fa  fa-circle" aria-hidden="true"></i>还未阅读</router-link></li>
        </ul>
      </div>
      <div  class="recording"><!-- 历史记录 -->
      </div>
    </div>
    <div id="content">
    <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return {options:'home',childrenOptions:'all'}
  }
}
</script>

<style>
  ul{margin:0;padding: 0;}
  li{list-style: none;}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
  /* 左侧功能区 */
  .left-side{
    height:100%;
    width: 250px;
    overflow:hidden ;
    position: fixed;
    top:0;
    bottom: 0;
    left:0;
    background-color: #1F2638;
    color:#69728C;
    text-align: left;
  }
  .left-side > div ~ div{
    border-top:5px solid #191F2B;
  }
  .left-side li>a{
    padding: 0 20px;
  }
  .left-side li>a >i{
    padding-right: 10px;
  }
  /* 功能区 */
  .left-side .features{

  }
  .left-side .features li>a{
    color:#69728C;
    text-decoration: none;
    display: block;
    height: 48px;
    line-height: 48px;
  }
  .left-side .features li>a:hover,
  .left-side .features li>a.active {
    color:#fff;
    background-color: #16A4FA;
  }
 /* 推荐部分 */
  .left-side .recommend{

  }
  .left-side .recommend li{

  }
  .left-side .recommend li>a{
    display: block;
    height: 48px;
    line-height: 48px;
    cursor:pointer;
    color:#69728C;
    text-decoration: none;
  }
  .left-side .recommend li>a:hover{
    color:#fff;background-color: #16A4FA;
  }
  /* 历史记录  */
  .left-side .recording{

  }
  .left-side .recording li{
    height: 48px;
    line-height: 48px;
    cursor:pointer;
  }
  .left-side .recording li:hover{
   color:#fff;background-color: #16A4FA;
  }
#content{
  margin-left: 250px;
}
</style>
