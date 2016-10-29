package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Calendario {

	private int horasLaborables;
	private ArrayList<LocalDate> feriados;
	private ArrayList<Integer> diasNoLaborables;

	public ArrayList<Integer> getDiasNoLaborables() {
		return diasNoLaborables;
	}

	public void setDiasNoLaborables(ArrayList<Integer> diasNoLaborables) {
		this.diasNoLaborables = diasNoLaborables;
	}

	// Fechas esperadas
	private LocalDate fechaInicioEsperada;
	private LocalDate fechaFinEsperada;

	// Fechas reales
	private LocalDate fechaInicioReal;
	private LocalDate fechaFinReal;
	
	Calendario() {
		this(0);
	}

	Calendario(int horasLaborables) {
		this.horasLaborables = horasLaborables;
		this.feriados = new ArrayList<LocalDate>();
		this.diasNoLaborables = new ArrayList<Integer>();
		this.diasNoLaborables.add(6);
		this.diasNoLaborables.add(7);
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
		return esfuerzoEnHoras(fechaInicioReal, fechaFinReal);
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
		
		// Sabados y Domingos
		//if ((dia.getDayOfWeek().getValue() == 6) || (dia.getDayOfWeek().getValue() == 7)) {
		//	return false;
		//}
		return true;
	}

	public int esfuerzoEnHoras(LocalDate inicio, LocalDate fin) {
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

	public static void main(String[] args) {

	}

}
