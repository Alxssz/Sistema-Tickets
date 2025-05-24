package sistematickets;

public class SISTEMA_TICKETS {

    public static void main(String[] args) {

        //CLASE 1
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 1");

        C1_ParametrosDelSistema empresa = new C1_ParametrosDelSistema(
                "Mi Empresa S.A.",
                "logo.png",
                "es",
                "GMT-5",
                30
        );

        // Agregar prioridades
        empresa.AgregarPrioridad("Alta");
        empresa.AgregarPrioridad("Media");
        empresa.AgregarPrioridad("Baja");

        // Mostrar información completa
        empresa.mostrarInformacion();

        //CLASE 2
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 2");
        C2_SistemaRolPermiso sistema = new C2_SistemaRolPermiso();

        
        C2_Permiso p1 = new C2_Permiso("Crear", "Crea tickets");
        sistema.agregarPermiso(p1);

        
        C2_Rol adminRol = new C2_Rol("Administrador", "acceso completo");
        C2_Rol tecnicoRol = new C2_Rol("Tecnico", "Tecnico principal");
        C2_Rol usuarioRol = new C2_Rol("Usuario", "Usuario Comun");

        System.out.println("Datos Rol = " + adminRol.getNombreRol() + " - " + adminRol.getDescripcion());
        System.out.println("Datos Permiso = " + p1.getNombrePermiso() + " - " + p1.getDescripcion());

        System.out.println("ASQWQEW");
        C3_Departamento dp1 = new C3_Departamento("Fiscal", "Fiscalizacion");
        C2_SistemaRolPermiso sistemaRP = new C2_SistemaRolPermiso();
        C2_Administrador admin = new C2_Administrador("Juan", "juan@empresa.com", "Juanito", "contra123", adminRol, dp1, sistemaRP);

        admin.asignarPermisoARol(adminRol, p1);
        admin.asignarPermisoARol(tecnicoRol, p1);
        adminRol.mostrarPermisosAsignados();
        sistema.mostrarRoles();

        // CLASE 3
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 3");

        C2_Tecnico tecnico1 = new C2_Tecnico("Carlos", "carlos@empresa.com", "Carlos123", "pass123", tecnicoRol, dp1);
        C2_Tecnico tecnico2 = new C2_Tecnico("Ana", "ana@empresa.com", "Ana456", "clave456", tecnicoRol, dp1);

        C3_GestionDepartamento gestion = new C3_GestionDepartamento(admin);
        gestion.registrarDepartamento("Computacion", "Area informatica", tecnico1, tecnico2);
        gestion.registrarDepartamento("Info", "Investigar", tecnico1);
        gestion.eliminarDepartamento("Computacion");
        gestion.mostrarDepartamentosConTecnicos();

        // CLASE 4
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 4");
        C3_Departamento dep = new C3_Departamento("TI", "Tecnologia");
        C2_Administrador a1 = new C2_Administrador("Laura", "laura@empresa.com", "LauraAdmin", "clave123", adminRol, null, sistemaRP);

        C4_RegistroUsuarios registro = new C4_RegistroUsuarios();
        registro.agregarUsuario(a1);
        registro.verUsuarios();

        //CLASE 5
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 5");

        C5_GestorEstadosTicket gestor = new C5_GestorEstadosTicket();

        C5_EstadoTicket pendiente = new C5_EstadoTicket("Pendiente", "En espera de atención", false);
        C5_EstadoTicket enProceso = new C5_EstadoTicket("En proceso", "Atendido", false);
        C5_EstadoTicket resuelto = new C5_EstadoTicket("Resuelto", "Resuelto", true);
        C5_EstadoTicket cerrado = new C5_EstadoTicket("Cerrado", "Cerrado", true);

        a1.crearEstado(gestor, pendiente);
        a1.crearEstado(gestor, enProceso);
        a1.crearEstado(gestor, resuelto);
        a1.crearEstado(gestor, cerrado);

        pendiente.agregarSiguienteEstado(enProceso);
        enProceso.agregarSiguienteEstado(resuelto);
        resuelto.agregarSiguienteEstado(cerrado);
        
        System.out.println("");
        C5_GestorEstadosTicket.mostrarEstados(gestor);
        a1.modificarEstado(gestor, "En proceso", new C5_EstadoTicket("En progreso", "Mayor prioridad", false));
        a1.eliminarEstado(gestor, "Resuelto");

        // CLASE 6
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 6");
        C5_EstadoTicket estadoAbierto = new C5_EstadoTicket("Abierto", "Ticket abierto", false);
        C5_EstadoTicket estadoCerrado = new C5_EstadoTicket("Cerrado", "Ticket cerrado", true);

        C5_TransicionEstado transicion = new C5_TransicionEstado(estadoAbierto, estadoCerrado, "regla");
        C6_AccionAutomatica accion = new C6_AccionAutomatica(estadoCerrado, "Enviar mensaje de cierre");

        C6_GestorFlujosTrabajo flujo = new C6_GestorFlujosTrabajo("Flujo Soporte");
        flujo.agregarEstados(estadoAbierto);
        flujo.agregarEstados(estadoCerrado);
        flujo.agregarTransiciones(transicion);
        flujo.agregarAccionAutomatica(accion);

        System.out.println("DATOS DEL FLUJO");
        flujo.mostrarEstados();
        flujo.mostrarTransiciones();
        flujo.mostrarAccionesAutomaticas();
        System.out.println("FIN CLASE 6");

        // CLASE 7
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 7");

        C7_SistemaGestionTickets sistemaTicket = new C7_SistemaGestionTickets();
        C7_Ticket ticket1 = sistemaTicket.crearTicket("Error en el servidor", "El servidor no responde", dp1, "Alta", pendiente, "2025-04-11");
        C7_Ticket ticket2 = sistemaTicket.crearTicket("Error en el servidor", "El servidor no responde", dp1, "Alta", pendiente, "2025-04-11");

        sistemaTicket.asignarTecnico(ticket1, tecnico1, tecnico2);
        sistemaTicket.asignarTecnico(ticket2, tecnico1);

        // Clase 8
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 8");

        tecnico1.asignarTicket(ticket1);
        tecnico1.asignarTicket(ticket2);
        sistemaTicket.consultarSolicitudesPendientes(tecnico1);
        sistemaTicket.consultarSolicitudesPendientes(tecnico2);

        // Clase 9
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 9");
        tecnico1.cambiarEstadoTicket(ticket2, enProceso, "Se cambio estado de ticket 2");
        tecnico2.cambiarEstadoTicket(ticket1, cerrado, "Estado ticket 1");
        admin.cambiarEstadoTicket(ticket2, resuelto, "Cmbaido a resuelto");

        // Clase 10
        System.out.println("");
        System.out.println("____________________________________________________________________");
        System.out.println("CLASE 10");

        C2_Usuario us1 = new C2_Usuario("Alexis", "ale@empresa.com", "ale123", "pass1234", usuarioRol, null);
        tecnico1.agregarNotaATicket(ticket1, "Se reinició el servidor y se restauro el servicio", "reinicio_logs.txt");
        tecnico2.agregarNotaATicket(ticket2, "Nota ejemplo", "Archivo.png");
        admin.agregarNotaATicket(ticket1, "nota nueva", "sin adjunto");
        us1.agregarNotaATicket(ticket2, "Usuario", null);
    }
}
