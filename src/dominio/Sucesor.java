package dominio;

import java.util.ArrayList;

public class Sucesor {
	public ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc;
	private int[] movimiento = new int[2];
	private int[][] acciones;
	private Estado es;
	private int costo;

	public Sucesor(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc, Estado es) {
		this.suc = suc;
		movimiento[0] = suc.get(0).get(0).get(0).get(0);
		movimiento[1] = suc.get(0).get(0).get(0).get(1);
		acciones = new int[3][suc.get(1).size()];
		for (int i = 0; i < acciones[0].length; i++) {
			acciones[0][i] = suc.get(1).get(i).get(0).get(0);
			acciones[1][i] = suc.get(1).get(i).get(1).get(0);
			acciones[2][i] = suc.get(1).get(i).get(1).get(1);
		}
		this.es = new Estado(es.getTerreno());
		this.es.setTerreno(Modificar_Terreno(es.getTerreno()));
		costo = es.Costo(acciones);
	}

	private Terreno Modificar_Terreno(Terreno terreno) {
		int[][] terreno2 = terreno.getTerreno();
		for (int i = 0; i < acciones[0].length; i++) {
			terreno2[terreno.getXt()][terreno.getYt()] -= acciones[0][i];
			terreno2[acciones[1][i]][acciones[2][i]] += acciones[0][i];
		}
		terreno.setTerreno(terreno2);
		return terreno;
	}

	public int[] getMovimiento() {
		return movimiento;
	}

	public int[][] getAcciones() {
		return acciones;
	}

	public int getCosto() {
		return costo;
	}
	public Estado getEstado() {
		return es;
	}
}