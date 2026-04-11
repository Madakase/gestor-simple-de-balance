create database schema;
use schema;

create table producto(
    nombre varchar(50) primary key not null,
    precio double
);

create table venta(
	id_venta int primary key not null AUTO_INCREMENT,
    fecha date not null,
    descripcion varchar(200) not null,
    producto varchar(50) not null,
    estado enum('PENDIENTE', 'CANCELADO'),
    pago double not null,
    foreign key(producto) references producto(nombre)
);

create table gasto(
	id_gasto int primary key not null AUTO_INCREMENT,
    fecha date not null,
    descripcion varchar(200) not null,
    tipo enum('PERSONAL', 'INSUMOS', 'OTRO'),
    estado enum('PENDIENTE', 'CANCELADO'),
    deuda double not null
);