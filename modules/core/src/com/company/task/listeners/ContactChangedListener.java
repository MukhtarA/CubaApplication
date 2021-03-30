package com.company.task.listeners;

import com.company.task.entity.Contact;
import com.company.task.entity.ContactType;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component("task_ContactChangedListener")
public class ContactChangedListener implements BeforeUpdateEntityListener<Contact> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ContactChangedListener.class);

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Contact, UUID> event) {

    }




    @Override
    public void onBeforeUpdate(Contact entity, EntityManager entityManager) {
        try {
            checkValidation(entity, entityManager);
        } catch (Exception e) {
            log.error("Error", e);
        }
    }

    private void checkValidation(Contact entity, EntityManager entityManager) throws Exception {
        if (entity.getContactType() == ContactType.EMAIL) {
            if (!entity.getValue().matches("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                throw new Exception("not valid email");
            }
        }
        if (entity.getContactType() == ContactType.PHONE) {
            if (!entity.getValue().matches("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")) {
                throw new Exception("not valid phone number");
            }
        }
    }

}