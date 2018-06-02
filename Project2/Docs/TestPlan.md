# Test Plan

**Author**: Team 85

| Version | Description     |
| --------|:---------------:|
| V3      |passed all the test cases  |

## 1 Testing Strategy

### 1.1 Overall strategy

#### 1.1.1 Unit Testing

Definition: Unit testing is to test the smallest testable parts of the application, specifically the individual class and its methods, in order to get the proper operation.

Participants: All developers

Methodology: Automated unit (white box) tests are generated from the Design Document and the Class Diagram. The specific tests are developed from the specification of the methods in each class.

#### 1.1.2 Integration Testing

Definition:  Integration testing is to detect any inconsistencies within the individual software modules that are integrated together as a group.

Participants: All developers

Methodology: Integration testing is derived from the component diagram. Each test needs to make sure the group of combined components work properly.


#### 1.1.3 System Testing

Definition: System testing takes all of the software components that have passed integration testing as the input, and seeks to detect defects both within the components and also within the system as a whole. System testing is the final test towards production, and its final goal is to make sure the system satisfies all the system requirements.

Participants: Whole Team

Methodology: In the system testing, black box tests related to use cases are developed from the use case diagrams. Manual testing and possibly automated testing will be performed.

#### 1.1.4 Regression Testing

Definition: Regression testing is to test changes to the programs to make sure that the older programming still works with the new changes.

Participants: all developers

Methodology: When changes are made, all related system tests will be applied to regression testing.


### 1.2 Test Selection


1. Unit testing for classes and individual methods use white-box approach. The specific tests are developed from the specification of the methods in each class.

2. Integration test for components and interfaces are with white-box approach. Each integration test is derived from the component diagram.

3. The system levels testing and the regression testing use the black-box approach. The basis of all the tests cases are the use cases which are derived from the business requirements.

### 1.3 Adequacy Criterion


1. Unit testing should cover all the critical and non-trivial classes and methods. All public methods in each class should be tested. Unit test for each individual class and function should cover different input combinations.

2. All the use cases should be 100% covered by System Testing.

3. Critical integration cases should be covered in Integration Testing.


### 1.4 Bug Tracking

We will create a bug list, with each item linked to a specific test case and steps.
Using the bug list, we track back where the bug happens and what use case it is related to.

### 1.5 Technology

The following tools will be utilized for testing.

Manual test is performed for system and regression test.  



## 2 Test Cases


| test cases  | test steps      | expected result | actual result | pass/fail |
|-----------|---------------|---------------|-------------|:---------:|
| Starting the program | open the program | main menu|main menu shows up|pass|
|Adding customer with wrong email address|1.select "new customer" from the menu.<br> 2.type in the customer name "John Smith" and email "Jsmith.gmail.com". <br>3. press "save changes".|Error message "please provide valid email address" will show up; customer information will not be saved|Error message "please provide valid email address" shows up; customer information is not saved|pass|
| Adding customer | 1.select "new customer" from the menu.<br> 2.type in the customer name "John Smith" and email "Jsmith@gmail.com". <br>3. press "save customer information". |1. Message: "New Customer Record Created". <br> 2. When choosing "customer management" from the main menu, the customer information can be displayed|1. Message: "New Customer Record Created". <br> 2.After choosing "customer management" from the main menu, the customer name, email and unique ID information are displayed|pass|
|Adding customer which already exists in the database | 1.select "new customer" from the menu.<br> 2.type in the customer name "Peter Green" and email "Jsmith@gmail.com".<br> 3. press "save customer information"|1.no new customer is added.<br> 2.Error messeage: "There is another currentCustomer with same email address"|1.No new customer added<br> 2.Error message: "There is another currentCustomer with same email address"|pass|
| Customer card printing | 1.press "customer management" button and enters the customer management page<br> 2.press "print customer card" button|Message:"Card Printed"|Message:"Card Printed"|pass|
| Finding customer | 1.choose "scan customer card" <br> | Customer information is loaded. Customer information is displayed in the customer management page  |Customer information is displayed in the customer management page|pass|
| Finding customer with bad customer card | 1.choose "scan customer card".<br>  | Error information "QRcode scan error" is displayed and no customer is located |Error information "QRcode scan error" is displayed and no customer is located|pass|
| Modifying customer name| 1.enter “customer management” page from main menu <br>2.select "edit customer information"<br> 3. re-enter customer name "John Green"<br> 4. press "save customer information" | 1.Message:"Customer record updated"<br>2.after press "back" button, the new customer name is displayed |1.Message:"Customer record updated"<br>2.after press "back" button, the new customer name "John Green" is displayed |pass|
| Modifying customer email| 1.enter "customer management" page from the main menu <br>2. select "edit customer information"<br> 3. re-enter customer email "Johnsmith@gmail.com" <br>4. press "save customer information" | 1.Message:"Customer record updated"<br>2.after press "back" button, the new customer email is displayed |1.Message:"Customer record updated"<br>2.after press "back" button, the new customer email "Johnsmith@gmail.com" is displayed|pass|
| Modifying customer email to an existing one| 1.create a customer with name "Peter Green" and email "pgreen@gmail.com" <br>2. select "edit customer information"<br> 3. re-enter customer email "Johnsmith@gmail.com" <br>4. press "save customer information" | 1.Error Message:"There is another current customer with same email address"<br>2.customer email is not modified. |1.Error Message:"There is another current customer with same email address"<br>2.customer email is not modified. |pass|
| Placing order | In main menu page, <br>1. scan the customer card.<br> 2. choose "create order"<br> 3. choose tea and press "ADD" 3 times. choose coffee and press "ADD" button 3 times.<br> 4. press "complete order and checkout" button and enter payment management page <br>5.choose "swipe card" and swipe the card <br>6. choose "process payment" to confirm payment |  1. order information displayed on the screen<br> 2. credit card information displayed on screen<br> 3.Message"credit card swipe successful" after pressing "swipe card" button <br> 4. Message "Payment processed" after pressing "process payment" button <br> 5. an email-sent notification containing the transaction number, date, cart total ,discount, reward, and yearly spent balance.  |1. order information: Cart total:$20.25; VIP Customer Discount:-$0.00; Reward Credit Discount:-$0.00; Total:$20.25<br> 2.credit card information: Card number: 9328895960019440; Exp. Date: 12/31/2016; CVV:111<br> 3.Message"swipe credit card successful" after pressing "swipe card" button <br> 4. Message "Payment processed" after pressing "process payment" button<br> 5. an email-sent notification:"Email sent: Transaction 875330680940 completed on Sat Mar 26 21:20:56 PDT 2016 for a total of 20.25. Your current credit balance is $0.00 and expires on Tue Apr 26 20:56:11 PDT 2016. Your current yearly spend balance is $114.75"|pass|
| Placing order with no item in cart | 1. choose "scan customer card" and scan the customer card.<br> 2. choose "create order" <br> 3. choose "complete order"  | Error message: "Please add item to the cart"|Error message: "Please add item to the cart|pass|
|Add an item with empty unit price| 1. choose "scan customer card" and scan the customer card.<br> 2. choose "create order" <br> 3. delete the unit price and press "add".|error message: "Please check unit price and try again"|error message: "Please check unit price and try again"|pass|
| Swiping credit card | same as "Placing order"| credit card information displayed on the screen at the payment management step|credit card information displayed on the screen:<br>  Card Number: 9328895960019440<br> Exp. Date: 12/31/2016 <br> CVV:111|pass|
| Swiping bad credit card | same as "Placing order" | credit card is rejected. Error message: "CC swipe error"|credit card is rejected. Error message: "CC swipe error"|pass|
|Payment suceed but email not sent|same as "Placing order"|a button "Send Notification" shows up. Email will be sent after pressing it|a button "Send Notification" shows up. Email is sent after pressing it|pass|
| Purchase: scenario1 | conditions: <br>1.without VIP status<br> 2.reward balance=0<br> 3.place an order of $20 | 1.total cart amount is $20<br> 2.VIP discount=0; reward credit discount=0 <br>3.credit card charged $20 <br>4.no reward earned |1.total cart amount is $20<br> 2.VIP discount=0; reward credit discount=0 <br>3.credit card charged $20 <br>4.reward balance=0 |pass|
| Purchase: scenario2 | conditions: <br>1.without VIP status<br> 2.reward balance=0<br> 3. place an order of $40 | 1.total cart amount is $40<br>  2.VIP discount=0; reward credit discount=0 <br>3.reward balance=$3<br>  4.credit card charged $40|1.total cart amount is $40<br>  2.VIP discount=0; reward credit discount=0 <br>3.reward balance=$3<br>  4.credit card charged $40|pass|
| Purchase: scenario3 | conditions: <br>1.without VIP status <br>2.reward balance=3<br> 3.place an order of $350 | 1. Cart total amount=$350 <br>2.VIP discount=0; reward credit discount=-$3 <br>3.reward balance=$3<br> 4.an notification of VIP notification.<br> 5.credit card charged $347 |1. Cart total amount=$350 <br>2.VIP discount=0; reward credit discount=-$3 <br>3.reward balance=$3<br> 4.an notification of VIP notification.<br> 5.credit card charged $347|pass|
|Purchase: scenario4 | conditions: <br>1. with VIP status <br>2.reward balance=$3<br> 3.place an order of $20 | 1.Cart total amount=$20 <br>2.VIP discount=-$2.00; Reward credit discount=-$3.00<br> 3. reward balance=0 <br>4.credit card charged $15 |1.Cart total amount=$20 <br>2.VIP discount=-$2.00; Reward credit discount=-$3.00<br> 3. reward balance=0 <br>4.credit card charged $15|pass|
|Purchase: scenario5 | conditions: <br>1. with VIP status <br>2.reward balance=0  <br>3.place an order of $40 | 1.Cart total amount of=$40 <br>2.VIP discount=-$4; Reward credit discount=0;<br>3.Reward balance=$3 <br>4.credit card charged $36 |1.Cart total amount of=$40 <br>2.VIP discount=-$4; Reward credit discount=0;<br>3.Reward balance=$3 <br>4.credit card charged $36|pass|
|Purchase: scenario6 | conditions: <br>1. with VIP status <br>2.reward balance=$3 <br> 3.place an order of $400 | 1.Cart total amount=$400<br> 2.VIP discount=-$40; Reward credit discount=-$3<br>3.Reward balance=$3 <br> 4.credit card charged $357 |1.Cart total amount=$400<br> 2.VIP discount=-$40; Reward credit discount=-$3<br>3.Reward balance=$3 <br> 4.credit card charged $357|pass|
|Purchase: scenario7 | conditions: <br>1.without VIP status <br> 2.reward balance=3 but expired<br> 3.place an order of $20 | 1. Cart total amount=$20 <br> 2.VIP discount=0 <br> 3.reward cridit discount=3. <br> 4.reward balance=0 <br>5.credit card charged $20| 1. Cart total amount=$20 <br> 2.VIP discount=0 <br> 3.reward cridit discount=3. <br> 4.reward balance=0 <br>5.credit card charged $20|pass|
|Order history display | 1. scan the customer card.<br> 2.choose "Customer management" <br>3. choose "show transaction history" |transaction history is displayed with purchase details |transaction history is displayed with purchase date, cart total, reward discount, VIP discount and total amount charged |pass|
|Database maintanence case1|without VIP status, place an order of $350. Change the sytstem date to 1/1/2017. Press "Database Maintenance" in the main menu. |In the customer management page, VIP status is "Yes", VIP begin date is null |In the customer management page, VIP status is "Yes", VIP begin date is null |pass|
|Database maintenance case2|without VIP status, place an order of $20. Change the sytstem date to 1/1/2017. Press "Database Maintanance" in the main menu.|In the customer management page, Spend Balance is $0|In the customer management page, Spend Balance is $0|pass| 

