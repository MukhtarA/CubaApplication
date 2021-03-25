package com.company.task.web.screens.account;

import com.company.task.entity.Account;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.FrameOwner;
import com.haulmont.cuba.gui.screen.OpenMode;

import java.util.function.Consumer;

public class AccountPreview {
    private final UiComponents uiComponents;
    private final ScreenBuilders screenBuilders;
    private final FrameOwner frameOwner;

    private HBoxLayout horizontalLayout(Component... childComponents) {

        HBoxLayout layout = uiComponents.create(HBoxLayout.class);

        layout.setAlignment(Component.Alignment.MIDDLE_CENTER);
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

    private Label accountName(InstanceContainer<Account> accountInstanceContainer) {

        Label accountLabel = uiComponents.create(Label.class);

        accountLabel.setValueSource(new ContainerValueSource<Account, Account>(accountInstanceContainer, "account"));
        accountLabel.setAlignment(Component.Alignment.MIDDLE_CENTER);
        accountLabel.setWidthFull();
        accountLabel.setStyleName("h1");

        return accountLabel;
    }

    private Button editAccountButton(Consumer<Account> accountSelectionHandler) {

        LinkButton button = uiComponents.create(LinkButton.class);

        button.setAlignment(Component.Alignment.MIDDLE_RIGHT);
        button.setIconFromSet(CubaIcon.EDIT_ACTION);
        button.setStyleName("borderless huge");
        button.setAction(new BaseAction("changeAccount").withHandler(event -> openVetLookup(event, accountSelectionHandler)));

        return button;
    }

    private void openVetLookup(Action.ActionPerformedEvent event, Consumer<Account> accountSelectionHandler) {
        screenBuilders.lookup(Account.class, frameOwner)
                .withOpenMode(OpenMode.DIALOG)
                .withSelectHandler(vets -> accountSelectionHandler.accept(vets.iterator().next()))
                .show();
    }

    public AccountPreview(UiComponents uiComponents, ScreenBuilders screenBuilders, FrameOwner frameOwner) {
        this.uiComponents = uiComponents;
        this.screenBuilders = screenBuilders;
        this.frameOwner = frameOwner;
    }



    public Component create(
            InstanceContainer<Account> accountInstanceContainer,
            Consumer<Account> accountConsumer) {

        return verticalLayout(
                accountImage(accountInstanceContainer),
                horizontalLayout(accountName(accountInstanceContainer), editAccountButton(accountConsumer)));
    }
}
