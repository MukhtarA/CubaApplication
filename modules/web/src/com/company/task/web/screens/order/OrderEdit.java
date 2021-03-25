package com.company.task.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Order;

@UiController("task_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
}