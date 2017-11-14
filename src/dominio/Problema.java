package dominio;

public class Problema {
	private Estado es;
	private EspacioEstado ee;

	public Problema(Estado es) {
		this.es = es;
		this.ee = new EspacioEstado(es);
	}
	public boolean Busqueda_Acotada(String estrategia,int prof_Max) {
		Frontera f= new Frontera();
		boolean solucion= false;
		Nodo n= new Nodo(es);
		f.Insertar(n);
		while(!solucion&&!f.esVacia()){
			n=f.Elimina();
			if(EsObjetivo(es.getTerreno().getTerreno(),es.getTerreno().getK())) {
				solucion=true;
			}else {
				ee.Generar_Sucesores(es);
				
				f.Insertar(n);
			}
		}
		return solucion;
	}
	/*public Nodo [] crearListaNodosArbol(Sucesor [] listaSucesores,Nodo nodoActual,long profundidadMaxima,String estrategia){
		Nodo[] listaNodos=new Nodo[listaSucesores.length];
		switch(estrategia){
		case "Anchura":
			listaNodos=new Nodo[listaSucesores.length];
			for(int i=0;i<listaSucesores.length;i++){	
				listaNodos[i]=new Nodo(nodoActual, listaSucesores[i].getEstado(), listaSucesores[i].getAccion(),nodoActual.getProfundidad()+1 );	
			}
			break;
		case "ProfundidadSimple":
			listaNodos=new Nodo[listaSucesores.length];
			for(int i=0;i<listaSucesores.length;i++){			
				listaNodos[i]=new Nodo(nodoActual, listaSucesores[i].getEstado(), listaSucesores[i].getAccion(),100000000-nodoActual.getProfundidad());	
			}
			break;
		case "ProfundidadAcotada": 
			if(nodoActual.getProfundidad()+1<= profundidadMaxima){
				listaNodos=new Nodo[listaSucesores.length];

				for(int i=0;i<listaSucesores.length;i++){			
					listaNodos[i]=new Nodo(nodoActual, listaSucesores[i].getEstado(), listaSucesores[i].getAccion(),100000000-nodoActual.getProfundidad());	
				}	
			}else{
				listaNodos=null;
			}
			break;
		case "CosteUniforme":
			listaNodos=new Nodo[listaSucesores.length];
			for(int i=0;i<listaSucesores.length;i++){
				listaNodos[i]=new Nodo(nodoActual, listaSucesores[i].getEstado(), listaSucesores[i].getAccion(),nodoActual.getCosto());	
			}
			break;
		case "A*":
			listaNodos=new Nodo[listaSucesores.length];
			for(int i=0;i<listaNodos.length;i++){
				listaNodos[i]=new Nodo(nodoActual, listaSucesores[i].getEstado(), listaSucesores[i].getAccion(), nodoActual.getCosto()+1+espacioEstados.heuristica(listaSucesores[i].getEstado()));
			}
			break;
		}
		return listaNodos;
	}*/

	public boolean EsObjetivo(int terreno[][], int K) {
		boolean verificar = true;
		for (int i = 0; i < terreno.length; i++) {
			for (int j = 0; j < terreno[i].length; j++) {
				if (terreno[i][j] != K) {
					verificar = false;
				}
			}
		}
		return verificar;
	}
}