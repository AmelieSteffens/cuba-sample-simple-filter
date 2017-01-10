-- begin SF_ORDER
create table SF_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DATE_ date,
    CUSTOMER varchar(255),
    AMOUNT decimal(19, 2),
    --
    primary key (ID)
)^
-- end SF_ORDER
