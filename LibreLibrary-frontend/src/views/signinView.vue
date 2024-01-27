<template>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-4">
          <div class="card shadow-lg border-0 rounded-lg mt-5">
            <div class="card-header">
              <h3 class="text-center font-weight-light my-4">會員註冊</h3>
            </div>
            <div class="card-body">
              <div class="form-floating mb-3">
                <input
                  class="form-control"
                  id="userPhoneNumber"
                  name="phoneNumber"
                  type="text"
                  v-model="signinData.phoneNumber"
                />
                <label>手機號碼</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  class="form-control"
                  id="userName"
                  name="userName"
                  type="text"
                  v-model="signinData.userName"
                />
                <label>姓名</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  class="form-control"
                  id="userPassword"
                  name="password"
                  type="password"
                  v-model="signinData.password"
                />
                <label>密碼設定</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  class="form-control"
                  id="userPasswordConfirm"
                  name="passwordConfirm"
                  type="password"
                  v-model="signinData.comfirmPassword"
                />
                <label>密碼確認</label>
              </div>

              <div class="text-center">
                <input
                  type="button"
                  class="btn btn-primary"
                  id="send"
                  value="註冊"
                  @click="submitSigninData()"
                />
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
import { RouterLink, useRouter } from "vue-router";
import { reactive } from "vue";
import axios from "axios";
import { useDialog, useMessage } from "naive-ui";
const URL = import.meta.env.VITE_API_JAVAURL;

/*
  Router
*/
const router = useRouter();

/*
Data
*/
const signinData = reactive({
  phoneNumber: "",
  password: "",
  userName: "",
  comfirmPassword: "",
});

const validSigninData = reactive({
  phoneNumber: "",
  password: "",
  userName: "",
});

/*
  Methods
*/
const submitSigninData = async () => {
  if (!signinData.phoneNumber.trim().length) {
    handleWarning("手機號碼不得為空");
    return;
  } else if (!isValidPhoneNumber(signinData.phoneNumber.trim())) {
    return;
  } else if (!signinData.userName.trim().length) {
    handleWarning("姓名不得為空");
    return;
  } else if (
    !signinData.password.trim().length ||
    !signinData.comfirmPassword.trim().length
  ) {
    handleWarning("密碼設定與確認不得為空");
    return;
  } else if (signinData.password.trim() !== signinData.comfirmPassword.trim()) {
    handleWarning("密碼設定與確認不一致");
    return;
  }
  const URLAPI = `${URL}/libuser/register`;
  validSigninData.phoneNumber = signinData.phoneNumber.trim();
  validSigninData.userName = signinData.userName.trim();
  validSigninData.password = signinData.password.trim();
  const response = await axios.post(URLAPI, validSigninData).catch((error) => {
    console.log(error.toJSON());
  });
  if (response.status == 200) {
    handleSuccess("註冊成功");
  } else {
    handleWarning("註冊失敗");
  }
  router.push("/");
};

const isValidPhoneNumber = (phoneNumber) => {
  const taiwanPhoneRegex = /^09\d{8}$/;
  let validatedResult = taiwanPhoneRegex.test(phoneNumber);
  if (!validatedResult) handleWarning("手機號碼格式不正確");
  return validatedResult;
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
