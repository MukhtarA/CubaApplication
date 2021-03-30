package com.company.task.web.screens.contact;

import com.company.task.entity.Contact;
import com.company.task.entity.ContactType;
import com.company.task.entity.Order;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.ValidationException;
import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.function.Consumer;

public class PhoneValidator implements Consumer<String> {
    public Contact contact;

    @Override
    public void accept(String s) {
        if (!s.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$")) {
            throw new ValidationException(s + "not valid phone number");
        }
    }
}

