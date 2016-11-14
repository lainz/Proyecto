package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Calendario {

	private Proyecto proyecto;
	private int horasLaborables;
	private ArrayList<LocalDate> feriados;
	private ArrayList<Integer> diasNoLaborables;

	// Fechas esperadas
	private LocalDate fechaInicioEsperada;
	private LocalDate fechaFinEsperada;

	// Fechas reales
	private LocalDate fechaInicioReal;
	private LocalDate fechaFinReal;

	Calendario(int horasLaborables, Proyecto proyecto) {
		this.proyecto = proyecto;
		this.horasLaborables = horasLaborables;
		this.feriados = new ArrayList<LocalDate>();
		this.diasNoLaborables = new ArrayList<Integer>();
		this.diasNoLaborables.add(DiaSemana.Sabado.getValue()); // Sabado
		this.diasNoLaborables.add(DiaSemana.Domingo.getValue()); // Domingo
	}

	public ArrayList<Integer> getDiasNoLaborables() {
		return diasNoLaborables;
	}

	public void setDiasNoLaborables(ArrayList<Integer> diasNoLaborables) {
		this.diasNoLaborables = diasNoLaborables;
	}

	public int getHorasLaborables() {
		return horasLaborables;
	}

	public void setHorasLaborables(int horasLaborables) {
		this.horasLaborables = horasLaborables;
	}

	public ArrayList<LocalDate> getFeriados() {
		return feriados;
	}

	public void setFeriados(ArrayList<LocalDate> feriados) {
		this.feriados = feriados;
	}

	public int esfuerzoRequeridoEnHoras() {
		return esfuerzoEnHoras(fechaInicioReal, fechaFinReal) * this.horasLaborables;
	}

	private boolean seTrabaja(LocalDate dia) {
		// Feriados
		for (int i = 0; i < feriados.size(); i++) {
			if (feriados.get(i).isEqual(dia)) {
				return false;
			}
		}

		// Otros dias no laborables
		if (diasNoLaborables.indexOf(dia.getDayOfWeek().getValue()) != -1) {
			return false;
		}
		
		return true;
	}

	private int esfuerzoEnHoras(LocalDate inicio, LocalDate fin) {
		int total_dias = 0;

		do {
			if (seTrabaja(inicio)) {
				total_dias++;
			}
			inicio = inicio.plusDays(1);
		} while (!inicio.isAfter(fin));

		return total_dias;
	}

	// Fecha inicio esperada

	public void setFechaInicioEsperada(int anio, int mes, int dia) throws Exception {
		fechaInicioEsperada = LocalDate.of(anio, mes, dia);
		// Si el a�o de fin esta establecido
		if (fechaFinEsperada != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioEsperada.compareTo(fechaFinEsperada) > 0) {
				throw new Exception("La fecha de inicio esperada debe ser menor o igual a la fecha de fin esperada.");
			}
		}
		// Cambiar en el proyecto
		if (proyecto != null) {
			if (proyecto.getCalendario().getFechaInicioEsperada() != null) {
				if (fechaInicioEsperada.isBefore(proyecto.getCalendario().getFechaInicioEsperada())) {
					proyecto.getCalendario().setFechaInicioEsperada(anio, mes, dia);
				}
			} else {
				proyecto.getCalendario().setFechaInicioEsperada(anio, mes, dia);
			}
		}
	}

	public LocalDate getFechaInicioEsperada() {
		return fechaInicioEsperada;
	}

	// Fecha fin esperada

	public void setFechaFinEsperada(int anio, int mes, int dia) throws Exception {
		fechaFinEsperada = LocalDate.of(anio, mes, dia);
		// Si el a�o de fin esta establecido
		if (fechaInicioEsperada != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioEsperada.compareTo(fechaFinEsperada) > 0) {
				throw new Exception("La fecha de fin esperada debe ser mayor o igual a la fecha de inicio esperada.");
			}
		}
		// Cambiar en el proyecto
		if (proyecto != null) {
			if (proyecto.getCalendario().getFechaFinEsperada() != null) {
				if (fechaFinEsperada.isAfter(proyecto.getCalendario().getFechaFinEsperada())) {
					proyecto.getCalendario().setFechaFinEsperada(anio, mes, dia);
				}
			} else {
				proyecto.getCalendario().setFechaFinEsperada(anio, mes, dia);
			}
		}
	}

	public LocalDate getFechaFinEsperada() {
		return fechaFinEsperada;
	}

	// Fecha inicio real

	public void setFechaInicioReal(int anio, int mes, int dia) throws Exception {
		fechaInicioReal = LocalDate.of(anio, mes, dia);
		// Si el a�o de fin esta establecido
		if (fechaFinReal != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioReal.compareTo(fechaFinReal) > 0) {
				throw new Exception("La fecha de inicio real debe ser menor o igual a la fecha de fin real.");
			}
		}
		// Cambiar en el proyecto
		if (proyecto != null) {
			if (proyecto.getCalendario().getFechaInicioReal() != null) {
				if (fechaInicioReal.isBefore(proyecto.getCalendario().getFechaInicioReal())) {
					proyecto.getCalendario().setFechaInicioReal(anio, mes, dia);
				}
			} else {
				proyecto.getCalendario().setFechaInicioReal(anio, mes, dia);
			}
		}
	}

	public LocalDate getFechaInicioReal() {
		return fechaInicioReal;
	}

	// Fecha fin real

	public void setFechaFinReal(int anio, int mes, int dia) throws Exception {
		fechaFinReal = LocalDate.of(anio, mes, dia);
		// Si el a�o de fin esta establecido
		if (fechaInicioReal != null) {
			// Si la fecha inicio es mayor
			if (fechaInicioReal.compareTo(fechaFinReal) > 0) {
				throw new Exception("La fecha de fin real debe ser mayor o igual a la fecha de inicio real.");
			}
		}
		// Cambiar en el proyecto
		if (proyecto != null) {
			if (proyecto.getCalendario().getFechaFinReal() != null) {
				if (fechaFinReal.isAfter(proyecto.getCalendario().getFechaFinReal())) {
					proyecto.getCalendario().setFechaFinReal(anio, mes, dia);
				}
			} else {
				proyecto.getCalendario().setFechaFinReal(anio, mes, dia);
			}
		}
	}
	
	public LocalDate getFechaFinReal() {
		return fechaFinReal;
	}

	public Double porcentajeDeAvance(LocalDate dia) {
		LocalDate inicio = LocalDate.of(fechaInicioReal.getYear(), fechaInicioReal.getMonth(),
				fechaInicioReal.getDayOfMonth());
		int total_dias = 0;
		int total_dias_pasados = 0;

		do {
			total_dias++;
			if (!inicio.isAfter(dia)) {
				total_dias_pasados++;
			}
			inicio = inicio.plusDays(1);
		} while (!inicio.isAfter(fechaFinReal));

		return (double) ((total_dias_pasados * 100) / total_dias);
	}

	public static void main(String[] args) {

	}

}
