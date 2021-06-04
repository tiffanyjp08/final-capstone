<template>
  <div>
    <div class="search">
      <input
        v-model="location"
        type="text"
        id="location"
        class="form-control"
        placeholder="Location"
        required
        autofocus
      />
      <button class="submit" type="submit" v-on:click="search">Submit</button>
    </div>
    <!-- <div>
      <button class="myInvitations" type="submit" v-on:click="myInvitations">
        My Invitation
      </button>
    </div> -->
  </div>
</template>

<script>
export default {
  name: "search",
  data() {
    return {
      location: "",
    };
  },
  methods: {
    myInvitations() {
      this.$router.push("/my-invitation");
    },

    search() {
      this.$store.commit("SET_LOCATION", this.location);
      this.$router.push("/restaurants");
    },
  },
};
</script>

<style scoped>
.search {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#location {
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 20%;
  width: 20%;
  font-size: 29px;
}

.submit {
  display: inline-block;
  border: 0.1em solid #ffffff;
  box-sizing: border-box;
  text-decoration: none;
  font-weight: 300;
  color: white;
  text-align: center;
  transition: all 0.2s;
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 30%;
  width: 9%;
  font-size: 20px;
  background-color: transparent;
  border-radius: 0.2rem;
}

button:hover {
  color: rgba(0, 0, 0, 0.192);
  font-weight: bold;
  background-color: white;
}

/* .myInvitations {
  line-height: 12px;
  width: 18px;
  font-size: 8pt;
  font-family: tahoma;
  margin-top: 1px;
  margin-right: 2px;
  position: absolute;
  top: 0;
  right: 0;
} */
</style>