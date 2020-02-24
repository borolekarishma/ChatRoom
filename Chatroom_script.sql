create database chatRoomData;

CREATE USER 'mysqluser'@'localhost' IDENTIFIED BY 'test123';

GRANT ALL PRIVILEGES ON chatRoomData.* TO 'mysqluser'@'localhost';

use chatRoomData;