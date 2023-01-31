--Insert into patient(patient_Id,patient_Name,date_of_birth,gender,address,mobile_number) values(100,'Himadri','06/06/1985','M','Address1','9884473347');
--Insert into patient(patient_Id,patient_Name,date_of_birth,gender,address,mobile_number) values(200,'Rohit','06/12/1984','M','Address2','9884473348');
Insert into patient(patient_Id,patient_Name,date_of_birth,gender) values(100,'Himadri','06/06/1985','M');
Insert into patient(patient_Id,patient_Name,date_of_birth,gender) values(200,'Rohit','06/12/1984','M');
Insert into address(address_id, address_type, city, postal_code, state, street, patient_id_fk) values(111, 'Present Address', 'Chennai', '600097' ,'Tamil Nadu', 'Thoraipakkam',100);
Insert into address(address_id, address_type, city, postal_code, state, street, patient_id_fk) values(222, 'Present Address', 'Chennai', '600097' ,'Tamil Nadu', 'Thoraipakkam',200);
Insert into Telephone( phone_id, country_code, phone_number, phone_type,  patient_id_fk) values(1111, '+91','9884473347', 'Primary Contact', 100);
Insert into Telephone( phone_id, country_code, phone_number, phone_type,  patient_id_fk) values(2222, '+91','9884473XXX', 'Alternate Contact', 100);
