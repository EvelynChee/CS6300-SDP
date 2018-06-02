# ** Design Document for UML class diagram**
## 1 UML Structures
* This design contains 1 interface, 10 classes, 3 external libraries, and 2 utility classes.
10 classes are: Customer, Card, Purchases, Item, Tea, Coffee, CreditCard, Credit, VIPCustomer, QRCode. 2 utilities are: Money, Date. 3 external libraries: CardPrinter, VideoCam, CreditCardScanner
* Money, and Date are utility classes. Since the requirements did not specifically describe what constitutes each they have been left as simple entities without attributes or operations

## 2 Some Important Design Decisions

### 2.1 Interface

1. The design uses an interface to fulfill functions of coffee tea cart system.

### 2.2 Classes, Relationship
1. The Purchases class and Card class
 * many:1 association
 * the Card class is used to track the Purchases history
2. The Purchases class and Credit class
 * 1:1 association.
 * every purchase is assigned with a purchasesID, the purchasesID is an auto-incremental number, starts with 1 as the first purchases. And the Credit class uses the previous purchasesID as its current creditID.
 * The creditValue is how much credit available for that purchasesID/creditID purchase. In other words, the creditValue and value of items determine the how much creditUsed.
 * At the same time, the creditLeft and the newly acquired credit is passed to the creditValue for the next purchaseID/creditID.
 * To determine if qualified for the new credit, by looking at the adjustedPrice is at least 30 dollars.
3. The Purchases class and VIPCustomer class
 * 1:1 association
 * The vipStatus is an boolean attribute which is false at the beginning then changes to true if the annualSpend in the Purchases class is at least 300. The annualSpend is the cumulative amount of adjustedPrice for that calender year.
4. The Customer class and Card class have composition relationship. So if the Customer class become inactive, the Card class will also be inactive.
