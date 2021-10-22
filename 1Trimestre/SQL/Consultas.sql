/* 4.1 -- Todos los datos de los empleados incluyendo las áreas de las que son responsables en caso de que sean los encargados de algún área.*/
select em.cod_emple,em.dni,em.nombre,em.telefono, ub.descripcion
from empleados em inner join ubicacion ub
on em.cod_emple = ub.cod_ubicacion;

/* 4.2 -- Ventas del último mes por prenda */
select v.referencia ,v.cod_prenda, count(v.referencia) as "Undidades Vendidas"
from venta v inner join prenda p
where v.cod_prenda= p.cod_prenda and v.referencia=p.referencia
and month(v.fecha) = month(now())
group by v.referencia;

/* 4.3 -- Listado de artículos por ubicación */
select  a.referencia, u.descripcion, a.tipo, t.tipo
from articulo a inner join prenda p inner join ubicacion u inner join tipo_articulo t
where a.referencia = p.referencia and p.ubicacion = u.cod_ubicacion and a.tipo = t.cod_tipo;

/* 5 -- Crea una sentencia que permita modificar el nombre de un empleado*/
update empleados
set nombre="Jose Juan" , dni="57946279S"
where cod_emple=1;

/* 6 -- Crea una sentencia que permita insertar una nueva prenda de un artículo que aún no exista */
insert into tipo_articulo values (6,"Zapatillas");
insert into color_articulo values (6,"Purpura");
insert into articulo values (7,6,4);
insert into prenda values (7,6,45,5,4);

/* 7 -- Crea una sentencia que elimine una ubicación (piensa en qué hacer con las prendas que hasta ahora estaban allí) */
update prenda 
set ubicacion=3
where ubicacion=4;

delete from ubicacion
where cod_ubicacion=4;

/* 8 -- Crea un procedimiento almacenado que registre una venta, descontando del stock de las prendas vendidas la cantidad que se haya comprado*/

DELIMITER //
create procedure AddVenta (in CodigoReferencia int, in Prenda int, in CodigoVendedor int, in NumeroDeUnidades int)

BEGIN
	DECLARE stock int;
    
	select unidades into stock
    from prenda
    where referencia = CodigoReferencia and cod_prenda = Prenda;
    
    if stock - NumeroDeUnidades >= 0  then
		update prenda 
			set unidades= stock - NumeroDeUnidades
			where referencia = CodigoReferencia and cod_prenda = Prenda;
		insert into venta(referencia,cod_prenda,vendedor,fecha,unidades)
			values (CodigoReferencia, Prenda,  CodigoVendedor, curdate(), NumeroDeUnidades);
       
	end if;
END
//




