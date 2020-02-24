Chatroom Application:

The chatroom application can perform the following:

1. signin and signout of the Application
2. signup new users
3. list all the chatrooms
4. create new chatroom
5. post messages to any chat room (user does not need to join the chatroom but should be authenticated)
6. post message directly to any other user using their username
7. list messages from any chat room
8. list messages posted to directly them


The following functionality is not currently available:
9. subscribe for real time delivery of messages from any chat room
10. subscribe for real time delivery of messages posted to directly them
11. User interface for the chatroom service (Work in progress using Angular7)

Technologies used:
Java
Spring boot
MySQL database
jboss 7.2 EAP

Steps to run the application:

1. Run the attached sql script in mysql to create the schema or update the application.properties under resources to update the datasource details
2. Build the application using
      mvn clean package
3. Deploy the war file in Jboss
4. Test the rest APIs as documented below. (Please find the attached postman collection Chatroom.postman_collection.json)
https://documenter.getpostman.com/view/10492857/SzKVRJZD
