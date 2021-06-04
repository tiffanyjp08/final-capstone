import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    location: '',
    selectedRestaurants: [],
    invitation: {
      host: "",
      users: "",
      selectedRestaurants: [],
      datetime: "",
    },

  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_LOCATION(state, location) {
      state.location = location;
      localStorage.setItem('location', location);
    },
    SET_RESTAURANTS(state, selectedRestaurants) {
      state.selectedRestaurants = selectedRestaurants;
      localStorage.setItem('selectedRestaurants', selectedRestaurants);
    },
    SET_INVITATION(state, invitation) {
      state.invitation.host = invitation.host;
      state.invitation.users = invitation.users;
      state.invitation.selectedRestaurants = invitation.selectedRestaurants;
      state.invitation.datetime = invitation.datetime;
      localStorage.setItem('invitation', invitation);

    },
    REMOVE_RESTAURANT(state, selectedRestaurant) {

      console.log('DEBUG');
      console.log(selectedRestaurant);

      // TODO : which selectedRestaurants
      for (let i = 0; i < state.selectedRestaurants.length; i++) {
        if (state.selectedRestaurants[i].id == selectedRestaurant.id) {
          state.selectedRestaurants.pop(i);
        }
      }

    }

  }
})
