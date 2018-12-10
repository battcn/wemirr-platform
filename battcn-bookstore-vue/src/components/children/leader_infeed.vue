<template><!-- 横向榜单 -->
<div class="leader_infeed">
  <div class="tit">
    <s></s>
    <span>掌阅收入榜</span>
  </div>
  <ul>
    <li>
      <span>
        <a href="">
          <img src="./../../../static/img/leader_infeed.jpg" alt="">
          <s>1</s>
        </a>
      </span>
      <h3><a href="">美女总裁的贴身保镖</a></h3>
    </li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s>2</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s>3</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">4</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">5</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">6</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">7</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">8</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">9</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
    <li><span><a href=""><img src="./../../../static/img/leader_infeed.jpg" alt=""><s class="gb">10</s></a></span>
      <h3><a href="">美女总裁的贴身保镖</a></h3></li>
  </ul>
</div>
</template>
<script type="text/ecmascript-6">
  export default {
    name:'leader_infeed',
  }

</script>
<style>
  .leader_infeed{}
  .leader_infeed .tit{    width: 97px;
    height: 284px;
    text-align: center;
    border-right: 1px solid #f0f0f0;
    position: relative;
    padding-top: 80px;
    float: left;}
  .leader_infeed .tit s{
    width: 74px;    position: absolute;top:0;left: 0;
    height: 67px;
    background-position: 0 -30px;
  }
  .leader_infeed .tit span{
    font-size: 26px;    width: 22px;
    color: #3498db;
    word-wrap: break-word;
    letter-spacing: 20px;
    margin: 0 auto;
  }
/*  图书部分 */
  .leader_infeed >ul{
    width: 960px;
    float: left;
    padding: 26px 20px 0;
    overflow: hidden;
  }
  .leader_infeed >ul li{    float: left;
    width: 192px;
    text-align: center;
    margin: 0 0 30px 0;}
  .leader_infeed >ul li span{    width: 82px;
    height: 102px;
    position: relative;}
  .leader_infeed >ul li span a{    width: 80px;
    height: 100px;
    margin: 0 auto;}
  .leader_infeed >ul li span a img{}
  .leader_infeed >ul li span a s{width: 24px;
    height: 25px;
    position: absolute;
    right: -17px;
    top: -6px;
    color: #fff;
    padding: 3px 9px 0 0;background: url("./../../../static/img/pdIcon.png") no-repeat;background-position: -30px 0;}
  .leader_infeed >ul li span a s.gb{
    background-position: -66px 0;
  }
  .leader_infeed >ul li h3{font-size: 14px;margin: 10px 0 0;}
  .leader_infeed >ul li h3 a{    max-width: 120px;overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;}

</style>
