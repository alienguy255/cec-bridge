package org.scottsoft.cecserver.cecbridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * Created by slaplante on 5/15/17.
 */
@Component
public class CECScanCommandHelper extends CommandLineHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CECScanCommandHelper.class);

    public Collection<CECDevice> scanForCECDevices() {
        try {
            LOGGER.info("Begin scan for devices:");
            Process process = executeCommand(new String[]{"/bin/sh", "-c", "echo scan | cec-client -s -d 1"});
            List<String> resultLines = getExecutionResult(process.getInputStream());
            Collection<CECDevice> cecDevices = new ArrayList<>();

            String currentDeviceId = null, currentAddress = null, currentVendor = null, currentOsdString = null;

            for(String resultLine : resultLines) {
                LOGGER.info(resultLine);

                if (resultLine.isEmpty()) {
                    if (currentDeviceId != null) {
                        cecDevices.add(new CECDevice(currentDeviceId, currentAddress, currentVendor, currentOsdString));
                        currentDeviceId = null;
                    }
                }

                if (resultLine.contains("device #")) {
                    currentDeviceId = resultLine.substring(resultLine.indexOf("#") + 1, resultLine.indexOf(":"));
                }

                if (resultLine.contains("address")) {
                    currentAddress = resultLine.substring(resultLine.indexOf(":") + 1, resultLine.length()).trim();
                }

                if (resultLine.contains("vendor")) {
                    currentVendor = resultLine.substring(resultLine.indexOf(":") + 1, resultLine.length()).trim();
                }

                if (resultLine.contains("osd string")) {
                    currentOsdString = resultLine.substring(resultLine.indexOf(":") + 1, resultLine.length()).trim();
                }

            }

            return cecDevices;
        } catch (IOException e) {
            //LOGGER.error("An error occurred running scan-devices", e);
            throw new IllegalStateException("An error occurred running nslookup", e);
        }
    }

}
