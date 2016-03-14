package org.motechproject.ipf.handler;

import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.motechproject.ipf.event.EventSubjects;
import org.motechproject.ipf.exception.RecipientNotFoundException;
import org.motechproject.ipf.exception.TemplateNotFoundException;
import org.motechproject.ipf.handler.helper.IPFActionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Component which listens for Motech events and sends data to the HL7 recipients.
 *
 * @see org.motechproject.ipf.domain.IPFTemplate
 * @see org.motechproject.ipf.domain.IPFRecipient
 * @see org.motechproject.ipf.event.EventSubjects
 */
@Component
public class IPFActionEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPFActionEventHandler.class);

    @Autowired
    private IPFActionHelper ipfActionHelper;

    /**
     * Handles an event and sends data to the HL7 recipient. Template data, recipient name and template name are in the
     * event parameters.
     *
     * @param event the event which contains data for template
     * @throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException when cannot generate data
     * @throws TemplateNotFoundException when a template does not exist in database
     * @throws RecipientNotFoundException when a recipient does not exist
     * from xml template or when template is incorrect
     */
    @MotechListener(subjects =  {EventSubjects.ALL_TEMPLATE_ACTIONS})
    public void handleIpfTaskAction(MotechEvent event) throws ParserConfigurationException, SAXException, IOException,
            ClassNotFoundException {
        LOGGER.info("Event handled {}", event.getSubject());
        ipfActionHelper.handleAction(event.getParameters());
    }
}
