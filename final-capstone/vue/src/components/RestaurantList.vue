
  <template>
  <div id="main">
    <div id="buttoncont">
      <button type="button" class="inviteButton" v-on:click="selectRestaurants">
        Create Invitation
      </button>
      <br />
      <br />
      <h1 id="swipe">Swipe through to like one restaurant</h1>
    </div>
    <div class="restaurantInfo">
      <div
        class="restaurantCard"
        v-for="restaurant in restaurants"
        :key="restaurant.id"
        :style="{ backgroundImage: `url(${restaurant.imageUrl})` }"
      >
        <div class="info">
          <div id="name">
            {{ restaurant.name }}
          </div>
          <br />

          <div id="invites">
            <button id="like" v-on:click="saveRestaurant(restaurant)">
              LIKE
            </button>
            <button id="dislike" v-on:click="deleteRestaurant(restaurant)">
              DISLIKE
            </button>
          </div>

          <div id="type">Type: {{ restaurant.categories }}</div>
          <br />

          <div id="address">Address: {{ restaurant.displayAddress }}</div>
          <br />

          <!-- <div id="img">{{ restaurant.imageUrl }}</div> -->

          <!-- <div class="img" v-if="restaurant.imageUrl">
          <img
            v-bind:src="restaurant.imageUrl"
            alt="restaurant pic"
            width="200"
          />
        </div> -->
          <div class="available">
            <div class="closed" v-if="restaurant.closed">CLOSED</div>
            <div class="open" v-else>OPEN NOW</div>
            <br />
            <div class="phone" v-if="restaurant.displayPhone">
              <button type="button" id="callButton">Call to Order</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import searchService from "@/services/SearchService";

export default {
  // template: <div class="restuarantCard" style="{background 'url('restaurant.imageUrl')'}"></div>,
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
      alert("Restaurant saved to your invitation!");
      this.restaurants.pop();
    },
    selectRestaurants() {
      this.$store.commit("SET_RESTAURANTS", this.selectedRestaurants);
      this.$router.push("/invite");
    },
    deleteRestaurant() {
      // MLG - this removes the restaurant from the top of the stack
      // and allows the one underneath to display
      this.restaurants.pop();
    },
  },
  created() {
    searchService
      .search(this.$store.state.location)
      .then((searchData) => {
        this.restaurants = searchData.data;
      })
      .catch((error) => {
        alert(error);
      });
  },
};
</script>

<style>
#swipe {
  font-size: 25px;
  text-align: center;
  margin-left: -10%;
  width: 100%;
  color: rgb(107, 61, 7);
}

#main {
  background: #ecdcc3;
}

.restaurantCard {
  /* background-image: url(./images/french.jpg); */
  /* background-size: cover; */
  border: 1px solid #ccc;
  border-radius: 1rem;
  box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.1);
  color: rgb(5, 5, 5);
  position: fixed;
  z-index: auto;
  top: 0;
  height: 100vh;
  width: 75vh;
  left: 30%;
  top: 30%;
}

#like {
  border: 0.1em solid #ffffff;
  text-decoration: none;
  font-weight: 300;
  color: white;
  text-align: center;
  transition: all 0.2s;
  line-height: 30px;
  font-size: 20px;
  background-color: transparent;
  border-radius: 0.2rem;
}

#like:hover {
  color: rgb(255, 255, 255);
  font-weight: bold;
  background-color: rgb(35, 255, 83);
  border-radius: 0.2rem;
}

#dislike {
  border: 0.1em solid #ffffff;
  text-decoration: none;
  font-weight: 300;
  color: white;
  text-align: center;
  transition: all 0.2s;
  line-height: 30px;
  font-size: 20px;
  background-color: transparent;
  border-radius: 0.2rem;
}

#dislike:hover {
  color: rgb(255, 254, 254);
  font-weight: bold;
  background-color: rgb(255, 14, 14);
  border-radius: 0.2rem;
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

.info {
  background: linear-gradient(
    180deg,
    rgba(128, 128, 128, 0.651),
    rgba(114, 113, 113, 0.068)
  );
  padding-bottom: 10px;
  border-radius: 1rem;
}

#name {
  font-size: 50px;
  color: rgb(255, 255, 255);
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
  color: rgb(47, 255, 65);
  text-align: center;
  font-weight: bold;
}

.img {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  /* margin-left: 40%;
  margin-right: 50%; */
  width: 200%;
  top: 40%;
  margin: auto;
}

.phone {
  display: flex;
  justify-content: center;
}

#callButton {
  font-size: 18px;
}

#buttoncont {
  left: 85vh;
  position: absolute;
}

.inviteButton {
  border: 0.1em solid #ffffff;
  text-decoration: none;
  color: white;
  text-align: center;
  transition: all 0.2s;
  line-height: 30px;
  font-size: 30px;
  background-color: transparent;
  padding-right: 50px;
  padding-left: 50px;
  margin-top: 100px;
  border-radius: 0.2rem;
}

.inviteButton:hover {
  color: rgba(14, 10, 5, 0.192);
  background-color: white;
  border: 0.1em solid #ffffff;
  line-height: 30px;
}

#h1-header {
  text-align: center;
}
</style>