<template>
  <div>
    <input type="text" v-model="search" placeholder="Select a user" />

    <div
      class="back"
      v-show="search"
      v-for="currentUser in filteredUsers"
      :key="currentUser.email"
    >
      {{ currentUser.firstName }} {{ currentUser.lastName }}
      {{ currentUser.email }}
    </div>
  </div>
</template>

<script>
import userDetailsService from "../services/UserDetailsService";
export default {
  name: "name",
  data() {
    return {
      currentUsers: [],
      search: "",
    };
  },
  created() {
    userDetailsService
      .list()
      .then((userData) => {
        this.currentUsers = userData.data;
      })
      .catch((err) => {
        console.err(err + "Missing users!");
      });
  },
  computed: {
    filteredUsers() {
      return this.currentUsers.filter((user) => {
        return (
          user.firstName.match(this.search) ||
          user.lastName.match(this.search) ||
          user.email.match(this.search)
        );
      });
    },
  },
};
</script>

<style>
.back {
  background-color: white;
}
</style>