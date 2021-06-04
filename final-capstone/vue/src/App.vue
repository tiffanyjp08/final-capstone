<template>
  <div id="app">
    <div id="dropdown">
      <!-- <router-link id="home" v-bind:to="{ name: 'home ' }">HOME</router-link> -->
      <button id="home" >MENU</button>

      <div class="dropdown-content">
      <!-- BELOW ROUTER LINKS FOR EASIER NAVIGATION BETWEEN PAGES -->
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link
        v-bind:to="{ name: 'register' }"
        v-if="$store.state.token != ''"
        >Register</router-link
      >
      <!-- <div>
        <router-link v-bind:to="{ name: 'home' }"
          >Search</router-link
        >
      </div> -->
      <!-- <router-link
        v-bind:to="{ name: 'making-guestInvite' }"
        v-if="$store.state.token != ''"
        >Invite a Friend</router-link
      > -->
      <!-- <router-link
        v-bind:to="{ name: 'invitationlink' }"
        v-if="$store.state.token != ''"
        >Invitation Link</router-link
      >
      <router-link
        v-bind:to="{ name: 'my-invitation' }"
        v-if="$store.state.token != ''"
        >My Invitation</router-link>
      <router-link
        v-bind:to="{ name: 'restaurant-finalists' }"
        v-if="$store.state.token != ''"
        >Choose a Restaurant</router-link
      >
      <router-link
        v-bind:to="{ name: 'final-restaurant' }"
        v-if="$store.state.token != ''"
        >Selected Restaurant</router-link
      > -->
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link>
    </div>
    </div>
    <router-view />
  </div>
</template>


<style>
body {
  background: #ecdcc3;
  width: 99%;
  height: 80%;
  /* overflow: hidden; */
}

#home {
  color: rgb(104, 62, 15);
  text-decoration: none;
  font-size: 18px;
  padding: 2px;
  border: 1px solid rgb(104, 62, 15);
  background: transparent;
  position: absolute;
  border-radius: 0.2rem;
}

#dropdown {
  margin-bottom: 10px;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
  background-color: #f1f1f1;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
  /* left: 1300px;
  position: absolute;
  top: 5%;
  width: 10%; */
}

#dropdown:hover .dropdown-content {
  display: block;
}
</style>
