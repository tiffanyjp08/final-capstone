import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    const userObj = {
      username: user.userName,
      password: user.password,
      confirmPassword: user.confirmPassword,
      role: 'user',
    };
    console.log(userObj);
    return axios.post('/register', userObj);
  }

}
