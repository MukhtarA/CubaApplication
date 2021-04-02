import { OrderCards } from "./app/order/OrderCards";
import { AccountManagement } from "./app/account/AccountManagement";
import { ProductManagement } from "./app/product/ProductManagement";
import { ContactManagement } from "./app/contact/ContactManagement";
import { OrderManagement } from "./app/order/OrderManagement";
import { getMenuItems } from "@cuba-platform/react-core";

export const menuItems = getMenuItems();

/*// Code below demonstrates how we can create SubMenu section
// Remove '/!*' '*!/' comments and restart app to get this block in menu


// This is RouteItem object that we want to see in User Settings sub menu
const backToHomeRouteItem = {
  pathPattern: "/backToHome",
  menuLink: "/",
  component: null,
  caption: "home"
};
// SubMenu object
const userSettingsSubMenu = {
  caption: 'UserSettings', // add router.UserSettings key to src/i18n/en.json for valid caption
  items: [backToHomeRouteItem, ]};
// Add sub menu item to menu config
menuItems.push(userSettingsSubMenu);*/


const Screens = {
  caption: 'Screens',
  items: [
    {
      pathPattern: "/accountManagement/:entityId?",
      menuLink: "/accountManagement",
      component: AccountManagement,
      caption: "Account"
    },
    {
      pathPattern: "/orderManagement/:entityId?",
      menuLink: "/orderManagement",
      component: OrderManagement,
      caption: "OrderManagement"
    },
    {
      pathPattern: "/orderCards",
      menuLink: "/orderCards",
      component: OrderCards,
      caption: "OrderCards"
    },
  ]
}

menuItems.push(Screens);

const Cards = {
  caption: 'Cards',
  items: [
    {
      pathPattern: "/contactManagement/:entityId?",
      menuLink: "/contactManagement",
      component: ContactManagement,
      caption: "Contact"
    },
    {
      pathPattern: "/productManagement/:entityId?",
      menuLink: "/productManagement",
      component: ProductManagement,
      caption: "Product"
    }
  ]
}


menuItems.push(Cards);

