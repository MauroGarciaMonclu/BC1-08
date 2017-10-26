package dominio;



public class Nodo {
	private Nodo nodoPadre; 
	//private Estado estadoActual;
	private String accion; 
	private int valor;
	private int costo; 

public Nodo(Estado estado) {
	this.nodoPadre = null;
	//this.estadoActual = estado;
	this.accion = null;
	this.valor = 0;
	this.costo = 0;
}



public int getCosto() {
	return costo;
}

public void setCosto(int costo) {
	this.costo = costo;
}

public Nodo getNodoPadre() {
	return nodoPadre;
}

public void setNodoPadre(Nodo nodoPadre) {
	this.nodoPadre = nodoPadre;
}

/*public Estado getEstadoActual() {
	return estadoActual;
}*/

/*public void setEstadoActual(Estado estadoActual) {
	this.estadoActual = estadoActual;
}*/

public String getAccion() {
	return accion;
}

public void setAccion(String accion) {
	this.accion = accion;
}

public int getValor() {
	return valor;
}

public void setValor(int valor) {
	this.valor = valor;
}


}

/*
 * 
 * Esta estructura contendra todo lo necesario para trabajar con el arbol de busqueda:
Informacion del nodo: Acceso al padre (informacion estructural)
Informacion del dominio:
Estado : representacion del terreno actual y el tractor
Costo : hasta el nodo desde el estado inicial
Accion : (distribucion de la arena sobrante, desplazamiento)
Valor : es el valor por el cual se va a ordenar en la frontera
 * 
 */
	

