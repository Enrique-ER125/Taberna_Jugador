 create database taberna_Jugador;

use taberna_Jugador;

create table if not exists juego(
id integer primary key auto_increment,
nombre varchar(50),
descripcion text,
reglas text
);

create table if not exists grupo(
id integer primary key auto_increment,
nombre varchar(50),
id_juego integer,
foreign key (id_juego) references juego (id)
);

create table if not exists usuario(
id integer primary key auto_increment,
nombre varchar(50),
contrasena varchar(9),
descripcion varchar(50),
imagen_url VARCHAR(255),
id_grupo integer,
id_juego integer,
id_usuario integer,
foreign key (id_usuario) references usuario (id),
foreign key (id_grupo) references grupo (id),
foreign key (id_juego) references juego (id)
);

create table if not exists estaditicas(
id integer primary key auto_increment,
ataque_fisico numeric(9),
salud numeric (9),
ataque_magico numeric(9),
mana numeric(9),
defensa_fisica numeric(9),
defensa_magica numeric(9),
id_juego integer,
foreign key (id_juego) references juego(id)
);

create table if not exists region(
id integer primary key auto_increment,
nombre varchar(50),
descripcion varchar(1000),
historia text,
id_juego integer,
foreign key (id_juego) references juego(id)
);

create table if not exists lugar (
id integer primary key auto_increment,
nombre varchar(50),
descripcion varchar(1000),
historia text,
id_juego integer,
id_region integer,
foreign key (id_juego) references juego (id),
foreign key (id_region) references region (id)
);

create table if not exists objeto(
id integer primary key auto_increment,
nombre varchar(50),
descripcion varchar(50),
tipo varchar(50),
id_estadistica integer,
foreign key (id_estadistica) references estaditicas(id)
);

create table if not exists hechizo(
id integer primary key auto_increment,
nombre varchar(50),
descripcion text,
tipo varchar(50),
elemento varchar(50),
id_estadistica integer,
id_juego integer,
foreign key (id_estadistica) references estaditicas(id),
foreign key (id_juego) references juego(id)
);

create table if not exists ficha_jugador(
id integer primary key auto_increment,
nombre varchar(50),
descripcion varchar(1000),
historia text,
imagen_url VARCHAR(255),
nivel numeric(5),
experiencia numeric(7),
id_estadistica integer,
id_juego integer,
id_usuario integer,
foreign key (id_usuario)  references usuario (id),
foreign key (id_estadistica) references estaditicas(id),
foreign key (id_juego) references juego(id)
);

create table if not exists criaturas(
id integer primary key auto_increment,
nombre varchar (50),
descripcion varchar(1000),
nivel numeric(5),
rango numeric(1),
imagen_url VARCHAR(255),
historia text,
id_estadistica integer,
id_juego integer,
foreign key (id_estadistica) references estaditicas(id),
foreign key (id_juego) references juego(id)
);

create table if not exists juego_grupos(
id_grupo integer,
id_juego integer,
PRIMARY KEY (id_juego, id_grupo),
foreign key (id_grupo) references grupo (id),
foreign key (id_juego) references juego (id)
);

create table if not exists juego_usuario(
id_usuario integer,
id_juego integer,
PRIMARY KEY (id_juego, id_usuario),
foreign key (id_usuario) references usuario (id),
foreign key (id_juego) references juego (id)
);

create table if not exists grupos_usuario(
id_grupo integer,
id_usuario integer,
PRIMARY KEY (id_usuario, id_grupo),
foreign key (id_grupo) references grupo (id),
foreign key (id_usuario) references usuario (id)
);

create table if not exists criaturas_objetos(
id_criaturas integer,
id_objetos integer,
PRIMARY KEY (id_criaturas, id_objetos),
foreign key (id_objetos) references objeto (id),
foreign key (id_criaturas) references criaturas (id)
);


create table if not exists criaturas_lugares(
id_criaturas integer,
id_lugar integer,
PRIMARY KEY (id_criaturas, id_lugar),
foreign key (id_lugar) references lugar (id),
foreign key (id_criaturas) references criaturas (id)
);

create table if not exists criaturas_hechizos(
id_criaturas integer,
id_hechizo integer,
PRIMARY KEY (id_criaturas, id_hechizo),
foreign key (id_hechizo) references hechizo (id),
foreign key (id_criaturas) references criaturas (id)
);

create table if not exists ficha_hechizos(
id_fichaPersonaje integer,
id_hechizo integer,
PRIMARY KEY (id_fichaPersonaje, id_hechizo),
foreign key (id_hechizo) references hechizo (id),
foreign key (id_fichaPersonaje) references ficha_Jugador (id)
);

create table if not exists ficha_objetos(
id_fichaPersonaje integer,
id_objeto integer,
PRIMARY KEY (id_fichaPersonaje, id_objeto),
foreign key (id_objeto) references objeto (id),
foreign key (id_fichaPersonaje) references ficha_Jugador (id)
);

CREATE TABLE IF NOT EXISTS relaciones_usuario (
    id_origen INTEGER,
    id_destino INTEGER,
    PRIMARY KEY (id_origen, id_destino),
    FOREIGN KEY (id_origen) REFERENCES usuario(id),
    FOREIGN KEY (id_destino) REFERENCES usuario(id)
);

 