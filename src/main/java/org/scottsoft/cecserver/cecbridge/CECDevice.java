package org.scottsoft.cecserver.cecbridge;

import java.text.MessageFormat;

/**
 * Created by slaplante on 5/15/17.
 */
public class CECDevice {

    private final String deviceId;

    private final String address;

    private final String vendor;

    private final String osd;

    public CECDevice(String deviceId, String address, String vendor, String osd) {
        this.deviceId = deviceId;
        this.address = address;
        this.vendor = vendor;
        this.osd = osd;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getAddress() {
        return address;
    }

    public String getVendor() {
        return vendor;
    }

    public String getOsd() {
        return osd;
    }

    @Override
    public String toString() {
        return MessageFormat.format("deviceId={0}, address={1}, vendor={2}, osd string={3}", deviceId, address, vendor, osd);
    }

}
