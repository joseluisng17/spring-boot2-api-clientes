INSERT INTO regiones (id, nombre) VALUES(1, 'Sudamérica');
INSERT INTO regiones (id, nombre) VALUES(2, 'Centroamérica');
INSERT INTO regiones (id, nombre) VALUES(3, 'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES(4, 'Europa');
INSERT INTO regiones (id, nombre) VALUES(5, 'Asia');
INSERT INTO regiones (id, nombre) VALUES(6, 'Africa');
INSERT INTO regiones (id, nombre) VALUES(7, 'Oceanía');
INSERT INTO regiones (id, nombre) VALUES(8, 'Antártida');

INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(1, 'jose', 'najar', 'jose@gmail.com', '2020-01-01');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(2, 'Luis', 'gonzalez', 'luis@gmail.com', '2020-01-02')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'jhon', 'katsser', 'jhon@gmail.com', '2020-01-03')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'joe', 'reymond', 'joe123@gmail.com', '2020-01-04')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'peter', 'parker', 'peter@gmail.com', '2020-01-05')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'tony', 'stark', 'stark@gmail.com', '2020-01-06')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'joel', 'gonzalez', 'joel@gmail.com', '2020-01-07')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'laura', 'pinedo', 'laura@gmail.com', '2020-01-08')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'cecilia', 'gonzalez', 'cecy@gmail.com', '2020-01-09')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(5, 'jade', 'medina', 'jade@gmail.com', '2020-01-10')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(6, 'raplh', 'lauren', 'raplh@gmail.com', '2020-01-11')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(7,'jaun', 'jedi', 'jaun@gmail.com', '2020-01-12')
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(8, 'erika', 'lozano', 'erika@gmail.com', '2020-01-13')

/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('jose','$2a$10$4NObQ1Kq2Ra27uuAzoOG9u5dupG3E3GuuDmV4JlBB15nUHl8F8hWi',1, 'Jose', 'ng', 'joseluisng0317@gmail.com'); 
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$iRvi3vcVd2yPyQWMB7tE8uXZy/EJmmzWTDwm7h6gdcglSOQvGbdqe',1, 'John', 'Doe', 'john.doe@gmail.com'); 

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1); 
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2); 
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1); 


