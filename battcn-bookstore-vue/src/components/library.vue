<template>
  <div class="library">
  <!--选择搜索 开始-->
  <div class="find">
    <div class="fi_t">
      <div class="boFi">
        <span><s></s></span>
        <input placeholder="我要搜.. " type="text">
        <a href="javascript:">搜索</a>
      </div>
    </div>
    <div class="opt">
      <div class="optCl">
        <span class="optTit">所属频道</span>
        <ul>
          <li><a @click="channels='不限',category='不限'" :class="{on:channels=='不限'}" href="javascript:">不限</a></li>
          <li><a :class="{on:channels=='男频'}" @click="channels='男频',category='不限'" href="javascript:">男频</a></li>
          <li><a :class="{on:channels=='女频'}" @click="channels='女频',category='不限'" href="javascript:">女频</a></li>
        </ul>
      </div>
      <div v-show="channels!=='不限'" class="optCl">
        <span class="optTit">作品大类</span>
        <ul v-show="channels=='男频'">
          <li><a @click="category='不限'" :class="{on:category=='不限'}" href="javascript:">不限</a></li>
          <li><a @click="category='都市生活'" :class="{on:category=='都市生活'}" href="javascript:">都市生活</a></li>
          <li><a @click="category='玄幻小说'" :class="{on:category=='玄幻小说'}" href="javascript:">玄幻小说</a></li>
          <li><a @click="category='历史军事'" :class="{on:category=='历史军事'}" href="javascript:">历史军事</a></li>
          <li><a @click="category='科幻灵异'" :class="{on:category=='科幻灵异'}" href="javascript:">科幻灵异</a></li>
          <li><a @click="category='武侠仙侠'" :class="{on:category=='武侠仙侠'}" href="javascript:">武侠仙侠</a></li>
          <li><a @click="category='综合其他'" :class="{on:category=='综合其他'}" href="javascript:">综合其他</a></li>
        </ul>
        <ul v-show="channels=='女频'">
          <li><a @click="category='不限'" :class="{on:category=='不限'}" href="javascript:">不限</a></li>
          <li><a @click="category='女性言情'" :class="{on:category=='女性言情'}" href="javascript:">女性言情</a></li>
        </ul>
      </div>
      <div class="optCl">
        <span class="optTit">是否完结</span>
        <ul>
          <li><a :class="{on:isend=='不限'}"  @click="isend='不限'"   href="javascript:">不限</a></li>
          <li><a :class="{on:isend=='连载中'}"  @click="isend='连载中'" href="javascript:">连载中</a></li>
          <li><a :class="{on:isend=='已完结'}"  @click="isend='已完结'" href="javascript:">已完结</a></li>
        </ul>
      </div>
      <div class="optCl">
        <span class="optTit">是否免费</span>
        <ul>
          <li><a :class="{on:isfree=='不限'}"  @click="isfree='不限'"  href="javascript:">不限</a></li>
          <li><a :class="{on:isfree=='免费'}"  @click="isfree='免费'" href="javascript:">免费</a></li>
          <li><a :class="{on:isfree=='付费'}"  @click="isfree='付费'" href="javascript:">付费</a></li>
        </ul>
      </div>
      <div class="optCl">
        <span class="optTit">作品字数</span>
        <ul>
          <li><a  :class="{on:word_count=='不限'}"  @click="word_count='不限'"  href="javascript:">不限</a></li>
          <li><a :class="{on:word_count=='30万字以下'}"  @click="word_count='30万字以下'"  href="javascript:">30万字以下</a></li>
          <li><a :class="{on:word_count=='30-50万字'}"  @click="word_count='30-50万字'" href="javascript:">30-50万字</a></li>
          <li><a :class="{on:word_count=='50-100万字'}"  @click="word_count='50-100万字'" href="javascript:">50-100万字</a></li>
          <li><a :class="{on:word_count=='50-100万字以上'}"  @click="word_count='50-100万字以上'" href="javascript:">50-100万字以上</a></li>
        </ul>
      </div>
      <div class="optCl">
        <span class="optTit">更新时间</span>
        <ul>
          <li><a  :class="{on:update_time=='不限'}"  @click="update_time='不限'"  href="javascript:">不限</a></li>
          <li><a :class="{on:update_time=='三天内'}"  @click="update_time='三天内'" href="javascript:">三天内</a></li>
          <li><a :class="{on:update_time=='一周内'}"  @click="update_time='一周内'" href="javascript:">一周内</a></li>
          <li><a :class="{on:update_time=='半月内'}"  @click="update_time='半月内'" href="javascript:">半月内</a></li>
          <li><a :class="{on:update_time=='一个月内'}"  @click="update_time='一个月内'" href="javascript:">一个月内</a></li>
        </ul>
      </div>
      <div class="optCl">
        <span class="optTit">排序方式</span>
        <ul>
          <li><a :class="{on:sorting=='默认'}"  @click="sorting='默认'"  href="javascript:">默认</a></li>
          <li><a :class="{on:sorting=='热度'}"  @click="sorting='热度'" href="javascript:">热度</a></li>
          <li><a :class="{on:sorting=='更新时间'}"  @click="sorting='更新时间'" href="javascript:">更新时间</a></li>
          <li><a :class="{on:sorting=='字数'}"  @click="sorting='字数'" href="javascript:">字数</a></li>
          <li><a :class="{on:sorting=='销售'}"  @click="sorting='销售'" href="javascript:">销售</a></li>
        </ul>
      </div>
    </div>
  </div>
  <!--选择搜索 结束-->
  <div v-show="Object.keys(find_condition).length!=0" class="yxx">
    <span v-for="(value,key) in find_condition">{{value}}<s @click="Reset(key)"></s></span>
    <i href="javascript:" @click="Reset()">清空筛选</i>
  </div>
    <!--搜索结果 开始-->
    <div class="result">
      <ul>
        <li>
          <a href="javascript:" class="book_cov"><img class="lazyload_book_cover" src="./../../static/img/large.jpg" alt=""></a>
           <div class="book_inf font_12">
             <h3><a href="javascript:">美女总裁的神龙兵王</a></h3>
             <p>
               <span>作者：<a href="javascript:">意外</a></span>
               <span>分类：<a href="javascript:">爱情婚姻</a></span>
               <span>状态：连载中</span>
               <span>总字数：28万字+</span>
             </p>
             <p class="tags">
               <i>标签：</i>
               <i>都市</i>
               <i>爱情</i>
             </p>
             <p><b>最近更新：</b><a href="javascript:">第九十二章 震惊</a></p>
             <p class="int">终极兵王叶真重返都市，只为保护自己的未婚妻，为了打击林清音身边的狂蜂浪蝶，叶真不得已让他们知道什么叫兵王！</p>
           </div>
          <div class="right">
            <span>更新时间：刚刚</span>
            <a class="read_btn btn" href="javascript:">立即阅读</a>
            <a href="javascript:" class="store_btn btn">加入书架</a>
          </div>
        </li>
      </ul>
    </div>
    <!--搜索结果 结束-->
  </div>
</template>
<script>
  export default {
    name: 'Library',
    data() {
      /* 所属频道，作品大类，是否完结,是否免费，字数,更新时间,排序方式 */
      return {channels:'不限',category:'不限',isend:'不限',isfree:'不限',word_count:'不限',update_time:'不限',sorting:'默认'}
    },
    computed:{
      find_condition(){
        let find_condition={};
        this.channels==='不限'?'':find_condition['channels']=this.channels;
        this.category==='不限'?'':find_condition['category']=this.category;
        this.isfree==='不限'?'':find_condition['isfree']=this.isfree;
        this.isend==='不限'?'':find_condition['isend']=this.isend;
        this.word_count==='不限'?'':find_condition['word_count']=this.word_count;
        this.update_time==='不限'?'':find_condition['update_time']=this.update_time;
        this.sorting==='默认'?'':find_condition['sorting']=this.sorting;
        return find_condition;
      }
    },
    methods:{
      Reset:function(key){/*重置某一项或者全部 */
        let _this=this;
        if(key!=undefined){
          key=='sorting'?_this[key]='默认':_this[key]='不限';
          return false;
        }
        this.channels='不限';
        this.category='不限';
        this.isfree='不限';
        this.isend='不限';
        this.word_count='不限';
        this.update_time='不限';
        this.sorting='默认';
      }
    }
  }

</script>
<style>
  /* 搜索条件部分 */
  .find {
    background: #fff;
    width: 1100px;
    overflow: hidden;
    margin-bottom: 20px;
  }
  /* 输入框 */
  .find .fi_t {
    padding: 40px 0;
    border-bottom: 1px solid #f0f0f0;
  }
  .find .boFi {
    width: 468px;
    height: 38px;
    border: 1px solid #8a8a8a;
    border-radius: 20px;
    margin: 0 auto;
    overflow: hidden;
  }
  .find .boFi span {
    float: left;
    margin: 6px 20px 0 14px;
  }
  .find .boFi span s {
    width: 24px;
    height: 24px;
    background-position: 0 0;
  }
  .find .boFi input {
    width: 330px;
    height: 38px;
    border-right: 1px solid #d8d8d8;
    color: #666;
    float: left;
  }
  .find .boFi a {
    width: 60px;
    text-align: center;
    letter-spacing: 2px;
    font-size: 16px;
    margin: 7px;
    float: left;
  }
  .find .opt {
    background: #fff;
    padding: 18px 42px 14px;
    overflow: hidden;
  }
  .find .opt .optCl {
    overflow: hidden;
  }
  .find .opt .optTit {
    width: 85px;
    float: left;
    color: #333;
    padding-top: 18px;
  }
  .find .opt ul {
    width: 930px;
    border-bottom: 1px solid #f0f0f0;
    overflow: hidden;
    float: left;
    padding: 14px 0;
  }
  .find .opt ul li {
    float: left;
  }
  .find .opt ul li a {
    padding: 4px 10px;
    margin: 0 4px 4px;
    border-radius: 5px;
    color: #9e9e9e;
  }
  .find .opt ul li .on {
    padding: 4px 10px;
    margin: 0 4px;
    border-radius: 5px;
    background: #7f6e6e;
    color: #fff;
  }
  /* 筛选选项 */
  .yxx {
    height: 38px;
    background: #fafafa;
    padding: 12px 42px 0 42px;
  }
  .yxx > i  {
    font-style: normal;
    color: #f26552;
    cursor: pointer;
  }
  .yxx span {
    padding: 4px 0 4px 12px;
    border: 1px solid #d7d7d7;
    font-size: 12px;
    color: #666;
    margin: 0 10px 0 0;
  }
  .yxx span:hover {
    border: 1px solid #f26552;
  }
  .yxx span s {
    float: right;
    width: 8px;
    height: 8px;
    background-position: -179px -49px;
    margin: 5px 10px 0 12px;
    cursor: pointer;
  }
  .yxx span:hover s {
    background-position: -190px -49px;
  }

  /* 搜索结果 */
  .result {
    background: #fff;
    width: 1100px;
    overflow: hidden;
  }
  .result ul {
    overflow: hidden;
  }
  .result ul li {
    padding: 30px 42px;
    border-bottom: 1px solid #f0f0f0;
    overflow: hidden;
  }
  .result ul li .book_cov {
    width: 120px;
    height: 150px;
    float: left;
  }
  .result ul li .book_inf {
    width: 670px;
    float: left;
    overflow: hidden;
    padding-left: 40px;
  }
  .result ul li .book_inf h3 {
    margin-bottom: 10px;
    font-size: 16px;
  }
  .result ul li .book_inf p {
    line-height: 18px;
  }
  .result ul li .book_inf p span {
    margin-right: 28px;
  }
  .result ul li .book_inf p span a{
    overflow: auto;display: inline;
  }
  .result ul li .book_inf .tags {
    margin: 4px 0 18px 0;
  }
  .result ul li .book_inf p i {
    margin-right: 6px;
  }
  .result ul li .book_inf p b {
    color: #333;
  }
  .result ul li .book_inf .int {
    margin: 6px 0 0 0;
  }
  /* 立即阅读及加入书架操作按钮 */
  .result ul li .right {
    float: right;
    overflow: hidden;
    width: 170px;
    text-align: right;
  }
  .result ul li .right span {
    font-size: 12px;
  }
  .result ul li .right .btn {
    width: 116px;
    height: 30px;
    text-align: center;
    line-height: 30px;
    background: #d2d2d2;
    border-radius: 2px;
    color: #fff;
    float: right;
  }
  .result ul li .right .btn {
    width: 116px;
    height: 30px;
    text-align: center;
    line-height: 30px;
    background: #d2d2d2;
    border-radius: 2px;
    color: #fff;
    float: right;
  }
  .result ul li .right .read_btn {
    margin: 62px 0 10px 0;
    background: #f26552;
  }
  .result ul li .right .read_btn:hover {
    background: #e5533f;
  }
  .result ul li .right .store_btn:hover {
    background: #c4c4c4;
  }

</style>



