<template>
  <header>
    <NavbarTop></NavbarTop>
  </header>
  <body>
    <section class="page-section">
      <div class="container" style="padding-top: 2%; padding-bottom: 1%">
        <h1 class="text-center mb-3">我的借書籃</h1>
      </div>
      <!-- Order table start -->
      <div class="row justify-content-center">
        <div class="col-11 col-md-9 col-lg-7">
          <div>
            <div
              @click="borrowAllBooks()"
              class="btn btn-sm btn-outline-primary"
            >
              批次借書
            </div>
            <table class="table caption-top">
              <caption>
                已借閲書籍
              </caption>
              <thead class="table-light text-left">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">書名</th>
                  <th scope="col">作者</th>
                  <th scope="col">館藏狀態</th>
                  <th scope="col">借書</th>
                  <th scope="col">移除</th>
                </tr>
              </thead>
              <tbody
                v-if="borrowInventaryData.length"
                v-for="(item, index) in borrowInventaryData"
              >
                <tr>
                  <td scope="row">{{ index + 1 }}</td>
                  <td>{{ item.book.name }}</td>
                  <td>{{ item.book.author }}</td>
                  <td>{{ item.status }}</td>
                  <td>
                    <div
                      @click="borrowBook(item.inventoryId)"
                      v-if="item.status == '在庫'"
                      class="btn btn-sm btn-outline-primary"
                    >
                      借書
                    </div>
                  </td>
                  <td>
                    <div
                      @click="removeBook()"
                      class="btn btn-sm btn-outline-warning"
                    >
                      移除
                    </div>
                  </td>
                </tr>
              </tbody>
              <tbody v-else>
                <tr class="text-center">
                  <td colspan="6">空的借書籃，快去借書吧！</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- Order table end -->
    </section>
  </body>
</template>
<script setup>
/**
 * Imports
 */

const URL = import.meta.env.VITE_API_JAVAURL;
import { ref, reactive, onMounted, watch } from "vue";
import { RouterLink, useRouter, useRoute } from "vue-router";
import NavbarTop from "../components/navbarTop.vue";
import axios from "axios";
import { useBorrowStore } from "../stores/borrowStore";
import { storeToRefs } from "pinia";
import { useDialog, useMessage } from "naive-ui";

/*
  Store and session storage
*/
const borrowStore = useBorrowStore();
const { bookBorrowStore } = storeToRefs(borrowStore);

/**
 * Load Datas
 */
// Load Inventary data
const borrowInventaryData = ref([]);
const wishBorrowData = reactive({
  userId: "",
  inventoryIds: [],
});

const loadBorrowInventaryData = async () => {
  const URLAPI = `${URL}/book/detail/ids`;
  if (bookBorrowStore.value.length != 0) {
    wishBorrowData.inventoryIds = bookBorrowStore.value;
    wishBorrowData.userId = window.localStorage.getItem("userId");
    const response = await axios.post(URLAPI, wishBorrowData).catch((error) => {
      console.log(error.toJSON());
    });
    borrowInventaryData.value = response.data;
  } else {
    borrowInventaryData.value = [];
  }
};

/**
 * Borrow Books
 */
const borrowData = reactive({
  userId: "",
  inventoryIds: [],
});

const borrowBook = async (inventoryId) => {
  const URLAPI = `${URL}/br/borrow`;
  borrowData.userId = window.localStorage.getItem("userId");
  borrowData.inventoryIds = [inventoryId];

  const response = await axios.post(URLAPI, borrowData).catch((error) => {
    console.log(error.toJSON());
  });

  if (response.status == 200) {
    handleSuccess("借閱成功");
    bookBorrowStore.value.splice(bookBorrowStore.value.indexOf(inventoryId), 1);
    loadBorrowInventaryData();
  } else {
    handleWarning("借閱失敗");
  }
};

const borrowAllBooks = async () => {
  const URLAPI = `${URL}/br/borrow`;
  borrowData.userId = window.localStorage.getItem("userId");
  borrowData.inventoryIds = bookBorrowStore.value;

  const response = await axios.post(URLAPI, borrowData).catch((error) => {
    console.log(error.toJSON());
  });

  if (response.status == 200) {
    handleSuccess("借閱成功");
    bookBorrowStore.value = [];
    loadBorrowInventaryData();
  } else {
    handleWarning("借閱失敗");
  }
};

/**
 * Remove Books
 */
const removeBook = (inventoryId) => {
  bookBorrowStore.value.splice(bookBorrowStore.value.indexOf(inventoryId), 1);
  handleSuccess("成功移除書籍");
  loadBorrowInventaryData();
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
    title: "小提醒",
    content: contentText,
    positiveText: "確定",
  });
};

/**
 * LifeCycle Hooks
 */
onMounted(() => {
  loadBorrowInventaryData();
});
</script>

<style scoped></style>
