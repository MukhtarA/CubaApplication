alter table TASK_ACCOUNT add constraint FK_TASK_ACCOUNT_ON_PHOTO foreign key (PHOTO_ID) references SYS_FILE(ID);
create index IDX_TASK_ACCOUNT_ON_PHOTO on TASK_ACCOUNT (PHOTO_ID);
