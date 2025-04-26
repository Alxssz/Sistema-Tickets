--CONSULTA USUARIO
--Crear
INSERT INTO usuario (nombre, correo, nombre_usuario, contrasena, rol, id_departamento)
VALUES ('Eddy Boror', 'boror@alex.com', 'lex', 'contra123', 'tecnico', null);
--Leer
SELECT * FROM usuario;
SELECT * FROM usuario WHERE id_usuario = 1;
--Actualizar 
UPDATE usuario
SET contrasena = 'nuevaClave123'
WHERE id_usuario = 1;
--Eliminar 
DELETE FROM usuario WHERE id_usuario = 0;
--Filtro id, rol y si esta actico
SELECT * FROM usuario WHERE id_usuario = 1;
SELECT * FROM usuario WHERE rol = 'tecnico';
SELECT * FROM usuario WHERE Activo = 'true';



--CONSULTA TICKET
--crear
INSERT INTO ticket (titulo, descripcion, id_departamento, prioridad, estado, comentario, adjuntos)
VALUES ('Problema de red', 'No hay Internet', null, 'alta', 'abierto', 'Comentaro', 'sin adjunto');
--leer
SELECT * FROM ticket;
SELECT * FROM ticket WHERE id_ticket = 1;
--Actualizar
UPDATE ticket
SET estado = 'cerrado', comentario = 'Resuelto', fecha_actualizacion = CURRENT_TIMESTAMP
WHERE id_ticket = 1;
--Eliminar
DELETE FROM ticket WHERE id_ticket = 0;
--Filtro
SELECT * FROM ticket WHERE id_departamento = 1;
SELECT * FROM ticket WHERE estado = 'abierto';
SELECT * FROM ticket WHERE prioridad = 'alta';


--CONSULTA DEPARTAMENTO
INSERT INTO departamento (nombre, descripcion)
VALUES ('Soporte Técnico', 'Departamento de asistencia a usuarios');
--leer
SELECT * FROM departamento;
SELECT * FROM departamento WHERE id = 1;
--Actualizar
UPDATE departamento
SET nombre = 'Atención al Cliente', descripcion = 'Nuevo nombre'
WHERE id = 1;
--Eliminar
DELETE FROM departamento WHERE id = 1;

--Filtro id, nombre y nombre con M o m
SELECT * FROM departamento WHERE id = 1;
SELECT * FROM departamento WHERE nombre = 'Atencion al Cliente';
SELECT * FROM departamento WHERE nombre ILIKE '%cliente%';


