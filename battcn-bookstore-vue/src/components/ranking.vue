<template><!-- 大排行页面 -->
  <div class="ranking">
    <!--大标题 开始-->
    <div class="rank_bt">
      <h4 @click="gender='male'" :class="{on:gender=='male'}">
        <a href="javascript:">男生频道</a>
      </h4>
      <b class="line">|</b>
      <h4 @click="gender='female'" :class="{on:gender=='female'}"><a href="javascript:">女生频道</a><s></s></h4>
      <a class="all_btn" href="">完整榜单</a>
    </div>
    <!--大标题 结束-->
    <!--各种榜单 开始-->
    <div class="ki_li">
      <!-- 榜单组件 -->
      <ranking style="float: left"  :title="'点击榜'"></ranking>
      <ranking style="float: left" :border="true" :title="'钻石榜'"></ranking>
      <ranking style="float: left" :title="'鲜花榜'"></ranking>
      <ranking style="float: left"  :title="'收藏榜'"></ranking>
      <ranking style="float: left" :border="true" :title="'评分榜'"></ranking>
      <ranking style="float: left" :title="'总字数'"></ranking>
    </div>
    <!--各种榜单 结束-->
  </div>
</template>
<script>
  import ranking from './children/rankingList.vue'

  export default {
    name:'Ranking',
    data(){
      return {gender:'male'}
    },
    components:{ranking}
  }
</script>
<style>
  .ranking {
    overflow: hidden;
    background: #fff;
  }
  .rank_bt {
    width: 1030px;
    height: 45px;
    border-bottom: 2px solid #f0f0f0;
    overflow: hidden;
    padding: 35px 20px 0 50px;
    font-size: 20px;
  }
  .rank_bt .on {
    color: #333;
  }
  .rank_bt .on a {
    color: #333;overflow: auto;display: inline;
  }

  /* 分割线 */
  .rank_bt .line {
    margin: 0 30px;
    float: left;
  }

/* 未激活频道样式 */
  .rank_bt h4 {
    width: 88px;
    float: left;
  }

  .rank_bt h4 a {
    letter-spacing: 2px;
    color: #ccc;
  }


  /* 右边完整表单按钮样式 */
  .rank_bt .all_btn {
    width: 72px;
    height: 24px;
    text-align: center;
    line-height: 24px;
    border-radius: 4px;
    font-size: 12px;
    color: #fff;
    float: right;
    background: #7f6e6e;
  }
  /* 显示排行榜区域 */
  .ki_li {
    overflow: hidden;
  }
  /* 响应式样式 */
  @media screen and (max-width: 1100px) {
    /*  标题 */
    .rank_bt{    font-size: 16px;width: auto;
      padding: 5px 10px;    height: auto;}
    .rank_bt h4{width: auto;}
    .rank_bt h4:first-child{margin-left: 10px;}
  }
  @media screen and (max-width: 500px) {
  .rank_bt .line{margin: 0 15px;}
  }
</style>


