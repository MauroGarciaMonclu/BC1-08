package dominio;


/*
 * Implementar una lista ordenada de nodos del arbol de busqueda.
El orden sera de menor a mayor valor de 'Valor' de los nodos y con
las siguientes operaciones:
CreaFrontera: Crea la frontera vaca y establece el criterio de
ordenacion
Insertar(nodoArbol): A~nada un nodo nuevo a la frontera.
Elimina(): Devuelve el primer nodo de la frontera y lo elimina
de la misma.
EsVacia(): Si o No
 */
	import java.util.Comparator;
	import java.util.PriorityQueue;

	public class Frontera {
		private PriorityQueue<Nodo> frontera; // Estructura de tipo
		

	public Frontera() {

	}

	public PriorityQueue<Nodo> getFrontera() {
	return frontera;
	}

	public void setFrontera(PriorityQueue<Nodo> frontera) {
	this.frontera = frontera;
	}

	/*********************************************************************
	* Method name: crearFrontera.
	* 
	* Description of the Method: Inicializa la cola con prioridad con un tamaño
	* inicial de 10 elementos y con orden de los elementos de menor a mayor
	* valor del NodoArbol que se encuentran
	* 
	* Return value: void
	*********************************************************************/

	public void crearFrontera() {
	setFrontera(new PriorityQueue<Nodo>(10, new CompararNodos()));
	}

	/*********************************************************************
	* Method name: insertar.
	* 
	* Description of the Method: introduce en la frontera un nuevo NodoArbol
	* 
	* Calling arguments: elemento de tipo NodoArbol nodo
	* 
	* Return value:void
	*********************************************************************/

	public void insertar(Nodo nodo) {
	boolean insertado;
	insertado = getFrontera().add(nodo);
	}

	/*********************************************************************
	* Method name: elimina.
	* 
	* Description of the Method: Elimina el elemento que se encuentra en la
	* cabecera de la Cola con prioridad
	* 
	* Return value: NodoArbol que contiene el elemento NodoArbol que se
	* encuentra en la cabeza de la cola con prioridad
	*********************************************************************/

	public Nodo elimina() {
	return getFrontera().poll();
	}

	/*********************************************************************
	* Method name: esVacia.
	* 
	* Description of the Method: Comprueba si en la cola con prioridad existe
	* algún elemento
	* 
	* Return value: boolean que contiene el valor verdadero si la frontera se
	* encuentran sin elementos
	*********************************************************************/

	public boolean esVacia() {
	return getFrontera().isEmpty();
	}
	/*********************************************************************
	* Method name: insertarListaNodos.
	* 
	* Description of the Method: Comprueba si listaNodos es null y si no lo
	* es inserta nodos en la lista
	* 
	* Calling arguments: array de NodoArbol
	*********************************************************************/

	public void insertarListaNodos(Nodo [] listaNodos){
	if(listaNodos!=null){
	for(int i=0;i<listaNodos.length;i++){
	insertar(listaNodos[i]);
	}
	}
	}

	}


