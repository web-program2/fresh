import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'


Vue.config.productionTip = false;
Vue.prototype.$Vue = Vue;

const init = async ()=> {
  


  new Vue({
    router,
    store,
    vuetify,
    render: h => h(App),
  }).$mount('#app')
}

try{
  init();
}catch(err){
  console.log(err);
}