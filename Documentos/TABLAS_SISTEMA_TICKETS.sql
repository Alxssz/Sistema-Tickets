--NIVELES DE PRIORIDAD 
CREATE TABLE public.niveles_prioridad (
    id_prioridad         	SERIAL PRIMARY KEY,
    nombre_prioridad 		character varying(50) NOT NULL
);

--PARAMETROS DEL SISTEMA
CREATE TABLE public.parametros_sistema(
    id 								INT PRIMARY KEY DEFAULT 1,
    nombre_empresa 					character varying(100) NOT NULL,
    logo 							character varying(100) NOT NULL,
    idioma 							character varying(20) NOT NULL,
    zona_horaria 					character varying(20) NOT NULL,
    tiempo_vencimiento_ticket 		INT NOT NULL,
    nivel_prioridad_id 				INT NOT NULL REFERENCES niveles_prioridad(id_prioridad)
);
--ROLES Y PERMISOS
CREATE TABLE public.roles(
    id_rol 				SERIAL PRIMARY KEY,
    nombre_rol 			character varying(50) NOT NULL,
    descripcion_rol 	character varying(100)
);

CREATE TABLE public.permisos(
    id_permiso 				SERIAL PRIMARY KEY,
    nombre_permiso 			character varying(50) NOT NULL,
    descripcion_permiso 	character varying(100)
);

CREATE TABLE roles_permisos (
    rol_id 				INT NOT NULL REFERENCES roles(id_rol),
    permiso_id 			INT NOT NULL REFERENCES permisos(id_permiso),
    PRIMARY KEY (rol_id, permiso_id)
);

--DEPARTAMENTOS
CREATE TABLE public.departamentos(
    id_departamento 			SERIAL PRIMARY KEY,
    nombre_departamento 		character varying(50) NOT NULL,
    descripcion_rol 			character varying(100)
);

--USUARIOS
CREATE TABLE public.usuarios(
    id_usuario 					SERIAL PRIMARY KEY,
    nombre_completo				character varying(100) NOT NULL,
    correo 						character varying(100) NOT NULL,
    nombre_usuario 				character varying(50) NOT NULL,
    contraseña 					character varying(255) NOT NULL,
    rol_asignado 				INT NOT NULL REFERENCES roles(id_rol),
    departamento_id 			INT REFERENCES departamentos(id_departamento)
);

--TECNICOS DE DEPARTAMENTOS 
CREATE TABLE tecnicos_departamentos (
    id_usuario 					INT NOT NULL REFERENCES usuarios(id_usuario),
    id_departamento 			INT NOT NULL REFERENCES departamentos(id_departamento),
    PRIMARY KEY (id_usuario, id_departamento)
);


-- ESTADO TICKET
CREATE TABLE public.estado_tickets(
    id_estado 					SERIAL PRIMARY KEY,
    nombre_estado 				character varying(50) NOT NULL,
    descripcion_estado 			character varying(100),
    estado_final				boolean NOT NULL,
    estados_siguientes 			character varying(500) NOT NULL
);

-- FLUJO DE TRABAJO TICKET
CREATE TABLE public.flujo_trabajo_ticket(
    id_flujo 						SERIAL PRIMARY KEY,
    nombre_flujo 					character varying(100) NOT NULL,
    estados_involucrados			character varying(500) NOT NULL,
    transiciones_permitidas 		character varying(1000) NOT NULL,
    reglas_transicion 				character varying(1000) NOT NULL,
    acciones_automaticas 			character varying(1000) NOT NULL 
);

CREATE TABLE flujo_estados (
    id_flujo 				INT REFERENCES flujo_trabajo_ticket(id_flujo),
    id_estado 				INT REFERENCES estado_tickets(id_estado),
    PRIMARY KEY (id_flujo, id_estado)
);

--TICKET 
CREATE TABLE public.ticket (
    id_ticket 					SERIAL PRIMARY KEY,
    titulo 						character varying(100) NOT NULL,
    descripcion 				character varying(250) NOT NULL,
    departamento_id 			INT NOT NULL REFERENCES departamentos(id_departamento),
    nivel_prioridad_id 			INT NOT NULL REFERENCES niveles_prioridad(id_prioridad),
    estado_id 					INT NOT NULL REFERENCES estado_tickets(id_estado),
    adjunto 					character varying(1000),
    usuario_creador_id 			INT NOT NULL REFERENCES usuarios(id_usuario),
    tecnico_asignado_id 		INT NOT NULL REFERENCES usuarios(id_usuario),
    fecha_creacion 				TIMESTAMP DEFAULT NOW()
);

--TICKET Y NOTAS
CREATE TABLE public.ticket_notas (
    id_nota 					SERIAL PRIMARY KEY,
    ticket_id					INT NOT NULL REFERENCES ticket(id_ticket),
    usuario_id 					INT NOT NULL REFERENCES usuarios(id_usuario),
    contenido 					character varying(1000),
    adjunto 					character varying(1000),
    fecha_creacion 				TIMESTAMP DEFAULT NOW()
);
