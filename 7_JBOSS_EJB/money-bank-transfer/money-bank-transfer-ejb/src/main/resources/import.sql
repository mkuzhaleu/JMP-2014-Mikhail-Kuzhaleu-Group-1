
insert into Currency (currencyId, name, shortCode, code) values (1, 'Dollar USA', '$', 841);
insert into Currency (currencyId, name, shortCode, code) values (2, 'EURO', '€', 978);
insert into Currency (currencyId, name, shortCode, code) values (3, 'Russian Ruble', 'р', 643);

insert into Bank (bankId, name) values (31, 'VTB');
insert into Bank (bankId, name) values (45, 'MTBank');
insert into Bank (BankId, name) values (14, 'PriorBank');

insert into Customer (customerId, firstName, lastName) values (1, 'Alexey', 'Zalivko')


insert into Account (id, bank_Id, number, fk_currency_id, moneyValue, FK_customer_id) values (1, 31, '123456789', 1, 100, 1);
insert into Account (id, bank_Id, number, fk_currency_id, moneyValue, FK_customer_id) values (2, 45, '987654321', 1, 50, 1);
insert into Account (id, bank_Id, number, fk_currency_id, moneyValue, FK_customer_id) values (3, 14, '5555555', 2, 25, 1);
insert into Account (id, bank_Id, number, fk_currency_id, moneyValue, FK_customer_id) values (4, 14, '3333', 1, 5, 1);

--insert into Exchange_Rate (id, bank_Id, FK_currency_id_from, FK_currency_id_to, rate) values (1, 14, 1, 2, 0.78);
insert into Exchange_Rate (id, rate, bankId, FK_currency_id_from, FK_currency_id_to) values (1, 0.78, 14, 1, 2);