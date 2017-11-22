package dominio;

import java.io.*;

public class Principal {
	public static void main(String[] args) throws IOException, Exception {
		boolean programa = true;
		while (programa) {
			char forma = Character.toLowerCase(
					leer.caracter("Cálculo del campo:\n F - Leer fichero\n R - Aleatorio\n S - Salir del programa"));
			switch (forma) {
			case 'f':
				String nombre = leer
						.cadena("Nombre del terreno que desea leer (sin .txt)\t-:-\n\t\t('Terreno' por defecto)");
				if (nombre.equals("")) {
					nombre = "Terreno";
				}
				Terreno terF = new Terreno(nombre);
				System.out.println("Terreno creado a partir de " + nombre + ".txt:");
				terF.Mostrar_Terreno();
				System.out.println();
				System.out.println("Sucesores:");
				System.out.println();
				Estado esF = new Estado(terF);
				Problema pF = new Problema(esF);
				pF.busqueda(100000, 1);
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
				Terreno terR = new Terreno(Xt, Yt, K, MAX, C, F, nombre);
				System.out.println("Terreno generado:");
				terR.Mostrar_Terreno();
				Estado esR = new Estado(terR);
				Problema pR = new Problema(esR);
				break;
			case 's':
				programa = false;
				break;
			default:
				System.out.println("Opcion incorrecta");
			}
		}
	}
}