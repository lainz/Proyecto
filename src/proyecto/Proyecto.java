package proyecto;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Proyecto {
	
	// Nombre del proyecto
	private String nombre;
	
	// Fechas esperadas
	private LocalDate fechaInicioEsperada;
	private LocalDate fechaFinEsperada;
	
	// Fechas reales
	private LocalDate fechaInicioReal;
	private LocalDate fechaFinReal;
	
	// Tareas
	private ArrayList<Tarea> tareas;
	
	// Personas
	private ArrayList<Persona> personas;
	
	// Calendario
	private Calendario calendario;

	// Constructor con nombre

	Proyecto(String nombre) throws Exception {
		this.setNombre(nombre);
		this.tareas = new ArrayList<Tarea>();
		this.personas = new ArrayList<Persona>();
		this.calendario = new Calendario(8);
	}
	
	// Calendario
	
	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	
	public int esfuerzoRequeridoEnHoras() {
		return calendario.esfuerzoEnHoras(fechaInicioReal, fechaFinReal);
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
		fechaInicioEsperada = LocalDate.of(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaFinEsperada != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioEsperada.compareTo(fechaFinEsperada) > 0) {
				throw new Exception("La fecha de inicio esperada debe ser menor o igual a la fecha de fin esperada.");
			}
		}
	}
	
	public LocalDate getFechaInicioEsperada() {
		return fechaInicioEsperada;
	}
	
	// Fecha fin esperada
	
	public void setFechaFinEsperada(int año, int mes, int dia) throws Exception {
		fechaFinEsperada = LocalDate.of(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaInicioEsperada != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioEsperada.compareTo(fechaFinEsperada) > 0) {
				throw new Exception("La fecha de fin esperada debe ser mayor o igual a la fecha de inicio esperada.");
			}
		}
	}
	
	public LocalDate getFechaFinEsperada() {
		return fechaFinEsperada;
	}
	
	// Fecha inicio real
	
	public void setFechaInicioReal(int año, int mes, int dia) throws Exception {
		fechaInicioReal = LocalDate.of(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaFinReal != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioReal.compareTo(fechaFinReal) > 0) {
				throw new Exception("La fecha de inicio real debe ser menor o igual a la fecha de fin real.");
			}
		}
	}
	
	public LocalDate getFechaInicioReal() {
		return fechaInicioEsperada;
	}
	
	// Fecha fin real
	
	public void setFechaFinReal(int año, int mes, int dia) throws Exception {
		fechaFinReal = LocalDate.of(año, mes, dia);
		// Si el año de fin esta establecido
		if (fechaInicioReal != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioReal.compareTo(fechaFinReal) > 0) {
				throw new Exception("La fecha de fin real debe ser mayor o igual a la fecha de inicio real.");
			}
		}
	}
	
	public LocalDate getFechaFinReal() {
		return fechaFinReal;
	}
	
	// Main

	public static void main(String[] args) {
		
		try {
			Proyecto p1 = new Proyecto("Trabajo 1");
			
			// Fechas esperadas
			p1.setFechaFinEsperada(2016, Month.OCTOBER.getValue(), 29);
			p1.setFechaInicioEsperada(2016, Month.OCTOBER.getValue(), 28);
			// Fechas reales
			p1.setFechaInicioReal(2016, Month.OCTOBER.getValue(), 26);
			p1.setFechaFinReal(2016, Month.OCTOBER.getValue(), 27);
			
			// Tareas
			p1.agregarTarea(new Tarea("Programar", "Hacer el tp 1"));
			//System.out.println(p1.numeroDeTareas());
			p1.agregarTarea(new Tarea("Programar", "Hacer el tp 2"));
			//System.out.println(p1.numeroDeTareas());
			p1.quitarTarea(0);
			p1.vaciasTareas();
			//System.out.println(p1.numeroDeTareas());
			
			// Personas
			p1.agregarPersona(new Persona("Leandro", "Diaz"));
			//System.out.println(p1.numeroDePersonas());
			p1.agregarPersona(new Persona("Leandro", "Diaz 2"));
			//System.out.println(p1.numeroDePersonas());
			p1.quitarPersona(0);
			p1.vaciarPersonas();
			//System.out.println(p1.numeroDePersonas());
			
			// Calendario
			
			// 1.2) a) 8 hs, lunes a viernes: resultado 40
			p1.getCalendario().setHorasLaborables(8);
			p1.setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);
			System.out.println(p1.esfuerzoRequeridoEnHoras());
			
			// 1.2) b) 6 hs, lunes a jueves: resultado 24
			p1.getCalendario().setHorasLaborables(6);
			p1.setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.setFechaFinReal(2016, Month.OCTOBER.getValue(), 27);
			System.out.println(p1.esfuerzoRequeridoEnHoras());
			
			// 1.2) c) 6 hs, lunes a jueves, feriado martes: resultado 18
			p1.getCalendario().setHorasLaborables(6);
			p1.setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.setFechaFinReal(2016, Month.OCTOBER.getValue(), 27);
			p1.getCalendario().getFeriados().add(LocalDate.of(2016, Month.OCTOBER.getValue(), 25));
			System.out.println(p1.esfuerzoRequeridoEnHoras());
			
			p1.getCalendario().getFeriados().clear();
			
			// 1.2) d) 8 hs, lunes a viernes de otra semana: resultado 80
			p1.getCalendario().setHorasLaborables(8);
			p1.setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.setFechaFinReal(2016, Month.NOVEMBER.getValue(), 4);
			System.out.println(p1.esfuerzoRequeridoEnHoras());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
