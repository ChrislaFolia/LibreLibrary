<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <RouterLink class="navbar-brand" to="/">LibreLibrary</RouterLink>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/">圖書館首頁</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink
              v-if="isLoginNavbarTop == 'true' || isLoginNavbarTop == true"
              class="nav-link"
              to="/account"
              >我的書房</RouterLink
            >
          </li>
          <li class="nav-item">
            <RouterLink
              v-if="isLoginNavbarTop == 'true' || isLoginNavbarTop == true"
              class="nav-link"
              to="/borrow"
              >我的借書籃 ({{ borrowStoreAmount }})</RouterLink
            >
          </li>
          <li class="nav-item">
            <RouterLink
              v-if="isLoginNavbarTop == 'false' || isLoginNavbarTop == false"
              class="nav-link"
              to="/login"
              >登入</RouterLink
            >
          </li>
          <li class="nav-item dropdown">
            <a
              v-if="isLoginNavbarTop == 'true' || isLoginNavbarTop == true"
              class="nav-link dropdown-toggle"
              data-bs-toggle="dropdown"
              href="#"
              role="button"
              aria-expanded="false"
              >您好 User
            </a>
            <ul class="dropdown-menu">
              <li>
                <div class="dropdown-item btn" @click="logout()">登出</div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
/*
  imports
*/
import { ref, onMounted, onUpdated, computed } from "vue";
import { RouterLink, useRouter } from "vue-router";
import { useMessage } from "naive-ui";
import { useBorrowStore } from "../stores/borrowStore";
import { storeToRefs } from "pinia";

/*
  Check whether login
*/
const isLoginNavbarTop = ref("false");

/*
  Store and session storage
*/
const borrowStore = useBorrowStore();
const { bookBorrowStore } = storeToRefs(borrowStore);

/*
  Router
*/
const router = useRouter();

/*
  Method
*/
const logout = () => {
  window.localStorage.setItem("isLogin", false);
  window.localStorage.setItem("token", "");
  window.localStorage.setItem("userName", "");
  window.localStorage.setItem("phoneNumber", "");
  window.localStorage.setItem("userId", "");
  bookBorrowStore.value = [];
  handleMessage("您已登出，期待您再次蒞臨");
  // handleSuccess();
  router.push("/login");
};

/**
 * watcher
 */
let borrowStoreAmount = computed(() => {
  if (bookBorrowStore.value !== null || bookBorrowStore.value !== undefined) {
    return bookBorrowStore.value.length;
  } else {
    return 0;
  }
});

/*
  Naive UI components
*/
const messageNaive = useMessage();

const handleMessage = (messageText) => {
  messageNaive.success(messageText, {
    closable: true,
    duration: 5000,
  });
};

/*
  LifeCycle Hooks
*/
onMounted(() => {
  isLoginNavbarTop.value = window.localStorage.getItem("isLogin");
});

onUpdated(() => {
  isLoginNavbarTop.value = window.localStorage.getItem("isLogin");
});
</script>

<style scoped></style>
