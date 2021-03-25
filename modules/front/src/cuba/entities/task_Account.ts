import { StandardEntity } from "./base/sys$StandardEntity";
import { Order } from "./task_Order";
import { FileDescriptor } from "./base/sys$FileDescriptor";
import { Contact } from "./task_Contact";
export class Account extends StandardEntity {
  static NAME = "task_Account";
  name?: string | null;
  order?: Order[] | null;
  lastName?: string | null;
  middleName?: string | null;
  photo?: FileDescriptor | null;
  contacts?: Contact[] | null;
}
export type AccountViewName =
  | "_base"
  | "_local"
  | "_minimal"
  | "account-contacts"
  | "account-view";
export type AccountView<V extends AccountViewName> = V extends "_base"
  ? Pick<Account, "id" | "name" | "lastName" | "middleName">
  : V extends "_local"
  ? Pick<Account, "id" | "name" | "lastName" | "middleName">
  : V extends "_minimal"
  ? Pick<Account, "id" | "name">
  : V extends "account-contacts"
  ? Pick<Account, "id" | "name" | "lastName" | "middleName" | "contacts">
  : V extends "account-view"
  ? Pick<
      Account,
      "id" | "name" | "lastName" | "middleName" | "contacts" | "photo"
    >
  : never;
