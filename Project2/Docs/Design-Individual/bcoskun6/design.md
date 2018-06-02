# Cart Management System Design Notes

## Assumptions:

- UniqueID utility class helps to generate random 8 digits unique hexadecimal ID and stores it.
- EmailAdress utility class validates email format and stores valid email address.  
- QRCode encoded and decoded as image data.  

## Design Notes:

- VideoCam Utility Class reads QRCode Image data and returns a UniqueID object that contains customer ID value.   
- The System doesn't store CreditCard Object in the persistence store.   
- CartManager uses Customer class to create Orders for the customer.  
- Order class holds all purchases related values the day Order placed.   
- A LastEarnedRewardDate attribute will be used for deciding how many days passed since the last reward earned.    
- Every time Transaction Class calls CalcuatesOrderBalance Operation it will use and update this attribute. 
- Requirement 11a will be decided based on LastEarnedRewardDate attribute.   
- Transaction Class holds states also provides calculation logic.   
- Implementation details of Requirement 11 will be implemented in Transaction Class via CalcuatesOrderBalance Operation.   
- If ProcessPurchase operation successful, Transaction Class will send an email for receipt and rewards.   
- SpendBalance attribute keeps the sum of total charged amount for a fiscal year. the fiscal year starts Jan 1st.   
- After every purchase, the system calls CalculateVIPStatus Operation over customer which it goes over all purchases from the beginning from current fiscal year start date to last order date and updates SpendBalance, VIPDiscountBegins and VIP flag attributes. If SpendBalance 300 or more then it set VIPDiscountBegins as next start date of next fiscal year.    
- CalculateVIPStatus checks VIPDiscountBegins date. if the value of this attribute is in the current year then it sets VIP flag attribute as true to indicate the customer is in the VIP status and discount should be applied.   
- CalculateVIPStatus Operation checks SpendBalance. if SpendBalance is 300 or more and VIPNotificationSent attribute is false then it trigger an email to the customer about their VIP status and beginning date information.  
- The VIPNotificationSent flag will be set true for this year to indicate VIP notification email has been sent after VIP related email sent.
- VIPNotificationSent, SpendBalance attributes for each customer will be reset their initial value at the begging of the fiscal year.   
- DiscountPercentage attribute holds percentage that will apply for the discount. Its default value is 10 means 10% will be applied for eligible order. This information will additionally be stored in Order class for historical information. 