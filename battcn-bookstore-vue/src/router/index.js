import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);
import Home from '@/components/home';
import Library from '@/components/library'
import Ranking from '@/components/ranking'
import maleChannel from '@/components/maleChannel'
import femaleChannel from '@/components/femaleChannel'
import login from '@/components/login'
import registered from '@/components/registered'
import yourseInfo from '@/components/yourseInfo'
import Info_home from '@/components/infoChildren/home'

export default new Router({
  mode:'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/', /* 主页 */
      name: 'Home',
      component: Home
    },
    {
      path:'/library',
      name:'Library',
      component:Library
    },
    {
      path:'/ranking',
      name:'Ranking',
      component:Ranking
    },
    {
      path:'/login',
      name:'login',
      component:login
    },
    {
      path:'/registered',
      name:'registered',
      component:registered
    },
    {
      path:'/maleChannel',
      name:'maleChannel',
      component:maleChannel
    },
    {
      path:'/femaleChannel',
      name:'femaleChannel',
      component:femaleChannel
    },
    {
      path:'/yourseInfo',
      name:'yourseInfo',
      component:yourseInfo,
      children:[
        {path:'/',component:Info_home},
        {path:'bookshelf',component:Info_home}
      ]
    }
  ]
})
