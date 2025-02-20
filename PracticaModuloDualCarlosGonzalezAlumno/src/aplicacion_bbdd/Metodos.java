package aplicacion_bbdd;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

public class Metodos {

	static int menu(int menu, Scanner input) {
		System.out.print("Menu de opciones:\n" + "1- Mostrar datos de Clientes\n" + "2- Mostrar datos de Restaurantes\n"
				+ "3- Mostrar datos de Riders\n" + "4- Mostrar datos de Pedidos\n"
				+ "5- Mostrar datos de Clientes y Restaurantes\n" + "6- Mostrar datos de Raiders y Pedidos\n"
				+ "7- Alta de datos en Clientes\n" + "8- Alta de datos en Restaurantes\n"
				+ "9- Alta de datos en Pedidos\n" + "10- Alta de datos en Raiders\n"
				+ "11- Modificar datos de Clientes\n" + "12- Modificar datos de Restaurantes\n"
				+ "13- Modificar datos de Raiders\n" + "14- Modificar datos de Pedidos\n"
				+ "15- Eliminar datos de Clientes\n" + "16- Eliminar datos de Restaurantes\n"
				+ "17- Eliminar datos de Raiders\n" + "18- Eliminar datos de Pedidos\n" + "19- Salir\n"
				+ "Escoge tu opción: ");
		return menu = input.nextInt();

	}

	static String buscarYAgregar(Scanner input, Connection conexion, String consulta, String parametro)
			throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);
		ResultSet res = ps.executeQuery();
		int contador = 0;
		
		while (res.next()) {
			System.out.println((contador+1) + "- " + res.getString(parametro));
			contador++;
		}
		
		String[] datos = new String[contador];
		System.out.println("longitud array " + datos.length);
	
		
		while (res.next()) {
			for(int i = 0; i < datos.length; i++) {
				datos[i] = res.getString(i);
			}
		}
		
		System.out.print("Elige una Opcion: ");
		int opcion = input.nextInt();
		opcion = opcion - 1;
		return datos[opcion];

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
		String consulta = "INSERT INTO clientes (login, clave, nombre, apellidos, calle, ciudad, numero,"
				+ "código, móvil , teléfono) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);

		System.out.println("Intoducir datos de Clientes: ");

		System.out.print("Login: ");
		String login = input.nextLine();

		System.out.print("Clave: ");
		String clave = input.nextLine();

		System.out.println("Informacion personal");

		System.out.print("Nombre: ");
		String nombre = input.nextLine();

		System.out.print("Apellido: ");
		String apellido = input.nextLine();

		System.out.print("Calle: ");
		String calle = input.nextLine();

		System.out.print("ciudad: ");
		String ciudad = input.nextLine();

		System.out.print("Número de piso: ");
		int numPiso = input.nextInt();
		input.nextLine();

		System.out.print("Código Postal: ");
		int codPostal = input.nextInt();
		input.nextLine();

		System.out.print("Número movil: ");
		int movil = input.nextInt();
		input.nextLine();

		System.out.print("Número de telefono: ");
		int telefono = input.nextInt();
		input.nextLine();

		ps.setString(1, login);
		ps.setString(2, clave);
		ps.setString(3, nombre);
		ps.setString(4, apellido);
		ps.setString(5, calle);
		ps.setString(6, ciudad);
		ps.setInt(7, numPiso);
		ps.setInt(8, codPostal);
		ps.setInt(9, movil);
		ps.setInt(10, telefono);

		ps.executeUpdate();
		ps.clearParameters();

	}



	static void insertarDatosRestaurantes(Connection conexion, Scanner input) throws SQLException {
		String consulta = "INSERT INTO restaurantes(nombre, calle, numero, ciudad, código, móvil, telefono)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conexion.prepareStatement(consulta);

		System.out.println("Introducir datos de Restaurantes: ");

		System.out.print("Nombre del restaurante: ");
		String nombre = input.nextLine();

		System.out.print("Ubicacion de la calle: ");
		String calle = input.nextLine();

		System.out.print("Número de portal: ");
		int numero = input.nextInt();
		input.nextLine();

		System.out.print("Ciudad: ");
		String ciudad = input.nextLine();

		System.out.print("Código postal: ");
		int codigo = input.nextInt();
		input.nextLine();

		System.out.print("Número Movil: ");
		int movil = input.nextInt();
		input.nextLine();

		System.out.print("Número de Telefono: ");
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

	static void insertarDatosRiders(Connection conexion, Scanner input) throws SQLException {
		String consulta = "INSERT INTO riders (id_restaurante, id_delivery, nombre, apellidos, identificación,"
				+ " calle, numero, ciudad, código, móvil, teléfono)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);

		String consulta2 = "Select MAX(id_restaurante) FROM restaurantes";
		PreparedStatement ps2 = conexion.prepareStatement(consulta2);
		ResultSet res2 = ps2.executeQuery();
		int idRestaurant = 0;

		if (res2.next()) {
			idRestaurant = res2.getInt(1);
			System.out.println("id restaurantes: " + idRestaurant);
		}
		input.nextLine();

		String consulta3 = "Select MAX(id_delivery) FROM pedidos";
		PreparedStatement ps3 = conexion.prepareStatement(consulta3);
		ResultSet res3 = ps3.executeQuery();
		int idDelivery = 0;

		if (res3.next()) {
			idDelivery = res3.getInt(1);
			System.out.println("id pedidos: " + idDelivery);
		}
		input.nextLine();

		System.out.println("Introducir datos de Riders: ");

		System.out.print("Nombre del repartidor: ");
		String nombre = input.nextLine();

		System.out.print("Apellido del repartidor: ");
		String apellidos = input.nextLine();

		System.out.print("DNI/NIE del repartidor: ");
		String identificacion = input.nextLine();

		System.out.println("informacion personal donde vive el repartidor");

		System.out.print("Calle: ");
		String calle = input.nextLine();

		System.out.print("Número de piso: ");
		int numero = input.nextInt();
		input.nextLine();

		System.out.print("Ciudad: ");
		String ciudad = input.nextLine();

		System.out.print("Código postal: ");
		int codigo = input.nextInt();
		input.nextLine();

		System.out.print("movil: ");
		int movil = input.nextInt();
		input.nextLine();

		System.out.print("telefono: ");
		int telefono = input.nextInt();
		input.nextLine();

		ps.setInt(1, idRestaurant);
		ps.setInt(2, idDelivery);
		ps.setString(3, nombre);
		ps.setString(4, apellidos);
		ps.setString(5, identificacion);
		ps.setString(6, calle);
		ps.setInt(7, numero);
		ps.setString(8, ciudad);
		ps.setInt(9, codigo);
		ps.setInt(10, movil);
		ps.setInt(11, telefono);

		ps.executeUpdate();
		ps.clearParameters();
	}

	static void insertarDatosPedidos(Connection conexion, Scanner input) throws SQLException {
		
		String consulta = "INSERT INTO pedidos(nombre_restaurante, id_usuario, total_compra,"
				+ "fecha_compra, calle_ini, numero_ini, ciudad_ini, codigo_ini,"
				+ "calle_fin, numero_fin, ciudad_fin, codigo_fin, estado)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);
		
		String consulta2 = "select max(id_usuario) from clientes";
		PreparedStatement ps2 = conexion.prepareStatement(consulta2);
		ResultSet res2 = ps2.executeQuery();
		int idUsuario = 0;

		if (res2.next()) {
			idUsuario = res2.getInt(1);
		}

		System.out.println("Introduce datos de los pedidos: ");

		String consultaRes = "select nombre FROM restaurantes";
		String parametro = "nombre";
		System.out.println("Lista de restaurantes para comprar");
		String restaurante = Metodos.buscarYAgregar(input, conexion, consultaRes, parametro);

		System.out.print("Total euros compra: ");
		int totalCompra = input.nextInt();
		input.nextLine();

		LocalDate fecha = LocalDate.now();
		String fechaCompra = fecha.toString();
		
		System.out.println("Datalles del pedido");
		System.out.println("Informacion del restaurante");

		System.out.print("Direccion Inicial Calle: ");
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
		
		
		ps.setString(1, restaurante);
		ps.setInt(2, idUsuario);
		ps.setInt(3, totalCompra);
		ps.setString(4, fechaCompra);
		ps.setString(5, calleIni);
		ps.setInt(6, numeroIni);
		ps.setString(7, ciudadIni);
		ps.setInt(8, codigoIni);
		ps.setString(9, calleFin);
		ps.setInt(10, numeroFin);
		ps.setString(11, ciudadFin);
		ps.setInt(12, codigoFin);
		ps.setInt(13, estado);

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
			
   		
		static void actualizarDatosPedidos(Scanner input, Connection conexion) throws SQLException {
			String consulta = new String();
			String ubicacion = new String();
			String modificacion = new String();

			int menu = 0, filas = 0;
			double modificar = 0;
			do {
				System.out.println("Que quieres modificar: ");
				System.out.println("1- Nombre del Restaurante");
				System.out.println("2- Total de la Compra");
				System.out.println("3- Fecha de la Compra");
				System.out.println("4- Reservas");
				System.out.println("5- Calle Inicial");
				System.out.println("6- Numero Inicial");
				System.out.println("7- Ciudad Inicial");
				System.out.println("8- Codigo Inicial");
				System.out.println("9- Descripcion Inicial");
				System.out.println("10- Calle final");
				System.out.println("11- Numero final");
				System.out.println("12- Ciudad final");
				System.out.println("13- Codigo final");
				System.out.println("14- Descripcion final");
				System.out.println("15- Fecha de reembolso");
				System.out.println("16- Monto de reembolso");
				System.out.println("17- Estado");
				System.out.println("18-salir");
				System.out.print("elige una opcion: ");
				menu = input.nextInt();
				input.nextLine();
				
				switch (menu) {
				
				case 1: 
				System.out.print("Ingresa tu ID del restaurante: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar nombre: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.nombre_restaurante = ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 2: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar El total de la compra: ");
				modificar = input.nextDouble();

				consulta = "UPDATE pedidos SET pedidos.compra = ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				
				break;
				
				case 3: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Fecha de la compra: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.fecha_compra= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				
				break;
				
				case 4: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar reservas: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.reservas = ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 5: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar la calle inicial: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.calle_ini = ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 6: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar numero inicial: ");
				modificar = input.nextInt();

				consulta = "UPDATE pedidos SET pedidos.numero_ini= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 7: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar ciudad inicial: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.ciudad_ini= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
					
				
				case 8: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar codigo inicial: ");
				modificar = input.nextInt();

				consulta = "UPDATE pedidos SET pedidos.codigo_ini= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 9: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Descripcion Inicial: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.descripcion_ini= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 10: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar calle final: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.calle_fin= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 11: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar numero final: ");
				modificar = input.nextInt();

				consulta = "UPDATE pedidos SET pedidos.numero_fin = ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 12: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar ciudad final: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.ciudad_fin = ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
					
				case 13: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar codigo final: ");
				modificar = input.nextInt();

				consulta = "UPDATE pedidos SET pedidos.codigo_fin= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
					
				case 14: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Descripcion final: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.descripcion_fin= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 15: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Fecha de reembolso: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.fecha_reembolso= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				case 16: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Monto de reembolso: ");
				modificar = input.nextDouble();

				consulta = "UPDATE pedidos SET pedidos.monto_reembolso= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				
				case 17: System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar Estado: ");
				modificar = input.nextDouble();

				consulta = "UPDATE pedidos SET pedidos.estado= ? WHERE pedidos.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
				
				default:
					System.out.println("Opcion incorrecta");
					break;
				
				}
		
			} while (menu != 18);


	}
	
	static void actualizarDatosRestaurante (Connection conexion, Scanner input) throws SQLException {
		String consulta = new String();
		String ubicacion = new String();
		String modificacion = new String();
		int menu = 0, filas = 0, modificar = 0;
		do {
		System.out.println("Que quieres modificar: ");
		System.out.println("1- Nombre");
		System.out.println("2- Calle");
		System.out.println("3- Numero");
		System.out.println("4- Ciudad");
		System.out.println("5- Codigo");
		System.out.println("6- Movil");
		System.out.println("7- Telefono");
		System.out.println("8- Salir");
		System.out.print("Elige una opcion: ");
		menu = input.nextInt();

		switch (menu) {

		case 1:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Nombre: ");
		modificacion = input.nextLine();

		consulta = "UPDATE restaurantes SET restaurantes.nombre = ? WHERE restaurantes.nombre LIKE ?";
		filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		case 2:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Calle: ");
		modificacion = input.nextLine();

		consulta = "UPDATE restaurantes SET restaurantes.calle = ? WHERE restaurantes.calle LIKE ?";
		filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		case 3:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Numero: ");
		modificar = input.nextInt();
		
		consulta = "UPDATE restaurantes SET restaurantes.numero = ? WHERE restaurantes.numero LIKE ?";
		filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		case 4:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Ciudad: ");
		modificacion = input.nextLine();

		consulta = "UPDATE restaurantes SET restaurantes.ciudad = ? WHERE restaurantes.ciudad LIKE ?";
		filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		case 5:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Codigo: ");
		modificar = input.nextInt();

		consulta = "UPDATE restaurantes SET restaurantes.código = ? WHERE restaurantes.código LIKE ?";
		filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		case 6:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Movil: ");
		modificar = input.nextInt();

		consulta = "UPDATE restaurantes SET restaurantes.móvil = ? WHERE restaurantes.móvil LIKE ?";
		filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		case 7:
		System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
		ubicacion = input.nextLine();
		input.nextLine();
		System.out.print("\nModificar Telefono: ");
		modificar = input.nextInt();

		consulta = "UPDATE restaurantes SET restaurantes.telefono = ? WHERE restaurantes.telefono LIKE ?";
		filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
		input.nextLine();
		System.out.printf("se han modificados %d filas%n", filas);
		System.out.println();
		break;
		
		default:
		System.out.println("Opcion incorrecta");
		break;
		}

		} while (menu != 8);

		}

	static void actualizarDatosRiders (Connection conexion, Scanner input) throws SQLException {
		String consulta = new String();
		String ubicacion = new String();
		String modificacion = new String();
		
		int menu = 0, filas = 0, modificar = 0;
		
		do {
			System.out.println("Que quieres modificar: ");
			System.out.println("1- Nombre");
			System.out.println("2- Apellido");
			System.out.println("3- Identificación");
			System.out.println("4- Calle");
			System.out.println("5- Numero");
			System.out.println("6- Ciudad");
			System.out.println("7- Código");
			System.out.println("8- Móvil");
			System.out.println("9- Teléfono");
			System.out.print("Elige una opcion: ");
			menu = input.nextInt();
		
		switch (menu) {
		case 1:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar nombre: ");
			modificacion = input.nextLine();

			consulta = "UPDATE riders SET riders.nombre = ? WHERE riders.nombre LIKE ?";
			filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 2:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar apellido: ");
			modificacion = input.nextLine();

			consulta = "UPDATE riders SET riders.apellidos = ? WHERE riders.apellidos LIKE ?";
			filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 3:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar identificación: ");
			modificacion = input.nextLine();

			consulta = "UPDATE riders SET riders.identificación = ? WHERE riders.identificación LIKE ?";
			filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 4:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar calle: ");
			modificacion = input.nextLine();

			consulta = "UPDATE riders SET riders.calle = ? WHERE riders.calle LIKE ?";
			filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 5:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar numero: ");
			modificar = input.nextInt();

			consulta = "UPDATE riders SET riders.numero = ? WHERE riders.numero LIKE ?";
			filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 6:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar ciudad: ");
			modificacion = input.nextLine();

			consulta = "UPDATE riders SET riders.ciudad = ? WHERE riders.ciudad LIKE ?";
			filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 7:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar codigo: ");
			modificar = input.nextInt();

			consulta = "UPDATE riders SET riders.código = ? WHERE riders.código LIKE ?";
			filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 8:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar movil: ");
			modificar = input.nextInt();

			consulta = "UPDATE riders SET riders.móvil = ? WHERE riders.móvil LIKE ?";
			filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
			input.nextLine();
			System.out.printf("se han modificados %d filas%n", filas);
			System.out.println();
			break;
		case 9:
			System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
			ubicacion = input.nextLine();
			input.nextLine();
			System.out.print("\nModificar teléfono: ");
			modificar = input.nextInt();

			consulta = "UPDATE riders SET riders.teléfono = ? WHERE riders.teléfono LIKE ?";
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
