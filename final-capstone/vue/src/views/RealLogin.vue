<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal"><img src="../images/tinder.png"/></h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>

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
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
            console.log("hello")
          }
        })
        .catch(error => {
          const response = error.response;
          console.log("hi")
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

#login {
  text-align: center;
}

h1 {
  color: gray;
    left: 600px;
    line-height: 500px;
    margin-top: -400px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 20%; 
    white-space: nowrap;
}

.sr-only {
    left: 50px;
    line-height: 30px;
    margin-top: -10px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 100%;
    font-size: 29px;
}

#password {
    left: 900px;
    line-height: 30px;
    margin-top: -10px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 20%;
}

.sr-only1 {
    right: 490px;
    line-height: 30px;
    margin-top: -10px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 100%;
    font-size: 29px;
}

#username {
    left: 350px;
    line-height: 30px;
    margin-top: -10px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 20%;
}

input {
  width: 200px;
}

button {
	background-color:#282779;
	border-radius:15px;
	display:inline-block;
	cursor: pointer;
	color: rgb(255, 255, 255);
	font-family:Arial;
	font-size:20px;
	font-weight:bold;
	padding:13px 16px;
	text-decoration:none;
    left: 600px;
    line-height: 30px;
    margin-top: 60px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 20%;
}

button:hover{
  width: 20%;
  border: 3px solid rgb(20, 112, 173);
  background: transparent;
  color:  rgb(20, 112, 173);
}

a {
  width: 20%;
  background: transparent;
	border-radius:15px;
	display:inline-block;
	cursor: pointer;
	color: rgb(255, 255, 255);
	font-family:Arial;
	padding:13px 16px;
	text-decoration:none;
    left: 600px;
    line-height: 30px;
    margin-top: 60px;
    position: absolute;
    text-align: center;
    top: 60%;
    width: 18%;
    font-size: 15px;
    text-decoration: none;
}

a:hover{
  background-color:#282779;
  width: 18%;
}

a:hover {
  color: gray;
}

a:visited {
  color: rgb(255, 255, 255);
}

#img {
  width: 400px;
  height: auto;
}

.form-signin {
  font-size: 40px;
}
</style>