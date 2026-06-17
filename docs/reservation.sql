CREATE DATABASE IF NOT EXISTS gtm_db;
USE gtm_db;


CREATE TABLE IF NOT EXISTS reservation (

    id INT AUTO_INCREMENT PRIMARY KEY,
    contact_name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    time VARCHAR(20) NOT NULL,
    holes INT NOT NULL,
    players INT NOT NULL,
    extra VARCHAR(255),
    total_price DECIMAL(10,2) NOT NULL
    );

SELECT * FROM reservation;

