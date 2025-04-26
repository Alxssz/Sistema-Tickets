create table departamento (
	 id_Departamento SERIAL PRIMARY KEY,
	 nombre VARCHAR(100) NOT NULL,
	 descripcion TEXT
	);

create table usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(30) NOT NULL CHECK (rol IN ('tecnico', 'admin', 'cliente')),
    id_departamento INTEGER REFERENCES departamento(id), -- solo si es tecnico
    activo BOOLEAN DEFAULT TRUE
);

create table ticket (
    id_ticket SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
	    descripcion TEXT,
	    id_departamento INTEGER REFERENCES departamento(id),
	    prioridad VARCHAR(20) CHECK (prioridad IN ('alta', 'media', 'baja')),
	    estado VARCHAR(30) CHECK (estado IN ('abierto', 'en proceso', 'cerrado')),
	    comentario TEXT,
	    adjuntos TEXT,
	    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);
	
	CREATE TABLE nota (
	    id_nota SERIAL PRIMARY KEY,
	    id_ticket INTEGER REFERENCES ticket(id),
	    contenido TEXT NOT NULL,
	    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);
	
	CREATE TABLE tecnico_ticket (
	    id_tecnico INTEGER REFERENCES usuario(id),
	    id_ticket INTEGER REFERENCES ticket(id),
	    PRIMARY KEY (id_tecnico, id_ticket)
	);