use PruebaTecnica;

CREATE TABLE rubro (
	id_rubro bigint,
	rubro varchar(50),
	CONSTRAINT rubro_id_pk PRIMARY KEY (id_rubro),
)

CREATE TABLE producto (
	codigo varchar(10),
	nombre varchar(50),
	fecha_creacion date,
	id_rubro bigint,
	CONSTRAINT producto_codigo_pk PRIMARY KEY (codigo),
	CONSTRAINT producto_id_fk_rubro FOREIGN KEY (id_rubro) REFERENCES rubro(id_rubro)
)

CREATE TABLE cliente (
	id_cliente bigint,
	nombre varchar(50),
	apellido varchar(50),
	cuit bigint,
	CONSTRAINT cliente_id_pk PRIMARY KEY (id_cliente)
)

CREATE TABLE venta (
	id_venta bigint,
	codigo_producto varchar(10),
	fecha date,
	cantidad bigint,
	precio_unitario float,
	id_cliente bigint,
	CONSTRAINT venta_id_pk PRIMARY KEY (id_venta),
	CONSTRAINT venta_codigo_fk_producto FOREIGN KEY (codigo_producto) REFERENCES producto(codigo),
	CONSTRAINT venta_id_fk_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
)

/*Todos los productos del rubro "librería", creados hoy*/
SELECT * 
FROM producto 
WHERE id_rubro = (
	SELECT id_rubro FROM rubro 
	WHERE rubro = 'libreria'
	) 
AND fecha_creacion = CONVERT(date, GETDATE())


/*Monto total vendido por cliente (mostrar nombre del cliente y monto)*/
SELECT cliente.id_cliente, nombre, apellido, SUM(precio_unitario * cantidad) AS monto 
FROM (cliente INNER JOIN venta ON cliente.id_cliente = venta.id_cliente) 
GROUP BY cliente.id_cliente


/*Cantidad de ventas por producto*/
SELECT codigo_producto, COUNT(id_venta) AS cantidad_vendida 
FROM venta 
GROUP BY codigo_producto


/*Cantidad de productos diferentes comprados por cliente en el mes actual*/
SELECT DISTINCT id_cliente, COUNT(codigo_producto) AS cantidad_productos 
FROM venta
WHERE MONTH(fecha) = MONTH(GETDATE()) 
GROUP BY id_cliente


/*Ventas que tienen al menos un producto del rubro "bazar"*/
SELECT * FROM venta 
WHERE (codigo_producto) = 
	(SELECT codigo FROM producto WHERE id_rubro = 
		(SELECT id_rubro FROM rubro WHERE rubro = 'bazar'))


/*Rubros que no tienen ventas en los últimos 2 meses*/
SELECT * FROM rubro 
WHERE id_rubro NOT IN (
	SELECT rubro.id_rubro
	FROM venta INNER JOIN producto ON venta.codigo_producto = producto.codigo
	INNER JOIN rubro ON producto.id_rubro = rubro.id_rubro
	WHERE fecha > DATEADD(MONTH, -2, fecha)
	)