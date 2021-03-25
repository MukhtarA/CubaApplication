alter table TASK_ORDER add constraint FK_TASK_ORDER_ON_ACCOUNT foreign key (ACCOUNT_ID) references TASK_ACCOUNT(ID);
alter table TASK_ORDER add constraint FK_TASK_ORDER_ON_PRODUCT foreign key (PRODUCT_ID) references TASK_PRODUCT(ID);
create index IDX_TASK_ORDER_ON_ACCOUNT on TASK_ORDER (ACCOUNT_ID);
create index IDX_TASK_ORDER_ON_PRODUCT on TASK_ORDER (PRODUCT_ID);
