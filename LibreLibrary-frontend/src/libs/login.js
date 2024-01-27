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
  const res = await axios.post(`${url}/auth`, data);
  if (res.data.status) {
    result.status = true;
    result.token = res.data.token;
    result.userName = res.data.username;
    result.loa = res.data.loa;
    setLoginStore({
      isLogin: true,
      token: result.token,
      userName: result.userName,
      loa: result.loa,
    });
  } else {
    setLoginStore({
      isLogin: false,
      token: "",
      userName: "",
      loa: result.loa,
    });
  }

  return result;
};

const setLoginStore = (options) => {
  window.localStorage.setItem("isLogin", options.isLogin);
  window.localStorage.setItem("token", options.token);
  window.localStorage.setItem("username", options.userName);
  window.localStorage.setItem("phoneNumber", options.phoneNumber);
};

const logout = () => {
  window.localStorage.setItem("isLogin", "");
  window.localStorage.setItem("token", "");
  window.localStorage.setItem("username", "");
  window.localStorage.setItem("phoneNumber", "");
};

export { login, authToken, logout };
