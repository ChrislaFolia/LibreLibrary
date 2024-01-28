<template>
  <header>
    <NavbarTop></NavbarTop>
  </header>
  <body>
    <section class="page-section">
      <div class="container" style="padding-top: 2%; padding-bottom: 1%">
        <h1 class="text-center mb-1">圖書館首頁</h1>
      </div>
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
                  <th scope="col">書名</th>
                  <th scope="col">作者</th>
                  <th scope="col">借閲</th>
                </tr>
              </thead>
              <tbody v-for="(item, index) in allBookData">
                <tr>
                  <td scope="row">{{ index + 1 }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.author }}</td>
                  <td class="text-center">
                    <div
                      @click="toSingleBook(item.isbn)"
                      class="btn btn-sm btn-outline-primary"
                    >
                      看館藏
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
 * Imports
 */

const URL = import.meta.env.VITE_API_JAVAURL;
import { ref, reactive, onMounted, watch } from "vue";
import { RouterLink, useRouter, useRoute } from "vue-router";
import NavbarTop from "../components/navbarTop.vue";
import axios from "axios";

/**
 * Load Datas
 */
// Load Book data
const allBookData = ref([]);
const loadAllBookData = async () => {
  const URLAPI = `${URL}/book/findAll`;
  const response = await axios.get(URLAPI).catch((error) => {
    console.log(error.toJSON());
  });

  allBookData.value = response.data;
};

/**
 * Router
 */
const router = useRouter();
const route = useRoute();
const toSingleBook = (isbn) => {
  router.push({
    path: "/book",
    query: { isbn },
  });
};

/**
 * LifeCycle Hooks
 */
onMounted(() => {
  loadAllBookData();
});
</script>
<style scoped></style>
