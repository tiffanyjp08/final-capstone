<template>
  <div class="main">
    <h1>GUEST INVITATION FORM</h1>
    <br />

    <div id="border">
    <h1 id="formPropmt">Please fill out the form to invite guest(s)</h1>
    <br />
    <h2 id="formPropmt2">
      Start typing to select from a list of registered guest(s) to be included
      on the invitation
    </h2>

    <div class="registered">
      <input
        class="selectUserBox"
        type="text"
        v-model="search"
        placeholder="Select a User"
      />
      <br />
      <div
        class="register"
        v-show="search"
        v-for="currentUser in filteredUsers"
        :key="currentUser.id"
      >
        {{ currentUser.firstName }} {{ currentUser.lastName }}
        {{ currentUser.email }}
        <button id="add" v-on:click="registeredUser(currentUser)">Add</button>
      </div>
    </div>

    <h2 id="formnonregistered">
      Guest not registered? Fill out the form below to add guest(s) to the
      invitation
    </h2>

    <!-- <table id="tblUsers"> -->
    <!-- <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email Address</th>
          <th>Add Guest</th>
        </tr>
      </thead> -->
    <!-- <tbody>
        <tr> -->
    <!-- <td> -->
    <div class="container">
      <input
        type="text"
        id="firstName"
        v-model="firstName"
        placeholder="First Name"
      />
      <!-- </td> -->
      <!-- <td> -->
      <input
        type="text"
        id="lastName"
        v-model="lastName"
        placeholder="Last Name"
      />
      <!-- </td>
          <td> -->
      <input type="text" id="email" v-model="email" placeholder="Email" />
      <!-- </td>
          <td> -->
      <button id="add" v-on:click="addUser">Add</button>
      <!-- </td> -->
      <!-- </tr> -->
      <!-- </tbody> -->
      <!-- </table> -->
    </div>

    <h3 id="dates">Select Date and Time</h3>
    <table id="time/date">
      <tr>
        <td>
          <datetime
            id="date"
            format="YYYY/MM/DD h:i"
            width="300px"
            v-model="datetime"
            placeholder="Date and Time"
          ></datetime>
        </td>
        <br />
      </tr>
    </table>
    <table>
      <tr v-for="(user, k) in users" :key="k">
        <td scope="row">{{ user.firstName }}</td>
        <td>{{ user.lastName }}</td>
        <td>{{ user.email }}</td>
        <td><button id="delete" v-on:click="deleteUser(k)">delete</button></td>
      </tr>
    </table>
    <br />
    <button id="invite" v-on:click="createInvitation">send Invitation</button>
    <br />
    <button id="cancel" v-on:click="cancel">Cancel</button>
  </div>
  </div>
</template>

<script>
import datetime from "vuejs-datetimepicker";
//import inviteFormService from "@/services/InviteFormService";
import userDetailsService from "../services/UserDetailsService";

export default {
  name: "inviteform",
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",

      //being used to store add selected users
      users: [],

      datetime: "",

      invitation: {
        host: "",
        users: "",
        selectedRestaurants: [],
        datetime: "",
      },

      //needed to store users from the database
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
  components: {
    datetime,
  },
  methods: {
    cancel() {
      this.$router.push("/");
    },
    addUser() {
      this.users.push({
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
      });
    },
    registeredUser(user) {
      this.users.push({
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
      });
    },
    deleteUser(index) {
      this.$delete(this.users, index);
    },
    createInvitation() {
      this.invitation.host = this.$store.state.user;
      this.invitation.users = this.users;
      this.invitation.datetime = this.datetime;
      this.invitation.selectedRestaurants = this.$store.state.selectedRestaurants;
      this.$store.commit("SET_INVITATION", this.invitation);
      //inviteFormService.saveInvitation(this.invitation).catch((error) => {
      //  alert(error);
      //});
      this.$router.push("/final-restaurant");
    },
  },
  computed: {
    filteredUsers() {
      return this.currentUsers.filter((user) => {
        return (
          user.firstName.toLowerCase().match(this.search) ||
          user.lastName.toLowerCase().match(this.search) ||
          user.email.match(this.search.toLowerCase())
        );
      });
    },
  },
};
</script>

<style scoped>
/* body {
  max-height: 100vh;
} */
#border {
  border: 2px solid rgb(255, 255, 255);
  border-radius: 1rem;
  margin-left: 17%;
  margin-right: 17%;
  padding-bottom: 10px;
}

h1 {
  text-align: center;
  color: rgb(104, 72, 12);
  padding-top: 40px;
  padding-bottom: 20px;
}

h2 {
  text-align: center;
  font-size: 22px;
}

h3 {
  text-align: center;
  font-size: 22px;
  margin-top: 40px;
}

tr {
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  /* min-height: 20vh; */
}

table {
  display: flex;
  justify-content: center;
  align-items: center;
}

.selectUserBox {
  position: relative;
  margin: auto;
  display: block;
  text-align: center;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
}

#invite {
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
  /* margin-left: 50%;
  margin-right: 50%;
  margin-top: 10px; */
  margin: auto;
}

/* table {
  margin-top: 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
th {
  text-transform: uppercase;
}
td {
  padding: 10px;
}
tr.disabled {
  color: red;
} */
input,
select {
  font-size: 16px;
}

.register {
  background-color: white;
  width: 25%;
}

.selectUserBox {
  width: 25%;
}

#firstName {
  text-align: center;
  margin: 40px;
}

#lastName {
  text-align: center;
  margin: 40px;
}

#email {
  text-align: center;
  margin: 40px;
}

#invite {
  border: 0.1em solid rgb(104, 72, 12);
  box-sizing: border-box;
  text-decoration: none;
  font-weight: 300;
  color: rgb(104, 72, 12);
  text-align: center;
  transition: all 0.2s;
  line-height: 30px;
  text-align: center;
  width: 15%;
  background-color: transparent;
  font-size: 16px;
  border-radius: 0.2rem;
}

#invite:hover {
  color: rgb(255, 255, 255);
  font-weight: bold;
  background-color: rgb(104, 72, 12);
  border-radius: 0.2rem;
}

#add {
  border: 0.1em solid rgb(104, 72, 12);
  box-sizing: border-box;
  text-decoration: none;
  font-weight: 300;
  color: rgb(255, 255, 255);
  text-align: center;
  transition: all 0.2s;
  line-height: 20px;
  text-align: center;
  width: 9%;
  background-color: transparent;
  background: rgb(104, 72, 12);
  border-radius: 0.2rem;
}

#add:hover {
  color: rgb(104, 72, 12);
  font-weight: bold;
  background-color: transparent;
  border-radius: 0.2rem;
}

#cancel {
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  position: relative;
  margin-left: 47%;
  background: transparent;
  border: 0.1em solid rgb(104, 72, 12);
  color: rgb(104, 72, 12);
  line-height: 20px;
  border-radius: 0.2rem;
}

#cancel:hover {
  color: rgb(255, 255, 255);
  font-weight: bold;
  background-color: rgb(104, 72, 12);
  line-height: 20px;
  border-radius: 0.2rem;
}

#dates {
  margin-top:5px;
  margin-bottom: 50px;
}

</style>