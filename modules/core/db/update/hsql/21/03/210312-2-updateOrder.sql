alter table TASK_ORDER alter column PRODUCT_ID rename to PRODUCT_ID__U38166 ^
alter table TASK_ORDER drop constraint FK_TASK_ORDER_ON_PRODUCT ;
drop index IDX_TASK_ORDER_ON_PRODUCT ;
