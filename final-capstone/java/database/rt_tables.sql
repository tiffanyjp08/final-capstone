CREATE TABLE user_details (

	user_id integer NOT NULL,
	firstName varchar(50) NOT NULL,
	lastName varchar(50) NOT NULL,
	emailAddress varchar(50) NOT NULL,
	
	CONSTRAINT pk_user_details_users_user_id PRIMARY KEY (user_id)
);

--How to add another table's primary key as a foreign key
ALTER TABLE user_details ADD FOREIGN KEY (user_id) REFERENCES users(user_id);

INSERT INTO user_details (user_id, firstname, lastname, emailaddress)
VALUES (1, 'UserFirst', 'UserLast', 'user@gmail.com'),
(2, 'AdminFirst', 'AdminLast', 'admin@gmail.com');

CREATE TABLE restaurant_display (

        id integer NOT NULL,
        name varchar(100) NOT NULL,
        location varchar(40) NOT NULL,
        displayAddress varchar(40) NOT NULL, 
        category varchar(50) NULL,
        image varchar(50) NULL,
        displayPhone varchar(20) NULL,
        
        CONSTRAINT pk_id PRIMARY KEY (id)
);

CREATE TABLE restaurant_user (
        
        host_id integer NOT NULL,
        restaurant_id integer NOT NULL,
        isLiked BOOLEAN,
        
        CONSTRAINT pk_restaurant_user_users_user_id PRIMARY KEY (host_id, restaurant_id)
);

ALTER TABLE restaurant_user ADD FOREIGN KEY (host_id) REFERENCES user_details(user_id);
ALTER TABLE restaurant_user ADD FOREIGN KEY (restaurant_id) REFERENCES restaurant_display(id);

CREATE TABLE invitation (

        invite_id serial,
        host_id integer NOT NULL,
        zipcode varchar(40) NOT NULL,
        dateTime varchar(40) NULL,
        restaurant_id integer NULL,
        isFinal BOOLEAN DEFAULT false NOT NULL,  
         
        CONSTRAINT pk_invitation_user_details_user_id PRIMARY KEY (invite_id)
);

ALTER TABLE invitation ADD FOREIGN KEY (host_id) REFERENCES user_details(user_id);
ALTER TABLE invitation ADD FOREIGN KEy (restaurant_id) REFERENCES restaurant_display(id);

CREATE TABLE restaurants_likes (

        restaurant_id integer NOT NULL,
        invite_id integer NULL,
        numberLikes numeric DEFAULT 0,
        numberDislikes numeric DEFAULT 0,

        CONSTRAINT pk_restaurants_likes PRIMARY KEY (restaurant_id)
);

ALTER TABLE restaurants_likes ADD FOREIGN KEY (restaurant_id) REFERENCES restaurant_display(id); 

CREATE TABLE invite_registered (

        invite_id integer NOT NULL, 
        invitee_id integer NOT NULL,

        CONSTRAINT pk_invite_registered_invitation_invite_id_invitee_id PRIMARY KEY (invite_id, invitee_id)
);

ALTER TABLE invite_registered ADD FOREIGN KEY (invite_id) REFERENCES invitation (invite_id);
ALTER TABLE invite_registered ADD FOREIGN KEY (invitee_id) REFERENCES user_details(user_id);

CREATE TABLE registered_votes (

        invite_id integer NOT NULL, 
        invitee_id integer NOT NULL,
        restaurant_id integer NOT NULL,
        isLiked BOOLEAN NOT NULL,

        CONSTRAINT pk_registered_votes_invitation_invite_id_invitee_id PRIMARY KEY (invite_id, invitee_id, restaurant_id)
);

ALTER TABLE registered_votes ADD FOREIGN KEY (invite_id) REFERENCES invitation (invite_id);
ALTER TABLE registered_votes ADD FOREIGN KEY (invitee_id) REFERENCES user_details(user_id);
ALTER TABLE registered_votes ADD FOREIGN KEY (restaurant_id) REFERENCES restaurant_display(id);



CREATE TABLE invite_unregistered (

        invite_id integer NOT NULL,
        guest_id serial NOT NULL,
        invitee_firstname varchar(50) NOT NULL, 
        invitee_lastname varchar(50) NOT NULL, 
        invitee_email varchar(50) NOT NULL,

        CONSTRAINT pk_invite_unregistered_invitation_invite_id PRIMARY KEY (guest_id)
);

ALTER TABLE invite_unregistered ADD FOREIGN KEY (invite_id) REFERENCES invitation(invite_id);



CREATE TABLE unregistered_votes (

        invite_id integer NOT NULL, 
        guest_id integer NOT NULL,
        restaurant_id integer NOT NULL,
        isLiked BOOLEAN NOT NULL,

        CONSTRAINT pk_unregistered_votes_invitation_invite_id_invitee_id PRIMARY KEY (invite_id, guest_id, restaurant_id)
);

ALTER TABLE unregistered_votes ADD FOREIGN KEY (invite_id) REFERENCES invitation (invite_id);
ALTER TABLE unregistered_votes ADD FOREIGN KEY (guest_id) REFERENCES invite_unregistered(guest_id);
ALTER TABLE unregistered_votes ADD FOREIGN KEY (restaurant_id) REFERENCES restaurant_display(id);
