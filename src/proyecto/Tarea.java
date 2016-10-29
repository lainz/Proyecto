package proyecto;

import java.util.ArrayList;

public class Tarea {

	private String nombre;
	private String detalles;

	// Subtareas
	ArrayList<Tarea> subtareas;

	// Calendario
	Calendario calendario;

	public ArrayList<Tarea> getSubtareas() {
		return subtareas;
	}

	public void setSubtareas(ArrayList<Tarea> subtareas) {
		this.subtareas = subtareas;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	Tarea(String nombre, String detalles) throws Exception {
		this.setNombre(nombre);
		this.setDetalles(detalles);
		this.subtareas = new ArrayList<Tarea>();
		this.calendario = new Calendario();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws Exception {
		if (nombre.isEmpty()) {
			throw new Exception("El nombre de la tarea no puede estar vacio.");
		}
		this.nombre = nombre;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		if (detalles == null) {
			if (other.detalles != null)
				return false;
		} else if (!detalles.equals(other.detalles))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	// SubTareas

	public void agregarSubTarea(Tarea tarea) throws Exception {
		if (subtareas.indexOf(tarea) != -1) {
			throw new Exception("No se puede agregar una tarea que ya fue agregada.");
		}
		subtareas.add(tarea);
	}

	public void quitarSubTarea(int indice) throws Exception {
		try {
			subtareas.remove(indice);
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("No se puede quitar una tarea que no fue agregada.");
		}
	}

	public void quitarSubTarea(Tarea tarea) throws Exception {
		if (!subtareas.remove(tarea)) {
			throw new Exception("No se puede quitar una tarea que no fue agregada.");
		}
	}

	public void vaciasSubTareas() {
		subtareas.clear();
	}

	public int numeroDeSubTareas() {
		return subtareas.size();
	}

	public int esfuerzoRequeridoEnHoras(int horasLaborables) {
		int total = 0;
		
		// Subtareas
		for (int i = 0; i < subtareas.size(); i++) {
			total = total + subtareas.get(i).getCalendario().esfuerzoRequeridoEnHoras();
		}
		// Esta tarea
		total = total + this.calendario.esfuerzoRequeridoEnHoras();
		
		return total * horasLaborables;
	}

	public static void main(String[] args) {

	}

}
