package org.scottsoft.cecserver.cecbridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class CECServiceController {

    @Autowired
    private CECScanCommandHelper cecScanCommandHelper;

    @Autowired
    private CECCommandExecutionHelper cecCommandExecutionHelper;

    @RequestMapping(value = "/cec/devices", method = RequestMethod.GET)
    public ResponseEntity<Collection<CECDeviceDTO>> getCECDevices() {
        Collection<CECDeviceDTO> cecDeviceDTOS = cecScanCommandHelper.scanForCECDevices().stream()
                .map(cecDevice -> new CECDeviceDTO(cecDevice.getDeviceId(), cecDevice.getAddress(), cecDevice.getVendor(), cecDevice.getOsd())).collect(Collectors.toList());
        return new ResponseEntity<>(cecDeviceDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/cec/{deviceId}/{command}", method = RequestMethod.PUT)
    public ResponseEntity<String> sendCECCommand(@PathVariable(value = "deviceId") String deviceId, @PathVariable(value = "command") String command) {
        cecCommandExecutionHelper.sendCommand(deviceId, command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
