import { StandardEntity } from "./base/sys$StandardEntity";
import { Order } from "./task_Order";
export class Product extends StandardEntity {
  static NAME = "task_Product";
  name?: string | null;
  price?: any | null;
  quantity?: number | null;
  order?: Order | null;
}
export type ProductViewName =
  | "_base"
  | "_local"
  | "_minimal"
  | "products-order";
export type ProductView<V extends ProductViewName> = V extends "_base"
  ? Pick<Product, "id" | "name" | "price" | "quantity">
  : V extends "_local"
  ? Pick<Product, "id" | "name" | "price" | "quantity">
  : V extends "_minimal"
  ? Pick<Product, "id" | "name">
  : V extends "products-order"
  ? Pick<Product, "id" | "name" | "price" | "quantity" | "order">
  : never;
