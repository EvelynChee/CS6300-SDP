# Extra requirements

**Author**: Team 85

- The hardware and software platform should meet the following requirements:
 1. Android phone with a 4" or larger screen size
 2. Android API level 19 (kitkat) or newer
 3. Minimum of 2GB RAM with a Quad Core CPU and bluetooth connection
 4. The phone should have a camera for scanning cards     
 5. Credit Card Reader
 6. Customer Card (QRCode) Printer
 7. Credit Card Processor (API - External System)
 8. SQLite Database for Local Persistance Storage
 9. Email Service (API - External System)
 10. Internet Connection

- A unique hexadecimal ID generation algorithm should be implemented to avoid different customers sharing the same ID.
- A batch processing should be triggered by the Cart Manager for all customers in the system at the beginning of the year. This process resets the customer's yearly spend balance, sets the VIP status for the year, and resets the VIP status begins date.
- Any credit card information should not be stored in the database.
- We assume there is only one user for the app (the Cart Manager). There is no login screen provided by application and the Android Phone's login mechanism will provide the security. 
- We only sell two types of items: coffee and tea.
- The customer can only be located by their customer card. Email cannot be used to locate a customer.
- The email address for each customer should be unique.
- The system will not allow a change to a customer's email address if the email address already exists in the system.
- The system will not allow the creation of a new customer if the new customer's email address already exists in the system. 
