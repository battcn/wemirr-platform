<template xmlns: xmlns:>
  <div id="app">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import {mapState, mapMutations, mapGetters} from 'vuex'
  export default {
    name: 'App',
    data() {
      /* options:'home', */
      return { show: false, childrenOptions: 'all'}
    },
    computed: {
      /* 使用数组形式将状态变量加载进来,页面使用该变量更简便 */
    }
  }
</script>

<style>
  ul {
    margin: 0;
    padding: 0;
  }

  li {
    list-style: none;
  }
a{text-decoration: none;}
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  .header > i {
    line-height: 35px;
    font-size: 25px;
    cursor: pointer;
    margin-left: 10px;
    color: #fff;
  }
</style>
