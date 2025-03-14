package aplicacion_bbdd;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Metodos {

	
	static int menu(int menu, Scanner input) {
		System.out.print("Menu de opciones:\n" + "1- Mostrar datos de Clientes\n" + "2- Mostrar datos de Restaurantes\n"
				+ "3- Mostrar datos de Riders\n" + "4- Mostrar datos de Pedidos\n"
				+ "5- Mostrar datos de Clientes y Restaurantes\n" + "6- Mostrar datos de Raiders y Pedidos\n"
				+ "7- Alta de datos en Clientes\n" + "8- Alta de datos en Restaurantes\n"
				+ "9- Alta de datos en Pedidos\n" + "10- Alta de datos en Raiders\n"
				+ "11- Modificar datos de Clientes\n" + "12- Modificar datos de Restaurantes\n"
				+ "13- Modificar datos de Pedidos\n" + "14- Modificar datos de Raiders\n"
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
		String resultado = new String();

		while (res.next()) {
			System.out.println((contador + 1) + "- " + res.getString(parametro));
			contador++;
		}

		ps = conexion.prepareStatement(consulta);
		res = ps.executeQuery();

		String[] datos = new String[contador];

		while (res.next()) {
			for (int i = 0; i < datos.length; i++) {
				datos[i] = res.getString(parametro);
			}
		}

		System.out.print("Elige una Opcion: ");
		int opcion = input.nextInt();
		opcion = opcion - 1;
		resultado = datos[opcion];
		return resultado;

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
			System.out.println();
			for (int i = 1; i < numFilas; i++) {
				System.out.println(rmd.getColumnName(i) + ": " + res.getString(i));
			}
			System.out.println();
		} while (res.next());
		
	}

	static boolean detener(Scanner input) {
		System.out.print("¿Deseas continuar agregando datos? [S/N]: ");
		boolean sn = input.nextLine().toLowerCase().startsWith("s");
		return sn;
	}

	static void insertarDatosClientes(Connection conexion, Scanner input) throws SQLException {
		String consulta = "INSERT INTO clientes (login, clave, nombre, apellidos, calle, ciudad, numero,"
				+ "código, móvil , teléfono) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conexion.prepareStatement(consulta);
		String descripcionPiso = new String();
		String descripcionCodPostal = new String();
		String descripcionMovil = new String();
		String descripcionTelefono = new String();
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

		descripcionPiso = "Número de portal: ";
		String numPisoS = validarNumero(input, descripcionPiso);
		int numPiso = insertarNumero(numPisoS);

		descripcionCodPostal = "Código Postal: ";
		String codPostalS = validarNumero(input, descripcionCodPostal);
		int codPostal = insertarNumero(codPostalS);

		descripcionMovil = "Número movil: ";
		String movilS = validarNumero(input, descripcionMovil);
		int movil = insertarNumero(movilS);

		descripcionTelefono = "Número de telefono: ";
		String telefonoS = validarNumero(input, descripcionTelefono);
		int telefono = insertarNumero(telefonoS);

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

		String descripcionNumero = new String();
		String descripcionCodigo = new String();
		String descripcionMovil = new String();
		String descripcionTelefono = new String();

		System.out.println("Introducir datos de Restaurantes: ");

		System.out.print("Nombre del restaurante: ");
		String nombre = input.nextLine();

		System.out.print("Ubicacion de la calle: ");
		String calle = input.nextLine();

		descripcionNumero = "Número de edificio: ";
		String numeroS = validarNumero(input, descripcionNumero);
		int numero = insertarNumero(numeroS);

		System.out.print("Ciudad: ");
		String ciudad = input.nextLine();

		descripcionCodigo = "Código postal: ";
		String codigoS = validarNumero(input, descripcionCodigo);
		int codigo = insertarNumero(codigoS);

		descripcionMovil = "Número Movil: ";
		String movilS = validarNumero(input, descripcionMovil);
		int movil = insertarNumero(movilS);

		descripcionTelefono = "Número de Telefono: ";
		String telefonoS = validarNumero(input, descripcionTelefono);
		int telefono = insertarNumero(telefonoS);

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

		String descripcionNumeroPiso = new String();
		String descripcionCodigo = new String();
		String descripcionMovil = new String();
		String descripcionTelefono = new String();

		String consulta2 = "Select MAX(id_restaurante) FROM restaurantes";
		PreparedStatement ps2 = conexion.prepareStatement(consulta2);
		ResultSet res2 = ps2.executeQuery();
		int idRestaurant = 0;

		if (res2.next()) {
			idRestaurant = res2.getInt(1);
		}

		String consulta3 = "Select MAX(id_delivery) FROM pedidos";
		PreparedStatement ps3 = conexion.prepareStatement(consulta3);
		ResultSet res3 = ps3.executeQuery();
		int idDelivery = 0;

		if (res3.next()) {
			idDelivery = res3.getInt(1);
		}
		System.out.println();

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

		descripcionNumeroPiso = "Número de piso: ";
		String numeroPisoS = validarNumero(input, descripcionNumeroPiso);
		int numeroPiso = insertarNumero(numeroPisoS);

		System.out.print("Ciudad: ");
		String ciudad = input.nextLine();

		descripcionCodigo = "Código postal: ";
		String codigoS = validarNumero(input, descripcionCodigo);
		int codigo = insertarNumero(codigoS);

		descripcionMovil = "Número Movil: ";
		String movilS = validarNumero(input, descripcionMovil);
		int movil = insertarNumero(movilS);

		descripcionTelefono = "Número de Telefono: ";
		String telefonoS = validarNumero(input, descripcionTelefono);
		int telefono = insertarNumero(telefonoS);

		ps.setInt(1, idRestaurant);
		ps.setInt(2, idDelivery);
		ps.setString(3, nombre);
		ps.setString(4, apellidos);
		ps.setString(5, identificacion);
		ps.setString(6, calle);
		ps.setInt(7, numeroPiso);
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

		String descripcionTotalCompra = new String();
		String descripcionNumeroInicial = new String();
		String descripcionCodigoInicial = new String();
		String descripcionNumeroFinal = new String();
		String descripcionCodigoFinal = new String();

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
		input.nextLine();
		
		descripcionTotalCompra = "Total euros compra: ";
		String totalCompraS = validarNumero(input, descripcionTotalCompra);
		int totalCompra = insertarNumero(totalCompraS);
		System.out.println(restaurante);

		LocalDate fecha = LocalDate.now();
		String fechaCompra = fecha.toString();

		System.out.println("Datalles del pedido");

		System.out.println("Informacion del restaurante");

		System.out.print("Direccion Inicial Calle: ");
		String calleIni = input.nextLine();

		descripcionNumeroInicial = "Numero de edifico: ";
		String numeroInicialS = validarNumero(input, descripcionNumeroInicial);
		int numeroInicial = insertarNumero(numeroInicialS);

		System.out.print("Ciudad inicial del pedido: ");
		String ciudadIni = input.nextLine();

		descripcionCodigoInicial = "Codigo postal inicial del pedido: ";
		String codigoInicialS = validarNumero(input, descripcionCodigoInicial);
		int codigoInicial = insertarNumero(codigoInicialS);
		
		System.out.println("Informacion donde se entragará el pedido");
		
		System.out.print("Calle final del pedido: ");
		String calleFin = input.nextLine();

		descripcionNumeroFinal = "Numero de piso : ";
		String numeroFinalS = validarNumero(input, descripcionNumeroFinal);
		int numeroFinal = insertarNumero(numeroFinalS);

		System.out.print("Ciudad final del pedido: ");
		String ciudadFin = input.nextLine();

		descripcionCodigoFinal = "Codigo postal: ";
		String codigoFinalS = validarNumero(input, descripcionCodigoFinal);
		int codigoFinal = insertarNumero(codigoFinalS);

		System.out.println("estado del pedio: ");
		boolean estado = Metodos.actualizarEstado(input);

		ps.setString(1, restaurante);
		ps.setInt(2, idUsuario);
		ps.setInt(3, totalCompra);
		ps.setString(4, fechaCompra);
		ps.setString(5, calleIni);
		ps.setInt(6, numeroInicial);
		ps.setString(7, ciudadIni);
		ps.setInt(8, codigoInicial);
		ps.setString(9, calleFin);
		ps.setInt(10, numeroFinal);
		ps.setString(11, ciudadFin);
		ps.setInt(12, codigoFinal);
		ps.setBoolean(13, estado);

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
		String descripcion = new String();
    
		int menu = 0, filas = 0, modificar = 0;
		do {
			System.out.println("Que quieres modificar en tus datos de cliente: ");
			System.out.println("1- Clave");
			System.out.println("2- Nombre");
			System.out.println("3- Apellido");
			System.out.println("4- Calle");
			System.out.println("5- Ciudad");
			System.out.println("6- Numero portal");
			System.out.println("7- Codigo");
			System.out.println("8- Movil");
			System.out.println("9- Telefono");
			System.out.println("10- Salir");
			System.out.print("Elige una opcion: "); 
			menu = input.nextInt();
			input.nextLine();
			
			switch (menu) {
			case 1:
				
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar Clave: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.clave = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 2:
				System.out.print("Ingresa el antiguo nombre de la persona que quieres cambiar el Nombre: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar el Nombre: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.nombre = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 3:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar el apellido: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.apellidos = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 4:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar el calle: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.calle = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 5:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar el ciudad: ");
				modificacion = input.nextLine();

				consulta = "UPDATE clientes SET clientes.ciudad = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 6:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				

				descripcion = "\nModificar el numero de piso: ";
				String numeroPisoS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroPisoS);
				input.nextLine();

				consulta = "UPDATE clientes SET clientes.numero = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 7:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				
				descripcion = "\nModificar el numero de codigo postal: ";
				String codigoPostalS = validarNumero(input, descripcion);
				modificar = insertarNumero(codigoPostalS);
				input.nextLine();

				consulta = "UPDATE clientes SET clientes.código = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 8:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar el numero movil: ";
				String numeroMovilS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroMovilS);
				input.nextLine();
				
				consulta = "UPDATE clientes SET clientes.móvil = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 9:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar el numero teléfono: ";
				String numeroTelefonoS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroTelefonoS);
				input.nextLine();
				
				consulta = "UPDATE clientes SET clientes.teléfono = ? WHERE clientes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
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
			String descripcion = new String();
		
			int menu = 0, filas = 0, modificar = 0;
			
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

				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar nombre: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.nombre_restaurante = ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 2:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar El total de la compra: ";
				String totalCompraS = validarNumero(input, descripcion);
				modificar = insertarNumero(totalCompraS);
				input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.total_compra = ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);

				break;

			case 3:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar Fecha de la compra: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.fecha_compra= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);

				break;

			case 4:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar reservas: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.reservas = ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 5:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar la calle inicial: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.calle_ini = ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 6:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();
				
				
				descripcion = "\nModificar Numero de edificio restaurante: ";
				String numeroEdificioS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroEdificioS);
				input.nextLine();
				
				consulta = "UPDATE pedidos SET pedidos.numero_ini= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 7:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar ciudad del donde se realiza el pedido (donde inicia): ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.ciudad_ini= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 8:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				descripcion = "\nModificar codigo postal inicial: ";
				String cPostalInicialS = validarNumero(input, descripcion);
				modificar = insertarNumero(cPostalInicialS);
				input.nextLine();
				
				consulta = "UPDATE pedidos SET pedidos.codigo_ini= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 9:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar Editar Descripcion inicil de ubicacion de pedido: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.descripcion_ini= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 10:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar calle final del pedido: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.calle_fin= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 11:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				descripcion = "\nModificar Numero de piso cliente: ";
				String numeroClienteS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroClienteS);
				input.nextLine();
				
				consulta = "UPDATE pedidos SET pedidos.numero_fin = ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 12:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar ciudad del donde se realiza el pedido (donde finaliza): ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.ciudad_fin = ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 13:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				descripcion = "\nModificar codigo postal final: ";
				String cPostalFinalS = validarNumero(input, descripcion);
				modificar = insertarNumero(cPostalFinalS);
				input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.codigo_fin= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 14:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar Editar Descripcion final de ubicacion de pedido: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.descripcion_fin= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 15:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar Fecha de reembolso: ");
				modificacion = input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.fecha_reembolso= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 16:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				descripcion = "\nModificar Monto de reembolso: ";
				String montoFinalS = validarNumero(input, descripcion);
				modificar  = insertarNumero(montoFinalS);
				input.nextLine();

				consulta = "UPDATE pedidos SET pedidos.monto_reembolso= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			case 17:
				System.out.print("Ingresa el nombre del restaurante: ");
				ubicacion = input.nextLine();

				System.out.print("\nModificar Estado: ");
				modificar = input.nextInt();
				input.nextLine();
				
				consulta = "UPDATE pedidos SET pedidos.estado= ? WHERE pedidos.nombre_restaurante LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);

				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			default:
				System.out.println("Opcion incorrecta");
				break;

			}

		} while (menu != 18);

	}

	static void actualizarDatosRestaurante(Connection conexion, Scanner input) throws SQLException {
		String consulta = new String();
		String ubicacion = new String();
		String modificacion = new String();
		String descripcion = new String();
		int menu = 0, filas = 0, modificar = 0;
		do {
			System.out.println("Que quieres modificar: ");
			System.out.println("1- Nombre");
			System.out.println("2- Calle");
			System.out.println("3- Numero edificio");
			System.out.println("4- Ciudad");
			System.out.println("5- Codigo postal");
			System.out.println("6- Movil");
			System.out.println("7- Telefono");
			System.out.println("8- Salir");
			System.out.print("Elige una opcion: ");
			menu = input.nextInt();
			input.nextLine();
			switch (menu) {

			case 1:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar Nombre: ");
				modificacion = input.nextLine();

				consulta = "UPDATE restaurantes SET restaurantes.nombre = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 2:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar Calle: ");
				modificacion = input.nextLine();

				consulta = "UPDATE restaurantes SET restaurantes.calle = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 3:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar Numero edificio: ";
				String numeroS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroS);
				input.nextLine();

				consulta = "UPDATE restaurantes SET restaurantes.numero = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 4:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar Ciudad: ");
				modificacion = input.nextLine();

				consulta = "UPDATE restaurantes SET restaurantes.ciudad = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				input.nextLine();
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 5:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar Codigo postal: ";
				String codigoS = validarNumero(input, descripcion);
				modificar  = insertarNumero(codigoS);
				input.nextLine();
				
				consulta = "UPDATE restaurantes SET restaurantes.código = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 6:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				
				descripcion = "\nModificar Movil: ";
				String movilS = validarNumero(input, descripcion);
				modificar  = insertarNumero(movilS);
				input.nextLine();

				consulta = "UPDATE restaurantes SET restaurantes.móvil = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 7:
				System.out.print("Ingresa el nombre del restaurante que quieres buscar: ");
				ubicacion = input.nextLine();
				
				
				descripcion = "\nModificar Telefono: ";
				String telefonoS = validarNumero(input, descripcion);
				modificar = insertarNumero(telefonoS);
				input.nextLine();

				consulta = "UPDATE restaurantes SET restaurantes.telefono = ? WHERE restaurantes.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;

			default:
				System.out.println("Opcion incorrecta");
				break;
			}

		} while (menu != 8);

	}

	static void actualizarDatosRiders(Connection conexion, Scanner input) throws SQLException {
		String consulta = new String();
		String ubicacion = new String();
		String modificacion = new String();

		String descripcion = new String();
		int menu = 0, filas = 0, modificar = 0;

		do {
			System.out.println("Que quieres modificar: ");
			System.out.println("1- Nombre");
			System.out.println("2- Apellido");
			System.out.println("3- Identificación");
			System.out.println("4- Calle");
			System.out.println("5- Numero de piso");
			System.out.println("6- Ciudad");
			System.out.println("7- Código postal");
			System.out.println("8- Móvil");
			System.out.println("9- Teléfono");
			System.out.println("10- salir");
			System.out.print("Elige una opcion: ");
			menu = input.nextInt();
			input.nextLine();
			switch (menu) {
			case 1:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar nombre: ");
				modificacion = input.nextLine();

				consulta = "UPDATE riders SET riders.nombre = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 2:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar apellido: ");
				modificacion = input.nextLine();

				consulta = "UPDATE riders SET riders.apellidos = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 3:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar identificación: ");
				modificacion = input.nextLine();

				consulta = "UPDATE riders SET riders.identificación = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
			
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 4:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				input.nextLine();
				System.out.print("\nModificar calle: ");
				modificacion = input.nextLine();

				consulta = "UPDATE riders SET riders.calle = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 5:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar numero de piso: ";
				String numeroS = validarNumero(input, descripcion);
				modificar = insertarNumero(numeroS);
				input.nextLine();
          
				consulta = "UPDATE riders SET riders.numero = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 6:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				System.out.print("\nModificar ciudad: ");
				modificacion = input.nextLine();

				consulta = "UPDATE riders SET riders.ciudad = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablas(conexion, consulta, ubicacion, modificacion);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 7:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar codigo postal: ";
				String codigoS = validarNumero(input, descripcion);
				modificar = insertarNumero(codigoS);
				

				consulta = "UPDATE riders SET riders.código = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 8:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar movil: ";
				String movilS = validarNumero(input, descripcion);
				modificar = insertarNumero(movilS);

				consulta = "UPDATE riders SET riders.móvil = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			case 9:
				System.out.print("Ingresa el nombre de la persona que quieres buscar: ");
				ubicacion = input.nextLine();
				
				descripcion = "\nModificar teléfono: ";
				String telefono = Metodos.validarNumero(input, descripcion);
				modificar = Metodos.insertarNumero(telefono);
				input.nextLine();
				
				consulta = "UPDATE riders SET riders.teléfono = ? WHERE riders.nombre LIKE ?";
				filas = Metodos.actualizarTablasInt(conexion, consulta, ubicacion, modificar);
				
				System.out.printf("se han modificados %d filas%n", filas);
				System.out.println();
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
			}


		} while (menu != 10);

	}

	static String validarNumero(Scanner input, String descripcion) {

		boolean detener = false;
		String esNumero = new String();
		do {
			System.out.print(descripcion);
			String numero = input.nextLine();

			if (numero.matches("^[0-9]+$")) {
				esNumero += numero;
				detener = true;
			} else {
				detener = false;
			}
		} while (!detener);

		return esNumero;
	}

	static int insertarNumero(String numero) {
		int esNumero = 0;
		if (numero.matches("^[0-9]+$")) {
			esNumero = Integer.parseInt(numero);
		}

		return esNumero;
	}

	static boolean actualizarEstado(Scanner input) {
		boolean validado = false;
		char validar = ' ';
		do {
			System.out.println("afirma con un [S] como pedido finalizado");
			System.out.println("afirma con una [N] como pedido no finalizado");
			System.out.print("Elige una opcion: ");
			validar = input.nextLine().toLowerCase().charAt(0);
			
			if(validar == 's') {
				validado = true;
			}else if(validar == 'n') {
				validado = false;
			}else {
				System.out.println("Caracter incorrecto por favor elige [S|N]");
			}
			
		} while (validar != 's' && validar != 'n');
		
		return validado;
	}

}
