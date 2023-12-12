import { createRouter, createWebHistory } from 'vue-router'
import AuthGuards from '../services/guards/AuthGuards';
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: { name: "home" }
    },
    {
      path: "/home",
      name: "home",
      component: () => import('../views/Home.vue')
    },
    {
      path: "/reservation",
      name: "reservation",
      component: () => import('../views/Reservation.vue')
    },
    {
      path: "/reservation/:id",
      name: "details",
      component: () => import('../views/Details.vue')
    },
    {
      path: "/reservation/:filters",
      name: "reservationFilters",
      component: () => import('../views/Reservation.vue')
    },
    ///DASHBOARD///
    {
      path: "/dashboard",
      name: "dashboard",
      component: () => import('../views/Dashboard.vue')
    },
    ///LOGIN///
    {
      path: "/login",
      name: "login",
      component: () => import('../views/Login.vue'),
      beforeEnter: AuthGuards.noAuthGuard, meta: { requiresAuth: true }
    },
    {
      path: "/register",
      name: "register",
      component: () => import('../views/Register.vue'),
      beforeEnter: AuthGuards.noAuthGuard, meta: { requiresAuth: true }
    },
    {
      path: "/profile",
      name: "profile",
      component: () => import('../views/Profile.vue'),
      beforeEnter: AuthGuards.AuthGuard, meta: { requiresAuth: true }
    },
    // //Category dashboard//
    {
      path: "/dashboard/categories",
      name: "categoriesList",
      component: () => import('../views/Category/CategoryList.vue')
    },
    {
      path: "/dashboard/categories/create",
      name: "categoriesCreate",
      component: () => import('../views/Category/CategoryCreate.vue')
    },
    {
      path: "/dashboard/categories/update/:id",
      name: "categoriesUpdate",
      component: () => import('../views/Category/CategoryUpdate.vue')
    },

    // //Mesa dashboard//
    {
      path: "/dashboard/mesas",
      name: "mesasList",
      component: () => import('../views/Mesa/MesaList.vue')
    },
    {
      path: "/dashboard/mesas/update/:id",
      name: "updateMesa",
      component: () => import('../views/Mesa/MesaUpdate.vue')
    },
    {
      path: "/dashboard/mesas/create",
      name: "createMesa",
      component: () => import('../views/Mesa/MesaCreate.vue')
    },
  ]
})

export default router