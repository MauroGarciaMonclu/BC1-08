package dominio;

public class Estado {
	private int[] tractor = new int[2];
	private int[][] terrenoM;
	private int K;
	private Terreno terreno;

	public Estado(Terreno terreno) {
		tractor[0] = terreno.getXt();
		tractor[1] = terreno.getYt();
		terrenoM = terreno.getTerreno();
		this.terreno = terreno;
		K = terreno.getK();
	}

	public int Costo(Sucesor suc) {
		int arena = 1;
		for (int i = 0; i < suc.getAcciones()[0].length; i++) {
			arena = +suc.getAcciones()[0][i];
		}
		suc.setCosto(arena);
		return arena;
	}

	public int[] getTractor() {
		return tractor;
	}

	public Terreno getTerreno() {
		return terreno;
	}

	public int getK() {
		return K;
	}

	public int[][] getTerrenoM() {
		return terrenoM;
	}
}