<template>
  <div class="yourseInfo">
    <div class="con_L">
      <div class="header">
        <a href=""><img src="./../../static/img/middle.jpg" alt=""></a>
        <h4>{{infoData&&infoData.nickName?infoData.nickName:infoData&&infoData.username}}</h4>
        <span>{{infoData&&infoData.level}}</span>
      </div>
      <ul>
        <li>
          <router-link to="/yourseInfo" href="javascript:">我的主页</router-link>
        </li>
        <li>
          <router-link to="/yourseInfo" href="javascript:">我的书架</router-link>
        </li>
        <li>
          <router-link to="/yourseInfo" href="javascript:">我的书评</router-link>
        </li>
        <li><a href="javascript:">充值记录</a></li>
        <li><a href="javascript:">订阅记录</a></li>
        <li><a href="javascript:">红包记录</a></li>
      </ul>
    </div>
    <div class="con_R">
      <router-view></router-view>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import {mapState, mapMutations} from 'vuex';

  export default {
    name: 'Info',
    data() {
      return {odata:1};
    },
    computed: {
      ...mapState({
        infoData: state => state.Account.infoData,
      }),
      inaa(){
        return 3+1;
      }
    },
    methods: {
      ...mapMutations([
        'getUserinfo'/* 各个触发函数名(不区分模块名)，相同函数名则同时引入 */
      ]),
    },
    beforeMount() {
      this.getUserinfo();
    }
  }

</script>
<style>
  .yourseInfo {

  }

  .yourseInfo .con_L {
    float: left;
    background-color: #FAFAFA;
    color: #6a6a6a;
  }

  /* 头像部分 */
  .yourseInfo .con_L .header {
    height: 300px;
    width: 300px;
    overflow: hidden;
    text-align: center;
  }

  .yourseInfo .con_L .header a {
    display: block;
    height: 140px;
    width: 140px;
    border-radius: 70px;
    margin: 50px auto 25px;

  }

  .yourseInfo .con_L .header h4 {

  }

  .yourseInfo .con_L .header span {
    font-size: 12px;
    color: #bebdbd;
    margin-top: 6px;
  }

  /* 路由选项 */
  .yourseInfo .con_L ul {

  }

  .yourseInfo .con_L ul li {
    height: 40px;
    line-height: 40px;
    text-align: center;
    border-top: 1px solid #f0f0f0;
    border-bottom: 1px solid #f0f0f0;
    margin-bottom: 6px;
    cursor: pointer;
  }

  .yourseInfo .con_L ul li a {

  }

  .yourseInfo .con_R {
    min-height: 770px;
    background: #fff;
    margin-left: 300px;
    padding-top: 40px;
    border-left: 1px solid #f0f0f0;
  }

</style>
