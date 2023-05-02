import Vue from 'vue'
import App from './App.vue'
//引入VueRouter
import VueRouter from 'vue-router'
//引入router
import router from './router'
//引入element
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//引入store
import store from './store';

// 设置反向代理，前端请求默认发送到 http://localhost:8443/api
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false

//应用vuerouter
Vue.use(VueRouter)
Vue.use(ElementUI)


//跳转路由之前的验证
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.state.user.address) {
        next()
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)

new Vue({
    el:'#app',
    render:h => h(App),
    router,
    store
})
