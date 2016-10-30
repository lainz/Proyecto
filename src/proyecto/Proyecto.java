package proyecto;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Proyecto {

	// Nombre del proyecto
	private String nombre;

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
		this.calendario = new Calendario(8, null);
	}

	// Tareas

	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}

	// Personas

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	// Calendario

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	// Tareas

	public void agregarTarea(String nombre, String descripcion) throws Exception {
		Tarea tarea = new Tarea(nombre, descripcion, this);
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

	// Esfuerzo en horas

	public int esfuerzoRequerido() {
		int total = 0;
		for (int i = 0; i < tareas.size(); i++) {
			tareas.get(i).getCalendario().setFeriados(calendario.getFeriados());
			tareas.get(i).getCalendario().setDiasNoLaborables(calendario.getDiasNoLaborables());
			total = total + tareas.get(i).esfuerzoRequeridoEnHoras(calendario.getHorasLaborables());
		}
		return total;
	}

	// Main

	public static void main(String[] args) {

		try {
			// VERSION 1
			Proyecto p1 = new Proyecto("Trabajo 1");

			// Fechas esperadas
			p1.getCalendario().setFechaFinEsperada(2016, Month.OCTOBER.getValue(), 29);
			p1.getCalendario().setFechaInicioEsperada(2016, Month.OCTOBER.getValue(), 28);
			// Fechas reales
			p1.getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 26);
			p1.getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 27);

			// Tareas
			p1.agregarTarea("Programar", "Hacer el tp 1");
			// System.out.println(p1.numeroDeTareas());
			p1.agregarTarea("Programar", "Hacer el tp 2");
			// System.out.println(p1.numeroDeTareas());
			p1.quitarTarea(0);
			p1.vaciasTareas();
			// System.out.println(p1.numeroDeTareas());

			// Personas
			p1.agregarPersona(new Persona("Leandro", "Diaz"));
			// System.out.println(p1.numeroDePersonas());
			p1.agregarPersona(new Persona("Leandro", "Diaz 2"));
			// System.out.println(p1.numeroDePersonas());
			p1.quitarPersona(0);
			p1.vaciarPersonas();
			// System.out.println(p1.numeroDePersonas());

			// Calendario

			// 1.2) a) 8 hs, lunes a viernes: resultado 40
			p1.getCalendario().setHorasLaborables(8);
			p1.getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);
		    System.out.println(p1.getCalendario().esfuerzoRequeridoEnHoras() * p1.getCalendario().getHorasLaborables());

			// 1.2) b) 6 hs, lunes a jueves: resultado 24
			p1.getCalendario().setHorasLaborables(6);
			p1.getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 27);
			System.out.println(p1.getCalendario().esfuerzoRequeridoEnHoras()  * p1.getCalendario().getHorasLaborables());

			// 1.2) c) 6 hs, lunes a jueves, feriado martes: resultado 18
			p1.getCalendario().setHorasLaborables(6);
			p1.getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 27);
			p1.getCalendario().getFeriados().add(LocalDate.of(2016, Month.OCTOBER.getValue(), 25));
			System.out.println(p1.getCalendario().esfuerzoRequeridoEnHoras()  * p1.getCalendario().getHorasLaborables());

			p1.getCalendario().getFeriados().clear();

			// 1.2) d) 8 hs, lunes a viernes de otra semana: resultado 80
			p1.getCalendario().setHorasLaborables(8);
			p1.getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p1.getCalendario().setFechaFinReal(2016, Month.NOVEMBER.getValue(), 4);
			System.out.println(p1.getCalendario().esfuerzoRequeridoEnHoras()  * p1.getCalendario().getHorasLaborables());

			// VERSION 2
			Proyecto p2 = new Proyecto("Trabajo 2");
			p2.agregarTarea("Tarea 1", "Ejemplo de tarea");
			p2.getTareas().get(0).getCalendario().setFechaFinEsperada(2016, Month.OCTOBER.getValue(), 10);
			p2.getTareas().get(0).getCalendario().setFechaInicioEsperada(2016, Month.OCTOBER.getValue(), 1);
			p2.getTareas().get(0).agregarSubTarea("Subtarea 1", "Ejemplo de subtarea");
			// System.out.println("Subtareas: " +
			// p2.getTareas().get(0).numeroDeSubTareas());
			p2.getTareas().get(0).vaciasSubTareas();
			// System.out.println("Subtareas: " +
			// p2.getTareas().get(0).numeroDeSubTareas());
			// System.out.println("Tareas: " + p2.numeroDeTareas());
			p2.vaciasTareas();
			// System.out.println("Tareas: " + p2.numeroDeTareas());

			Proyecto p3 = new Proyecto("Trabajo 3");
			p3.getCalendario().setHorasLaborables(8);

			p3.agregarTarea("Tarea 1", "Ejemplo de tarea"); // luneas
																		// a
																		// viernes
			p3.getTareas().get(0).getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p3.getTareas().get(0).getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);

			p3.agregarTarea("Tarea 2", "Otro ejemplo de tarea"); // martes
																			// a
																			// viernes
			p3.getTareas().get(1).getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 25);
			p3.getTareas().get(1).getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);

			System.out.println(p3.esfuerzoRequerido()); // 72

			p3.getTareas().clear();

			p3.getCalendario().setHorasLaborables(6);

			p3.agregarTarea("Tarea 1", "Ejemplo de tarea"); // luneas
																		// a
																		// viernes
			p3.getTareas().get(0).getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p3.getTareas().get(0).getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);

			p3.agregarTarea("Tarea 2", "Otro ejemplo de tarea"); // martes
																			// a
																			// viernes
			p3.getTareas().get(1).getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 25);
			p3.getTareas().get(1).getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);

			System.out.println(p3.esfuerzoRequerido()); // 54

			p3.getTareas().clear();

			p3.getCalendario().setHorasLaborables(6);

			p3.agregarTarea("Tarea 1", "Ejemplo de tarea"); // luneas
																		// a
																		// viernes
			p3.getTareas().get(0).getCalendario().setFechaInicioEsperada(2016, Month.OCTOBER.getValue(), 24);
			p3.getTareas().get(0).getCalendario().setFechaFinEsperada(2016, Month.OCTOBER.getValue(), 28);
			p3.getTareas().get(0).getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 24);
			p3.getTareas().get(0).getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);

			p3.agregarTarea("Tarea 2", "Otro ejemplo de tarea"); // martes
																			// a
																			// viernes
			p3.getTareas().get(1).getCalendario().setFechaInicioEsperada(2016, Month.OCTOBER.getValue(), 25);
			p3.getTareas().get(1).getCalendario().setFechaFinEsperada(2016, Month.OCTOBER.getValue(), 28);
			p3.getTareas().get(1).getCalendario().setFechaInicioReal(2016, Month.OCTOBER.getValue(), 25);
			p3.getTareas().get(1).getCalendario().setFechaFinReal(2016, Month.OCTOBER.getValue(), 28);
			
			// Mostrar un dia antes y un dia despues
			for (int i=24; i<30; i++) {
				System.out.println("Porcentaje de avance al final del dia " + i + ": " + p3.getTareas().get(1).getCalendario().porcentajeDeAvance(LocalDate.of(2016, Month.OCTOBER.getValue(), i)));
			}

			p3.getCalendario().getDiasNoLaborables().clear();
			p3.getCalendario().getDiasNoLaborables().add(4); // jueves

			System.out.println(p3.esfuerzoRequerido()); // 42
			
			System.out.println(p3.getCalendario().getFechaInicioEsperada()); // 2016-10-24
			System.out.println(p3.getCalendario().getFechaFinEsperada()); // 2016-10-28
			System.out.println(p3.getCalendario().getFechaInicioReal()); // 2016-10-24
			System.out.println(p3.getCalendario().getFechaFinReal()); // 2016-10-28

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
