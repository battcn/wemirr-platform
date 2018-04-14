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
      let config = token===undefined||token===null?{}:{headers:{'X-Authorization':token}};
      axios.get(url,config).then(function (response) {
        if(initData.state.homeData&&initData.state.homeData.homeClickList){
          initData.state.homeData.homeClickList= response.data;
        }
      }).catch(function (error) {
        console.log(error.message);
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
};

const Paging = {/* 辅助操作部分，分页跳转 */
  state:{},
  mutations:{
    /* 分页跳转：传入目标页数 */
    jumpPage(state,pageNum){
      init(pageNum);
    }
  }
}
const Account ={/* 账号身份部分 */
  state:{codeImg:null,clientId:null,token:null,infoData:null},
  mutations:{
    getVerificatCode(state,token){/* 获取验证码 */
      let url = "http://localhost:9090/v0.1/captcha";
      let config = token===undefined||token===null?{}:{headers:{'X-Authorization':token}};
      axios.get(url,config).then((response) =>{
        let clientId=response.data&&response.data.clientId;
        if(clientId===null || clientId === undefined){
          let  err;
          try {
            throw  err = new Error('获取客户端标识码失败');
          }catch (err){
            console.log(err);
          }
        }
        Account.state.clientId=clientId;
        let config = {headers:{'X-Authorization':null},responseType: 'arraybuffer'};
        let url = "http://localhost:9090/v0.1/captcha/"+clientId;
       axios.get(url,config).then(function (response) {
         Account.state.codeImg = response.data;
       }).catch(function (error) {
         console.log("请求发送错误"+error);
       })
      }).catch(function (error) {
        console.log("获取客户端标识码失败"+error);
      })
    },
    judgeVerificatCode(state,info){/* 验证码后台验证请求 */
      let _this = this;
      let url = 'http://localhost:9090/authorized/register/'+Account.state.clientId+'/'+(info['code']?info['code']:'');
      let config = info['X-Authorization']?{}:{headers:{'X-Authorization':info['X-Authorization']}};
      let data = info['context'];
      axios.post(url,data,config).then((response)=>{
        Account.state.token="Bearer ";
        response.data&&response.data.token?Account.state.token=Account.state.token+response.data.token:'';
        localStorage.setItem('token',Account.state.token);
       // console.log(info['$router']);
        info['$router'].push('/yourseInfo');/* 跳转到个人信息 */
      }).catch(function (error) {
        console.log("验证码输入错误");
        _this.commit('getVerificatCode');
      })
    },
    getUserinfo(state){/* 根据存储在本地的token获取个人信息 */
      let token = Account.state.token || localStorage.getItem("token");
      let url = 'http://localhost:9090/members';
      let config = token?{headers:{'X-Authorization':token}}:{};
      axios.get(url,config).then((response)=>{
        Account.state.infoData=response.data;
      }).catch(function (err) {
        console.log("令牌无效或已过期"+err);
      })
    },
    setUserinfo(state){},
    }
}
export default new Vuex.Store({
  modules:{
    initData:initData,
    Paging:Paging,
    Account:Account
  }
})




