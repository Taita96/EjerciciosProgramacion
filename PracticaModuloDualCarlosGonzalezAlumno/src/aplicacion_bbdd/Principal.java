package aplicacion_bbdd;

import java.sql.*;
import java.util.Scanner;
public class Principal {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String bbdd = "flash_delivery";
		String user = "root";
		String pwd = "";
		String server = "jdbc:mysql://localhost:3306/";
		Connection conexion= null;
		
		int menu = 0;
		String consulta = new String();
		System.out.println("Bienvenido a la base de datos");
		
		try {
			conexion = DriverManager.getConnection(server + bbdd,user,pwd);
			
			do {
				System.out.print("Menu de opciones:\n"
						+ "1- Mostrar datos de Clientes\n"
						+ "2- Mostrar datos de Restaurantes\n"
						+ "3- Mostrar datos de Riders\n"
						+ "4- Mostrar datos de Pedidos\n"
						+ "5- Mostrar datos de Clientes y Restaurantes\n"
						+ "6- Mostrar datos de Raiders y Pedidos\n"
						+ "7- Alta de datos en Clientes\n"
						+ "8- Alta de datos en Restaurantes\n"
						+ "9- Alta de datos en Raiders\n"
						+ "10- Alta de datos en Pedidos\n"
						+ "11- Modificar datos de Clientes\n"
						+ "12- Modificar datos de Restaurantes\n"
						+ "13- Modificar datos de Raiders\n"
						+ "14- Modificar datos de Pedidos\n"
						+ "15- Eliminar datos de Clientes\n"
						+ "16- Eliminar datos de Restaurantes\n"
						+ "17- Eliminar datos de Raiders\n"
						+ "18- Eliminar datos de Pedidos\n"
						+ "19- Salir\n"
						+ "Escoge tu opción: ");
				menu = input.nextInt();
				
				switch (menu) {
				case 1:
//					String cliente = "clientes";
//					
//					ResultSet res = Metodos.mostrarUnaTabla(cliente, conexion);
//					ResultSetMetaData rmd = Metodos.mostrarUnaTabla(res);
//					Metodos.msqlFilas(rmd);
//										
//					for (int i = 0; i < columnas; i++) {
//					System.out.print("=");
//					System.out.println();
//				}
//					
//				while(res.next()){
//					
//				}
					
					 consulta = "SELECT * FROM clientes";
					 Metodos.mostrar1Tabla(conexion, consulta);
					 input.nextLine();
					break;
				case 5:
					System.out.println("Tabla Clientes y Restaurantes");
					
					consulta = "SELECT * FROM clientes C JOIN pedidos p ON P.id_delivery=C.id_usuario "
						           + "JOIN riders R ON P.id_delivery=R.id_rider "
						           + "JOIN restaurantes RE ON R.id_rider=RE.id_restaurante";
					
					System.out.println(consulta);
					Metodos.mostrar2Tablas(conexion, consulta);

					break;
				case 7:
					boolean detener = true;
					do {
						Metodos.insertarDatosClientes(conexion, input);
						detener = Metodos.detener(input);
					} while (detener);
					break;
				case 11:
					consulta = "UPDATE";
					break;

				default:
					System.out.println("Has introducido una opción incorrecta");
					break;
				}
				
				
			} while (menu != 10);
			
			conexion.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
