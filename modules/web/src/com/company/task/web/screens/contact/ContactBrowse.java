package com.company.task.web.screens.contact;

import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Contact;

@UiController("task_Contact.browse")
@UiDescriptor("contact-browse.xml")
@LookupComponent("contactsTable")
@LoadDataBeforeShow
public class ContactBrowse extends StandardLookup<Contact> {

}