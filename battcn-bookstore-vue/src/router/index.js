import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);
import Home from '@/components/home';
import Details from '@/components/bookDetails';
import Contents from  '@/components/contents'
import Read from '@/components/bookRead'
import ShoppingCart from '@/components/shoppingCart';
import Collection from '@/components/collection';
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
      path: '/Details',/* 书籍简介 */
      name: 'Details',
      component: Details
    },
    {
      path:'/Contents',
      name:'Contents',
      component:Contents/* 目录 */
    },
    {/* 阅读界面 */
      path:'/Read',
      name:'Read',
      component:Read
    },
    {
      path: '/ShoppingCart',/* 购物车 */
      name: 'ShoppingCart',
      component: ShoppingCart
    },
    {
      path: '/Collection', /* 收藏夹 */
      name: 'Collection',
      component: Collection
    }
  ]
})
