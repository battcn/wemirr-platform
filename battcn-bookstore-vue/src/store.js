import Vue from 'vue';
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);


const initData = {/* 数据存放 */
  state: {data: {},homeData:{homeClickList:{}},detailsData:{},contentsData:{}},
  mutations:{
    initContent(state,pageNum=1) {
      /* 初始化书库 */
      let url='http://localhost:9090/app/books?pageSize=20&pageNum='+pageNum;
      axios.get(url).then(function (response) {
        initData.state.data=response.data;
      }).catch(function (error) {
        console.log("请求发送错误"+error);
      });
    },
    iniHomeClickList(state,token){/* 获取首页顶部的点击榜数据 */
      let url='http://localhost:9090/app/books/recommends';
      let header=token===undefined?{}:{headers:{'X-Authorization':token}};
      axios.get(url,header).then(function (response) {
        initData.state.homeData.homeClickList=response.data;
      }).catch(function (error) {
        console.log("请求发送错误"+error);
      });
    },
    iniBookDetails(state,bookNo) {/* 根据书号获取书详情 */
      let url='http://localhost:9090/app/books/'+bookNo;
      axios.get(url).then(function (response) {
        initData.state.detailsData=response.data;
      }).catch(function (error) {
        console.log("请求发送错误"+error);
      });
    },
    iniBookContents(state,bookNo){/* 根据书编号获取书籍 */
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




