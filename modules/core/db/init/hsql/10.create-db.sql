-- begin TASK_ACCOUNT
create table TASK_ACCOUNT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    LAST_NAME varchar(255) not null,
    MIDDLE_NAME varchar(255),
    PHOTO_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TASK_ACCOUNT
-- begin TASK_CONTACT
create table TASK_CONTACT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CONTACT_TYPE varchar(50) not null,
    ACCOUNT_ID varchar(36) not null,
    VALUE_ varchar(255),
    --
    primary key (ID)
)^
-- end TASK_CONTACT
-- begin TASK_ORDER
create table TASK_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DATE_ timestamp,
    AMOUNT double precision,
    ACCOUNT_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TASK_ORDER
-- begin TASK_PRODUCT
create table TASK_PRODUCT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    PRICE double precision,
    QUANTITY integer not null,
    ORDER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end TASK_PRODUCT
