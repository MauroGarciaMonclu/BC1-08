package dominio;

public class Estado {
	private int[] tractor = new int[2];
	private int[][] terreno;
	private Accion ac;
	private int K;
	private Terreno ter;

	public Estado(Terreno terreno) {
		tractor[0] = terreno.getXt();
		tractor[1] = terreno.getYt();
		this.terreno = terreno.getTerreno();
		K = terreno.getK();
		ter = terreno;
	}

	public Estado(Terreno terreno, Accion ac) {
		tractor[0] = terreno.getXt();
		tractor[1] = terreno.getYt();
		this.terreno = terreno.getTerreno();
		this.ac = ac;
		K = terreno.getK();
		ter = terreno;
	}

	public int Costo(int acciones[][]) {
		int arena = 1;
		for (int i = 0; i < acciones[0].length; i++) {
			arena += acciones[0][i];
		}
		return arena;
	}

	public int[][] getTerreno() {
		return terreno;
	}

	public void setTerreno(int[][] terreno) {
		this.terreno = terreno;
	}

	public int[] getTractor() {
		return tractor;
	}

	public void setTractor(int[] tractor) {
		this.tractor = tractor;
	}

	public Accion getAc() {
		return ac;
	}

	public void setAc(Accion ac) {
		this.ac = ac;
	}

	public int getK() {
		return K;
	}

	public Terreno getTer() {
		return ter;
	}
}