<template>
  <div>
    <div class="topboxes">
      <div class="title">{{ host }}</div>
      <div class="deadline">
        {{ deadline }}
      </div>
    </div>
    <div class="card">
      v-for="restaurant in $store.state.invitation.selectedRestaurants"
      :key="restaurant.id>"
      <div id="name">
        {{ restaurant.name }}
      </div>
      <br />

      <div id="invites">
        <input
          class="checkbox"
          type="checkbox"
          v-bind:id="restaurant.id"
          v-bind:value="restaurant"
          v-model="selectedRestaurants"
        />
        <label for="checkbox" id="invite"> Select to add to invitation</label>
      </div>
      <br />
      <br />

      <div id="type">Type: {{ restaurant.categories }}</div>
      <br />

      <div id="address">Address: {{ restaurant.displayAddress }}</div>
      <br />

      <div class="available">
        <div class="closed" v-if="restaurant.closed">CLOSED</div>
        <div class="open" v-else>OPEN NOW</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  //   data() {
  //     deadline = "";
  //   },
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

<style scoped>
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

.card {
  border: 3px solid blue;
  margin: 20px;
  padding: 20px;
  width: 50%;
  font-weight: bolder;
}

.topboxes {
  display: flex;
  justify-content: space-between;
}
span {
  color: red;
}
</style>