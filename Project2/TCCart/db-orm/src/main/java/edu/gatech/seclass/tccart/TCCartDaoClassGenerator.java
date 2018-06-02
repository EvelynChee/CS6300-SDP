package edu.gatech.seclass.tccart;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
import de.greenrobot.daogenerator.ToOne;

public class TCCartDaoClassGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "edu.gatech.seclass.tccart.db");
        schema.enableKeepSectionsByDefault();

        AddCustomer(schema);
        new DaoGenerator().generateAll(schema,"./app/src/main/java");

    }


    private static void AddCustomer(Schema schema) {

        Entity entityCustomer = schema.addEntity("Customer");

        entityCustomer.setSuperclass("CustomerBase");

        entityCustomer.setTableName("Customers");

        entityCustomer.addStringProperty("UniqueID")
                .notNull()
                .primaryKey();

        entityCustomer.addStringProperty("Email")
                .notNull()
                .unique();

        entityCustomer.addStringProperty("Name")
                .notNull();

        entityCustomer.addBooleanProperty("VIP");
        entityCustomer.addDoubleProperty("RewardBalance");
        entityCustomer.addDoubleProperty("SpendBalance");
        entityCustomer.addDateProperty("RewardExpireDate");
        entityCustomer.addDateProperty("VIPDiscountBegins");
        entityCustomer.addBooleanProperty("VIPNotificationSent");


        Entity entityTransaction = schema.addEntity("Transaction");
        entityTransaction.setSuperclass("TransactionBase");

        entityTransaction.setTableName("CustomerTransactions");

        entityTransaction.addIdProperty().columnName("transactionId");

        Property entityTransaction_CustomerID = entityTransaction.addStringProperty("customerID").getProperty();

        Property transactionDate = entityTransaction.addDateProperty("TransactionDate").getProperty();

        entityTransaction.addDoubleProperty("CartTotal");
        entityTransaction.addDoubleProperty("CreditDiscount");
        entityTransaction.addDoubleProperty("VIPDiscount");
        entityTransaction.addDoubleProperty("Total");


        ToMany customerToTransactions = entityCustomer.addToMany(entityTransaction, entityTransaction_CustomerID);

        customerToTransactions.setName("transactions");
        customerToTransactions.orderAsc(transactionDate);


        /*
        Entity entityOrder = schema.addEntity("Order");
        entityOrder.setTableName("CustomerOrders");

        Property orderIdProperty = entityOrder
                .addIdProperty()
                .columnName("orderId")
                .getProperty();

        ToOne TransactionToOrder = entityTransaction.addToOne(entityOrder,orderIdProperty);

        Entity entityOrderItem = schema.addEntity("OderItem");
        Property orderItemIdProperty = entityOrderItem.addIdProperty().columnName("orderId").getProperty();
        entityOrderItem.addStringProperty("ItemType");
        entityOrderItem.addDoubleProperty("TotalPrice");
        entityOrderItem.addIntProperty("ItemCount");

        entityOrder.addToMany(entityOrderItem, orderItemIdProperty);
        */
    }
}
