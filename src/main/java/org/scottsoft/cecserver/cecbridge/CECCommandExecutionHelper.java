package org.scottsoft.cecserver.cecbridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by slaplante on 5/17/17.
 */
@Component
public class CECCommandExecutionHelper extends CommandLineHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CECCommandExecutionHelper.class);

    public void sendCommand(String deviceId, String command) {
        try {
            String commandToExecute = MessageFormat.format("echo {0} {1} | cec-client -s -d 1", command, deviceId);
            LOGGER.info("Executing command: {}", commandToExecute);
            Process process = executeCommand(new String[]{"/bin/sh", "-c", commandToExecute});
            List<String> resultLines = getExecutionResult(process.getInputStream());
            resultLines.stream().forEach(resultLine -> LOGGER.info(resultLine));
            LOGGER.info("Execution complete for command: {}", command);
        } catch (IOException e) {
            throw new IllegalStateException(MessageFormat.format("An error occurred executing command {0}", ""), e);
        }
    }

}
