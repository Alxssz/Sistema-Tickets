package sistematickets;

public class Sistematickets {

    public static void main(String[] args) {

        // clase 01
        System.out.println("___________________________________________________");
        System.out.println("CLASE 1"); // imprime el título de la clase 1
        ParametrosDelSistema e1 = new ParametrosDelSistema("Duracel", "logo", "idioma Espanol", " 13.00", 360); // crea empresa
        e1.mostrarEmpresa(); //  información de la empresa
        System.out.println("");

        // clase 2
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 2");

        SistemaRolPermiso sistema = new SistemaRolPermiso(); // crea el sistema de roles y permisos

        Permiso p1 = new Permiso("Crear", "Crea tickets"); // crea un permiso 
        sistema.agregarPermiso(p1); // agrega el permiso al sistema

        Rol adminRol = new Rol("Administrador", "con acceso completo al sistema"); // crea el rol de administrador
        Rol tecnicoRol = new Rol("Tecnico", "Tecnico principal"); // crea el rol de técnico

        System.out.println("Rol = " + adminRol.getNombreRol() + " -" + adminRol.getDescripcion()); // muestra el nombre y descripción del rol administrador
        System.out.println("Permiso = " + p1.getNombrePermiso() + " -" + p1.getDescripcion()); // muestra el nombre y descripción del permiso

        Departamento dp1 = new Departamento("Fiscal", "Fiscalizacion"); // crea un departamento 
        SistemaRolPermiso sistemaRP = new SistemaRolPermiso();
        Administrador admin = new Administrador("Juan", "juan@empresa.com", "Juanito", "contra123", dp1, sistemaRP); // crea un administrador con datos personales y asignado al departamento fiscal

        admin.asignarPermisoARol(adminRol, p1); // asigna el permiso al rol administrador
        admin.asignarPermisoARol(tecnicoRol, p1); // asigna el permiso al rol técnico

        GestionRol gestorRoles = new GestionRol(admin); // crea un gestor de roles con el administrador
        gestorRoles.agregarRol(adminRol); // agrega el rol administrador al sistema
        gestorRoles.agregarRol(tecnicoRol); // agrega el rol técnico al sistema
        gestorRoles.editarRol("Administrador", "Administradora ", "Actualizacion de acceso al sistema"); // edita el rol administrador

        gestorRoles.mostrarRoles(); // muestra todos los roles existentes

        // clase 03
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 3");

        Tecnico tecnico1 = new Tecnico("Carlos", "carlos@empresa.com", "Carlos123", "pass123", "tecnico", dp1); // crea un técnico 
        Tecnico tecnico2 = new Tecnico("Ana", "ana@empresa.com", "Ana456", "clave456", "Tecnico", dp1); // crea una técnica 

        GestionDepartamento gestion = new GestionDepartamento(admin); // crea un gestor de departamentos usando al administrador

        gestion.registrarDepartamento("Computacion", "Area informatica", tecnico1, tecnico2); // registra el departamento computación con los técnicos
        gestion.registrarDepartamento("Info", "Investigar", tecnico1); // registra el departamento info con el técnico

        gestion.eliminarDepartamento("Computacion"); // elimina el departamento computación

        gestion.mostrarDepartamentosConTecnicos(); // muestra los departamentos actuales y sus técnicos

        //clase 4
// primero creamos un departamento
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 4");
        Departamento dep = new Departamento("TI", "Tecnologia");

// luego creamos un administrador (rol administrador)
        // Suponiendo que ya tienes una clase SistemaRolPermiso
        Administrador a1 = new Administrador("Laura", "laura@empresa.com", "LauraAdmin", "clave123", null, sistemaRP);

// ahora creamos una instancia del registro de usuarios
        RegistroUsuarios registro = new RegistroUsuarios();
        registro.registrarNuevoUsuario("Carlos123");
        //registro.registrarNuevoUsuario("LauraAdmin");

        //clase 5
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 5");
        GestorEstadosTicket gestor = new GestorEstadosTicket();

        // Crear algunos estados
        EstadoTicket pendiente = new EstadoTicket("Pendiente", "El ticket está en espera de atención.", false);
        EstadoTicket enProceso = new EstadoTicket("En proceso", "El ticket está siendo atendido.", false);
        EstadoTicket resuelto = new EstadoTicket("Resuelto", "El ticket ha sido resuelto.", true);
        EstadoTicket cerrado = new EstadoTicket("Cerrado", "El ticket ha sido cerrado.", true);

        // Agregar los estados al gestor
        a1.crearEstado(gestor, pendiente);
        a1.crearEstado(gestor, enProceso);
        a1.crearEstado(gestor, resuelto);
        a1.crearEstado(gestor, cerrado);

        // Establecer transiciones entre estados
        pendiente.agregarSiguienteEstado(enProceso); // De Pendiente a En proceso
        enProceso.agregarSiguienteEstado(resuelto);  // De En proceso a Resuelto
        resuelto.agregarSiguienteEstado(cerrado);    // De Resuelto a Cerrado

        // Mostrar los estados disponibles
        GestorEstadosTicket.mostrarEstados(gestor);
        System.out.println("");

        // Modificar un estado
        EstadoTicket enProcesoModificado = new EstadoTicket("En progreso", "El ticket está siendo atendido, pero con mayor prioridad.", false);
        a1.modificarEstado(gestor, "En proceso", enProcesoModificado);

        // Intentar eliminar un estado
        a1.eliminarEstado(gestor, "Resuelto");

        // Mostrar los estados después de las modificaciones
        GestorEstadosTicket.mostrarEstados(gestor);

        //clase 6
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 6");
        System.out.println("flujo");
        GestorFlujosTrabajo gestorflujo = new GestorFlujosTrabajo();
        //aun no iniciado
        //gestorflujo.iniciar(); 

        //clase 07
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 7");

        // Crear un gestor de estados de tickets
        GestorEstadosTicket gestorEstados = new GestorEstadosTicket();

        // Crea un ticket
        SistemaGestionTickets sistemaTicket = new SistemaGestionTickets();
        Ticket ticket1 = sistemaTicket.crearTicket("Error en el servidor", "El servidor no responde", dp1, "Alta",
                pendiente, "Se reinicio el servidor", "captura.png", "2025-04-11");

        Ticket ticket2 = sistemaTicket.crearTicket("Error en el servidor", "El servidor no responde", dp1, "Alta",
                pendiente, "Se reinicio el servidor", "captura.png", "2025-04-11");

        sistemaTicket.asignarTecnico(ticket1, tecnico1, tecnico2);
        sistemaTicket.asignarTecnico(ticket2, tecnico1);

        // Actualizar ticket
        //sistemaTicket.actualizarTicket(ticket1, "Reiniciado el servidor", "nuevo_adjunto.png");
        // Cambiar estado 
        // sistemaTicket.cambiarEstadoTicket(ticket1, cerrado);
        // ticket actualizado datoosa
        //sistemaTicket.mostrarTodosTickets();
        //clase 8
        System.out.println("___________________________________________________");
        System.out.println("CLASE 8");
        tecnico1.asignarTicket(ticket1);
        tecnico1.asignarTicket(ticket2);
        sistemaTicket.consultarSolicitudesPendientes(tecnico1);
        sistemaTicket.consultarSolicitudesPendientes(tecnico2);

        //clase 9
        System.out.println("___________________________________________________");
        System.out.println("");
        System.out.println("CLASE 9");

        tecnico1.cambiarEstadoTicket(ticket2, enProceso, "Se cambio estado de ticket 2");
        tecnico2.cambiarEstadoTicket(ticket1, cerrado, "Estado ticket 1");
        admin.cambiarEstadoTicket(ticket2, resuelto, "Cmbaido a resuelto");

        //clase 10
        System.out.println("___________________________________________________");
        System.out.println("CLASE 10");
        Usuario us1 = new Usuario("Alexis", "ale@empresa.com", "ale123", "pass1234", "Usuario", null);
        tecnico1.agregarNotaATicket(ticket1, "Se reinició el servidor y se restauro el servicio", "reinicio_logs.txt");
        tecnico2.agregarNotaATicket(ticket2, "Nota ejemplo", "Archivo.png");
        admin.agregarNotaATicket(ticket1, "nota nueva", "sin adjunto");

        us1.agregarNotaATicket(ticket2, "Uusario", null);
    }

}
