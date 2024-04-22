package java_ddbb;

import java.util.Enumeration;
import java.util.Hashtable;

public class gestionEmployees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*OBJETIVO: listar los empleados de la BBDD*/
		
		/* 1: CONECTARNOS A LA BBDD */
		
		String database ="3";
		
		//pasamos a int para el case
		int intDatabase = Integer.valueOf(database).intValue();
		
		/* creamos una instancia de conexion y nos conectamos */
		DBF dbf= new DBF();
		dbf.selectDB(intDatabase);
		
		/* 2: Recogemos los datos de la BBDD */
		
		Hashtable ListaEmployees = dbf.getListaEmployees();
		Employee employee;
		
		/* 3: Recorremos la tabla para mostrar los datos */
		
		Enumeration enumeration = ListaEmployees.elements();
        while (enumeration.hasMoreElements()){
          employee = ((Employee) enumeration.nextElement());
          int employee_id = employee.getEmployee_id();
          System.out.println("employee_id " + employee.getEmployee_id());
          
}//while listado
		
		
		
		
		
	}

}
