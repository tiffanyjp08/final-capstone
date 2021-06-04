<template>
  <div id="main">
    <br />
    <br />
    <h2>Invitations Received</h2>
    <router-link :to="{ name: 'guest-making-selection' }">
      <div class="invitationCard">
        <p>You have been invited by: {{ host }}</p>
        <br />

        <p>To attend an outing in this location: {{ location }}</p>
        <br />

        <p>On this date and time: {{ dateTime }}</p>
      </div>
    </router-link>
    <br />
    <h2>Invitations Sent</h2>
    <router-link :to="{ name: 'restaurant-finalists' }">
      <div class="invitationCard">
        <p>My invite for {{ date }} {{ location }}</p>
        <br />

        <p>For: {{ location }}</p>
        <br />
      </div>
    </router-link>
    <button v-on:click="backToHome">Back to Home Page</button>
  </div>
</template>

<script>
export default {
  methods: {
    guestMakingSelection() {
      this.$router.push("/guest-making-selection");
    },

    backToHome() {
      this.$router.push("/");
    },
  },
  computed: {
    dateTime() {
      return this.$store.state.invitation.dateTime;
    },
    host() {
      return this.$store.state.invitation.username;
    },
    location() {
      return this.$store.state.location;
    },
  },
};
</script>

<style scoped>
.invitationCard {
  border: 3px solid black;

  font-weight: bold;
}
</style>