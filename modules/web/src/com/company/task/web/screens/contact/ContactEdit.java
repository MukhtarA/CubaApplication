package com.company.task.web.screens.contact;

import com.company.task.entity.ContactType;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.validators.EmailValidator;
import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Contact;
import sun.jvm.hotspot.debugger.DataSource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@UiController("task_Contact.edit")
@UiDescriptor("contact-edit.xml")
@EditedEntityContainer("contactDc")
@LoadDataBeforeShow
public class ContactEdit extends StandardEditor<Contact> {

    @Inject
    private LookupField<ContactType> contactTypeField;

    @Inject
    private TextField<String> valueField;

    @Subscribe
    public void onInit(InitEvent event) {
        contactTypeField.addValueChangeListener(contactType -> {
            String email = String.valueOf(ContactType.EMAIL);
            String phone = String.valueOf(ContactType.PHONE);
            if (contactType.equals(email)) {
                valueField.addValidator(new EmailValidator());
            } else if (contactType.equals(phone)) {
                valueField.addValidator(new PhoneValidator());
            }
        });
    }
}