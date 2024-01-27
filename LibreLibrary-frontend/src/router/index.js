import { createRouter, createWebHistory } from "vue-router";
import { authToken, logout } from "../libs/login";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/homeView.vue"),
      //   meta: {
      //     needLogin: true,
      //   },
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/loginView.vue"),
    },
    {
      path: "/signin",
      name: "signin",
      component: () => import("../views/signinView.vue"),
    },
    {
      path: "/navbartop",
      name: "navbartop",
      component: () => import("../components/navbarTop.vue"),
      // meta: {
      //   needLogin: true,
      // },
    },
    {
      path: "/book/:isbn",
      name: "book",
      component: () => import("../views/bookView.vue"),
      // meta: {
      //   needLogin: true,
      // },
    },
    {
      path: "/account",
      name: "account",
      component: () => import("../views/accountView.vue"),
      // meta: {
      //   needLogin: true,
      // },
    },
    {
      path: "/borrow",
      name: "borrow",
      component: () => import("../views/borrowView.vue"),
      // meta: {
      //   needLogin: true,
      // },
    },
  ],
});

router.beforeResolve(async (to) => {
  if (to.meta.needLogin) {
    const isLogin = window.localStorage.getItem("isLogin");
    const loa = window.localStorage.getItem("loa");

    if (!isLogin) {
      return { name: "login" };
    }
    if (loa < to.meta.loa) {
      logout();
      return { name: "login" };
    }

    const token = window.localStorage.getItem("token");
    const authResult = await authToken(token);
    if (!authResult.status) {
      return { name: "login" };
    }
  }
});

export default router;
