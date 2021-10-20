create table empleados(
cod_emple int auto_increment,
nombre varchar(100),
dni varchar(9),
telefono int(9),
constraint primary key (cod_emple)
);

create table ubicacion(
cod_ubicacion int auto_increment,
descripcion varchar(200),
responsable int,
constraint primary key (cod_ubicacion),
constraint foreign key (cod_ubicacion) references empleados(cod_emple)
);

create table tipo_articulo(
cod_tipo int auto_increment,
tipo varchar(100),
constraint primary key (cod_tipo)
);

create table color_articulo(
cod_color int auto_increment,
color varchar(100),
constraint primary key (cod_color)
);

create table articulo(
referencia int auto_increment,
tipo int,
color int,
constraint primary key (referencia),
constraint foreign key (tipo) references tipo_articulo(cod_tipo),
constraint foreign key (color) references color_articulo(cod_color)
);

create table prenda(
referencia int,
cod_prenda int,
talla int,
unidades int,
ubicacion int,
constraint primary key (referencia, cod_prenda),
constraint foreign key (referencia) references articulo(referencia),
constraint foreign key (ubicacion) references ubicacion(cod_ubicacion)
);

create table venta(
cod_venta int auto_increment,
referencia int,
cod_prenda int,
vendedor int,
fecha date,
unidades int,
constraint primary key (cod_venta),
constraint foreign key (referencia, cod_prenda) references prenda(referencia, cod_prenda),
constraint foreign key (vendedor) references empleados(cod_emple)
);


insert into empleados (dni,nombre,telefono) values ("62132925X","Eric",666567744), ("73891285Z","Raul",668517955),("54983158H","Marc",658745966),("41424345O","Sandra",999574501),("71496324A","Esther",742689510);
insert into ubicacion (descripcion,responsable) values ("Almacen Trasero",6),("Tienda calle castellon",7),("Almacen Grande",8), ("Tienda centro", 9), ("Almacen pequeño",10);
insert into color_articulo(color) values ("Naranja"),("Negro"),("Amarillo"),("Verde"),("Azul");
insert into tipo_articulo(tipo) values ("Camiseta"),("Chupa"),("Pantalones"),("Chaquetas"),("Pantalones Cortos");
insert into articulo(color,tipo) values  (2,2),(1,1),(3,3),(4,4),(5,5);
insert into prenda(cod_prenda,referencia,talla,unidades,ubicacion) values 
(1,5,40,5,4),
(2,4,48,9,1),
(3,3,46,10,5),
(5,2,42,2,2),
(4,1,50,1,3);

insert into venta(cod_prenda,fecha,unidades,vendedor,referencia) values 
(1,"2018-09-01",5,1,5),
(2,"2018-09-02",6,2,4),
(3,"2018-07-03",8,3,3),
(4,"2018-06-04",7,4,2),
(5,"2018-10-11",4,5,1);

insert into venta (referencia,cod_prenda,vendedor,fecha,unidades) values
(1,4,1,"2018-12-12",5),
(2,5,2,"2020-05-01",7),
(3,3,3,"2019-02-14",14),
(4,2,3,"2019-02-19",2),
(5,1,3,"2020-10-30",8);

   
   
