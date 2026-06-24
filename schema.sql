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

INSERT INTO patient (name, age, gender, contact, address)
VALUES
('Aarav Deshmukh', 34, 'Male', '+919823012345', 'Flat 402, Shanti Niwas, FC Road, Shivaji Nagar, Pune, Maharashtra - 411005'),
('Ananya Kulkarni', 29, 'Female', '+919145123456', 'House No. 12, Ram Maruti Road, Naupada, Thane West, Mumbai, Maharashtra - 400602'),
('Rahul Chavan', 45, 'Male', '+919503112233', 'Plot 56, Anand Nagar, Near Cidco Office, Aurangabad, Maharashtra - 431003'),
('Priya Joshi', 62, 'Female', '+918888456789', 'A-3, Laxmi Apartments, Dhantoli, Nagpur, Maharashtra - 440012'),
('Amit Patil', 23, 'Male', '+917798112244', 'Gat No. 112, Near Sugar Factory, Kasaba Bawada, Kolhapur, Maharashtra - 416006');

CREATE TABLE specialisation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO specialisation (name)
VALUES
('Ayurveda'),
('Homeopathy'),
('General Medicine'),
('Cardiology'),
('Orthopedics');

CREATE TABLE doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialisation_id INT NOT NULL,
    consultation_fee DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (specialisation_id) REFERENCES specialisation(id)
);

INSERT INTO doctor (name, specialisation_id, consultation_fee, id) VALUES
('Dr. Prakash Joshi', 1, 300.00, 1),
('Dr. Anuradha Deshmukh', 2, 250.00, 2),
('Dr. Sanjay Patil', 3, 400.00, 3),
('Dr. Milind Kulkarni', 4, 800.00, 4),
('Dr. Anjali Chavan', 5, 600.00, 5);

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

INSERT INTO appointment (doctor_id, patient_id, appointment_date, slot_time, status)
VALUES
(1, 1, '2026-06-24', '09:30:00', 'BOOKED'),
(2, 2, '2026-06-24', '10:15:00', 'BOOKED'),
(3, 3, '2026-06-24', '11:00:00', 'CANCELLED'),
(4, 4, '2026-06-24', '16:30:00', 'BOOKED'),
(5, 5, '2026-06-24', '17:15:00', 'BOOKED');
