import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);
import Home from '@/components/home';
import Library from '@/components/library'
import Ranking from '@/components/ranking'
import login from '@/components/login'

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
    }
  ]
})
