package proyecto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Proyecto {
	
	// Nombre del proyecto
	private String nombre;
	
	// Fechas esperadas
	private Calendar fechaInicioEsperada;
	private Calendar fechaFinEsperada;
	
	// Fechas reales
	private Calendar fechaInicioReal;
	private Calendar fechaFinReal;
	
	// Tareas
	private ArrayList<Tarea> tareas;
	
	// Personas
	private ArrayList<Persona> personas;

	// Constructor con nombre
	
	Proyecto(String nombre) throws Exception {
		this.setNombre(nombre);
		this.tareas = new ArrayList<Tarea>();
		this.personas = new ArrayList<Persona>();
	}
	
	// Tareas
	
	public void agregarTarea(Tarea tarea) throws Exception {
		if (tareas.indexOf(tarea) != -1) {
			throw new Exception("No se puede agregar una tarea que ya fue agregada.");
		}
		tareas.add(tarea);
	}
	
	public void quitarTarea(int indice) throws Exception {
		try {
			tareas.remove(indice);
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("No se puede quitar una tarea que no fue agregada.");
		}
	}
	
	public void quitarTarea(Tarea tarea) throws Exception {
		if (!tareas.remove(tarea)) {
			throw new Exception("No se puede quitar una tarea que no fue agregada.");
		}
	}
	
	public void vaciasTareas() {
		tareas.clear();
	}
	
	public int numeroDeTareas() {
		return tareas.size();
	}
	
	// Personas
	
	public void agregarPersona(Persona persona) throws Exception {
		if (personas.indexOf(persona) != -1) {
			throw new Exception("No se puede agregar una persona que ya fue agregada.");
		}
		personas.add(persona);
	}
	
	public void quitarPersona(int indice) throws Exception {
		try {
			personas.remove(indice);
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("No se puede quitar una persona que no fue agregada.");
		}
	}
	
	public void quitarPersona(Persona persona) throws Exception {
		if (!personas.remove(persona)) {
			throw new Exception("No se puede quitar una persona que no fue agregada.");
		}
	}
	
	public void vaciarPersonas() {
		personas.clear();
	}
	
	public int numeroDePersonas() {
		return personas.size();
	}
	
	// Nombre
	
	public void setNombre(String nombre) throws Exception {
		if (nombre.isEmpty()) {
			throw new Exception("El nombre del proyecto no puede estar vacio.");
		}
		
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	// Fecha inicio esperada
	
	public void setFechaInicioEsperada(int año, int mes, int dia) throws Exception {
		fechaInicioEsperada = new GregorianCalendar(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaFinEsperada != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioEsperada.compareTo(fechaFinEsperada) > 0) {
				throw new Exception("La fecha de inicio esperada debe ser menor o igual a la fecha de fin esperada.");
			}
		}
	}
	
	public Calendar getFechaInicioEsperada() {
		return fechaInicioEsperada;
	}
	
	// Fecha fin esperada
	
	public void setFechaFinEsperada(int año, int mes, int dia) throws Exception {
		fechaFinEsperada = new GregorianCalendar(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaInicioEsperada != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioEsperada.compareTo(fechaFinEsperada) > 0) {
				throw new Exception("La fecha de fin esperada debe ser mayor o igual a la fecha de inicio esperada.");
			}
		}
	}
	
	public Calendar getFechaFinEsperada() {
		return fechaFinEsperada;
	}
	
	// Fecha inicio real
	
	public void setFechaInicioReal(int año, int mes, int dia) throws Exception {
		fechaInicioReal = new GregorianCalendar(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaFinReal != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioReal.compareTo(fechaFinReal) > 0) {
				throw new Exception("La fecha de inicio real debe ser menor o igual a la fecha de fin real.");
			}
		}
	}
	
	public Calendar getFechaInicioReal() {
		return fechaInicioEsperada;
	}
	
	// Fecha fin real
	
	public void setFechaFinReal(int año, int mes, int dia) throws Exception {
		fechaFinReal = new GregorianCalendar(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaInicioReal != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioReal.compareTo(fechaFinReal) > 0) {
				throw new Exception("La fecha de fin real debe ser mayor o igual a la fecha de inicio real.");
			}
		}
	}
	
	public Calendar getFechaFinReal() {
		return fechaFinReal;
	}
	
	// Main

	public static void main(String[] args) {
		
		try {
			Proyecto p1 = new Proyecto("Trabajo 1");
			
			// Fechas esperadas
			p1.setFechaFinEsperada(2016, 10, 29);
			p1.setFechaInicioEsperada(2016, 10, 28);
			// Fechas reales
			p1.setFechaInicioReal(2016, 12, 26);
			p1.setFechaFinReal(2016, 12, 27);
			
			// Tareas
			p1.agregarTarea(new Tarea("Programar", "Hacer el tp 1"));
			System.out.println(p1.numeroDeTareas());
			p1.agregarTarea(new Tarea("Programar", "Hacer el tp 2"));
			System.out.println(p1.numeroDeTareas());
			p1.quitarTarea(0);
			p1.vaciasTareas();
			System.out.println(p1.numeroDeTareas());
			
			// Personas
			p1.agregarPersona(new Persona("Leandro", "Diaz"));
			System.out.println(p1.numeroDePersonas());
			p1.agregarPersona(new Persona("Leandro", "Diaz 2"));
			System.out.println(p1.numeroDePersonas());
			p1.quitarPersona(0);
			p1.vaciarPersonas();
			System.out.println(p1.numeroDePersonas());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
