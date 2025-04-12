package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private static int contador = 1;
    private int id;
    private String titulo;
    private String descripcion;
    private Departamento departamento;
    private String prioridad;
    private EstadoTicket estado;
    private List<Tecnico> tecnicosAsignados;
    private String comentario;
    private String adjuntos;
    private String fechaCreacion;
    private String fechaActualizacion;
    private List<Nota> notas = new ArrayList<>();

    // Constructor
    public Ticket(String titulo, String descripcion, Departamento departamento, String prioridad,
            EstadoTicket estado, List<Tecnico> tecnicosAsignados, String comentario,
            String adjuntos, String fechaCreacion, String fechaActualizacion) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tecnicosAsignados = tecnicosAsignados;
        this.comentario = comentario;
        this.adjuntos = adjuntos;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Métodos para actualizar el ticket
    public void asignarTecnico(Tecnico tecnico) {
        if (tecnico != null && !tecnicosAsignados.contains(tecnico)) {
            this.tecnicosAsignados.add(tecnico);
        }
    }

    /*public void actualizarTicket(String comentario, String adjunto) {
        if (comentario != null && !comentario.isEmpty()) {
            this.comentarios.add(comentario);
        }
        if (adjunto != null && !adjunto.isEmpty()) {
            this.adjuntos.add(adjunto);
        }
        this.fechaActualizacion = java.time.LocalDateTime.now().toString();
    }*/
    public void cambiarEstado(EstadoTicket estado) {
        this.estado = estado;
        this.fechaActualizacion = java.time.LocalDateTime.now().toString();
    }

    public void cerrarTicket(EstadoTicket estado) {
        this.estado = estado;
        this.fechaActualizacion = java.time.LocalDateTime.now().toString();
    }

    public void agregarTecnico(Tecnico tecnico) {
        if (tecnico != null) {
            this.tecnicosAsignados.add(tecnico);
        }
    }

    // Método para mostrar los datos del ticket
    public void mostrarDatosTicket() {
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Departamento: " + departamento.getNombre());
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Estado: " + estado.getNombre());
        System.out.println("Tecnicos Asignados: ");
        if (tecnicosAsignados != null && !tecnicosAsignados.isEmpty()) {
            for (Tecnico tecnico : tecnicosAsignados) {
                System.out.println("  -" + tecnico.getNombre() + " " + tecnico.getCorreo());
            }
        }
        System.out.println("\nComentarios: " + comentario);
        System.out.println("Adjuntos: " + adjuntos);
        System.out.println("");
    }

    public void mostrarTicketPendientes() {
        System.out.println("ID: " + id);
        System.out.println("Departamento: " + departamento.getNombre());
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Estado: " + estado.getNombre());
        System.out.println("Comentarios: " + comentario);
        System.out.println("Adjuntos: " + adjuntos);
        System.out.println("");
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public Departamento getDepartamento() {
        return departamento;
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

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Ticket(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setTecnicosAsignados(List<Tecnico> tecnicosAsignados) {
        this.tecnicosAsignados = tecnicosAsignados;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public void agregarComentario(String comentario) {
        this.comentario = comentario;
    }

    public void agregarAdjunto(String adjunto) {
        this.adjuntos = adjunto;
    }

    public void agregarNota(String contenido, String archivoAdjunto, String autor) {
        if (contenido == null || contenido.trim().isEmpty()) {
            System.out.println("No se puede agregar una nota vacia.");
            return;
        }

        Nota nota = new Nota(contenido, archivoAdjunto, autor);
        notas.add(nota);
        // Mostrar el ticket afectado (puede personalizarse más si quieres)
        System.out.println("Nota agregada exitosamente a ticket #" + id);
    }

    public List<Nota> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return "Ticket con ID: " + id;
    }

}
