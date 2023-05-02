import Vue from 'vue'
import Router from 'vue-router'
// 导入刚才编写的组件
import AppIndex from '@/components/Home/AppIndex'
import Login from '@/components/Login'
import Home from '../components/Home.vue'
import  MailIndex from '../components/mail/MailIndex'
import ContactIndex from '../components/contact/ContactIndex'
import MailContent from '../components/mail/MailContent'
import MailTable from '../components/mail/MailTable'
import OutboxIndex from '../components/outbox/OutboxIndex'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
  // 下面都是固定的写法
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/mail',
          name: 'Mail',
          component: MailIndex,
          meta: {
            requireAuth: true
          },
          children: [
            {
              path: '',
              name: 'MailTable',
              component: MailTable
            },
            {
              path: ':id',
              name: 'MailContent',
              component: MailContent
            }
          ]
        },
        {
          path: '/contact',
          name: 'Contact',
          component: ContactIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/outbox',
          name: 'Outbox',
          component: OutboxIndex,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})

