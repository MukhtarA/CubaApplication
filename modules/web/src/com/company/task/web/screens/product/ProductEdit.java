package com.company.task.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Product;

@UiController("task_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}