import Vue from 'vue'
import Vuex from 'vuex'
import anonymous from './modules/anonymous.js'

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
    anonymous,
  },
})
