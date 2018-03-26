import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);
import Home from '@/components/home';
import Library from '@/components/library'
import Ranking from '@/components/ranking'

export default new Router({
  mode:'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/home', /* 主页 */
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
    }
  ]
})
