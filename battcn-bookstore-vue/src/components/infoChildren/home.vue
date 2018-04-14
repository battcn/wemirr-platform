<template>
  <div class="info-home">
    <div class="gr">
      <div class="tit">
        <h2><s></s>个人信息</h2>
        <a href=""><s></s>修改信息</a>
      </div>
      <div class="con">
        <p><span>ID：<i>{{infoData&&infoData.memberNo}}</i></span>
          <span class="lt">上次登录：15分钟前</span>
        </p>
        <p><span>用户名：<i>{{infoData&&infoData.nickName}}</i></span></p>
        <p><span>VIP等级：<i>非VIP </i></span>
          <a href="javascript:"> 充值 </a>
          <a href="javascript:"> VIP等级划分 </a>
        </p>
        <p><span>会员等级：<i>{{infoData&&infoData.level}}</i></span>
          <span>升级为：黑铁会员还需要81经验值</span>
          <a href="">会员等级划分</a>
        </p>
        <p>
          <span>阅读积分：<i>{{infoData&&infoData.point}}</i></span>
          <a href="">充值</a>
        </p>
      </div>
    </div>
    <div class="shj">
      <div class="tit">
        <h2><s></s>我的书架</h2>
        <a href="">全部藏书</a>
      </div>
      <div class="con">
        <p>我还没有收藏！</p>
      </div>
    </div>
    <div class="shp">
      <div class="tit">
        <h2><s></s>我的书评</h2>
        <a href="">全部书评</a>
      </div>
      <div class="con">
        <p>我还没有评论！</p>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import {mapState, mapMutations} from 'vuex';
  export default {
    name:'info-home',
    data(){
      /* level：用户等级   point： 账户积分*/
      return {}
    },
    computed:mapState({
      infoData:state=>state.Account.infoData
    })
  }
</script>
<style>
  .info-home{

  }
  .info-home s{
    background: url(./../../../static/img/pIcon.png) no-repeat;
    float: left;
  }
  /* 标题：通用 */
  .info-home  .tit{
    height: 34px;
    border-bottom: 1px solid #f0f0f0;
    padding: 0 50px 0 20px;
  }
  .info-home  .tit h2{
    float: left;
    font-size: 22px;
    color: #8c8c8c;}
  .info-home   .tit h2 s{
    width: 24px;
    height: 23px;
    margin: 4px 10px 0 0;
  }
  /* 修改按钮样式：通用 */
  .info-home   .tit a{    height: 24px;width: 72px;
    line-height: 24px;
    text-align: center;
    font-size: 12px;
    color: #fff;
    border-radius: 4px;
    background: #7f6e6e;
    float: right;}
  .info-home   .tit a s{
    width: 13px;
    height: 12px;
    background-position: 0 -26px;
    margin: 6px 0 0 8px;
  }
  /* 个人信息内容部分：通用 */
  .info-home   .con{
    padding: 32px 50px 44px 60px;
    overflow: hidden;
  }
  /* 个人信息 */
  .info-home .gr .tit{  }
  .info-home  .tit h2{
    float: left;
    font-size: 22px;
    color: #8c8c8c;}

  .info-home  .gr .tit a{
    width: 82px;
  }
  /* 个人信息内容部分 */
  .con_R .gr .con p {
    margin: 0 0 20px 0;
  }
  .con_R .gr .con p .lt{
    float: right;
  }
  .con_R .gr .con p i, .home .gr .con p b{
    color: #f26552;
  }
  .con_R .gr .con p a{overflow: visible;margin-left: 10px;
    color: #e10808;}

</style>
