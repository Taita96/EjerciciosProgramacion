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
		Connection conexion = null;

		int menu = 0;
		int filas = 0;
		String consulta = new String();
		boolean detener = true;
		System.out.println("Bienvenido a la base de datos");

		try {
			conexion = DriverManager.getConnection(server + bbdd, user, pwd);

			do {
				
				menu = Metodos.menu(menu, input);
				input.nextLine();
				
				switch (menu) {
				case 1:
					consulta = "SELECT * FROM clientes"; 
					Metodos.mostrarTablas(conexion, consulta);
					break;
				case 2:
					consulta = "SELECT * FROM restaurantes"; 
					Metodos.mostrarTablas(conexion, consulta);
					break;
				case 3:
					consulta = "SELECT * FROM pedidos"; 
					Metodos.mostrarTablas(conexion, consulta);
					break;
				case 4:
					consulta = "SELECT * FROM riders"; 
					Metodos.mostrarTablas(conexion, consulta);
					break;
				case 5:
					System.out.println("Tabla Clientes y Restaurantes");

					consulta = "SELECT * FROM clientes C JOIN pedidos p ON P.id_delivery=C.id_usuario "
							+ "JOIN riders R ON P.id_delivery=R.id_rider "
							+ "JOIN restaurantes RE ON R.id_rider=RE.id_restaurante";

					Metodos.mostrarTablas(conexion, consulta);
					break;
				case 6:
					System.out.println("Tabla Pedidos y Riders");

					consulta = "SELECT * FROM restaurantes "
							+ "JOIN riders ON restaurantes.id_restaurante=riders.id_restaurante "
							+ "JOIN pedidos ON riders.id_delivery=pedidos.id_delivery";

					Metodos.mostrarTablas(conexion, consulta);
					break;
				case 7:
					detener = true;
					do {
						Metodos.insertarDatosClientes(conexion, input);
						detener = Metodos.detener(input);
					} while (detener);
					break;
<<<<<<< HEAD

				case 9: 
					detener = true;
					do {
						Metodos.insertarDatosRiders(conexion, input);
						detener = Metodos.detener(input);
					} while (detener);
					break;
=======
>>>>>>> cf414298e47cce3097bdab7a5e1856424b39de6f
				case 8:
					detener = true;
					do {
						Metodos.insertarDatosRestaurantes(conexion, input);
						detener = Metodos.detener(input);
					} while (detener);
					break;
				case 9: 
					detener = true;
					do {
						Metodos.insertarDatosPedidos(conexion, input);
<<<<<<< HEAD

=======
>>>>>>> cf414298e47cce3097bdab7a5e1856424b39de6f
						detener = Metodos.detener(input);
					} while (detener);
					break;
				case 10:
					detener = true;
					do {
						Metodos.insertarDatosRiders(conexion, input);
						detener = Metodos.detener(input);
					}while (detener);
					break;
				case 11:
					Metodos.actualizarDatosClientes(input, conexion);
					break;
				case 12:
<<<<<<< HEAD
					Metodos.actualizarDatosRestaurante(conexion, input);
					break;
				case 13:
					Metodos.actualizarDatosRiders(conexion, input);
=======
					Metodos.actualizarDatosClientes(input, conexion);
>>>>>>> cf414298e47cce3097bdab7a5e1856424b39de6f
					break;
				case 15:
					consulta = "Delete From clientes";
					filas = Metodos.elimanarTablas(conexion, consulta);
					System.out.printf("%d tabla(s) elimina(s)%n", filas);
					break;
				case 16:
					consulta = "Delete From restaurantes";
					filas = Metodos.elimanarTablas(conexion, consulta);
					System.out.printf("%d tabla(s) elimina(s)%n", filas);
					break;
				case 17:
					consulta = "Delete From raiders";
					filas = Metodos.elimanarTablas(conexion, consulta);
					System.out.printf("%d tabla(s) elimina(s)%n", filas);
					break;
				case 18:
					consulta = "Delete From pedidos";
					filas = Metodos.elimanarTablas(conexion, consulta);
					System.out.printf("%d tabla(s) elimina(s)%n", filas);
					break;
				default:
					System.out.println("Has introducido una opción incorrecta");
					break;
				}

<<<<<<< HEAD
				} while (menu != 10);
=======
			} while (menu != 19);
>>>>>>> cf414298e47cce3097bdab7a5e1856424b39de6f

			conexion.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		   }
	}
}
