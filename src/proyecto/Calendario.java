package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Calendario {
	
	private int horasLaborables;
	private ArrayList<LocalDate> feriados;
	
	Calendario(int horasLaborables) {
		this.horasLaborables = horasLaborables;
		this.feriados = new ArrayList<LocalDate>();
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
	
	private boolean seTrabaja(LocalDate dia) {
		// Feriados
		for (int i=0; i<feriados.size(); i++) {
			if (feriados.get(i).isEqual(dia)) {
				return false;
			}
		}
		// Sabados y Domingos
		if ((dia.getDayOfWeek().getValue() == 6) || (dia.getDayOfWeek().getValue() == 7)) {
			return false;
		}
		return true;
	}
	
	public int esfuerzoEnHoras(LocalDate inicio, LocalDate fin) {
		int total_dias = 0;
		
		do {
			if (seTrabaja(inicio)) {
				total_dias++;
			}
			inicio = inicio.plusDays(1);
		} while(!inicio.isAfter(fin));
		
		return total_dias * this.horasLaborables;
	}

	public static void main(String[] args) {

	}

}
