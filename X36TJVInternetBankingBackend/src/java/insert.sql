INSERT INTO x36tjv.bank (`CODE`, `NAME`) VALUES (100, 'Komerční banka');
INSERT INTO x36tjv.bank (`CODE`, `NAME`) VALUES (123, 'Bank of Japan');
INSERT INTO x36tjv.bank (`CODE`, `NAME`) VALUES (540, 'Chinese Bank of Mao');
INSERT INTO x36tjv.bank (`CODE`, `NAME`) VALUES (800, 'Česká spořitelna');
INSERT INTO x36tjv.bank (`CODE`, `NAME`) VALUES (999, 'Bank of America');

INSERT INTO x36tjv.currency (`CODE`, `NAME`, `NUMOFDIGITS`) VALUES ('CNY', 'Chinese Yuan', 1);
INSERT INTO x36tjv.currency (`CODE`, `NAME`, `NUMOFDIGITS`) VALUES ('CZK', 'Česká koruna', 2);
INSERT INTO x36tjv.currency (`CODE`, `NAME`, `NUMOFDIGITS`) VALUES ('USD', 'American Dollar', 2);
INSERT INTO x36tjv.currency (`CODE`, `NAME`, `NUMOFDIGITS`) VALUES ('EUR', 'Euro', 2);
INSERT INTO x36tjv.currency (`CODE`, `NAME`, `NUMOFDIGITS`) VALUES ('JPY', 'Japanese Yen', 0);

INSERT INTO x36tjv.currencyrate (`CODE`, `RATE`) VALUES ('CZK', 8.50);
INSERT INTO x36tjv.currencyrate (`CODE`, `RATE`) VALUES ('JPY', 8.15);
INSERT INTO x36tjv.currencyrate (`CODE`, `RATE`) VALUES ('CNY', 7.4);
INSERT INTO x36tjv.currencyrate (`CODE`, `RATE`) VALUES ('USD', 2.1);
INSERT INTO x36tjv.currencyrate (`CODE`, `RATE`) VALUES ('EUR', 1.25);

INSERT INTO x36tjv.currentcurrencyrate (`CODE`, `RATE`) VALUES ('CZK', 8.50);
INSERT INTO x36tjv.currentcurrencyrate (`CODE`, `RATE`) VALUES ('JPY', 8.15);
INSERT INTO x36tjv.currentcurrencyrate (`CODE`, `RATE`) VALUES ('CNY', 7.4);
INSERT INTO x36tjv.currentcurrencyrate (`CODE`, `RATE`) VALUES ('USD', 2.1);
INSERT INTO x36tjv.currentcurrencyrate (`CODE`, `RATE`) VALUES ('EUR', 1.25);

INSERT INTO x36tjv.customer (`LASTNAME`, `EMAIL`, `FIRSTNAME`) VALUES ('Novák', 'pepa@seznam.cz', 'Pepa');
INSERT INTO x36tjv.customer (`LASTNAME`, `EMAIL`, `FIRSTNAME`) VALUES ('Hynek', 'karel@centrum.cz', 'Karel');
INSERT INTO x36tjv.customer (`LASTNAME`, `EMAIL`, `FIRSTNAME`) VALUES ('Dvořáková', 'aneta@atlas.cz', 'Aneta');

INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, NULL, 'CZK', 100);
INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, 1, 'CZK', 100);
INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, 2, 'CZK', 800);
INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, 2, 'CNY', 540);
INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, 3, 'EUR', 100);
INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, 3, 'JPY', 123);
INSERT INTO x36tjv.account (`BALANCE`, `CUSTOMER_ID`, `CURRENCY_CODE`, `BANK_CODE`) VALUES (1000, 1, 'USD', 999);

INSERT INTO x36tjv.exchangerate (`ID`, `RATE`) VALUES ('CZK', 1.0000);
INSERT INTO x36tjv.exchangerate (`ID`, `RATE`) VALUES ('EUR', 25.3650);
INSERT INTO x36tjv.exchangerate (`ID`, `RATE`) VALUES ('USD', 18.7080);
INSERT INTO x36tjv.exchangerate (`ID`, `RATE`) VALUES ('CNY', 2.7412);
INSERT INTO x36tjv.exchangerate (`ID`, `RATE`) VALUES ('JPY', 19.9081);
