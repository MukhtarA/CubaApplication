-- begin TASK_ORDER
alter table TASK_ORDER add constraint FK_TASK_ORDER_ON_ACCOUNT foreign key (ACCOUNT_ID) references TASK_ACCOUNT(ID)^
create index IDX_TASK_ORDER_ON_ACCOUNT on TASK_ORDER (ACCOUNT_ID)^
-- end TASK_ORDER
-- begin TASK_ACCOUNT
alter table TASK_ACCOUNT add constraint FK_TASK_ACCOUNT_ON_PHOTO foreign key (PHOTO_ID) references SYS_FILE(ID)^
create index IDX_TASK_ACCOUNT_ON_PHOTO on TASK_ACCOUNT (PHOTO_ID)^
-- end TASK_ACCOUNT
-- begin TASK_CONTACT
alter table TASK_CONTACT add constraint FK_TASK_CONTACT_ON_ACCOUNT foreign key (ACCOUNT_ID) references TASK_ACCOUNT(ID)^
create index IDX_TASK_CONTACT_ON_ACCOUNT on TASK_CONTACT (ACCOUNT_ID)^
-- end TASK_CONTACT
-- begin TASK_PRODUCT
alter table TASK_PRODUCT add constraint FK_TASK_PRODUCT_ON_ORDER foreign key (ORDER_ID) references TASK_ORDER(ID)^
create index IDX_TASK_PRODUCT_ON_ORDER on TASK_PRODUCT (ORDER_ID)^
-- end TASK_PRODUCT
