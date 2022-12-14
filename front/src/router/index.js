import Vue from 'vue'
import VueRouter from 'vue-router'
import signIn from '../views/auth/SignIn.vue'
import signUp from '../views/auth/SignUp.vue'
import findId from '../views/auth/FindId.vue'
import findPw from '../views/auth/FindPw.vue'
import authLayout from '../views/auth/layout/Index.vue';

import coreLayout from '../views/core/layout/Index.vue';
import home from '../views/core/Home.vue';
import catalog from '../views/core/Catalog.vue';
import notice from '../views/core/Notice.vue';
import about from '../views/core/About.vue';
import order from '../views/core/Order.vue';

import orderClick from '../views/core/ClickOrder.vue';
import catalogClick from '../views/core/ClickCatalog.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect : '/home',
  },
  // auth router
  {
    path:'/auth',
    component: authLayout,
    children:[
      {
        path:"signIn",
        name:'signIn',
        component: signIn
      },
      {
        path:"signUp",
        name:'signUp',
        component: signUp
      },
      {
        path:"findId",
        name:'findId',
        component: findId
      },
      {
        path:"findPw",
        name:'findPw',
        component: findPw
      },
    ]
  },
  {
    path : '/',
    component : coreLayout,
    children : [
      {
        path : 'home',
        component : home
      },
      {
        path : 'catalog',
        component : catalog
      },
      {
        path : 'catalog/:catalogIdx',
        component : catalogClick,
      },
      {
        path : 'about',
        component : about
      },
      {
        path : 'notice',
        component : notice
      },
      {
        path : 'order',
        component : order
      },
      {
        path : 'order/:orderIdx',
        component : orderClick,
      },

    ]
  },  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
