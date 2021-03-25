package com.company.task.web.screens.order;

import com.company.task.entity.Account;
import com.company.task.entity.Product;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Order;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@UiController("task_Order.browseScreen")
@UiDescriptor("screen.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class Screen extends StandardLookup<Order> {
    @Inject
    protected GroupTable<Order> ordersTable;

    @Inject
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        ordersTable.addGeneratedColumn(
                "Products",
                this::renderNameColumn
        );
    }

    private Component renderNameColumn(Order order) {
        Label<List> label = uiComponents.create(Label.NAME);
        label.setValue(order.getProduct().stream().map(Product::getName).collect(Collectors.toList()));
        return label;
    }

    private Component renderQuantityColumn(Order order) {
        Label<String> label = uiComponents.create(Label.NAME);
        label.setValue("dsd");
        return label;
}
}