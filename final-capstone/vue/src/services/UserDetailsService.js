import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080/"
});

export default {

    list() {
        return axios.get('api/allUsersDetails');
    },

    create(user) {
        return axios.post('api/newUser', user);
    }


}