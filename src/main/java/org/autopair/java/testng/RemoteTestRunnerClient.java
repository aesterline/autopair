package org.autopair.java.testng;

import org.testng.remote.strprotocol.AbstractRemoteTestRunnerClient;
import org.testng.remote.strprotocol.GenericMessage;
import org.testng.remote.strprotocol.MessageHelper;
import org.testng.remote.strprotocol.SuiteMessage;
import org.testng.remote.strprotocol.TestMessage;
import org.testng.remote.strprotocol.TestResultMessage;

public class RemoteTestRunnerClient extends AbstractRemoteTestRunnerClient
{
    public void start()
    {
        startListening(null, null, new SimpleServerConnection());
    }

    protected void notifyStart(GenericMessage genericMessage)
    {
    }

    protected void notifySuiteEvents(SuiteMessage suiteMessage)
    {
    }

    protected void notifyTestEvents(TestMessage testMessage)
    {
    }

    protected void notifyResultEvents(TestResultMessage testResultMessage)
    {
        int resultValue = testResultMessage.getResult();

        if(resultValue != MessageHelper.TEST_STARTED)
        {
            String result = "";

            if(MessageHelper.FAILED_TEST == resultValue)
            {
                result = "FAIL";
            }
            else if(MessageHelper.PASSED_TEST == resultValue)
            {
                result = "PASS";
            }

            String message = String.format("%s.%s -> %s", testResultMessage.getTestClass(), testResultMessage.getMethod(), result);
            System.out.println(message);
        }
    }

    public class SimpleServerConnection extends ServerConnection
    {
        public SimpleServerConnection()
        {
            super(5000);
        }

        protected void handleThrowable(Throwable cause)
        {
            cause.printStackTrace();
        }
    }
}
