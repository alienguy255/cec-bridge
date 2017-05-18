package org.scottsoft.cecserver.cecbridge;

/**
 * Created by slaplante on 5/15/17.
 */
public class CECDeviceDTO {

    private String deviceId;

    private String address;

    private String vendor;

    private String osd;

    public CECDeviceDTO(String deviceId, String address, String vendor, String osd) {
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

}
