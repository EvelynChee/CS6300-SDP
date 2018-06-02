# **Design Document**

## 1 Introduction
​
This Design Document explains the non-obvious reasons in the organization of the UML class diagram.
​
## 2 Explanations

​* Added the Date library to handle expiration dates and transaction dates.
* Added the Money library to handle the currency for transactions.
* Added CartItem class because I believe at the most basic level the cart manager would like to track the name, price, and quantity of items sold in a purchase.
* Many of the numbers which would not have any mathematical operations done on them, but were really there for comparison/verification (accountNumber, cvv, etc) were made as strings.
* The CartManager uses DataSource to store all the multiple Customers and multiple Purchases in a database.
* At the beginning of a new year, the CartManager uses updateAllVIP() to look at each customer's total purchase value from the previous year and determine if a Customer's vip Boolean is set to True.
* Whenever a purchase is made with addPurchase(), the CartManager also uses checkCustomerVIP() to determine if a Customer is over $300 and sends an email if this is the case.
* The CartManager uses uses readQrCode() to use the Videocam to scan a card's QR code and determine which Customer they are dealing with.
* CreditCard uses the CreditCardScanner to scan a credit card's info and then temporarily saves the information. Once it uses processPayment() to connector to the payment processing service and make the payment, it clears the credit card information with clearCreditCardInfo() to guarantee the information is not saved.
* When a Purchase is made, it uses checkThirtyReward() to see if the amount is $30 or more and uses Customer setCredits() to add $3 to the Customer's currentCredits and updates the Customer's creditExpDate to a month from Purchase's purchaseDate.
* applyDiscounts looks at the Customer and applies credits and VIP discount if the Customer has these, it then saves these discounts as creditsApplied and vipDiscountApplied for future reference.
* applyDiscount also uses Customer's setCredits() to subtract the creditsApplied from the Customer's currentCredits (in case the item purchased is less than the Customer's currentCredits).
* When a Purchase is made, Purchase uses emailReceipt() to send the Customer a receipt and a record of any credits they have earned.
* It is assumed that all classes have getters and setters as needed.
