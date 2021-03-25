-- update TASK_CONTACT set ACCOUNT_ID = <default_value> where ACCOUNT_ID is null ;
alter table TASK_CONTACT alter column ACCOUNT_ID set not null ;
