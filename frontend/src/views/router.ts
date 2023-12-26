import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/TasksView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // {
    //   name: 'tenants',
    //   path: '/tenants',
    //   component: () => import('@/views/tenant/list/TenantView.vue'),
    // },
    {
      name: 'home',
      path: '/',
      component: HomeView,
    },
    {
      path: '/:pathMatch(.*)*',
      component: HomeView,
    },
  ],
})

export default router
