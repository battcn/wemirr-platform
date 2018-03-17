<template>
  <div class="home">
    <header>
      <span>某某APP所有书本</span>
      <ul>
        <li @click="homeOptions='all'">
          <router-link :class="{active:homeOptions=='all'}" to="/all" href="">所有书本</router-link>
        </li>
        <li @click="homeOptions='recent'">
          <router-link :class="{active:homeOptions=='recent'}" to="/recent" href="">最近新书</router-link>
        </li>
        <li @click="homeOptions='recommend'">
          <router-link  :class="{active:homeOptions=='recommend'}" to="/recommend" href="">热销推荐</router-link>
        </li>
        <li @click="homeOptions='free'">
          <router-link :class="{active:homeOptions=='free'}" to="/free" href="">免费书本</router-link>
        </li>
        <li>
          <div class="search">
            <input placeholder="请输入书名" type="text"/>
            <i class="fa fa-search" aria-hidden="true"></i>
          </div>
        </li>
      </ul>
    </header>
    <section>
        <router-view></router-view>
    </section>
    <footer>

    </footer>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    name: 'Home',
    data(){
      return {homeOptions:'all'}
    }
  }

</script>
<style>
  /* 头部功能区 */
  header {
    position: relative;
    right: 0;
    top: 0;
    background-color: #EEF1F8;
  }

  header > span {
    padding: 20px 20px;
    display: block;
    text-align: left;
    font-size: 20px;
    font-weight: 600;
    color: #444c63;
    border-bottom: 1px solid #dadfea;
  }

  header ul {
    overflow: hidden;
    padding: 15px 20px;
    border-bottom: 1px solid #dadfea;
  }

  header ul > li {
    float: left;
  }

  header ul > li:last-child {
    float: right;
  }

  header ul > li > a {
    color: #8c97b2;
    text-decoration: none;
    display: block;
    width: 72px;
    height: 20px;
    line-height: 20px;
    border-radius: 12px;
    font-size: 14px;
    font-weight: 600;
  }

  header ul > li > a:hover,
  header ul > li > a.active{
    color: #fff;
    background-color: #97b3ce;
  }

  /* 搜索输入框 */
  header .search {
    position: relative;
  }

  header .search input {
    border-radius: 20px;
    border: none;
    padding: 5px 26px 5px 10px;
    height: 15px;
    font-size: 15px;
    color: #8c97b2;
  }

  header .search input::placeholder {
    color: #8c97b2;
  }

  header .search input:focus {
    border: none;
    outline: none;
  }

  header .search i {
    position: absolute;
    right: 8px;
    top: 4px;
  }

  /* 内容 */
  section {
    position: fixed;
    right: 0;
    left: 250px;
    bottom: 0;
    top: 120px;
    padding: 20px 20px 0 15px;
    overflow-x: hidden;
    overflow-y: scroll;
  }
  section::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #EDEDED;
  }
  section::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #535353;
  }
  section::-webkit-scrollbar {
    width: 5px;
    height: 1px;
  }

</style>


