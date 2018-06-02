package edu.gatech.seclass.tccart.utility;

import edu.gatech.seclass.services.QRCodeService;

/**
 * Created by bulentcoskun on 4/6/2016.
 */
public final class VideoCam {

    public static UniqueID ReadCustomerCard() {
        return  new UniqueID(QRCodeService.scanQRCode()); //Scans a QR code and returns the corresponding hexadecimal ID as String
    }
}
