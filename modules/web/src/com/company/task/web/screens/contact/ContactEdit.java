package com.company.task.web.screens.contact;

import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Contact;
import sun.jvm.hotspot.debugger.DataSource;

import javax.inject.Inject;
import javax.validation.Valid;

@UiController("task_Contact.edit")
@UiDescriptor("contact-edit.xml")
@EditedEntityContainer("contactDc")
@LoadDataBeforeShow
public class ContactEdit extends StandardEditor<Contact> {
}