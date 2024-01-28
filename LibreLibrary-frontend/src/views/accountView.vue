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
        <div class="col-11 col-md-9 col-lg-7">
          <div>
            <table class="table caption-top">
              <caption>
                已借閲書籍
              </caption>
              <thead class="table-light text-center">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">書名</th>
                  <th scope="col">作者</th>
                  <th scope="col">到期日</th>
                  <th scope="col">還書</th>
                </tr>
              </thead>
              <!-- <tbody v-for="(item, index) in pageClasses">
                <tr>
                  <th scope="row">{{ index + 1 }}</th>
                  <td>{{ item.courseName }}</td>
                  <td>{{ item.employeename }}</td>
                  <td>{{ item.classDate }}&nbsp;{{ item.classTime }}</td>
                  <td>NT$ &nbsp;{{ item.price.toLocaleString() }}</td>
                </tr>
              </tbody> -->
            </table>
          </div>
        </div>
      </div>
      <!-- Order table end -->
    </section>
  </body>
</template>
<script setup>
import NavbarTop from "../components/navbarTop.vue";
</script>
<style scoped></style>
