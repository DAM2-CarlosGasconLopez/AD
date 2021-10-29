
create table categoria(
cod_categoria int auto_increment,
descripcion varchar(100),
salario_base int,
constraint primary key (cod_categoria)
);

insert into categoria (descripcion,salario_base) values ("Limpieza",900),("Secretaría",1200),("Conductor Carretilla",1200),("Conductor Camion",1500),("Encargado",1300);

alter table empleados 
drop column categoria;

alter table empleados
drop foreign key fk;
