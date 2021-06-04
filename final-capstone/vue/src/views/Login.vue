<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <!-- <h1 class="h3 mb-3 font-weight-normal"> -->
        <div class="centerimage">
        <img id="img" src="../images/brownbg.png" />
        </div>
      <!-- </h1> -->
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>

      <div class="container">
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />

      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <div class="account">
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      </div>
      <div class="signin">
      <button type="submit">Sign in</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  // beforeCreate() {
  // document.querySelector('body').setAttribute('style', 'background:../images/french.jpg')
  // },
  // beforeDestroy() {
  // document.querySelector('body').setAttribute('style', '')
  // },
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
            console.log("hello");
          }
        })
        .catch((error) => {
          const response = error.response;
          console.log("hi");
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>

#login {
  text-align: center;
  background-image: url('../images/french.jpg');
  border-radius: 1%;
}

/* h1 {
  color: gray;
  left: 600px;
  line-height: 500px;
  margin-top: -400px;
  position: absolute;
  text-align: center;
  top: 55%;
  width: 20%;
  white-space: nowrap;
} */

h1 {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* #password {
  left: 900px;
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 50%;
  width: 20%;
} */

.container {
  display: flex;
  /* line-height: 30px;
  margin-top: -10px;
  width: 20%; */
  justify-content: space-evenly;
  align-items: center;
  padding-top: 100vh;
  /* min-height: 100vh; */
}

#password {
  font-size: 20px;
  line-height: 30px;
  /* margin-top: -10px; */
  width: 20%;
  text-align: center;
  /* padding-top: 600px; */
  margin-top: -47%;
}

#username {
  font-size: 20px;
  line-height: 30px;
  margin-top: -10px;
  width: 20%;
  text-align: center;
  margin-top: -47%;
}

/* #username {
  left: 350px;
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 50%;
  width: 20%;
} */

input {
  width: 200px;
}

.signin {
  display: flex;
  justify-content: center;
}

button {
  background-color: rgb(100, 58, 19);
  border-radius: 15px;
  border: #cda6da;
  cursor: pointer;
  color: rgb(255, 255, 255);
  font-family: Arial;
  font-size: 20px;
  padding: 13px 16px;
  text-decoration: none;
  line-height: 30px;
  margin-top: 60px;
  position: absolute;
  text-align: center;
  top: 60%;
  width: 20%;
  /* display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; */
}

button:hover {
  width: 20%;
  border: 3px solid rgb(255, 255, 255);
  background: transparent;
  color: rgb(255, 255, 255);
}

.account {
  display: flex;
  justify-content: center;
}

a {
  position: absolute;
  background: transparent;
  border-radius: 15px;
  cursor: pointer;
  color: rgb(255, 255, 255);
  font-family: Arial;
  text-decoration: none;
  line-height: 30px;
  margin-top: 60px;
  text-align: center;
  top: 70%;
  width: 18%;
  font-size: 17px;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
  color: rgb(255, 255, 255);
  width: 18%;
}

a:hover {
  color: rgb(102, 60, 60);
}

a:visited {
  color: rgb(255, 255, 255);
}

/* #img {
  width: 90%;
  height: auto;
  margin: 50px;
  left: -10%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  min-width: 100vh;
} */

.centerimage {
  display: flex;
  align-items: center;
  justify-content: center;
}

#img {
  display: block;
  width: 20%;
  margin: auto;
  top: 10%;
}

.form-signin {
  font-size: 40px;
}

</style>