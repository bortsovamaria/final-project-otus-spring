import axios from "axios";

const AUTH_REST_API_URL = "http://localhost:8080/auth";

const registerAPICall = (registerObj) => {
    return axios.post(AUTH_REST_API_URL + "/register", {
        username: registerObj.username,
        password: registerObj.password,
    });
};

const loginAPICall = (loginObj) => {
    return axios.post(AUTH_REST_API_URL + "/login", {
        username: loginObj.username,
        password: loginObj.password,
    });
};

const storeToken = (token) => localStorage.setItem("token", token);

const getToken = () => localStorage.getItem("token");

const saveLoggedInUser = (username, role) => {
    sessionStorage.setItem("authenticatedUser", username);
    sessionStorage.setItem("role", role);
};

const isUserLoggedIn = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username != null;
};

const getLoggedInUser = () => {
    return sessionStorage.getItem("authenticatedUser");
};

const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
};

export {
    registerAPICall,
    loginAPICall,
    storeToken,
    getToken,
    saveLoggedInUser,
    isUserLoggedIn,
    getLoggedInUser,
    logout,
};
