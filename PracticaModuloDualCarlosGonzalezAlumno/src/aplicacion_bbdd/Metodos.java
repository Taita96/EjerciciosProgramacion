package aplicacion_bbdd;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Metodos {

	static int menu(int menu, Scanner input) {
		System.out.print("Menu de opciones:\n" + "1- Mostrar datos de Clientes\n" + "2- Mostrar datos de Restaurantes\n"
				+ "3- Mostrar datos de Riders\n" + "4- Mostrar datos de Pedidos\n"
				+ "5- Mostrar datos de Clientes y Restaurantes\n" + "6- Mostrar datos de Raiders y Pedidos\n"
				+ "7- Alta de datos en Clientes\n" + "8- Alta de datos en Restaurantes\n"
				+ "9- Alta de datos en Raiders\n" + "10- Alta de datos en Pedidos\n"
				+ "11- Modificar datos de Clientes\n" + "12- Modificar datos de Restaurantes\n"
				+ "13- Modificar datos de Raiders\n" + "14- Modificar datos de Pedidos\n"
				+ "15- Eliminar datos de Clientes\n" + "16- Eliminar datos de Restaurantes\n"
				+ "17- Eliminar datos de Raiders\n" + "18- Eliminar datos de Pedidos\n" + "19- Salir\n"
				+ "Escoge tu opción: ");
		return menu = input.nextInt();
	}

	static void mostrarTablas(Connection conexion, String consulta) throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);
		ResultSet res = ps.executeQuery();
		ResultSetMetaData rmd = res.getMetaData();
		int numFilas = rmd.getColumnCount();

		if (!res.next()) {
			System.out.println("No hay datos.");
			return;
		}

		do {
			for (int i = 1; i < numFilas; i++) {
				System.out.println(rmd.getColumnName(i) + ": " + res.getString(i));
			}

		} while (res.next());
		System.out.println();

	}

	static boolean detener(Scanner input) {
		System.out.print("¿Deseas continuar agregando datos? [S/N]");
		boolean sn = input.nextLine().toLowerCase().startsWith("s");
		return sn;
	}

	static void insertarDatosClientes(Connection conexion, Scanner input) throws SQLException {
		String consulta = "INSERT INTO clientes (id_usuario, login, clave, nombre, apellidos, calle, ciudad, numero,"
				+ "código, móvil , teléfono, saldo, actividad_principal, fecha_reembolso, monto_reembolso)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);

		System.out.println("intoducir datos de Clientes: ");
		input.nextLine();

		System.out.print("login: ");
		String login = input.nextLine();

		System.out.print("clave: ");
		String clave = input.nextLine();

		System.out.print("Nombre: ");
		String nombre = input.nextLine();

		System.out.print("Apellido: ");
		String apellido = input.nextLine();

		System.out.print("calle: ");
		String calle = input.nextLine();

		System.out.print("ciudad: ");
		String ciudad = input.nextLine();

		System.out.print("numero piso: ");
		int numPiso = input.nextInt();
		input.nextLine();

		System.out.print("codigo Postal: ");
		int codPostal = input.nextInt();
		input.nextLine();

		System.out.print("movil: ");
		int movil = input.nextInt();
		input.nextLine();

		System.out.print("telefono: ");
		int telefono = input.nextInt();
		input.nextLine();

		ps.setInt(1, Types.INTEGER);
		ps.setString(2, login);
		ps.setString(3, clave);
		ps.setString(4, nombre);
		ps.setString(5, apellido);
		ps.setString(6, calle);
		ps.setString(7, ciudad);
		ps.setInt(8, numPiso);
		ps.setInt(9, codPostal);
		ps.setInt(10, movil);
		ps.setInt(11, telefono);
		ps.setBigDecimal(12, BigDecimal.ZERO);
		ps.setNull(13, Types.VARCHAR);
		ps.setNull(14, Types.DATE);
		ps.setBigDecimal(15, BigDecimal.ZERO);

		ps.executeUpdate();
		ps.clearParameters();

	}

	static void insertarDatosRestaurantes (Connection conexion, Scanner input) throws SQLException {
		String consulta = "INSERT INTO restaurantes(nombre, calle, numero, ciudad, código, móvil, telefono)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);
		
		System.out.println("Introducir datos de Restaurantes: ");
		
//		System.out.print("id: ");
//		int id = input.nextInt();
		input.nextLine();
		
		System.out.print("Nombre: ");
		String nombre = input.nextLine();
		
		System.out.print("Calle: ");
		String calle = input.nextLine();
		
		System.out.print("Numero: ");
		int numero = input.nextInt();
		input.nextLine();
		
		System.out.print("Ciudad: ");
		String ciudad = input.nextLine();
		
		System.out.print("Codigo: ");
		int codigo = input.nextInt();
		input.nextLine();
		
		System.out.print("Movil: ");
		int movil = input.nextInt();
		input.nextLine();
		
		System.out.print("Telefono: ");
		int telefono = input.nextInt();
		input.nextLine();
		
		ps.setString(1, nombre);
		ps.setString(2, calle);
		ps.setInt(3, numero);
		ps.setString(4, ciudad);
		ps.setInt(5, codigo);
		ps.setInt(6, movil);
		ps.setInt(7, telefono);

		ps.executeUpdate();
		ps.clearParameters();
		
	}
	
	static void insertarDatosPedidos(Connection conexion, Scanner input) throws SQLException {
		String consulta = "INSERT INTO pedidos(nombre_restaurante, total_compra," 
		+ "fecha_compra, calle_ini, numero_ini, ciudad_ini, codigo_ini,"
		+ "calle_fin, numero_fin, ciudad_fin, codigo_fin, estado)"
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);
		
		System.out.println("Introduce datos de pedidos: ");
		
		
//		System.out.print("Id: ");
//		int id = input.nextInt();
		
		System.out.print("Nombre del restaurante: ");
		String restaurante = input.nextLine();
		input.nextLine();
		
		System.out.print("Total de compra: ");
		int totalCompra = input.nextInt();
		input.nextLine();
		
		System.out.print("Fecha de compra: ");
		String fechaCompra = input.nextLine();
		
		System.out.print("Calle ini: ");
		String calleIni = input.nextLine();
		
		System.out.print("Numero ini: ");
		int numeroIni = input.nextInt();
		input.nextLine();
		
		System.out.print("Ciudad ini: ");
		String ciudadIni = input.nextLine();
		
		System.out.print("Codigo ini: ");
		int codigoIni = input.nextInt();
		input.nextLine();
		
		System.out.print("Calle fin: ");
		String calleFin = input.nextLine();
		
		System.out.print("Numero fin: ");
		int numeroFin = input.nextInt();
		input.nextLine();
		
		System.out.print("Ciudad fin: ");
		String ciudadFin = input.nextLine();
		
		System.out.print("Codigo final: ");
		int codigoFin = input.nextInt();
		input.nextLine();
		
		System.out.print("Estado: ");
		int estado = input.nextInt();
		input.nextLine();
		
//		ps.setInt(1, id_usuario);
		ps.setString(1, restaurante);
		ps.setInt(2, totalCompra);
		ps.setString(3, fechaCompra);
		ps.setString(4, calleIni);
		ps.setInt(5, numeroIni);
		ps.setString(6, ciudadIni);
		ps.setInt(7, codigoIni);
		ps.setString(8, calleFin);
		ps.setInt(9, numeroFin);
		ps.setString(10, ciudadFin);
		ps.setInt(11, codigoFin);
		ps.setInt(12, estado);

		ps.executeUpdate();
		ps.clearParameters();
		
	}
	
	static int actualizarTablas(Connection conexion, String consulta, String ubicacion, String modificacion)
			throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);

		ubicacion = "%" + ubicacion + "%";
		ps.setString(1, modificacion);
		ps.setString(2, ubicacion);
		int modificados = ps.executeUpdate();
		return modificados;

	}

	static int actualizarTablasInt(Connection conexion, String consulta, String ubicacion, int modificar)
			throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);

		ubicacion = "%" + ubicacion + "%";
		ps.setInt(1, modificar);
		ps.setString(2, ubicacion);
		int modificados = ps.executeUpdate();
		return modificados;

	}

	static int elimanarTablas(Connection conexion, String consulta) throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);
		int modificados = ps.executeUpdate();
		return modificados;
	}

	static void actualizarDatosClientes(Scanner input, Connection conexion) throws SQLException {
		String consulta = new String();
		String ubicacion = new String();
		String modificacion = new String();

		int menu = 0, filas = 0, modificar = 0;
		do {
			System.out.println("Que quieres modificar: ");
			System.out.println("1- Clave");
			System.out.println("2- Nombre");
			System.out.println("3- Apellido");
			System.out.println("4- Calle");
			System.out.println("5- Ciudad");
			System.out.println("6- Numero");
			System.out.println("7- Codigo");
			System.out.println("8- Movil");
			System.out.println("9- Telenofo");
			System.out.println("10-salir");
			System.out.print("elige una opcion: ");
			menu = input.nextInt();

			switch (menu) {
			case 1:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Clave: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.clave = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 2:
				System.out.print("Ingresa el antiguo nombre de la persona que quieres cambiar el Nombre: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el Nombre: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.nombre = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 3:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el apellido: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.apellidos = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 4:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el calle: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.calle = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 5:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el ciudad: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.ciudad = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 6:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el numero de piso: ");
				modificar = input.nextInt();

				consulta = "UPDATE clientes SET clientes.numero = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 7:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el numero de codigo postal: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.código = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 8:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el numero movil: ");
				modificar = input.nextInt();

				consulta = "UPDATE clientes SET clientes.móvil = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 9:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar el numero teléfono: ");
				modificar = input.nextInt();

				consulta = "UPDATE clientes SET clientes.teléfono = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
			}
		} while (menu != 10);

	}

}
