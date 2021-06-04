import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080/"
});

export default {
    saveInvitation(invitation) {
        console.log(JSON.stringify(invitation));


        let invitationObject = {};
        invitationObject.hostUserId = invitation.host.id;
        invitationObject.hostUserName = invitation.host.username;
        invitationObject.guestUserEmail = invitation.users[0].email;
        invitationObject.restaurantId = invitation.selectedRestaurants[0].id;

        console.log(invitationObject);


        return http.get('');
    }

}