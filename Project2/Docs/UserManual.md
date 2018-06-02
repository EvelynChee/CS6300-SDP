# **User Manual for TCCart**

## Document Revisions

Author: 6300Spring16Team85   
Date: 3/26/2016   
Document Version: 1.0    
Document Changes: Initial Release

## Table of Contents

1. Introduction   
1.1 Scope and Purpose   
1.2 Process Overview   
2. Using TCCart (Use Cases)   
2.1 Creating Customer  
2.2 Finding Customer  
2.3 Modify Customer Name/Email   
2.4 Place Order   
2.5 Order History   
3. Using TCCart - Additional Functions   
3.1 Print New Customer Card   
3.2 Database Maintenance   
4. Additional Information   

## 1 Introduction

### 1.1 Scope and Purpose

TCCart is a tea and coffee cart management application developed for Android. It has five major uses which are creating a new customer, finding an existing customer, modifying the customer's name & email, placing an order, and viewing a customer's order history. It also provides features including a rewards system, credit card swiping, credit card processing, customer card printing, customer card scanning, and an email notification system. This User Manual will show the User how to use the application in the given use cases and will also explain related details and options. Enjoy!

### 1.2 Process Overview

TCCart must run on an Android device with Android API Level 19 (KitKat) installed. After the application is installed it will generate a new TCCart.db SQLite on the device for persistance. See Additional Information for more notes about the application.

## 2 Using TCCart - Main Use Cases

### 2.1 Creating Customer  

1. Open the application, "TCCart Main Menu" should be showing.   
2. Click on the "New Customer" button, this should bring up the "Customer Management, New Customer Information" screen.
3. Fill in the name and a valid email address and click the "Save Customer Information" button.
	* If you see a message that says "Please provide a valid email address!" double check that your email address is in a valid format and try to save again.
4. When successful, you should a message that says "New Customer Record Created" and the application will return to the "TCCart Main Menu". The customer is now loaded as the current customer and their name should show up in the "Customer: " field.
5. To print the new customer's card see section 3.1.   

### 2.2 Finding Customer     

1. Open the application, "TCCart Main Menu" should be showing.   
2. Click on the "Scan Customer Card" button to scan the customer's card.
	* If the "UniqueID: " field shows "QRCode Scan Error", the customer card scan failed and you need to click "Scan Customer Card" button again.
3. When successful, the customer is now loaded as the current customer and their name should show up in the "Customer: " field.    

### 2.3 Modify Customer Name/Email    

1. Open the application, "TCCart Main Menu" should be showing.   
2. Make sure a customer is loaded as the current customer. This can be done by creating a new customer (Section 2.1) or finding an existing customer (Section 2.2). The customer name and unique ID should show up on the "TCCart Main Menu" page.
3. Click on the "Customer Management" button, this should bring up the "Customer Management" screen.
4. Click the "Edit Customer Information" button, this should bring up the "Customer Management, Edit Customer Information" screen.
5. Edit the name and/or email address and click the "Save Customer Information" button.
	* If you see a message that says "Please provide a valid email address!" double check that your email address is in a valid format and try to save again.
6. When successful, you should see a message that says "Customer Record Updated!". Now use the phone's back button to return to the "TCCart Main Menu". The customer should be loaded as the current customer and their name (new name if it was updated) should show up in the "Customer: " field.

### 2.4 Place Order   

1. Open the application, "TCCart Main Menu" should be showing.   
2. Make sure a customer is loaded as the current customer. This can be done by creating a new customer (Section 2.1) or finding an existing customer (Section 2.2). The customer name and unique ID should show up on the "TCCart Main Menu" page.
3. Click the "Create Order" button, this should bring up the "Order Management" screen.
4. Generate the order:
	* You can select "Tea" or "Coffee" from the drop down box.
	* You can use the default price (Tea = $2.50, Coffee = $4.25) or edit the Unit Price field to the value of your choosing.
	* Click the "Add" button to add the item to the shopping cart.
	* Click on the shopping cart item to delete it from the cart.
5. When the order is ready, click the "Complete Order and Checkout" button, this should bring up the "Payment Management" screen.
	* If you see a message that says "Please add some item into the Cart!" the cart is empty and you need to add items to proceed.
6. Swipe the credit card by clicking the "Swipe Card" button.
	* If you see a message that says "Credit Card Swipe Error" the credit card swipe failed, click the "Swipe Card" button again.
	* If the "Swipe Card" button is disabled, this means the order was paid for by discounts and reward credits and no payment is needed. Continue to step 8.
7. When successful the "Card Number: ", "Exp. Date: ", and "CVV: " fields should be filled in.
8. Process the payment by clicking the "Process Payment" button.
	* If you see a message that says "Payment Processing Failed", the payment processing failed, click the "Process Payment" button again.
	* If you see a message that says "Email failed to send." the email failed to send. Click the "Send Notification" button again.
9. When successful you will see a "Payment Processed" message and a message with the email sent to the customer. The application will return to the "TCCart Main Menu" screen.

### 2.5 Order History   

1. Open the application, "TCCart Main Menu" should be showing.   
2. Make sure a customer is loaded as the current customer. This can be done by creating a new customer (Section 2.1) or finding an existing customer (Section 2.2). The customer name and unique ID should show up on the "TCCart Main Menu" page.
3. Click on the "Customer Management" button, this should bring up the "Customer Information" screen.
4. Click the "Show Transaction History" button, this should bring up the "Transaction History" screen.

## 3 Using TCCart - Additional Functions
### 3.1 Print New Customer Card
1. After you have created a new customer (Section 2.1) and they are loaded as the current customer. Click the "Customer Management" button from the "TCCart Main Menu" screen, this should bring up the "Customer Management" screen.
2. Click the "Print Customer Card" button to print the customer's card.
	* If you see a message that says "Error Occurred, Please try again!" click the "Print Customer Card" button again.
3. When successful, you should see a message that says "Card Printed.".   

### 3.2 Database Maintenance   
Database maintenance needs to be done the first time every year that the client opens up the application. Its primary function is to look through and set each Customer's VIP status to true if they spent over $300 in the previous year. To perform the database maintenance:    

1. Open the application, "TCCart Main Menu" should be showing.   
2. Click on the "Database Maintenance" button, this should show the message "Database Maintenance Completed" and clear the "Unique ID:" and "Customer:" fields.   
	* Although it is only meant to be used once at the beginning of each year, it will not hurt anything to click the "Database Maintenance" button more than once. It will actually double check the current VIP status and spending balance.   

## 4 Additional Information
* In the future we will implement customer lookup by email to allow an existing customer to print a new card if they lose theirs. The current application does not include this functionality.
* Each customer reaches VIP status after spending $300 in a given year. The first time the User opens the app during a new year, they must run the Database Maintenance in section 3.2 which does a batch processing to set the VIP status for Customer's that obtained the status.   
* An email is sent whenever a payment is successfully processed. Depending on the customer's status it may also include credit reward balance information and VIP status information.

