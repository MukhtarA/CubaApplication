import { StandardEntity } from "./base/sys$StandardEntity";
import { Account } from "./task_Account";
import { Product } from "./task_Product";
export class Order extends StandardEntity {
  static NAME = "task_Order";
  date?: any | null;
  amount?: any | null;
  account?: Account | null;
  product?: Product[] | null;
}
export type OrderViewName =
  | "_base"
  | "_local"
  | "_minimal"
  | "order-products"
  | "order-view";
export type OrderView<V extends OrderViewName> = V extends "_base"
  ? Pick<Order, "id" | "date" | "amount">
  : V extends "_local"
  ? Pick<Order, "id" | "date" | "amount">
  : V extends "order-products"
  ? Pick<Order, "id" | "date" | "amount" | "product">
  : V extends "order-view"
  ? Pick<Order, "id" | "date" | "amount" | "product" | "account">
  : never;
