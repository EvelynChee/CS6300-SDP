package edu.gatech.seclass.tccart.utility;

/**
 * Created by bulentcoskun on 3/20/16.
 */
public class UniqueID {

    private String ID;

    public UniqueID(String uID) {
        ID = uID;
    }

    public String getID() { return ID; }

    public static UniqueID GenerateID() {
        UniqueID uniqueID = new UniqueID(null);

        try{
            Thread.currentThread().sleep(0,1);
        }catch (Exception e) {

        }finally {
            String id = Long.toHexString(System.currentTimeMillis());
            uniqueID.ID = id.substring(3,11).toUpperCase();
        }

        return uniqueID;
    }

}
