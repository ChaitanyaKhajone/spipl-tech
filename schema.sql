CREATE USER 'clinic'@'localhost' IDENTIFIED BY 'clinic@DB#2026';

CREATE DATABASE  clinic_db;

GRANT ALL PRIVILEGES ON clinic_db.* TO 'clinic'@'localhost';

USE clinic_db;

CREATE TABLE patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(11) NOT NULL,
    contact VARCHAR(15) NOT NULL,
    address VARCHAR(500) NOT NULL
);

CREATE TABLE specialisation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialisation_id INT NOT NULL,
    consultation_fee DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (specialisation_id) REFERENCES specialisation(id)
);

CREATE TABLE appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    slot_time TIME NOT NULL,
    status VARCHAR(9) DEFAULT 'BOOKED',
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    CONSTRAINT unique_doctor_slot UNIQUE (doctor_id, appointment_date, slot_time)
);
