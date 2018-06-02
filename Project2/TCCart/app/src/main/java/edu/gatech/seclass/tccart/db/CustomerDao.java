package edu.gatech.seclass.tccart.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import edu.gatech.seclass.tccart.db.Customer;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Customers".
*/
public class CustomerDao extends AbstractDao<Customer, String> {

    public static final String TABLENAME = "Customers";

    /**
     * Properties of entity Customer.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property UniqueID = new Property(0, String.class, "UniqueID", true, "UNIQUE_ID");
        public final static Property Email = new Property(1, String.class, "Email", false, "EMAIL");
        public final static Property Name = new Property(2, String.class, "Name", false, "NAME");
        public final static Property VIP = new Property(3, Boolean.class, "VIP", false, "VIP");
        public final static Property RewardBalance = new Property(4, Double.class, "RewardBalance", false, "REWARD_BALANCE");
        public final static Property SpendBalance = new Property(5, Double.class, "SpendBalance", false, "SPEND_BALANCE");
        public final static Property RewardExpireDate = new Property(6, java.util.Date.class, "RewardExpireDate", false, "REWARD_EXPIRE_DATE");
        public final static Property VIPDiscountBegins = new Property(7, java.util.Date.class, "VIPDiscountBegins", false, "VIPDISCOUNT_BEGINS");
        public final static Property VIPNotificationSent = new Property(8, Boolean.class, "VIPNotificationSent", false, "VIPNOTIFICATION_SENT");
    };

    private DaoSession daoSession;


    public CustomerDao(DaoConfig config) {
        super(config);
    }
    
    public CustomerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Customers\" (" + //
                "\"UNIQUE_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: UniqueID
                "\"EMAIL\" TEXT NOT NULL UNIQUE ," + // 1: Email
                "\"NAME\" TEXT NOT NULL ," + // 2: Name
                "\"VIP\" INTEGER," + // 3: VIP
                "\"REWARD_BALANCE\" REAL," + // 4: RewardBalance
                "\"SPEND_BALANCE\" REAL," + // 5: SpendBalance
                "\"REWARD_EXPIRE_DATE\" INTEGER," + // 6: RewardExpireDate
                "\"VIPDISCOUNT_BEGINS\" INTEGER," + // 7: VIPDiscountBegins
                "\"VIPNOTIFICATION_SENT\" INTEGER);"); // 8: VIPNotificationSent
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Customers\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Customer entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getUniqueID());
        stmt.bindString(2, entity.getEmail());
        stmt.bindString(3, entity.getName());
 
        Boolean VIP = entity.getVIP();
        if (VIP != null) {
            stmt.bindLong(4, VIP ? 1L: 0L);
        }
 
        Double RewardBalance = entity.getRewardBalance();
        if (RewardBalance != null) {
            stmt.bindDouble(5, RewardBalance);
        }
 
        Double SpendBalance = entity.getSpendBalance();
        if (SpendBalance != null) {
            stmt.bindDouble(6, SpendBalance);
        }
 
        java.util.Date RewardExpireDate = entity.getRewardExpireDate();
        if (RewardExpireDate != null) {
            stmt.bindLong(7, RewardExpireDate.getTime());
        }
 
        java.util.Date VIPDiscountBegins = entity.getVIPDiscountBegins();
        if (VIPDiscountBegins != null) {
            stmt.bindLong(8, VIPDiscountBegins.getTime());
        }
 
        Boolean VIPNotificationSent = entity.getVIPNotificationSent();
        if (VIPNotificationSent != null) {
            stmt.bindLong(9, VIPNotificationSent ? 1L: 0L);
        }
    }

    @Override
    protected void attachEntity(Customer entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Customer readEntity(Cursor cursor, int offset) {
        Customer entity = new Customer( //
            cursor.getString(offset + 0), // UniqueID
            cursor.getString(offset + 1), // Email
            cursor.getString(offset + 2), // Name
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // VIP
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // RewardBalance
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // SpendBalance
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // RewardExpireDate
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // VIPDiscountBegins
            cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8) != 0 // VIPNotificationSent
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Customer entity, int offset) {
        entity.setUniqueID(cursor.getString(offset + 0));
        entity.setEmail(cursor.getString(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setVIP(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setRewardBalance(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setSpendBalance(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setRewardExpireDate(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setVIPDiscountBegins(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setVIPNotificationSent(cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Customer entity, long rowId) {
        return entity.getUniqueID();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Customer entity) {
        if(entity != null) {
            return entity.getUniqueID();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}