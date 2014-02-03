/*
 * Copyright 2010-2013, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package localdomain.localhost.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/myQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MyQueueMdb implements MessageListener {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected final AtomicInteger messageCounter = new AtomicInteger();

    @Override
    public void onMessage(Message message) {
        int messageNumber = messageCounter.incrementAndGet();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                logger.info("Receive message #" + messageNumber + ": " + textMessage.getText());
            } catch (JMSException e) {
                logger.log(Level.WARNING, "Exception reading message", e);
            }
        } else {
            logger.info("Receive message #" + messageNumber + ": " + message);
        }
    }
}
