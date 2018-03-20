<template>
  <div class="home">
    <div class="header">
      <i v-show="fullWidth<480" @click="show=!show" class="fa fa-navicon"></i>
    </div>
    <sidebar v-if="show|fullWidth>480" ></sidebar>
    <div id="content">
      <header>
        <span>某某APP所有书本</span>
        <ul>
          <li @click="homeOptions='all'">
            <a :class="{active:homeOptions=='all'}" href="javascript:">所有书本</a>
          </li>
          <li @click="homeOptions='recent'">
            <a :class="{active:homeOptions=='recent'}" href="javascript:">最近新书</a>
          </li>
          <li @click="homeOptions='recommend'">
            <a :class="{active:homeOptions=='recommend'}" href="javascript:">热销推荐</a>
          </li>
          <li @click="homeOptions='free'">
            <a :class="{active:homeOptions=='free'}" href="javascript:">免费书本</a>
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
        <all :homeOptions="homeOptions"></all>
      </section>
      <footer>

      </footer>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import sidebar from './sidebar.vue'
  import all from './homeChildren/all.vue';

  export default {
    name: 'Home',
    components: {all, sidebar},
    data() {
      return {fullWidth: document.documentElement.clientWidth,show: false, childrenOptions: 'all', homeOptions: 'all'}
    },
    mounted() {
      const that = this;
      window.onresize = () => {
        return (() => {
          window.fullWidth = document.documentElement.clientWidth;
          that.fullWidth = window.fullWidth;
          this.fullWidth > 480 && this.show === true ? this.show = false : '';
        })()
      }
    },
    watch: {
      fullWidth(val) {
        if (!this.timer) {
          this.fullWidth = val;
          this.timer = true;
          let that = this;
          setTimeout(function () {
            that.timer = false;
          }, 400)
        }
      }
    }
  }

</script>
<style>
  /* 内容主框 */
  #content {
    margin-left: 250px;
  }
  /* 头部 */
  .header {
    background-color: #2F3649;
    height: 35px;
    text-align: left;
  }

  .header > i {
    line-height: 35px;
    font-size: 25px;
    cursor: pointer;
    margin-left: 10px;
    color: #fff;
  }

  /* 头部功能区 */
  header {
    position: relative;
    right: 0;
    top: 0;
    background-color: #EEF1F8;
  }

  header > i {
    position: absolute;
    top: 22px;
    left: 3px;
    cursor: pointer;
  }

  header > span {
    padding: 12px 20px;
    display: block;
    text-align: left;
    font-size: 20px;
    font-weight: 600;
    color: #444c63;
    border-bottom: 1px solid #dadfea;
  }

  header ul {
    overflow: hidden;
    padding: 11px 20px;
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
    height: 25px;
    line-height: 25px;
    border-radius: 12px;
    font-size: 14px;
    font-weight: 600;
  }

  header ul > li > a:hover,
  header ul > li > a.active {
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
    z-index: -1;
    position: fixed;
    right: 0;
    left: 250px;
    bottom: 0;
    top: 131px;
    padding: 20px 20px 0 15px;
    overflow-x: hidden;
    overflow-y: scroll;
  }

  section::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    background: #EDEDED;
  }

  section::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: #535353;
  }

  section::-webkit-scrollbar {
    width: 5px;
    height: 1px;
  }

  /* 响应式样式 */
  @media (max-width: 803px) {
    section {
      top: 156px;
    }

    header ul {
      padding: 11px 20px;
    }
  }

  /* 超小屏幕（手机，大于等于 630px） */
  @media (max-width: 630px) {
    section {
      left: 150px;
      top: 164px;
    }

    header ul {
      padding: 15px 5px;
    }
    #content {
      margin-left: 150px;
    }
  }

  @media (max-width: 480px) {
    section {
      left: 0;
    }
    #content {
      margin-left: 0;
    }
  }

</style>


