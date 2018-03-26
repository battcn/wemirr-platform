import Vue from 'vue';
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);
function init(pageNum=1) {
  /* 初始化书库 */
  let url='http://localhost:9090/app/books?pageSize=20&pageNum='+pageNum;
  axios.get(url).then(function (response) {
    initData.state.data=response.data;
  }).catch(function (error) {
    console.log("请求发送错误"+error);
  });
}

const initData = {/* 数据存放 */
  state: {data: {},detailsData:{},contentsData:{}},
  mutations:{
    iniBookDetails(state,bookNo) {/* 根据书号获取书详情 */
      let url='http://localhost:9090/app/books/'+bookNo;
      axios.get(url).then(function (response) {
        initData.state.detailsData=response.data;
      }).catch(function (error) {
        console.log("请求发送错误"+error);
      });
    },
    iniBookContents(state,bookNo){
      let url='http://localhost:9090/app/books/'+bookNo+'/chapters?pageSize=10';
      axios.get(url).then(function (response) {
        initData.state.contentsData=response.data;
      }).catch(function (error) {
        console.log("请求发送错误"+error);
      });
    }
  }
}

const Paging = {/* 分页跳转 */
  state:{},
  mutations:{
    /* 分页跳转：传入目标页数 */
    jumpPage(state,pageNum){
      init(pageNum);
    }
  }
}


//init();


export default new Vuex.Store({
  modules:{
    initData:initData,
    Paging:Paging
  }
})




