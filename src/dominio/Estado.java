package dominio;

public class Estado {
	private Terreno terreno;

	public Estado(Terreno terreno) {
		this.terreno = terreno;
	}

	public int Costo(int acciones[][]) {
		int arena = 1;
		for (int i = 0; i < acciones[0].length; i++) {
			arena += acciones[0][i];
		}
		return arena;
	}
	
	public Terreno getTerreno() {
		return terreno;
	}

	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}
}