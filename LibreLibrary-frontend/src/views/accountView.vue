<template>
  <header>
    <NavbarTop></NavbarTop>
  </header>
  <body>
    <section class="page-section">
      <div class="container" style="padding-top: 2%; padding-bottom: 1%">
        <h1 class="text-center mb-3">我的書房</h1>
      </div>
      <!-- Order table start -->
      <div class="row justify-content-center">
        <div class="col-11 col-md-9 col-lg-9">
          <div>
            <div
              @click="borrowAllBooks()"
              class="btn btn-sm btn-outline-primary"
            >
              批次還書
            </div>
            <table class="table caption-top">
              <caption>
                已借閲書籍
              </caption>
              <thead class="table-light text-left">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">ISBN</th>
                  <th scope="col">書名</th>
                  <th scope="col">作者</th>
                  <th scope="col">借書日</th>
                  <th scope="col">到期日</th>
                  <th scope="col">還書</th>
                </tr>
              </thead>
              <tbody v-for="(item, index) in userBorrowedData">
                <tr>
                  <th scope="row">{{ index + 1 }}</th>
                  <td>{{ item.isbn }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.author }}</td>
                  <td>{{ item.borrowingTime }}</td>
                  <td>{{ item.expirationTime }}</td>
                  <td>
                    <div
                      @click="returnBook()"
                      class="btn btn-sm btn-outline-primary"
                    >
                      還書
                    </div>
                  </td>
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
 * imports
 */
const URL = import.meta.env.VITE_API_JAVAURL;
import { ref, reactive, onMounted, watch } from "vue";
import axios from "axios";
import { useDialog, useMessage } from "naive-ui";
import NavbarTop from "../components/navbarTop.vue";

/**
 * Load Datas
 */
// Load user borrowed books data
const userBorrowedData = ref([]);

const loadUserBorrowedData = async () => {
  const URLAPI = `${URL}/br/getborrowed`;
  const response = await axios
    .get(URLAPI, {
      params: {
        userId: window.localStorage.getItem("userId"),
      },
    })
    .catch((error) => {
      console.log(error.toJSON());
    });
  console.log(response.data);
  userBorrowedData.value = response.data;
};

/**
 * Return Books
 */
const returnBooksData = reactive({
  userId: "",
  inventoryIds: [],
});

const returnBook = async (inventoryId) => {
  const URLAPI = `${URL}/br/return`;
  returnBooksData.userId = window.localStorage.getItem("userId");
  returnBooksData.inventoryIds = [inventoryId];

  const response = await axios.put(URLAPI, returnBooksData).catch((error) => {
    console.log(error.toJSON());
  });

  if (response.status == 200) {
    handleSuccess("還書成功");
    loadUserBorrowedData();
  } else {
    handleWarning("還書失敗");
  }
};

const borrowAllBooks = async () => {
  const URLAPI = `${URL}/br/return`;
  returnBooksData.userId = window.localStorage.getItem("userId");
  for (const iterator of userBorrowedData.value) {
    returnBooksData.inventoryIds.push(iterator[inventoryId]);
  }

  const response = await axios.put(URLAPI, returnBooksData).catch((error) => {
    console.log(error.toJSON());
  });

  if (response.status == 200) {
    handleSuccess("還書成功");
    bookBorrowStore.value = [];
    loadUserBorrowedData();
  } else {
    handleWarning("還書失敗");
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
    title: "小提醒",
    content: contentText,
    positiveText: "確定",
  });
};

/**
 * LifeCycle Hooks
 */
onMounted(() => {
  loadUserBorrowedData();
});
</script>

<style scoped></style>
