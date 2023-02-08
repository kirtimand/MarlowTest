# MarlowTest

### Microservices:
1) Eureka Discovery Service 
2) Congif Service
3) Cloud Gateway Service
4) ATM Service 
5) Notification Service

### Database:
My SQL 



### Architecture:

![ATMService](https://user-images.githubusercontent.com/84015066/217490709-fabc4721-a85a-4204-b46a-494d7c8d2675.jpg)





### Build & Execute services individually
1) For each microservice execute:
  mvn clean
2) java -jar target/.jar   

Path to config file: https://github.com/kirtimand/configuration.git


### Testing:
  Test folder contains postman collection , jmeter script to test atm service api.
  
  This service is tested for 100 concurrent requests on same and different accounts, results are attached in csv format in test folder. 
  


  
