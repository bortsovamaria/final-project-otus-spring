import axios from "axios";
import {getToken} from "./AuthService";

const BASE_REST_API_URL = "http://localhost:8080/api/tasks";

// Add a request interceptor
axios.interceptors.request.use(
    function (config) {
        config.headers["Authorization"] = getToken();
        return config;
    },
    function (error) {
        // Do something with request error
        return Promise.reject(error);
    }
);

const getAllTasks = () => {
    return axios.get(BASE_REST_API_URL);
};

const addTask = (task) => {
    return axios.post(BASE_REST_API_URL, {
        title: task.title,
        description: task.description,
        priority: task.priority,
        status: task.status,
        assignedTo: task.assignedTo
    });
};

const getTask = (taskId) => {
    return axios.get(BASE_REST_API_URL + "/" + taskId);
};

const updateTask = (taskId, task) => {
    return axios.put(BASE_REST_API_URL + "/" + taskId, {
        title: task.title,
        description: task.description,
    });
};

const deleteTask = (taskId) => {
    return axios.delete(BASE_REST_API_URL + "/" + taskId);
};

export {
    getAllTasks,
    addTask,
    getTask,
    updateTask,
    deleteTask,
};
