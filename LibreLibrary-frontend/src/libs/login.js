import axios from "axios";
const URL = import.meta.env.VITE_API_JAVAURL;

const login = async (phoneNumber, password) => {
  const result = {
    status: 1,
    token: "",
  };

  const data = {
    phoneNumber: phoneNumber,
    password: password,
  };

  const res = await axios.post(`${URL}/login`, data);
  if (res.data.status) {
    result.status = 0;
    result.token = res.data.token;
    setLoginStore({
      isLogin: true,
      token: result.token,
      phoneNumber: res.data.phoneNumber,
      userName: res.data.userName,
      userId: res.data.userId,
    });
  }

  return result;
};

const authToken = async (token) => {
  const result = {
    status: false,
    token: "",
    userName: "",
    loa: 0,
  };
  const data = {
    token: token,
  };
  const res = await axios.post(`${URL}/auth`, data);
  if (res.data.status) {
    result.status = true;
    result.token = res.data.token;
    result.userName = res.data.username;
    setLoginStore({
      isLogin: true,
      token: result.token,
      userName: result.userName,
    });
  } else {
    setLoginStore({
      isLogin: false,
      token: "",
      userName: "",
    });
  }

  return result;
};

const setLoginStore = (options) => {
  window.localStorage.setItem("isLogin", options.isLogin);
  window.localStorage.setItem("token", options.token);
  window.localStorage.setItem("userName", options.userName);
  window.localStorage.setItem("userId", options.userId);
  window.localStorage.setItem("phoneNumber", options.phoneNumber);
};

const logout = () => {
  window.localStorage.setItem("isLogin", false);
  window.localStorage.setItem("token", "");
  window.localStorage.setItem("userName", "");
  window.localStorage.setItem("userId", -1);
  window.localStorage.setItem("phoneNumber", "");
};

export { login, authToken, logout };
