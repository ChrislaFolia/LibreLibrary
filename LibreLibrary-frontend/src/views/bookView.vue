<template>
  <header>
    <NavbarTop></NavbarTop>
  </header>
  <body>
    <section class="page-section">
      <div class="container" style="padding-top: 2%; padding-bottom: 1%">
        <h1 class="text-center mb-1">館藏狀況</h1>
      </div>
      <!--Card Start-->
      <div class="row justify-content-center">
        <div class="card col-11 col-md-9 col-lg-9 h-100 mb-4">
          <div class="row justify-content-center align-items-center">
            <!-- <div class="col-4 mx-3 mb-3">
              <img
                :src="`https://picsum.photos/id/24/400/250`"
                class="card-img-top mt-3"
                alt="..."
              />
            </div> -->
            <div class="col-4 mx-3 mb-3">
              <img
                :src="`src/assets/figure/oldbook.jpg`"
                class="card-img-top mt-3"
                alt="..."
              />
            </div>
            <div class="col-6 mx-3">
              <div class="my-3">
                <div class="row align-items-center">
                  <div class="rol">
                    <div>書名:</div>
                  </div>
                  <div>
                    <span>{{ bookData.name }}</span>
                  </div>
                </div>
              </div>

              <div class="my-3">
                <div>作者:&nbsp;{{ bookData.author }}</div>
              </div>

              <div class="my-3">
                <div>ISBN:&nbsp;{{ bookData.isbn }}</div>
              </div>

              <div class="ml-2 my-3">
                <div>書本介紹:</div>
                <p class="fs-6">
                  {{ bookData.introduction }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Card end-->
      <!-- Order table start -->
      <div class="row justify-content-center">
        <div class="col-11 col-md-9 col-lg-7">
          <div>
            <table class="table table-hover caption-top">
              <caption>
                本館館藏
              </caption>
              <thead class="table-light text-center">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">館藏地點</th>
                  <th scope="col">館藏狀況</th>
                  <th scope="col">借閲</th>
                </tr>
              </thead>
              <tbody v-for="(item, index) in allInventaryData">
                <tr class="text-center">
                  <td scope="row">{{ index + 1 }}</td>
                  <td>總舘</td>
                  <td>{{ item.status }}</td>
                  <td class="text-center">
                    <div
                      @click="addToBooBorrowStore(item.inventoryId)"
                      v-if="item.status == '在庫'"
                      class="btn btn-sm btn-outline-primary"
                    >
                      放入借書籃
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- Order table end -->
      <div class="text-center">
        <RouterLink class="btn btn-sm btn-primary" to="/">回首頁</RouterLink>
      </div>
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

/**
 * Router
 */
const route = useRoute();

/**
 * Load Datas
 */
// Load Book data
const bookData = ref({});
const loadSingleBookData = async () => {
  const URLAPI = `${URL}/book/single`;
  const response = await axios
    .get(URLAPI, {
      params: {
        isbn: route.query.isbn,
      },
    })
    .catch((error) => {
      console.log(error.toJSON());
    });

  bookData.value = response.data;
};

// Load Inventary data
const allInventaryData = ref([]);
const loadAllInventaryData = async () => {
  const URLAPI = `${URL}/book/detail`;
  const response = await axios
    .get(URLAPI, {
      params: {
        isbn: route.query.isbn,
      },
    })
    .catch((error) => {
      console.log(error.toJSON());
    });

  allInventaryData.value = response.data;
};

/*
  Store and session storage
*/
const borrowStore = useBorrowStore();
const { bookBorrowStore } = storeToRefs(borrowStore);

/**
 * Add inventory to bookBorrowStore
 */
const addToBooBorrowStore = (inventoryId) => {
  if (!bookBorrowStore.value.includes(inventoryId)) {
    bookBorrowStore.value.push(inventoryId);
    handleSuccess("已加入借書籃");
  } else {
    handleWarning("本書已經在借書籃中");
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
  loadSingleBookData();
  loadAllInventaryData();
});
</script>
<style scoped>
.card-img-top {
  width: 350px;
  height: 250px;
}
</style>
