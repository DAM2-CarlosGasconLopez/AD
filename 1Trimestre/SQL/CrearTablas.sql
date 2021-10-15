  
CREATE TABLE Empleados(
   Cod_Empleado int PRIMARY KEY NOT NULL,  
   Nombre varchar(25) NOT NULL,  
   DNI varchar(10),  
   Telefono varchar(20) NULL
   );

CREATE TABLE Ubicacion(
   Cod_Ubicacion int PRIMARY KEY NOT NULL,  
   Descripcion varchar(30),  
   Responsable varchar(25)
   );

CREATE TABLE Venta(
   Cod_Venta int PRIMARY KEY NOT NULL,  
   Prenda int ,  
   Vendedor varchar(25),  
   Fecha date,
   Unidades int,
   Cod_Prenda int
   );
   
CREATE TABLE Prenda(
   Articulo int PRIMARY KEY NOT NULL,  
   Codigo int PRIMARY KEY NOT NULL,  
   Talla int,  
   Unidades int,
   Ubicacion int
   );

CREATE TABLE if not exists Articulo(
   Referencia int PRIMARY KEY NOT NULL,  
   Tipo varchar(20),  
   Color varchar(20)
   );

CREATE TABLE Tipo(
   Cod_Tipo int PRIMARY KEY NOT NULL,  
   Nombre varchar(20)
   );
   
CREATE TABLE Color(
   Cod_color int PRIMARY KEY NOT NULL,  
   Nombre varchar(20)
   );
   
   
