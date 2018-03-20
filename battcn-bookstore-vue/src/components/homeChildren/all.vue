<template>
  <div class="book-content">
    <ul class="library" style="overflow: hidden;">
      <li  :key="book.bookNo" v-for="book in bookData" class="single-content">
        <div class="content-img">
          <router-link  :to="{path:'/Details',query:{bookNo:book.bookNo}}">
            <img  :alt="book.name" :title="book.name" src="../../../static/img/noTime.jpg" :src="book.cover"/>
          </router-link>
        </div>
        <router-link  :to="{path:'/Details',query:{bookinfo:book.bookNo}}">
          <h3 v-text="book.name" class="title"></h3>
        </router-link>
        作者：<span v-text="book.author" class="author">未知</span>
      </li>
    </ul>
    <ul class="pagination"><!-- 分页部分 -->
      <li><a @click="jumpPageControl(AllData.pageNum,1)" :class="{active:AllData.pageNum==1}" href="javascript:">首页</a>
      </li>
      <li><a @click="jumpPageControl(AllData.pageNum,AllData.pageNum-1)" :class="{active:AllData.pageNum==1}"
             href="javascript:">前一页</a></li>
      <li @click="jumpPageControl(AllData.pageNum,num)" v-for="num in AllData.navigatepageNums"><a
        :class="{active:AllData.pageNum==num}" v-text="num" href="javascript:">*</a></li>
      <li><a @click="jumpPageControl(AllData.pageNum,AllData.pageNum+1)"
             :class="{active:AllData.pageNum==AllData.pages}" href="javascript:">下一页</a></li>
      <li><a @click="jumpPageControl(AllData.pageNum,AllData.pages)" :class="{active:AllData.pageNum==AllData.pages}"
             href="javascript:">尾页</a></li>
    </ul>
  </div>
</template>
<script type="text/ecmascript-6">
  import {mapState, mapMutations, mapGetters} from 'vuex'
  import store from './../../store'

  export default {
    name: 'Home',
    data() {
      return {}
    },
    computed: {
      AllData() {
        return this.$store.state.initData.data
      },
      bookData() {/* 书信息 */
        return this.AllData && this.AllData.list;
      }
    },
    methods: {
      ...mapMutations([
        'jumpPage'
      ]),
      jumpPageControl: function (currentPage, nextPage) {
        if (currentPage === nextPage) {
          return false;
        }
        this.$store.commit('jumpPage', nextPage);
      },
    }
  }

</script>
<style slot-scope>
  /* 分页容器 */
  .pagination {
    display: inline-block;
    padding-left: 0;
    margin: 20px 0;
    border-radius: 4px;
  }

  .pagination li {
    display: inline;
  }

  .pagination li a {
    position: relative;
    float: left;
    padding: 6px 12px;
    margin-left: -1px;
    line-height: 1.42857143;
    color: #337ab7;
    text-decoration: none;
    background-color: #fff;
    border: 1px solid #ddd;
  }

  .pagination li a:hover {
    z-index: 2;
    color: #23527c;
    background-color: #eee;
    border-color: #ddd;
  }

  .pagination li a.active {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #337ab7;
    border-color: #337ab7;
  }

  /* 书本大容器 */
  .book-content {
    text-align: center;
  }

  /* 单本小容器 */
  .single-content {
    display: inline-block;
    overflow: hidden;
    font-size: 15px;
    color: #8c97b2;
  }

  .single-content > .content-img {
    overflow: hidden;
    border: 1px solid #f3f3f3;
    cursor: pointer;
    border-radius: 4px;
  }

  .single-content > .content-img img {
    transition: all 0.6s;
    overflow: hidden;
    display: block;
    width: 100%;
    height: 100%;

  }

  .single-content > .content-img img:hover {
    transform: scale(1.1);
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

    .single-content > .content-img {
      height: 210px;
      width: 180px;
      margin: auto;
    }

    .single-content > .title {
      font-size: 15px;
    }
  }

  @media (max-width: 480px) {
    .book-content .library {
      margin-right: -10px;
    }

    .single-content {
      width: calc(50% - 10px);
    }
  }

  @media (max-width: 410px) {
    .single-content {
      width: calc(100% - 10px);
    }

    .book-content .library {
      margin-right: -10px;
    }
  }

  /* 大于等于 530px */
  @media (min-width: 530px) {
    .book-content .library {
      margin-right: -10px;
    }

    /* 单本小容器 */
    .single-content {
      height: 285px;
      width: calc(50% - 10px);
      float: left;
      margin-right: 10px;
      margin-bottom: 10px;
    }

    .single-content > .content-img {
      height: 210px;
      width: 180px;
    }

    .single-content > .title {
      font-size: 15px;
    }
  }

  /* 超小屏幕（手机，大于等于 630px） */
  @media (min-width: 630px) {
    .book-content .library {
      margin-right: -10px;
    }

    /* 单本小容器 */
    .single-content {
      height: 240px;
      width: calc(33.333% - 10px);
      float: left;
      margin-right: 10px;
      margin-bottom: 15px;
    }

    .single-content > .content-img {
      height: 150px;
      width: 120px;
    }

    .single-content > .title {
      font-size: 15px;
    }
  }

  /* 小屏幕（平板，大于等于 768px） */
  @media (min-width: 768px) {
    .book-content .library {
      margin-right: -15px;
    }

    /* 单本小容器 */
    .single-content {
      width: calc(25% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
    }

    .single-content > .content-img {
      height: 150px;
      width: 120px;
    }

    .single-content > .title {
      font-size: 17px;
    }
  }

  /* 中等屏幕（桌面显示器，大于等于 992px） */
  @media (min-width: 992px) {
    .book-content .library {
      margin-right: -15px;
    }

    /* 单本小容器 */
    .single-content {
      height: 300px;
      width: calc(25% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
    }

    .single-content > .content-img {
      width: 180px;
      height: 210px;
    }

    .single-content > .title {
      font-size: 17px;
    }
  }

  /* 大屏幕（大桌面显示器，大于等于 1200px） */
  @media (min-width: 1200px) {
    .book-content .library {
      margin-right: -15px;
    }

    /* 单本小容器 */
    .single-content {
      width: calc(16.6666% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
    }

    /*.single-content > .content-img img{*/
    /*height: 230px;*/
    /*}*/
    .single-content > .title {
      font-size: 17px;
    }

  }

  @media (min-width: 1690px) {
    /* 超超大屏幕 */
    .book-content .library {
      margin-right: -15px;
    }

    /* 单本小容器 */
    .single-content {
      width: calc(12.5% - 15px);
      float: left;
      margin-right: 15px;
      margin-bottom: 20px;
      height: auto;
    }

    /*.single-content > img{*/
    /*width: 300px;*/
    /*height: 350px;*/
    /*}*/
    .single-content > .title {
      font-size: 17px;
    }
  }

</style>
