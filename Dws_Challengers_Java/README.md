This project is build for Add functionality for a transfer of money between accounts. : 

Environment details : 
Language : Java 8
Framework : Spring boot
Database : H2 
Documentation tool : Swagger 

Rest end points : 
	URL : http://localhost:18080/v1/accounts
	HTTP Method : Post
	Description : Use tihs end point to create the account with balance
	Sample input : 
	     {

              "accountId":54321,
             "balance":10000
             }

       Sample input : 
	      {

              "accountId":1234,
             "balance":10000
             }
	
	URL : http://localhost:18080/v1/accounts/54321
	HTTP Method : Get
	Description : Use tihs end point get the list of transactions.
	Sample Response : 
		{

              "accountId":54321,
             "balance":10000
             }
	
	URL : http://localhost:18080/v1/accounts/transaction
	HTTP Method : POST
	Description : Use tihs end point save and transfer amount toAccount to fromAccount.
	Sample input : 
	 
            {
	
		"fromAccountId":54321,
		"toAccountId":"1234",
		"amount" :10000
	}
	 Sample output : 
	  [
             {
        	   "accountId": "54321",
    	           "balance": 0
    	     },
    	     {
        	"accountId": "1234",
        	"balance": 20000
    	     }
         ]