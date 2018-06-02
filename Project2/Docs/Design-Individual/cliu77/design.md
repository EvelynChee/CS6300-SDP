###Design Considerations

- In this design, I defined two major classes, namely TCcartManager and PurchaseManager. 
- The TCcartManager class processes the user information, such as adding customers, edit customer information.
- The PurchaseManager class processes each purchase behavior. It obtaines the customer information from the webcam, calculates the purchasing price and credit, sends emails and processes the creditcard.
- The customer class has one or more PurchaseRecord objects, which means association relationship.The PurchaseRecord objects get its values from the PurchaseManger, which means dependency relationship.
- The TCcartManager has the customer members, which means an association relationship.
