import Vue from 'vue'
import Vuex from 'vuex'
import anonymous from './modules/anonymous.js'
import catalog from './modules/catalog.js'
import order from './modules/order.js'
import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex)
export default new Vuex.Store({
  // state: {
  // },
  // getters: {
  // },
  // mutations: {
  // },
  // actions: {
  // },
  modules: {
    anonymous, catalog, order
  },
  // plugins: [
  //   createPersistedState()
  // ]
})
