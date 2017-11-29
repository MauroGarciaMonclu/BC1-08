package dominio;

public class Sucesor {
	private int[] desplazamiento = new int[2];
	private int[][] distribucion;
	private String accion;
	private Estado es;
	private int coste;

	public Sucesor(Estado es) {
		desplazamiento = es.getAc().getDesplazamiento();
		distribucion = es.getAc().getDistribucion();
		accion = es.getAc().toString();
		coste = es.Costo(es.getAc());
		this.es = es;
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

	public Estado getEs() {
		return es;
	}

	public void setEs(Estado es) {
		this.es = es;
	}

	public void setDesplazamiento(int[] desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	public void setDistribucion(int[][] distribucion) {
		this.distribucion = distribucion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
}