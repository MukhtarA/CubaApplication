package com.company.task.web.screens.account;

import com.company.task.entity.Account;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.FrameOwner;

import java.util.function.Consumer;

public class FormPreviewComponentFactory {
    private final UiComponents uiComponents;
    private final ScreenBuilders screenBuilders;
    private final FrameOwner frameOwner;

    public FormPreviewComponentFactory(UiComponents uiComponents, ScreenBuilders screenBuilders, FrameOwner frameOwner) {
        this.uiComponents = uiComponents;
        this.screenBuilders = screenBuilders;
        this.frameOwner = frameOwner;
    }

    public Component create(InstanceContainer<Account> accountDc,
                            Consumer<Account> accountSelectionHandler){
        return(verticalLayout(accountImage(accountDc)));
    }

    private Image accountImage(InstanceContainer<Account> accountDc){
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("80");
        image.setWidth("80");
        image.setAlignment(Component.Alignment.MIDDLE_CENTER);
        image.setValueSource(new ContainerValueSource<>(accountDc, "photo"));
        return image;
    }

    private HBoxLayout horizontalLayout(Component... childComponents) {

        HBoxLayout layout = uiComponents.create(HBoxLayout.class);

        layout.setAlignment(Component.Alignment.BOTTOM_CENTER);
        layout.setWidthFull();
        layout.setSpacing(true);
        layout.add(childComponents);

        return layout;
    }

    private VBoxLayout verticalLayout(Component... childComponents) {

        VBoxLayout layout = uiComponents.create(VBoxLayout.class);

        layout.setAlignment(Component.Alignment.BOTTOM_CENTER);
        layout.add(childComponents);
        layout.setWidthFull();

        return layout;
    }
}
