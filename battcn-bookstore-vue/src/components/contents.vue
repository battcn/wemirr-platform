<template>
  <div class="contents">
    <div class="header"></div>
    <div class="co-content">
      <h3 v-text="bookInfo.name">天庭临时工</h3>
      <p>作者：<span v-text="bookInfo.author">我咬月亮</span></p>
      <div @click="isShow=!isShow" class="title"><!-- 标题及控制隐藏 -->
        <span>正文</span>
        <i v-show="!isShow" class="fa fa-plus"></i>
        <i v-show="isShow" class="fa fa-minus"></i>
      </div>
      <div class="content"><!-- 目录内容 -->
        <ul v-show="isShow">
          <li v-for="content in contentsInfo"><a v-text="content.title" href="javascript:">第1章：桃花仙人</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import {mapState, mapMutations, mapGetters} from 'vuex'
  import store from './../store'
  export default {
    /* 目录页面 */
    name: 'contents',
    data() {
      return {isShow:true}
    },
    computed:{
      contentsInfo:function () {
        return this.$store&&this.$store.state.initData&&this.$store.state.initData.contentsData;
      },
      bookInfo:function () {
        return this.$store&&this.$store.state.initData&&this.$store.state.initData.detailsData;
      }
    },
    methods:{
      ...mapMutations([
        'iniBookContents','iniBookDetails'
      ]),
      iniBookContents:function () {
        let bookNo= this.$route.query.bookNo;
        this.$store.commit('iniBookDetails',bookNo);
        this.$store.commit('iniBookContents',bookNo);
      }
    },
    mounted(){
      this.iniBookContents();
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
  .co-content {
    padding: 40px 20px;
    background-color: #fff;
    width: 95%;
    margin: auto;
    text-align: center;
  }

  .co-content > p ,.co-content .content ul{
    color: #9e9e9e;    overflow: hidden;
    font-size: 12px;
  }
  /* 标题及控制隐藏 */
  .co-content .title{
    height: 34px;
    border: 1px solid #f0f0f0;
    font-size: 14px;
    line-height: 34px;
    color: #333;
    text-align: left;    padding: 0 10px;
  }
  .co-content .title span{
    padding-left: 10px;
    border-left: solid 4px #f26552;
  }
  .co-content .title i{
    float: right;    line-height: 34px;margin-right: 10px;
  }
/* 目录 */
  .co-content .content{overflow: hidden;}
  .co-content .content li{    float: left;
    width: 23%;
    height: 48px;
    line-height: 48px;
    border-bottom: 1px dashed #f0f0f0;
    padding: 0 10px;}
  .co-content .content li a{color: #9e9e9e;overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;}
  .co-content .content li a:hover{color: #f26552;}

  /* 媒体查询样式 */
  @media (min-width: 1055px){
    .co-content .content li{
      width:22%;
    }
  }
  @media (max-width: 1055px) and (min-width: 750px){
  .co-content .content li{
    width:22%;
  }
    .co-content{
      padding: 30px 0;
    }
  }
  @media (max-width: 750px) and (min-width: 425px){
  .co-content .content li{
    width:45%;text-align: left;
  }
    .co-content{
      padding: 30px 0;
    }
  }
  @media (max-width: 425px){
  .co-content .content li{
    width:100%;
    text-align: left;
  }
    .co-content{
      padding: 30px 0;
    }
  }

</style>
