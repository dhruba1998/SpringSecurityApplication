--The following queries are used to use default table "users" provided by spring security team

create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert into users values("dhruba","{bcrypt}$2a$12$CFgjw0M9EDijMxJRWWki.uCU1NmeEurf.A7ZX7PScE7ukEdDjCkOq",1);
insert into users values("souvik","{noop}souvikDas@123",1);

insert into authorities values("dhruba","admin");
insert into authorities values("souvik","user");


-- The following queries are used to use custom created table "customers"

create table customers(id int not null primary key, email varchar(50) not null,password varchar(50) not null,role varchar(50) not null);

