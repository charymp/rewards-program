# Read Me First

##Business Requirement
A retailer offers a rewards program to its customers, awarding points based on each valid purchase. 

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction 
(e.g. if one customer spends $120 total in a transaction, then points earned is 2x$20 + 1x$50 = 90 points).

Given all the transactions of every customer during a period of time(1 year for example), calculate the reward points earned for each customer per month and total.

# Prerequisite to Run the Application/Project
	Java 17.0.7 or above
	Latest or compatible browser, preferably Chrome

# How To Execute the Project
	Step-1: Either run the rewards-program\target\rewards-program-0.0.1-SNAPSHOT.jar directly, (OR)
		Import this maven project to any Spring Boot supported IDE (Eclipse or other that supports Java 17)

	Step-2: Run the below .java file (by right click and Run as Java Application or using any other equivalent options)
		rewards-program\src\main\java\com\retailer\api\rest\rewardsprogram\RewardsProgramApplication.java

	Step-3: Once the application runs successfully, go to any Browser (preferable Chrmore) and open below Swagger UI URL
		http://localhost:8080/swagger-ui/index.html	
	
	Step-4: Swagger UI is self explanatory and it guides on what all REST APIs are exposed and how to run those
		- Base URL: http://localhost:8080
		- API-1: POST /rewards/transactions/create
		- API-2: GET  /rewards/transactions/all
		- API-3: POST /rewards/customers/create
		- API-4: GET  /rewards/customers/all
		- API-5: GET  /rewards/per-customer/{last_x_years}/{customer_id}
		- API-6: GET  /rewards/customers/all
		- API-7: GET  /rewards/per-customer/{id}
		- API-8: GET  /rewards/for-all-customers		
		- Note: Default customer ids available in DB: 1, 2, 3


# TODO list (Optional)
		- Unit Tests
		- Exception Handling
		- Logging for debugging purposes
