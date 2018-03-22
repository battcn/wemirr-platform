<template>
  <div class="details">
    <div class="header">
    </div>
    <div class="de-content">
      <div class="introduction-box"><!-- 书籍k简介部分 -->
        <div class="introduction-img">
          <div class="img">
            <img src="../../static/img/noTime.jpg" :src="bookInfo.cover" :title="bookInfo.name" :alt="bookInfo.name">
          </div>
          <div class="btn">
            <a href="javascript:">
              <i class="fa fa-star-o"></i>
              加入书架
            </a>
            <a href="javascript:">
              <i class="fa fa-thumbs-o-up"></i>
              点个赞
            </a>
          </div>
        </div>
        <div class="introduction-info">
          <h3 v-text="bookInfo.name" class="i01">娱乐春秋</h3>
          <p class="i02">
            作者：
            <span v-text="bookInfo.author">不详</span>
            创建日期：
            <span>2017年08月07日</span>
          </p>
          <div class="i03">
            <h4>简介</h4>
            <div class="i03_box">
              <p v-text="bookInfo.description" :class="{heightShow:isHeight=='show',heightHide:isHeight=='hide'}">
                无
              </p>
              <span v-show="isHeight=='show'" @click="isHeight='hide'">收起&nbsp;&nbsp;<i
                class="fa fa-angle-up"></i></span>
              <span v-show="isHeight=='hide'" @click="isHeight='show'">展开&nbsp;&nbsp;<i
                class="fa fa-angle-down"></i></span>
            </div>
          </div>
          <div class="i04">
            <router-link :to="{path:'/Read',query:{bookNo:bookInfo.bookNo}}" href="javascript:">立即阅读</router-link>
            <router-link :to="{path:'/Contents',query:{bookNo:bookInfo.bookNo}}" href="javascript:">目录</router-link>
          </div>
          <div class="i05">
            作品标签：
            <span v-text="bookInfo.type"></span>
          </div>
        </div>
      </div>
      <div class="chapter-box">
      </div>
    </div>
  </div>

</template>
<script type="text/ecmascript-6">
  import {mapState, mapMutations, mapGetters} from 'vuex'
  import store from './../store'
  export default {
    name: 'bookDetails',
    data() {
      return {isHeight: 'hide'}
    },
    computed: {
      bookInfo(){
        return this.$store&&this.$store.state.initData&&this.$store.state.initData.detailsData
      }
    },
    methods:{
      ...mapMutations([
        'iniBookDetails'
      ]),
      iniBookDetails:function () {
        if(!this.$route&&this.$route.query&&this.$route.query.bookNo){
          return false;
        }
        let bookNo= this.$route.query.bookNo;
        this.$store.commit('iniBookDetails',bookNo);
      }
    },
    mounted(){
      this.iniBookDetails();
    }
  }
</script>
<style slot-scope>
  body {
    background-color: #F0F0F0;
  }

  /* 头部 */
  .header {
    background-color: #2F3649;
    height: 35px;
    text-align: left;
  }

  /* 整体大容器 */
  .de-content {
    background-color: #fff;
    width: 95%;
    margin: auto;
    text-align: left;
  }

  /* 书籍详情框 */
  .de-content .introduction-box {
    overflow: hidden;
    border-right: 1px solid #f0f0f0;
    margin-top: 20px;
  }

  /* 详情图片 */
  .de-content .introduction-box .introduction-img {
    float: left;
    width: 200px;
    padding: 50px;
    text-align: center;
    overflow: hidden;
  }
  .de-content .introduction-box .introduction-img .img{
    overflow: hidden;
    height:284.3px;
    cursor: pointer;
  }
  .de-content .introduction-box .introduction-img img {
    width: 100%;
    height: 100%;
    margin: 0 auto 44px;
    transition: all 0.6s;
  }

  .de-content .introduction-box .introduction-img img:hover {
    transform: scale(1.1);
  }

  .de-content .introduction-box .introduction-img .btn {
    margin-top: 25px;
  }

  .de-content .introduction-box .introduction-img .btn a {
    text-align: center;
    padding: 3px 11px;
    display: inline-block;
    height: 28px;
    border: 1px solid #d2d2d2;
    border-radius: 4px;
    line-height: 28px;
    font-size: 12px;
    color: #6a6a6a;
  }

  .de-content .introduction-box .introduction-img .btn a:hover {
    background: #d2d2d2;
    color: #fff;
  }

  /* 详情信息 */
  .introduction-info {
    float: left;
    width: 580px;
    border-left: 1px solid #f0f0f0;
    padding: 10px;
  }

  /* 标题 */
  .introduction-info .i01 {
    white-space: nowrap;
    text-overflow: ellipsis;
    color: #333;
    font-size: 20px;
  }

  .introduction-info .i01:hover {
    color: #f26552;
  }

  /* 作者 */
  .introduction-info .i02 {
    color: #9e9e9e;
    font-size: 12px;
  }

  /* 简介 */
  .introduction-info .i03 {
    border-bottom: 1px solid #f0f0f0;
  }

  .introduction-info .i03 h4 {
    margin-bottom: 10px;
    margin-top: 0;
  }

  .introduction-info .i03 .i03_box {
    font-size: 12px;
  }

  .introduction-info .i03 .i03_box p {
    overflow: hidden;
    color: #9e9e9e;
    transition: all .5s;
    font-size: 15px;
  }

  .introduction-info .i03 .i03_box p.heightShow {
    height: auto;
    transition: all .5s;
  }

  .introduction-info .i03 .i03_box p.heightHide {
    height: 54px;
    transition: all .5s;
  }

  .introduction-info .i03 .i03_box {
    overflow: hidden;
  }

  .introduction-info .i03 .i03_box > span {
    cursor: pointer;
    float: right;
  }

  .introduction-info .i03 .i03_box > span:hover {
    color: #f26552;
  }

  /* 阅读按钮 */
  .introduction-info .i04 {
    padding: 40px 0 0 0;
  }

  .introduction-info .i04 > a {
    margin: 0 12px 0 22px;
    width: 100px;
    border-radius: 5px;
    color: #fff;
    font-size: 14px;
    height: 34px;
    line-height: 34px;
    text-align: center;
    display: inline-block;
  }

  .introduction-info .i04 > a:first-child {
    background: #f26552;
  }

  .introduction-info .i04 > a:first-child:hover {
    background: #e5533f;
  }

  .introduction-info .i04 > a:last-child {
    background: #d2d2d2;
  }

  .introduction-info .i04 > a:last-child:hover {
    background: #c4c4c4;
  }

  /* 作品标签 */
  .introduction-info .i05 {
    font-size: 12px;
    background: #fbfbfb;
    margin: 54px auto 0;
    padding: 15px 15px;
    color: #666;
  }
  .introduction-info .i05 >span{
    display: inline-block;
    padding: 3px 5px;background-color: #D2D2D2;border-radius: 2px;cursor: pointer;
  }
  .introduction-info .i05 >span:hover{
    color:#f26552;
  }

  .chapter-box {
  }

  /* 媒体查询样式 */
  @media (min-width: 560px) and (max-width: 800px) {
    .de-content .introduction-box .introduction-img {
      padding: 50px 20px;
      width: 150px;
    }
    .de-content .introduction-box .introduction-img .img{
      height: 180px;
      margin: 0 auto 10px;
    }
    .de-content .introduction-box .introduction-img img {

    }

    .de-content .introduction-box .introduction-img .btn {
      text-align: left;
    }

    .de-content .introduction-box .introduction-img .btn a {
      margin-bottom: 10px;
      width: 63.4px;
    }

    .introduction-info {
      width: 314px;
    }
  }

  @media (min-width: 800px) and (max-width: 1000px) {
    .de-content .introduction-box .introduction-img {
      padding: 50px 20px;
    }

    .introduction-info {
      width: 488px;
    }
  }

  @media (min-width: 1000px) {
    /* 整体大容器 */
    .de-content {
      width: 1100px;
      margin: 0 auto;
      overflow: hidden;
    }
  }

</style>
