# ATM-Service

### API Details:

1) Deposit API:
   
 ``` 
  Request URI: localhost:8760/api/v1/transactions/deposit
  Method: POST
  Request Body: 
{
        "pin":1234,       
         "cardNo":"123456789111",
        "amount":1.0
    }  
 ``` 
Note
-   pin:ATM PIN
-   cardNo: ATM Card Number
-   amount: Amount to deposit
  
 ``` 
   Success Response:
          {
          "message": "Deposit successful",
          "transactionId": "ceb5f368-6463-41b8-bb7d-af36e485341a"
      }
```	 

2)Withdraw API:
```  
  Request URI: localhost:8760/api/v1/transactions/withdraw
  Method: POST
  Request Body: 
   {
        "pin":1234,       
         "cardNo":"123456789111",
        "amount":1.0
    } 
````    
      
  
-   Note
-   pin:ATM PIN
-   cardNo: ATM Card Number
-   amount: Amount to deposit
  
 ``` 
    Success Response:
          {
          "message": "Withdraw successful",
          "transactionId": "ceb5f368-6463-41b8-bb7d-af36e485341a"
      }	  
```    
  Invalid Request/Bad Request Response:
```
    {
        "errors": [
            "Card Number must be 12 characters",
            "Please enter valid PIN",
            "Amount should be greater then 0 and less then 100000"
        ]
    }
    
```
Invalid Card Response:
In case of ATM card number and pin combination is incorrect, or card is expired it will give
    
    {
        "code": "Given Card is not valid 123456789110",
        "message": "BANKING-CORE-SERVICE-1000"
    }


Inactive Account Response:
In case of Bank Account is InActive
    
    {
        "code": "Inactive Account 123456789110",
        "message": "BANKING-CORE-SERVICE-1002"
    }


INSUFFICIENT FUNDS Response:
In case of insufficient funds in account while withdraw
    
    {
        "code": "Insufficient funds in the account 123456789110",
        "message": "BANKING-CORE-SERVICE-1001"
    }
    
    
    
