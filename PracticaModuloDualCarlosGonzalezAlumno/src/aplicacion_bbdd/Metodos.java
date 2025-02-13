package aplicacion_bbdd;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Type;

public class Metodos {

	static ResultSet mostrarUnaTabla(String datos, Connection conexion) throws SQLException {
		String consulta = "SELECT * FROM " + datos;
		PreparedStatement ps = conexion.prepareStatement(consulta);
		ResultSet res = ps.executeQuery();
		return res;
	}

	static ResultSetMetaData mostrarUnaTabla(ResultSet res) throws SQLException {
		ResultSetMetaData rmd = res.getMetaData();
		return rmd;
	}

	static ResultSet mostrarDosTabla(String consulta, Connection conexion) throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);
		ResultSet res = ps.executeQuery();
		return res;
	}

	static ResultSetMetaData mostrarDosTabla(ResultSet res) throws SQLException {
		ResultSetMetaData rmd = res.getMetaData();
		return rmd;
	}

	static void msqlFilas(ResultSetMetaData rmd) throws SQLException {

		int columnas = rmd.getColumnCount();
		int contador = 1;
		while (contador <= columnas) {
			System.out.printf("%4s ", rmd.getColumnName(contador));
			contador++;
		}
	}

	static void mostrar1Tabla(Connection conexion, String consulta) throws SQLException {
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

	static void mostrar2Tablas(Connection conexion, String consulta) throws SQLException {
		PreparedStatement ps = conexion.prepareStatement(consulta);
		ResultSet res = ps.executeQuery();
		ResultSetMetaData rmd = res.getMetaData();
		int numFilas = rmd.getColumnCount();
		
		System.out.println(res.getString(1));
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

//		System.out.print("id_usuario: ");
//		int id = input.nextInt();
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

}
