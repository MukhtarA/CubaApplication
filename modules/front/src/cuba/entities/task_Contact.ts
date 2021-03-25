import { StandardEntity } from "./base/sys$StandardEntity";
import { ContactType } from "../enums/enums";
import { Account } from "./task_Account";
export class Contact extends StandardEntity {
  static NAME = "task_Contact";
  contactType?: ContactType | null;
  account?: Account | null;
  value?: string | null;
}
export type ContactViewName =
  | "_base"
  | "_local"
  | "_minimal"
  | "contacts-account";
export type ContactView<V extends ContactViewName> = V extends "_base"
  ? Pick<Contact, "id" | "account" | "contactType" | "value">
  : V extends "_local"
  ? Pick<Contact, "id" | "contactType" | "value">
  : V extends "_minimal"
  ? Pick<Contact, "id" | "account">
  : V extends "contacts-account"
  ? Pick<Contact, "id" | "contactType" | "value" | "account">
  : never;
