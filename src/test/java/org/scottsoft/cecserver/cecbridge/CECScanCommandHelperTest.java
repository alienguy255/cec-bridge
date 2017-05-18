package org.scottsoft.cecserver.cecbridge;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by slaplante on 5/17/17.
 */
public class CECScanCommandHelperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CECScanCommandHelperTest.class);

    @Test
    public void testScan() {
        CECScanCommandHelper cecScanCommandHelper = new TestCECScanCommandHelper();
        Collection<CECDevice> cecDevices = cecScanCommandHelper.scanForCECDevices();
        cecDevices.forEach(cecDevice -> LOGGER.info(cecDevice.toString()));

        //assertTrue("", true);
    }

    private class TestCECScanCommandHelper extends CECScanCommandHelper {

        @Override
        protected Process executeCommand(String[] commands) {
            Process mockProcess = mock(Process.class);
            InputStream testStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test-cec-scan.txt");
            when(mockProcess.getInputStream()).thenReturn(testStream);
            return mockProcess;
        }
    }

}
