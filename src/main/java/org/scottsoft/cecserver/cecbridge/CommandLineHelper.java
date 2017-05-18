package org.scottsoft.cecserver.cecbridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandLineHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineHelper.class);

    protected List<String> getExecutionResult(InputStream inputStream) {
        List<String> fileContents = new ArrayList<>();
        BufferedReader outputReader = null;
        try {
            outputReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String currentLine;
            while ((currentLine = outputReader.readLine()) != null) {
                fileContents.add(currentLine.trim());
            }
        } catch (IOException e) {
            throw new IllegalStateException("An error occurred reading execution result stream", e);
        } finally {
            try {
                if (outputReader != null) {
                    outputReader.close();
                }
            } catch (IOException ex) {
                LOGGER.error("An error occurred closing execution result stream.", ex);
            }
        }
        return fileContents;
    }

    protected Process executeCommand(String[] commands) throws IOException {
        return Runtime.getRuntime().exec(commands);
    }

}