package com.company.task.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.task.entity.Product;

@UiController("task_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}