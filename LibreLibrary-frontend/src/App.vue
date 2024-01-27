<script setup>
import { RouterView } from "vue-router";
import { ref, onMounted } from "vue";
import { NDialogProvider, NMessageProvider } from "naive-ui";

const isLogin = ref([]);

onMounted(() => {
  isLogin.value = window.localStorage.getItem("isLogin");
  // console.log("isLogin App:" + isLogin.value);
});
</script>

<template>
  <n-dialog-provider>
    <n-message-provider>
      <router-view></router-view>
    </n-message-provider>
  </n-dialog-provider>
</template>

<style scoped></style>
