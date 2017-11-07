package dominio;

import java.util.ArrayList;

public class Sucesor {
	public ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc;
	private int[] movimiento=new int[2];
	private int[][] acciones;
	private int costo;
	public Sucesor(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> suc) {
		this.suc = suc;
		movimiento[0]=suc.get(0).get(0).get(0).get(0);
		movimiento[1]=suc.get(0).get(0).get(0).get(1);
		acciones=new int[3][suc.get(1).size()];
		for(int i=0;i<acciones[0].length;i++) {
			acciones[0][i]=suc.get(1).get(i).get(0).get(0);
			acciones[1][i]=suc.get(1).get(i).get(1).get(0);
			acciones[2][i]=suc.get(1).get(i).get(1).get(1);
		}
	}
	public int[] getMovimiento() {
		return movimiento;
	}
	public int[][] getAcciones() {
		return acciones;
	}
	public void setCosto(int costo) {
		this.costo=costo;
	}
}