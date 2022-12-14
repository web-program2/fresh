import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'


Vue.config.productionTip = false;
Vue.prototype.$Vue = Vue;

const init = async ()=> {
  const accessToken = localStorage.getItem('accessToken');
  if(accessToken !== null){
    await store.dispatch('get_user_data', accessToken);
  }
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