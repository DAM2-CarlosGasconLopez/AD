
/* ======================================================================================================================= */
/*                                             CREACION Y UNION DE TABLAS                                                  */

/* Tabla RAZA */
create table raza (
id_Raza int auto_increment not null,
tipoRaza varchar (100) not null,
colorPiel varchar (100) not null,
constraint primary key (id_Raza)
);

/* Tabla PADRE */
create table padre (
id_Crotal int not null,
id_Raza int not null,
altura double,
peso double,
constraint primary key (id_Crotal)
);
alter table padre add constraint foreign key (id_Raza) references raza(id_Raza);

/* Tabla MADRE */
create table madre (
id_Crotal int not null,
id_Raza int,
altura double,
peso double,
nPartos int,
constraint primary key (id_Crotal)
);
alter table madre add constraint foreign key (id_Raza) references raza(id_Raza);

/* Tabla TERNEROS */
create table terneros (
id_Crotal int not null,
id_Raza int not null,
sexo int NOT NULL,
peso double,
fecha_Nacimiento date,
id_Crotal_Padre int,
id_Crotal_Madre int,
constraint primary key (id_Crotal)
);

alter table terneros add constraint foreign key (id_Raza) references raza(id_Raza);
alter table terneros add constraint foreign key (id_Crotal_Padre) references padre(id_Crotal);
alter table terneros add constraint foreign key (id_Crotal_Madre) references madre(id_Crotal);

/* ======================================================================================================================= */
/*                                      INSERCCION DE DATOS EN LAS TABLAS                                                  */

/* inserccion tabla RAZAS */
insert into raza(tipoRaza,colorPiel) values ("Pirenaicas","Marron"),("Charoles","Blanco"),("Limusin","Rojizo"),("Cruce Pirenaico y Charoles","Marron Claro"),("Cruce Pirenaico y Limusim","Marron"),("Cruce Limusin y Charoles","Rojo Claro");










