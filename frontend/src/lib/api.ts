import axios from 'axios';

const api = axios.create({
    baseURL: "http://localhost:8080/"
});


api.interceptors.request.use(config => {
    const jvToken = localStorage.getItem("jvToken");

    if(jvToken && config.headers)
        config.headers.Authorization = "Bearer " + jvToken;

    return config;
});


export default api;