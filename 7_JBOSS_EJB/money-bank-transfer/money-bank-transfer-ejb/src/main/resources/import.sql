-- You can use this file to load seed data into the database using SQL statements
--drop table REGISTRANTTEST;
--CREATE TABLE REGISTRANT (ID INT, NAME VARCHAR, email VARCHAR, phone_number VARCHAR);
--insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212');

--drop table Currency;
--CREATE TABLE Currency (ID INT, NAME VARCHAR, shortCode VARCHAR, code VARCHAR);
insert into Currency (id, name, shortCode, code) values (1, 'Dollar USA', '$', 841);
insert into Currency (id, name, shortCode, code) values (2, 'EURO', '€', 978);
insert into Currency (id, name, shortCode, code) values (3, 'Russian Ruble', 'р', 643);


--drop table Bank;
--CREATE TABLE Bank (ID INT, code int, name VARCHAR);		
		
insert into Bank (code, name) values (31, 'VTB');
insert into Bank (code, name) values (45, 'MTBank');
insert into Bank (code, name) values (14, 'PriorBank');

--create table Customer(ID INT, firstname VARCHAR, lastname VARCHAR);
insert into Customer (id, firstName, lastName) values (1, 'Ivan', 'Ivankov')

--create table Account (ID INT, firstname VARCHAR, lastname VARCHAR);

--insert into Account (id, account_number, FK_person_id, amount, FK_currency_id) values (1, 12, 1, 100, 1)