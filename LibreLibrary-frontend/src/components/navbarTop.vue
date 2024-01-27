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
            <RouterLink class="nav-link" to="/account">我的書房</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/login">登入</RouterLink>
          </li>
          <li class="nav-item dropdown">
            <a
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
import { ref } from "vue";
import { RouterLink, useRoute, useRouter } from "vue-router";
import { useDialog, useMessage } from "naive-ui";

/*
  Router
*/
const router = useRouter();

/*
  Method
*/
const logout = () => {
  window.localStorage.setItem("isLogin", "");
  window.localStorage.setItem("token", "");
  window.localStorage.setItem("username", "");
  window.localStorage.setItem("phoneNumber", "");
  handleSuccess("您已登出，期待您再次蒞臨");
  router.push("/");
};

/*
  Naive UI components
*/
const dialog = useDialog();
const handleSuccess = (contentText) => {
  dialog.success({
    title: "成功",
    content: contentText,
    positiveText: "確定",
  });
};
</script>

<style scoped></style>
