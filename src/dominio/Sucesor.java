package dominio;

import java.util.ArrayList;

public class Sucesor {
	public ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc;
	private int[] desplazamiento = new int[2];
	private int[][] distribucion;
	private Estado es;

	public Sucesor(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc, Estado es) {
		this.suc = suc;
		desplazamiento[0] = suc.get(0).get(0).get(0).get(0);
		desplazamiento[1] = suc.get(0).get(0).get(0).get(1);
		distribucion = new int[3][suc.get(1).size()];
		for (int i = 0; i < distribucion[0].length; i++) {
			distribucion[0][i] = suc.get(1).get(i).get(0).get(0);
			distribucion[1][i] = suc.get(1).get(i).get(1).get(0);
			distribucion[2][i] = suc.get(1).get(i).get(1).get(1);
		}
		int[][] terreno2 = new int[es.getTerreno().getTerreno().length][es.getTerreno().getTerreno().length];
		for (int i = 0; i < terreno2.length; i++) {
			for (int j = 0; j < terreno2[i].length; j++) {
				terreno2[i][j] = es.getTerreno().getTerreno()[i][j];
			}
		}
		for (int i = 0; i < distribucion[0].length; i++) {
			terreno2[es.getTerreno().getXt()][es.getTerreno().getYt()] -= distribucion[0][i];
			terreno2[desplazamiento[0]][desplazamiento[1]] += distribucion[0][i];
		}
		es.getTerreno().setTerreno(terreno2);
		es.getTerreno().setXt(desplazamiento[0]);
		es.getTerreno().setYt(desplazamiento[1]);
		this.es = new Estado(es.getTerreno());
		this.es.setTerreno(Modificar_Terreno(es.getTerreno()));
	}

	private Terreno Modificar_Terreno(Terreno terreno) {
		int[][] terreno2 = terreno.getTerreno();
		for (int i = 0; i < distribucion[0].length; i++) {
			terreno2[terreno.getXt()][terreno.getYt()] -= distribucion[0][i];
			terreno2[distribucion[1][i]][distribucion[2][i]] += distribucion[0][i];
		}
		terreno.setTerreno(terreno2);
		return terreno;
	}

	public int[] getDesplazamiento() {
		return desplazamiento;
	}

	public int[][] getDistribucion() {
		return distribucion;
	}

	public Estado getEstado() {
		return es;
	}
}