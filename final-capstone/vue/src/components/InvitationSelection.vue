<template>
  <div id="main">
    <div class="topboxes">
      <div class="title">{{ host }}</div>
      <div class="deadline">
        {{ deadline }}
      </div>
    </div>
    {{ $store.state.selectedRestaurants }}
    <div class="restaurantInfo">
      <div
        class="restaurantCard"
        v-for="restaurant in $store.state.selectedRestaurants"
        :key="restaurant.id"
      >
        <div id="name">
          {{ restaurant.name }}
        </div>
        <br />

        <div id="invites">
          <button id="like" v-on:click="saveRestaurant(restaurant)">LIKE</button>
          <button id="dislike" v-on:click="deleteRestaurant(restaurant)">DISLIKE</button>
        </div>

        <div id="type">Type: {{ restaurant.categories }}</div>
        <br />

        <div id="address">Address: {{ restaurant.displayAddress }}</div>
        <br />

        <div class="available">
          <div class="closed" v-if="restaurant.closed">CLOSED</div>
          <div class="open" v-else>OPEN NOW</div>
          <br />
          <div class="phone" v-if="restaurant.displayPhone">
            <button type="button" class="callButton">Call to Order</button>
          </div>
        </div>
      </div>
      <button
        type="button"
        class="guestMakingSelection"
        v-on:click="selectRestaurants"
      >
        Send
      </button>
    </div>
  </div>
</template>

<script>
// import searchService from "@/services/SearchService";

export default {
  name: "restaurantlist",
  data() {
    return {
      restaurants: [],
      selectedRestaurants: [],
    };
  },
  methods: {
    saveRestaurant(selectedRestaurant) {
      this.selectedRestaurants.push(selectedRestaurant);
      alert("Restaurant saved to your list!");

      console.log("Length of restaurants array: " + this.restaurants.length);
      this.$store.commit("REMOVE_RESTAURANT", selectedRestaurant);

      // this.restaurants.pop();
    },
    selectRestaurants() {
      this.$store.commit("SET_RESTAURANTS", this.selectedRestaurants);
      this.$router.push("/");
    },
    deleteRestaurant() {
      // MLG - this removes the restaurant from the top of the stack
      // and allows the one underneath to display
      this.restaurants.pop();
    },
  },
  computed: {
    deadline() {
      return this.$store.state.invitation.datetime;
    },
    host() {
      return this.$store.state.invitation.host.username;
    },
  },
};
</script>

<style>
body {
  /* height: 100vh; */
  /* padding: 30px; */
}

.title {
  border: 3px solid blue;
  margin: 20px;
  padding: 20px;
  width: 50%;
  font-weight: bolder;
  color: rgb(90, 73, 73);
}

.deadline {
  border: 3px solid blue;
  margin: 20px;
  padding: 20px;
  width: 50%;
  font-weight: bolder;
  color: rgb(49, 41, 41);
}

.topboxes {
  display: flex;
  justify-content: space-between;
}

.restaurantCard {
  background-image: url("../images/bg.jpg");
  /* background-size: cover; */
  border: 1px solid #ccc;
  border-radius: 1rem;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
  color: #333;
  position: fixed;
  z-index: auto;
  top: 0;
  height: 100vh;
  width: 75vh;
  left: 30%;
  top: 30%;
}

#invites {
  /* MLG - 
this is the code that puts the like and dislike buttons next to each other */
  display: flex;
  width: auto;
  height: auto;
  margin: 30px 50px 30px 50px;
  align-items: center;
  justify-content: space-around;
}

.restaurantInfo {
  padding: 20px;
  display: flex;
  flex-wrap: wrap;
  position: absolute;
}

#name {
  font-size: 50px;
  color: rgb(255, 253, 253);
  font-weight: bold;
  text-align: center;
}

#type {
  font-size: 20px;
  color: rgb(255, 253, 253);
  text-align: center;
}

#address {
  font-size: 20px;
  color: rgb(255, 253, 253);
  text-align: center;
}

.closed {
  font-size: 20px;
  color: rgb(248, 41, 41);
  font-weight: bold;
  text-align: center;
}

.open {
  font-size: 20px;
  color: rgb(45, 245, 135);
  text-align: center;
  font-weight: bold;
}

img {
  position: absolute;
  margin-left: 40%;
  margin-right: 50%;
  width: 20%;
  top: 40%;
}

.callButton {
  font-size: 18px;
  color: rgb(1, 12, 6);
  text-align: center;
  right: 35%;
  position: absolute;
}

* {
  box-sizing: border-box;
}
.inviteButton {
  background: #ecdcc3;
}
</style>