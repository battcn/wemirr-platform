<template>
  <div class="book-content">
    <ul style="overflow: hidden;">
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
      <li class="single-content">
        <img src="./../../assets/bookcover/bookCover.png" alt=""/>
        <h3 class="title">梦中神树</h3>
        作者：<span class="author">未知</span>
      </li>
    </ul>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    name: 'Home',
    data(){
      return {}
    }
  }

</script>
<style slot-scope>
  /* 书本大容器 */
  .book-content {
    text-align: left;
  }

  /* 单本小容器 */
  .single-content {
    display: inline-block;
    overflow: hidden;
    font-size: 15px;
    color: #8c97b2;
  }

  .single-content > img {
    display: block;
    width: 100%;
    border-radius: 4px;
  }

  .single-content > .title {
    color: #444C63;
    font-size: 15px;
    margin: 10px 0 5px;
  }

  .single-content > .author {
  }
  /* 响应式样式 */
  @media (min-width: 0) {
    /* 单本小容器 */
    .single-content {
      width: calc(100% - 10px);
      float: left;
      margin-right: 10px;
      margin-bottom: 10px;
      text-align: center;
    }
    .single-content > img{
      height: 210px;
      width: 180px;
      margin:auto;
    }
    .single-content > .title{
      font-size: 15px;
    }
  }
  @media (max-width: 480px) {
    .book-content > ul{
      margin-right: -10px;
    }
    .single-content{
      width: calc(50% - 10px);
    }
  }
  @media (max-width: 410px) {
    .single-content{
      width: calc(100% - 10px);
    }
    .book-content > ul{
      margin-right: -10px;
    }
  }
  /* 大于等于 530px */
  @media (min-width: 530px) {
    .book-content > ul{
      margin-right: -10px;
    }
    /* 单本小容器 */
    .single-content {
      width: calc(50% - 10px);
      float: left;
      margin-right: 10px;
      margin-bottom: 10px;
    }
    .single-content > img{
      height: 210px;
      width: 180px;
    }
    .single-content > .title{
      font-size: 15px;
    }
  }
  /* 超小屏幕（手机，大于等于 630px） */
  @media (min-width: 630px) {
    .book-content > ul{
      margin-right: -10px;
    }
    /* 单本小容器 */
    .single-content {
      width: calc(33.333% - 10px);
      float: left;
      margin-right: 10px;
      margin-bottom: 15px;
    }
    .single-content > img{
      height: 150px;
      width: 120px;
    }
    .single-content > .title{
      font-size: 15px;
    }
  }

  /* 小屏幕（平板，大于等于 768px） */
  @media (min-width: 768px) {
    .book-content > ul{
      margin-right: -15px;
    }
    /* 单本小容器 */
    .single-content {
      width: calc(25% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
    }
    .single-content > img{
      height: 150px;
      width: 120px;
    }
    .single-content > .title{
      font-size: 17px;
    }
  }

  /* 中等屏幕（桌面显示器，大于等于 992px） */
  @media (min-width: 992px) {
    .book-content > ul{
      margin-right: -15px;
    }
    /* 单本小容器 */
    .single-content {
      width: calc(25% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
    }
    .single-content > img{
      width: 180px;
      height: 210px;
    }
    .single-content > .title{
      font-size: 17px;
    }
  }

  /* 大屏幕（大桌面显示器，大于等于 1200px） */
  @media (min-width: 1200px) {
    .book-content > ul{
      margin-right: -15px;
    }
    /* 单本小容器 */
    .single-content {
      width: calc(20% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
    }
    .single-content > img{
      height: 230px;
    }
    .single-content > .title{
      font-size: 17px;
    }

  }

</style>
