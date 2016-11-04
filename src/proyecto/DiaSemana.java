package proyecto;

public enum DiaSemana {
	Lunes(1),
	Martes(2),
	Miercoles(3),
	Jueves(4),
	Viernes(5),
	Sabado(6),
	Domingo(7);
	
	private int valor;
	
	DiaSemana(int valor) {
		this.valor = valor;
	}
	
	public int getValue() {
        return this.valor;
    }
}
