import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080/"
});

export default {
    saveRestaurant(restaurant) {
        return axios.post('api/hostLikedRestaurant', restaurant);
    }
}