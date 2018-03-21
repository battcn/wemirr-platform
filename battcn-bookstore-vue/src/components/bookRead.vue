<template>
  <div class="bookRead">
    <div class="header"></div>
    <div class="re-content">
      <div class="control"> <!-- 功能控制 -->
        <span class="nav">
          <a href="javascript:">首页</a> >
          <a href="javascript:">都市异能</a> >
          <a href="javascript:">书名</a>
        </span>
      </div>
      <div class="content">
        <h2>第1章：桃花仙人</h2>
        <ul class="co-info">
          <li>作者：<span>我咬月亮</span></li>
          <li>类别：<span>都是异能</span></li>
          <li>更新时间：<span>1个月前</span></li>
          <li>本章字数：<span>3035</span></li>
        </ul>
        <div class="co-text">
          <p>张晋悄悄瞥了一眼手机上闪出的信息，悬着的心终于放一眼手机上闪出的信息，悬着的心终于放一眼手机上闪出的信息，悬着的心终于放一眼手机上闪出的信息，悬着的心终于放下，旋即扬手一扔，将崭新的肾8甩入了大海之中。</p>
          <p>张晋悄悄瞥了一眼手机上闪出的信息，悬着的心终于放下，旋即扬手一扔，将崭新的肾8甩入了大海之中。</p>
          <p>张晋悄悄瞥了一眼手机上闪出的信息，悬着的心终于放下，旋即扬手一扔，将崭新的肾8甩入了大海之中。</p>
          <p>张晋悄悄瞥了一眼手机上闪出的信息，悬着的心终于放下，旋即扬手一扔，将崭新的肾8甩入了大海之中。</p>
          <p>张晋悄悄瞥了一眼手机上闪出的信息，悬着的心终于放下，旋即扬手一扔，将崭新的肾8甩入了大海之中。</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    name: 'bookRead'
  }
</script>
<style slot-scope>
  body {
    background-color: #3D3E45;
    color: #9e9e9e;
  }

  /* 头部 */
  .header {
    background-color: #fff;
    height: 35px;
    text-align: left;
  }

  /* 整体大容器 */
  .re-content {
    background-color: #EAEAEA;
    width: 80%;
    margin: 15px auto 0;
    text-align: center;
  }

  /* 导航控制区域 */
  .re-content .control {
    border-bottom: 1px solid #3D3E45;
    padding: 32px 35px 30px;
    font-size: 12px;
    overflow: hidden;
  }

  .re-content .control .nav {
    float: left;
  }

  .re-content .control .nav a {
    color: #9e9e9e;
    margin: 0 6px;
  }

  /* 正文部分 */
  .re-content .content {
    overflow: hidden;
    padding: 60px 70px 0;
  }

  .re-content .content h2 {
    font-style: normal;
    font-weight: normal;
    font-size: 32px;
    color: #333;
    text-align: center;
    margin-bottom: 24px;
  }

  .re-content .content .co-info li {
    display: inline-block;
    font-size: 12px;
  }

  .re-content .content .co-text p {
    text-align: left;
    text-indent: 2em;
    line-height: 2em;
    letter-spacing: 2px;
    padding-bottom: 2em;
  }

  /* 响应式样式 */
  @media (max-width: 525px) {
    .re-content .control {
      padding: 19px 12px 16px;
    }

    .re-content .content h2 {
      font-size: 19px;
    }

    .re-content .content {
      padding: 13px 14px 0;
    }
  }
</style>


