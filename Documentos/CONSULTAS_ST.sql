--Listar tickets con su departamento, prioridad y estado
SELECT 
  t.id_ticket, t.titulo, d.nombre_departamento, np.nombre_prioridad, et.nombre_estado
FROM 
  public.ticket t
JOIN 
  public.departamentos d ON t.departamento_id = d.id_departamento
JOIN 
  public.niveles_prioridad np ON t.nivel_prioridad_id = np.id_prioridad
JOIN 
  public.estado_tickets et ON t.estado_id = et.id_estado
ORDER BY t.fecha_creacion DESC;

--Obtener usuarios con su rol y departamento
SELECT 
  u.id_usuario, u.nombre_completo, r.nombre_rol, d.nombre_departamento
FROM 
  public.usuarios u
JOIN 
  public.roles r ON u.rol_asignado = r.id_rol
LEFT JOIN 
  public.departamentos d ON u.departamento_id = d.id_departamento
WHERE 
  r.nombre_rol = 'Tecnico';

-- Permisos asignados a un rol específico
SELECT 
  r.nombre_rol, p.nombre_permiso
FROM 
  public.roles r
JOIN 
  public.roles_permisos rp ON r.id_rol = rp.rol_id
JOIN 
  public.permisos p ON rp.permiso_id = p.id_permiso
WHERE 
  r.id_rol = 1;

-- TEcnicos asignados a un departamento específico
SELECT 
  u.id_usuario, u.nombre_completo, d.nombre_departamento
FROM 
  public.tecnicos_departamentos td
JOIN 
  public.usuarios u ON td.id_usuario = u.id_usuario
JOIN 
  public.departamentos d ON td.id_departamento = d.id_departamento
WHERE 
  d.id_departamento = 2;

-- Notas de un ticket con usuario autor 
SELECT 
  tn.id_nota, tn.contenido, u.nombre_completo, tn.fecha_creacion
FROM 
  public.ticket_notas tn
JOIN 
  public.usuarios u ON tn.usuario_id = u.id_usuario
WHERE 
  tn.ticket_id = 10
ORDER BY tn.fecha_creacion DESC;

-- 6. Tickets asignados a un técnico
SELECT 
  t.id_ticket, t.titulo, et.nombre_estado
FROM 
  public.ticket t
JOIN 
  public.estado_tickets et ON t.estado_id = et.id_estado
WHERE 
  t.tecnico_asignado_id = 5
  AND et.nombre_estado = 'Abierto';

-- Parámetros con el nombre del nivel de prioridad
SELECT 
  ps.nombre_empresa, ps.idioma, np.nombre_prioridad
FROM 
  public.parametros_sistema ps
JOIN 
  public.niveles_prioridad np ON ps.nivel_prioridad_id = np.id_prioridad;

-- Flujo de trabajo con estados involucrados
SELECT 
  ftt.nombre_flujo, et.nombre_estado
FROM 
  public.flujo_estados fe
JOIN 
  public.flujo_trabajo_ticket ftt ON fe.id_flujo = ftt.id_flujo
JOIN 
  public.estado_tickets et ON fe.id_estado = et.id_estado
WHERE 
  fe.id_flujo = 3;
