package com.company.task.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Order;

@UiController("task_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}