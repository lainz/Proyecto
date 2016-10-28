package proyecto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

public class Calendario {
	
	private int horasLaborables;
	private ArrayList<Calendar> feriados;
	
	Calendario(int horasLaborables) {
		this.horasLaborables = horasLaborables;
		this.feriados = new ArrayList<Calendar>();
	}
	
	public int getHorasLaborables() {
		return horasLaborables;
	}

	public void setHorasLaborables(int horasLaborables) {
		this.horasLaborables = horasLaborables;
	}

	public ArrayList<Calendar> getFeriados() {
		return feriados;
	}

	public void setFeriados(ArrayList<Calendar> feriados) {
		this.feriados = feriados;
	}
	
	private boolean seTrabaja(LocalDate dia) {
		// Feriados
		for (int i=0; i<feriados.size(); i++) {
			LocalDate feriado = feriados.get(i).getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (feriado.isEqual(dia)) {
				return false;
			}
		}
		// Sabados y Domingos
		if ((dia.getDayOfWeek().getValue() == 6) || (dia.getDayOfWeek().getValue() == 7)) {
			return false;
		}
		return true;
	}
	
	public int esfuerzoEnHoras(Calendar inicio, Calendar fin) {
		LocalDate ld_inicio = inicio.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ld_fin = fin.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int total_dias = 0;
		
		do {
			if (seTrabaja(ld_inicio)) {
				total_dias++;
			}
			ld_inicio = ld_inicio.plusDays(1);
		} while(!ld_inicio.isAfter(ld_fin));
		
		return total_dias * this.horasLaborables;
	}

	public static void main(String[] args) {

	}

}
