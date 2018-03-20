import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);
import Home from '@/components/home';
import Details from '@/components/bookDetails';
import ShoppingCart from '@/components/shoppingCart';
import Collection from '@/components/collection';
export default new Router({
  mode:'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/Details',
      name: 'Details',
      component: Details
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
