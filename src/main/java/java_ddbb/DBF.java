package java_ddbb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

public class DBF {

	private static DBF instance;
	private String url;
	private Connection konexioa;
	private Statement statement;
		
	/* gestion de multi instancias*/
	
	  public static DBF instance()
	  {
	  	if (instance == null)
	  		instance = new DBF();    
	  	return instance;
	  }
	  
	// estructura multi base de datos 
	public void selectDB(int database)
	  {
	  try
		{  				 
		 switch (database) {
	           
	             case 1://mysql              
	              //url = "jdbc:mysql://IP/esquemaBBDD?user=username&password=password";  ejemplo
	              url = "jdbc:mysql://172.16.6.207/inesmecgestion?user=inesmec&password=inesmec2020*"; 
	              Class.forName("com.mysql.jdbc.Driver").newInstance();
	              konexioa = DriverManager.getConnection(url);	              
	              statement = konexioa.createStatement();
	              System.out.println("Mysql connected");
	                     break;
	                     
	             case 2:  //access
	              url = "jdbc:odbc:KBPACC";
	              Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	              konexioa = DriverManager.getConnection(url, "", "");
	              statement = konexioa.createStatement();
	              System.out.println("access connected");
	                     break;
	       
	            case 3://oracle	            	
	              
	            	//URL of Oracle database server
	                String url = "jdbc:oracle:thin:@192.168.100.10:49161:xe";       
	                Properties props = new Properties();
	                props.setProperty("user", "system");
	                props.setProperty("password", "oracle");
	              
	                //creating connection to Oracle database 
	              
	                konexioa = DriverManager.getConnection(url,props);
	                statement = konexioa.createStatement();

	                System.out.println("Se ha conectado a la BBDD-Oracle satisfactoriamente");	   
	                     break;
	                     
	                     
	            case 4: //sqlserver
	              url = "jdbc:odbc:KBPACC";
	              Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	              konexioa = DriverManager.getConnection(url, "", "");
	              statement = konexioa.createStatement();
	              System.out.println("Sqlserver connected");
	                     break;
	            
	        }  	
		}
		catch (SQLException anException)
		{
			while (anException != null)
			{
				System.out.println("SQL Exception:  " + anException.getMessage());
				anException = anException.getNextException();
			}
		}
		catch (java.lang.Exception anException)
		{
			anException.printStackTrace();
		}
	  }
	
	//@SuppressWarnings("rawtypes")
	public Hashtable getListaEmployees()
	{
		
		String query;
		Hashtable ListaEmployees;
		ResultSet resultSet;
				
	                
		query = "SELECT * FROM EMPLOYEES ORDER BY EMPLOYEE_ID ASC";
		System.out.println("SQL a ejecutar: " + query);
		ListaEmployees = new Hashtable();
	    Employee employee;
	    
		try
		{
			// Run SQL
	        resultSet = statement.executeQuery(query);    
			//	vamos a gestionar los resultados de la query
	    
	    int lineas=0;
			while (resultSet.next())
		{     
				int employee_id = resultSet.getInt(1);
				String first_name = resultSet.getString(2);     
				String last_name = resultSet.getString(3);
				String email = resultSet.getString(4);
				String phone_number = resultSet.getString(5);           
				Date hire_date = resultSet.getDate(6);
				String job_id = resultSet.getString(7);
				float salary = resultSet.getFloat(8);
				float commision_pct = resultSet.getFloat(9);
				int manager_id = resultSet.getInt(10);
				int department_id = resultSet.getInt(11);
	 
	      
				System.out.println("employee : " + "first_name = " + first_name );
			  System.out.println("Select usuario Result: " + "employee_id = " + employee_id );
	  
			  employee = new Employee (employee_id,first_name, last_name, email, phone_number, hire_date, job_id, salary, commision_pct, manager_id, department_id);        
			  ListaEmployees.put(new Integer(employee_id), employee);  
	      
	          
			  lineas++;
			}
	   
			System.out.println("lineas :"+lineas);    
	     
			resultSet.close();
	    //konexioa.close();
	    
	  }
	      
		catch (SQLException anException)
		{
			while (anException != null)
			{
				System.out.println("SQL Exception:  " + anException.getMessage());
				anException = anException.getNextException();
			}
		}
		catch (java.lang.Exception anException)
		{
	    System.out.println("DBF: "+ anException.getMessage());
		  anException.printStackTrace();
		}
		finally
		{
			return ListaEmployees;
		}
	}

	
	
} // end class
