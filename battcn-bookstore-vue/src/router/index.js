import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);
import Home from '@/components/home';
import all from '@/components/homeChildren/all';
import recent from '@/components/homeChildren/recent';
import recommend from '@/components/homeChildren/recommend';
import free from '@/components/homeChildren/free';


import ShoppingCart from '@/components/shoppingCart';
import Collection from '@/components/collection';
export default new Router({
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/ShoppingCart',
      name: 'ShoppingCart',
      component: ShoppingCart
    },
    {
      path: '/Collection',
      name: 'Collection',
      component: Collection
    }
  ]
})
