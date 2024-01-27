<template>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-4">
          <div class="card shadow-lg border-0 rounded-lg mt-5">
            <div class="card-header">
              <h3 class="text-center font-weight-light my-4">登入我的書房</h3>
            </div>
            <div class="card-body">
              <div class="form-floating mb-3">
                <input
                  class="form-control"
                  id="userAccount"
                  name="account"
                  type="text"
                  v-model="loginData.phoneNumber"
                />
                <label>手機號碼</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  class="form-control"
                  id="userPassword"
                  name="password"
                  type="password"
                  v-model="loginData.password"
                />
                <label>密碼</label>
              </div>

              <div class="row justify-content-center">
                <div class="col-3">
                  <RouterLink type="button" class="btn btn-primary" to="/signin"
                    >去註冊</RouterLink
                  >
                </div>
                <div class="col-3">
                  <input
                    type="button"
                    class="btn btn-primary"
                    id="send"
                    value="登入"
                    @click="submit()"
                  />
                </div>
              </div>
              <div class="text-center">
                <RouterLink type="button" class="btn btn-link" to="/"
                  >回首頁</RouterLink
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>
<script setup>
/*
  imports
*/
const URL = import.meta.env.VITE_API_JAVAURL;
import { RouterLink, useRouter } from "vue-router";
import { login } from "../libs/login";
import { reactive } from "vue";
import { useDialog, useMessage } from "naive-ui";
import axios from "axios";

/*
  Data
*/
const loginData = reactive({
  phoneNumber: "",
  password: "",
});

/*
  Router
*/
const router = useRouter();

/*
  Method
*/
const submit = async () => {
  const res = await login(loginData.phoneNumber, loginData.password);
  if (res.status == 0) {
    handleSuccess("登入成功");
    router.push("/");
  } else {
    handleWarning("登入失敗，請重新輸入");
    loginData.phoneNumber = "";
    loginData.password = "";
  }
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

const handleWarning = (contentText) => {
  dialog.warning({
    title: "輸入資料有誤！",
    content: contentText,
    positiveText: "確定",
  });
};
</script>
<style scoped>
body {
  margin: 0;
  padding: 0;
  background-image: url(src/assets/figure/bookfigure_background.jpg);
  background-size: cover;
  background-position: center;
  height: 100vh;
}
</style>
