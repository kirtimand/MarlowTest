INSERT INTO `banking_core_user` (`id`, `email`, `first_name`, `identification_number`, `last_name`) VALUES(1, 'kirti2091@gmail.com', 'kirti', '1234', 'nile');

INSERT INTO `banking_core_account` (`id`, `actual_balance`, `available_balance`, `NUMBER`, `STATUS`, `TYPE`, `user_id`, `version`) VALUES(1, '0.00', '50.00', '1', 'ACTIVE', 'SAVINGS_ACCOUNT', 1, 108);

INSERT INTO `atm` (`id`, `cvv`, `expiry`, `cardNumber`, `provider_name`, `account_id`, `pin`, `card_number`, `version`) VALUES (1, 343, '2027-03-31 00:00:00', '123456789111', 'Debit', 1, 1234, '123456789111', 46);

INSERT INTO `banking_core_user` (`id`, `email`, `first_name`, `identification_number`, `last_name`) VALUES(2, 'abc@gmail.com', 'abc', '8888', 'xyz');

INSERT INTO `banking_core_account` (`id`, `actual_balance`, `available_balance`, `NUMBER`, `STATUS`, `TYPE`, `user_id`, `version`) VALUES(2, '0.00', '50.00', '23452123', 'ACTIVE', 'SAVINGS_ACCOUNT', 2,0);

INSERT INTO `atm` (`id`, `cvv`, `expiry`, `cardNumber`, `provider_name`, `account_id`, `pin`, `card_number`, `version`) VALUES (2, 867, '2027-03-31 00:00:00', '123456789222', 'Debit', 2, 8888, '123456789222', 0);


