package com.company.task.web.screens.account;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Account;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import org.checkerframework.checker.units.qual.A;

import javax.inject.Inject;

@UiController("task_Account.edit")
@UiDescriptor("account-edit.xml")
@EditedEntityContainer("accountDc")
@LoadDataBeforeShow
public class AccountEdit extends StandardEditor<Account> {

    @Inject
    protected UiComponents uiComponents;

    @Inject
    protected Form form;

    @Inject
    protected Image image;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Inject
    protected InstanceContainer<Account> accountDc;

    @Subscribe
    protected void onInit(InitEvent event){
        renderImage(accountDc.getItem());
    }


    public Component renderImage(Account account){
        FileDescriptor fileImage = account.getPhoto();
        if(fileImage == null){
            return null;
        }
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setSource(FileDescriptorResource.class)
                .setFileDescriptor(fileImage);

        return image;
    }


    /* @Subscribe
    protected void onInit(InitEvent event){
        imageTable.addGeneratedColumn(
                "photo",
                this::renderAccountPhoto
        );
    }*/

/*    private Component renderAccountPhoto(Account account) {
        FileDescriptor fileDescriptor = account.getPhoto();
        Image image = null;
        assert false;
        image.setSource(FileDescriptorResource.class).setFileDescriptor(fileDescriptor);
        return image;
    }*/
/*
    @Inject
    protected GroupTable<Account> accountsTable;

    @Inject
    protected UiComponents uiComponents;

    private Image accountImage(InstanceContainer<Account> accountDc) {

        Image image = uiComponents.create(Image.class);

        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("80");
        image.setWidth("80");
        image.setStyleName("avatar-icon-large");
        image.setAlignment(Component.Alignment.MIDDLE_CENTER);
        image.setValueSource(new ContainerValueSource<>(accountDc, "account.image"));

        return image;
    }
*/


/*

    @Subscribe
    protected void onInit(InitEvent event) {
        accountsTable.addPrintable(
                "image",
                this:: accountImage
        );
    }

    private Component accountImage(Account account) {
        FileDescriptor fileImage = account.getPhoto();
        Image image = uiComponents.create(Image.class);

        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("80");
        image.setWidth("80");
        image.setStyleName("avatar-icon-large");
        image.setAlignment(Component.Alignment.MIDDLE_CENTER);
        image.setSource(FileDescriptorResource.class)
                .setFileDescriptor(fileImage);
        return image;
    }


*/

    /*@Inject
    protected Form accountEditForm;

    @Inject
    protected InstanceContainer<Account> accountInstanceContainer;

    @Inject
    protected UiComponents uiComponents;

    @Inject
    protected ScreenBuilders screenBuilders;

    @Subscribe
    protected void renderAccountLayout(AfterShowEvent afterShowEvent){
        AccountPreview accountPreview = new AccountPreview(uiComponents,screenBuilders,this);

        Component accountPre = accountPreview.create(accountInstanceContainer, account -> getEditedEntity().setName(account.getName()));
    }*/
}