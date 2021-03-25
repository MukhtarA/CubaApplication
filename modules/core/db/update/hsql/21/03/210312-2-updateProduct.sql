update TASK_PRODUCT set QUANTITY = 0 where QUANTITY is null ;
alter table TASK_PRODUCT alter column QUANTITY set not null ;
