CREATE TABLE `banking_core_user` ( `id` bigint(20) NOT NULL, `email` varchar(255) DEFAULT NULL, `first_name` varchar(255) DEFAULT NULL, `identification_number` varchar(255) DEFAULT NULL, `last_name` varchar(255) DEFAULT NULL );

CREATE TABLE `banking_core_account` ( `id` bigint(20) NOT NULL, `actual_balance` decimal(19, 2) DEFAULT NULL, `available_balance` decimal(19, 2) DEFAULT NULL, `NUMBER` varchar(255) DEFAULT NULL, `STATUS` varchar(255) DEFAULT NULL, `TYPE` varchar(255) DEFAULT NULL, `user_id` bigint(20) DEFAULT NULL, `version` bigint(20) DEFAULT NULL );

CREATE TABLE `atm` ( `id` bigint(20) NOT NULL, `cvv` int(11) NOT NULL, `expiry` datetime DEFAULT NULL, `cardNumber` varchar(255) DEFAULT NULL, `provider_name` varchar(255) DEFAULT NULL, `account_id` bigint(20) DEFAULT NULL, `pin` int(4) NOT NULL, `card_number` varchar(255) DEFAULT NULL, `version` bigint(20) DEFAULT NULL );

CREATE TABLE `banking_core_transaction` ( `id` bigint(20) NOT NULL, `amount` decimal(19, 2) DEFAULT NULL, `reference_number` varchar(255) DEFAULT NULL, `transaction_id` varchar(255) DEFAULT NULL, `transaction_type` varchar(255) DEFAULT NULL, `account_id` bigint(20) DEFAULT NULL, `date` date DEFAULT NULL );

ALTER TABLE `atm` ADD PRIMARY KEY (`id`), ADD KEY `FKja12tejswxou9slos5iq5cxsi` (`account_id`);
ALTER TABLE `banking_core_account`  ADD PRIMARY KEY (`id`), ADD KEY `FKt5uqy9p0v3rp3yhlgvm7ep0ij` (`user_id`);
--
-- Indexes for table `banking_core_transaction`
--
ALTER TABLE `banking_core_transaction` ADD PRIMARY KEY (`id`), ADD KEY `FKk9w2ogq595jbe8r2due7vv3xr` (`account_id`);

--
-- Indexes for table `banking_core_user`
--
ALTER TABLE `banking_core_user`  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `atm`
--
ALTER TABLE `atm` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `banking_core_account`
--
ALTER TABLE `banking_core_account`  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `banking_core_transaction`
--
ALTER TABLE `banking_core_transaction` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `banking_core_user`
--
ALTER TABLE `banking_core_user` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `atm`
--
ALTER TABLE `atm`  ADD CONSTRAINT `FKja12tejswxou9slos5iq5cxsi` FOREIGN KEY (`account_id`) REFERENCES `banking_core_account` (`id`);

--
-- Constraints for table `banking_core_account`
--
ALTER TABLE `banking_core_account` ADD CONSTRAINT `FKt5uqy9p0v3rp3yhlgvm7ep0ij` FOREIGN KEY (`user_id`) REFERENCES `banking_core_user` (`id`);

--
-- Constraints for table `banking_core_transaction`
--
ALTER TABLE `banking_core_transaction`ADD CONSTRAINT `FKk9w2ogq595jbe8r2due7vv3xr` FOREIGN KEY (`account_id`) REFERENCES `banking_core_account` (`id`);
