package dominio;

public class Principal {
	public static void main(String[] args) throws Exception {
		boolean programa = true;
		while (programa) {
			Problema p = null;
			boolean lectura = true;
			boolean busque = true;
			while (lectura) {
				lectura = false;
				char forma = Character.toLowerCase(leer
						.caracter("Cálculo del campo:\n F - Leer fichero\n R - Aleatorio\n S - Salir del programa"));
				switch (forma) {
				case 'f':
					String nombre = leer
							.cadena("Nombre del terreno que desea leer (sin .txt)\t-:-\n\t\t('Terreno' por defecto)");
					if (nombre.equals("")) {
						nombre = "Terreno";
					}
					Estado esF = new Estado(nombre);
					p = new Problema(esF);
					break;
				case 'r':
					int K, C, F, MAX, Xt, Yt;
					System.out.println();
					System.out.println("Introduzca los valores del nuevo terreno");
					K = leer.entero("K (media de arena)\t-:- ");
					C = leer.entero("Columnas\t\t-:- ");
					F = leer.entero("Filas\t\t\t-:-");
					MAX = leer.entero("Máxima arena\t\t-:- ", K, K * C * F);
					System.out.println("Introduzca posición del tractor");
					Xt = leer.entero("X\t\t\t-:- ", 0, C - 1);
					Yt = leer.entero("Y\t\t\t-:- ", 0, F - 1);
					System.out.println();
					nombre = leer.cadena("Nombre del terreno\n\t\t('Terreno' por defecto)");
					if (nombre.equals("")) {
						nombre = "Terreno";
					}
					Estado esR = new Estado(Xt, Yt, K, MAX, C, F, nombre);
					p = new Problema(esR);
					break;
				case 's':
					programa = false;
					busque = false;
					break;
				default:
					lectura = true;
					System.out.println("Opcion incorrecta");
				}
			}
			while (busque) {
				busque = false;
				String busqueda = leer.cadena(
						"Estrategia de búsqueda a realizar:\n Anchura, Profundidad Simple, Profundidad Acotada, Coste Uniforme, A* o Todas");
				switch (busqueda) {
				case "Anchura":
					p.busqueda("Anchura", 10000000, 1);
					break;
				case "Profundidad Simple":
					p.busqueda("ProfundidadSimple", 10000000, 1);
					break;
				case "Profundidad Acotada":
					p.busqueda("ProfundidadAcotada", 10000000, 1);
					break;
				case "Coste Uniforme":
					p.busqueda("CosteUniforme", 10000000, 1);
					break;
				case "A*":
					p.busqueda("A*", 10000000, 1);
					break;
				case "Todas":
					p.busqueda("Anchura", 10000000, 1);
					p.busqueda("ProfundidadSimple", 10000000, 1);
					p.busqueda("ProfundidadAcotada", 10000000, 1);
					p.busqueda("CosteUniforme", 10000000, 1);
					p.busqueda("A*", 10000000, 1);
					break;
				default:
					busque = true;
					System.out.println("Opcion incorrecta");
				}
			}
		}
	}
}