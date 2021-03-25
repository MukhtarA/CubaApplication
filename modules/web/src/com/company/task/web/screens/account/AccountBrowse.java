package com.company.task.web.screens.account;

import com.company.task.entity.Contact;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Account;
import com.haulmont.cuba.gui.screen.LookupComponent;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UiController("task_Account.browse")
@UiDescriptor("account-browse.xml")
@LookupComponent("accountsTable")
@LoadDataBeforeShow
public class AccountBrowse extends StandardLookup<Account> {
    @Inject
    protected GroupTable<Account> accountsTable;

    @Inject
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        accountsTable.addGeneratedColumn(
                "photo",
                this::renderAvatarImageComponent
        );
        accountsTable.addGeneratedColumn(
                "fullName",
                this::toString
        );
        accountsTable.addGeneratedColumn(
                "contacts",
                this::allContacts
        );
    }

    private Component allContacts(Account account) {
        Label<List> label = uiComponents.create(Label.NAME);
        label.setValue(account.getContacts().stream().map(Contact::getValue).collect(Collectors.toList()));
        return label;
    }

    private Component toString(Account account) {
        Label<String> label = uiComponents.create(Label.NAME);
        if(account.getMiddleName() == null){
            label.setValue(account.getLastName() + " " + account.getName());
        }else{
            label.setValue(account.getLastName() + " " + account.getName() + " " + account.getMiddleName());
        }
        return label;
    }


    private Component renderAvatarImageComponent(Account account) {
        FileDescriptor fileImage = account.getPhoto();
        if(fileImage == null){
            return null;
        }
        Image image = smallAvatarImage();
        image.setSource(FileDescriptorResource.class)
                .setFileDescriptor(fileImage);

        return image;
    }

    private Image smallAvatarImage() {
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("60");
        image.setWidth("60");
        image.setStyleName("avatar-icon-small");
        return image;
    }
}