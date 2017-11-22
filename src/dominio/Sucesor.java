package dominio;
public class Sucesor {
	private int[] desplazamiento = new int[2];
	private int[][] distribucion;
	private String accion;
	private Estado es;
	private int coste;

	public Sucesor(Estado es) {
		desplazamiento=es.getAc().getDesplazamiento();
		distribucion=es.getAc().getDistribucion();
		accion=es.getAc().toString();
		coste=es.Costo(distribucion);
		int[][] terreno=es.getTerreno();
		int total=0;
		for(int i=0; i<distribucion[0].length;i++) {
			terreno[distribucion[1][i]][distribucion[2][i]]+=distribucion[0][i];
			total+=distribucion[0][i];
		}
		terreno[es.getTractor()[0]][es.getTractor()[1]]-=total;
		es.setTerreno(terreno);
		es.setTractor(desplazamiento);
		this.es=es;
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

	public String getAccion() {
		return accion;
	}

	public int getCoste() {
		return coste;
	}
}