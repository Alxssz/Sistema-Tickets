package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C7_Ticket {

    private static int contador = 1;
    private int id;
    private String titulo;
    private String descripcion;
    private C3_Departamento departamentoAsignado;
    private String prioridad;
    private C5_EstadoTicket estado;
    private List<C2_Tecnico> tecnicosAsignados;
    private String comentario;
    private String adjuntos;
    private String fechaCreacion;
    private String fechaActualizacion;
    private List<D10_Nota> notas = new ArrayList<>();

    // Constructor principal
    public C7_Ticket(String titulo, String descripcion, C3_Departamento departamento, String prioridad,
            C5_EstadoTicket estado, String fechaCreacion) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.departamentoAsignado = departamento;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;

        this.tecnicosAsignados = new ArrayList<>();
        this.comentario = "";
        this.adjuntos = "";
        this.fechaActualizacion = "";

        // Agregar ticket a la cola del gestor
        C3_GestorColas.agregarTicketACola(this, departamentoAsignado);
    }

    //
    public C7_Ticket(String prioridad) {
        this.prioridad = prioridad;
    }

    // Métodos para actualizar el ticket
    public void asignarTecnico(C2_Tecnico tecnico) {
        if (tecnico != null && !tecnicosAsignados.contains(tecnico)) {
            tecnicosAsignados.add(tecnico);
        }
    }

    public void agregarTecnico(C2_Tecnico tecnico) {
        if (tecnico != null) {
            tecnicosAsignados.add(tecnico);
        }
    }

    public void actualizarTicket(String comentario, String adjunto) {
        if (comentario != null && !comentario.isEmpty()) {
            this.comentario = comentario;
        }
        if (adjunto != null && !adjunto.isEmpty()) {
            this.adjuntos = adjunto;
        }
        this.fechaActualizacion = java.time.LocalDateTime.now().toString();
    }

    public void cambiarEstado(C5_EstadoTicket estado) {
        this.estado = estado;
        this.fechaActualizacion = java.time.LocalDateTime.now().toString();
    }

    public void cerrarTicket(C5_EstadoTicket estado) {
        this.estado = estado;
        this.fechaActualizacion = java.time.LocalDateTime.now().toString();
    }

    // Mostrar datos completos del ticket
    public void mostrarDatosTicket() {
        System.out.println("ID          : " + id);
        System.out.println("Titulo      : " + titulo);
        System.out.println("Descripcion : " + descripcion);
        System.out.println("Departamento: " + departamentoAsignado.getNombre());
        System.out.println("Prioridad   : " + prioridad);
        System.out.println("Estado      : " + estado.getNombre());
        System.out.println("Tecnicos Asignados:");
        if (tecnicosAsignados != null && !tecnicosAsignados.isEmpty()) {
            for (C2_Tecnico tecnico : tecnicosAsignados) {
                System.out.println("  - " + tecnico.getNombre() + " " + tecnico.getCorreo());
            }
        }
        System.out.println("Comentarios : " + comentario);
        System.out.println("Adjuntos    : " + adjuntos);
        System.out.println("");

    }

    // Mostrar resumen de tickets pendientes
    public void mostrarTicketPendientes() {
        System.out.println("ID          : " + id);
        System.out.println("Titulo      : " + titulo);
        System.out.println("Departamento: " + departamentoAsignado.getNombre());
        System.out.println("Prioridad   : " + prioridad);
        System.out.println("Estado      : " + estado.getNombre());
        System.out.println("Comentarios : " + comentario);
        System.out.println("Adjuntos    : " + adjuntos);
        System.out.println("");
    }

    // Agregar nota al ticket
    public void agregarNota(String contenido, String archivoAdjunto, String autor) {
        if (contenido == null || contenido.trim().isEmpty()) {
            System.out.println("No se puede agregar una nota vacía.");
            return;
        }
        D10_Nota nota = new D10_Nota(contenido, archivoAdjunto, autor);
        notas.add(nota);
        System.out.println("Nota agregada exitosamente a ticket #" + id);
    }

    public void agregarComentario(String comentario) {
        this.comentario = comentario;
    }

    public void agregarAdjunto(String adjunto) {
        this.adjuntos = adjunto;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public C5_EstadoTicket getEstado() {
        return estado;
    }

    public C3_Departamento getDepartamento() {
        return departamentoAsignado;
    }

    public String getComentario() {
        return comentario;
    }

    public String getAdjuntos() {
        return adjuntos;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public List<C2_Tecnico> getTecnicosAsignados() {
        return tecnicosAsignados;
    }

    public List<D10_Nota> getNotas() {
        return notas;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setAdjuntos(String adjuntos) {
        this.adjuntos = adjuntos;
    }

    public void setEstado(C5_EstadoTicket estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDepartamento(C3_Departamento departamento) {
        this.departamentoAsignado = departamento;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setNotas(List<D10_Nota> notas) {
        this.notas = notas;
    }

    public void setTecnicosAsignados(List<C2_Tecnico> tecnicosAsignados) {
        this.tecnicosAsignados = tecnicosAsignados;
    }

    public static void setContador(int contador) {
        C7_Ticket.contador = contador;
    }

    // Para imprimir el ticket de forma simple
    @Override
    public String toString() {
        return "Ticket con ID: " + id;
    }
}
