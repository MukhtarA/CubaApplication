package com.company.task.web.screens.account;

import com.company.task.entity.Account;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FileDescriptorResource;
import com.haulmont.cuba.gui.components.Image;

public class ImageRender {

    public Account account;
    public UiComponents uiComponents;
    public ImageRender(UiComponents uiComponents, ScreenBuilders screenBuilders, AccountEdit accountEdit) {
    }

    public Component create(){
        return renderImage();
    }

    public Image renderImage(){
        FileDescriptor fileImage = account.getPhoto();
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setSource(FileDescriptorResource.class)
                .setFileDescriptor(fileImage);

        return image;
    }
}
