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
    PRODUCT_ID varchar(36),
    --
    primary key (ID)
);