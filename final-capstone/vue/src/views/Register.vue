<template>
  <div id="register" class="text-center">
    <!-- <div class="bg" :style="{'background-image':'url(https://wallpapercave.com/wp/wp5204110.jpg)'}"> -->
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <div class="container">
      <!-- <label for="firstname" class="sr-only2">First Name</label> -->
      <input
        type="text"
        id="firstname"
        class="form-control"
        placeholder="First Name"
        v-model="user.firstName"
        required
        autofocus
      />

      <!-- <label for="lastname" class="sr-only3">Last Name</label> -->
      <input
        type="text"
        id="lastname"
        class="form-control"
        placeholder="Last Name"
        v-model="user.lastName"
        required
        autofocus
      />

      <!-- <label for="emailaddress" class="sr-only4">Email Address</label> -->
      <input
        type="text"
        id="emailaddress"
        class="form-control"
        placeholder="Email Address"
        v-model="user.email"
        required
        autofocus
      />

      <!-- <label for="username" class="sr-only1">Username</label> -->
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.userName"
        required
        autofocus
      />

      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />

      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />

      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import userDetailsService from "../services/UserDetailsService";

export default {
  name: "register",
  data() {
    return {
    beforeCreate() {
    document.querySelector('body').setAttribute('style', 'background:#ecdcc3')
    },
    beforeDestroy() {
    document.querySelector('body').setAttribute('style', '')
    },
      user: {
        userName: "",
        password: "",
        confirmPassword: "",
        firstName: "",
        lastName: "",
        email: "",
        role: "user",
      },
      // user2: {},
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status === 201) {
              userDetailsService
                .create(this.user)
                .then((response) => {
                  if (response.status == 201) {
                    this.$router.push({
                      path: "/login",
                      query: { registration: "success" },
                    });
                  }
                })
                .catch((error) => {
                  const response = error.response;
                  this.registrationErrors = true;
                  if (response.status === 400) {
                    this.registrationErrorMsg =
                      "Bad Request: Registration Errors";
                  }
                });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>

.container {
  display: flex;
  justify-content: center;
  align-items: center;
}

body {
  background-image: brown;
}

h1 {
  left: 83vh;
  display: flex;
  justify-content: center;
  min-height: 100vh;
  color: rgb(90, 52, 9);
  font-size: 70px;
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 10%;
  width: 20%;
  white-space: nowrap;
}

#firstname {
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 25%;
  width: 20%;
  font-size: 23px;
}

#lastname {
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 35%;
  width: 20%;
  font-size: 23px;
}

#emailaddress {
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 45%;
  width: 20%;
  font-size: 23px;
}

#username {
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 55%;
  width: 20%;
  font-size: 23px;
}

#password {
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 65%;
  width: 20%;
  font-size: 23px;
}

#confirmPassword {
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 75%;
  width: 20%;
  font-size: 23px;
}

button {
  width: 20%;
  border: 3px solid rgb(90, 52, 9);
  background: transparent;
  color: rgb(90, 52, 9);
  border-radius: 15px;
  display: inline-block;
  cursor: pointer;
  color: rgb(255, 255, 255);
  font-family: Arial;
  font-size: 14px;
  font-weight: bold;
  padding: 13px 16px;
  text-decoration: none;
  left: 40%;
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 85%;
  width: 20%;
  font-size: 23px;
}

button:hover {
  background-color: rgb(90, 52, 9);
  width: 20%;
  border: 3px solid rgb(90, 52, 9);
}

a {
  text-decoration: none;
  border: 3px solid transparent;
  border-radius: 15px;
  display: inline-block;
  cursor: pointer;
  color: rgb(128, 89, 44);
  font-family: Arial;
  font-weight: bold;
  font-size: 18px;
  padding: 13px 16px;
  left: 40%;
  line-height: 10px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 17.5%;
  width: 20%;
}

a:hover {
  width: 20%;
  font-size: 18px;
  border: 3px solid rgb(128, 89, 44);
  background: transparent;
  color: rgb(128, 89, 44);
}

</style>
