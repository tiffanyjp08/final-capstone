<template>
  <div class="home">
    <img id="logo" src="../images/brown.png" />
    <search />
  </div>
</template>

<script>
import Search from "../components/Search.vue";
export default {
    beforeCreate() {
    document.querySelector('body').setAttribute('style', 'background:#ecdcc3')
    },
    beforeDestroy() {
    document.querySelector('body').setAttribute('style', '')
    },
  components: { Search },
  name: "home",
};
</script>

<style scoped>
.dropdown {
  position: relative;
  display: inline-block;
  left: 1350px;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
  background-color: #f1f1f1;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
  /* left: 1300px;
  position: absolute;
  top: 5%;
  width: 10%; */
}

.dropdown:hover .dropdown-content {
  display: block;
}

/* #logo {
  position: absolute;
  top: 40%;
  width: 20%;
  display: flex;
  justify-content: center;
  align-items: center;
} */

#profilepic {
  left: 1300px;
  position: relative;
  top: 5%;
  width: 10%;
}

/* .flex-box {
  display: flex;
  border: black;
  flex-direction: row;
  justify-content: space-evenly;
  left: 400px;
  line-height: 30px;
  margin-top: -10px;
  position: absolute;
  text-align: center;
  top: 60%;
  width: 40%;
  font-size: 29px;
} */

#logo {
  padding: 0px;
  text-align: center;
  left: 5px;
}

.home {
  display: block;
  margin: auto;
}
</style>
