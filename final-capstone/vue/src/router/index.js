import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import Restaurants from '../views/Restaurants.vue'
import store from '../store/index'
import MakingGuestInvite from '../views/MakingGuestInvite.vue'
import InvitationLink from '../views/InvitationLink.vue'
import RestaurantFinalists from '../views/RestaurantFinalists.vue'
import FinalRestaurant from '../views/FinalRestaurant'
import TestingSite from '../views/TestingSite'
import InviteLinkTest from '../views/InviteLinkTest'
import MyInvitation from '../views/MyInvitation.vue'
import GuestMakingSelection from '../views/GuestMakingSelection.vue'
// import HomeScreen from '../store/HomeScreen.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/restaurants",
      name: "restaurants",
      component: Restaurants,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/invite",
      name: "making-guestInvite",
      component: MakingGuestInvite,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/invitationlink",
      name: "invitationlink",
      component: InvitationLink,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/restaurant-finalists",
      name: "restaurant-finalists",
      component: RestaurantFinalists,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/final-restaurant",
      name: "final-restaurant",
      component: FinalRestaurant,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/testing",
      name: "testing",
      component: TestingSite,
      meta: {
        requireAuth: true
      }
    },
    {
      path: "/my-invitation",
      name: "my-invitation",
      component: MyInvitation,
      meta: {
        requireAuth: true
      }
    },
    {
      path: "/invite-link-test",
      name: "invite-link-test",
      component: InviteLinkTest,
      meta: {
        requireAuth: true
      }
    },
    {
      path: "/guest-making-selection",
      name: "guest-making-selection",
      component: GuestMakingSelection,
      meta: {
        requireAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
