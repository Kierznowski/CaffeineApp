CREATE TABLE IF NOT EXISTS Coffee_Order (
    id identity,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Coffee (
    id identity,
    name varchar(50) not null,
    coffee_order bigint not null,
    coffee_order_key bigint not null,
    created_at timestamp not null
 );

create table if not exists Ingredient_Ref (
    ingredient varchar(4) not null,
    coffee bigint not null,
    coffee_key bigint not null
 );

create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
 );

alter table Coffee
    add foreign key (Coffee_order) references Coffee_Order(id);

