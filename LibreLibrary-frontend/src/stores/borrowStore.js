import { ref } from "vue";
import { defineStore } from "pinia";

export const useBorrowStore = defineStore(
  "borrow",
  () => {
    // 用setup的方式定義
    const bookBorrowStore = ref([]);
    return { bookBorrowStore };
  },
  {
    persist: [
      {
        paths: ["bookBorrowStore"],
        storage: sessionStorage,
      },
    ],
  }
);
