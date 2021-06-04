<template>
  <div>
    <div class="box1">
      <h1>You have been invited!</h1>
      <p id="host">Name of Host: </p> <p>{{ JSON.stringify(this.invitation.host.username) }}</p>
      <p id="time">Date and Time: <p> 
        <p id="date">{{ this.invitation.datetime }} <p>
      <p id="invitees">List of Invitees:</p>
      <div id="invites" v-for="user in this.invitation.users" :key="user.id">
        {{ user.firstName }} {{ user.lastName }}
      </div>
      <br />
      <p id="restaurant"> Restaurant: </p>
      <div
        id="restname"
        v-for="restaurant in this.$store.state.selectedRestaurants"
        :key="restaurant.name"
      >
        {{ restaurant.name }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "finalinvite",
  data() {
    return {
      invitation: {
        host: "",
        users: "",
        selectedRestaurants: [],
        datetime: "",
      },
    };
  },
  created() {
    this.invitation = this.$store.state.invitation;
  },
  methods: {
    backToHome() {
      this.$router.push("/");
    },
  },
};
</script>

<style>
#restname {
  margin-top: -10px;
}

#restaurant {
  margin-top: -25px;
}

#invitees {
  margin-top: 20px;
  margin-bottom: -20px;
}

#time {
  margin-top: 2px;
}

#date {
  padding-top: -20px;
  margin-top: -50px;
  margin-bottom: -30px;
}

h1 {
  display: flex;
  justify-content: center;
  color: rgb(124, 96, 61);
}

#host {
  font-size: 25px;
  margin-bottom: -20px;
}

.box1 {
  padding-top: 50px;
  border: 3px solid rgb(255, 255, 255);
  width: 50%;
  font-weight: bolder;
  color: rgb(255, 255, 255);
  font-size: 20px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  min-height: 75vh;
  margin-right: 50%;
  margin-left: 25%;
  border-radius: 1rem;
}

#send {
  border: 0.1em solid rgb(104, 72, 12);
  box-sizing: border-box;
  text-decoration: none;
  font-weight: 300;
  color: rgb(104, 72, 12);
  transition: all 0.2s;
  line-height: 30px;
  margin-top: -10px;
  width: 10%;
  background-color: transparent;
  text-align: center;
  margin-left: 45%;
}

#send:hover {
  color: rgb(255, 255, 255);
  font-weight: bold;
  background-color: rgb(104, 72, 12);
}

span {
  color: red;
}
</style>