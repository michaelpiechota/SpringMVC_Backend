Hibernate: 
    drop table if exists car;
    drop table if exists rating;
    create table car (
        id integer not null auto_increment,
        make varchar(45) not null,
        model varchar(45) not null,
        year integer not null,
        primary key (id)
    );
    create table rating (
        id integer not null auto_increment,
        rating integer,
        car_id integer not null,
        primary key (id)
    );
    alter table rating 
        add constraint FKtk5oym7po28cvokyv25jet7nx 
        foreign key (car_id) 
        references car (id);
