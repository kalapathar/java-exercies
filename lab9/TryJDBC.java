
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.lang.*;

public class TryJDBC{
	public static void main(String[] args){
		Connection con=null;
		String user=null;
		String password=null;
		
		try{
			ResourceBundle bundle=ResourceBundle.getBundle("javaconfig");
			user=bundle.getString("jdbc.user");
			password=bundle.getString("jdbc.password");
			String url = "jdbc:postgresql://shelob.cs.stolaf.edu:5432/mca i18";
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("ClassNotFoundException");
		}catch (SQLException e){
			e.printStackTrace();
			System.out.println("SQLException");
		}
		//System.out.println(user);
		//System.out.println(password);
	// Part 3
	Statement st;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	int rowCounter = 0;
	String cat = "";
	String str;
	int padding;
	String columnNames = "";
	int columnsNumber = 0;

	try {
	  st = con.createStatement();
	  //Part 4
	  st.executeUpdate("SET search_path =" + user + ",public;");
	   //Part 5
	  // Have to comment out because the unique constraint will cause an SQLException
	  // st.executeUpdate("INSERT INTO patron (pid, name, phone) VALUES (10, 'Nathan', '1234');");

	  // Part 7
	  rs = st.executeQuery("SELECT * FROM patron");
	  rsmd = rs.getMetaData();
	  columnsNumber = rsmd.getColumnCount();
	  for (int z = 1; z <= columnsNumber; z++) {
	  padding = 15;
		columnNames = columnNames +  rsmd.getColumnName(z);
		for (int f = 0; f < padding-rsmd.getColumnName(z).length(); f++ ) {
		  columnNames = columnNames + " ";
		}
	  }
	  padding = 15;
	  while(rs.next()) {
		for (int i = 1; i <= columnsNumber; i++) {
		  // if (i > 1) System.out.print(", ");
		  if (i > 1) {
			cat = cat +  ", ";
			for (int yolo = 0; yolo < padding-rs.getString(i).length(); yolo++) {
			  cat = cat + " ";
			}
		  }
		  String columnValue = rs.getString(i);
		  // System.out.print(columnValue + " " + rsmd.getColumnName(i));
		  cat = cat + columnValue;
		}
		// System.out.println("");
		cat = cat + "\n";
		rowCounter++;
	  }
	  // Part 8
	  // Part 9
	  System.out.println(rowCounter + "\n");
	  System.out.println(columnNames);
	  System.out.println(cat);

	  // Part 10




		Scanner input = new Scanner(System.in);
		System.out.println("Input malicious query (e.g. name, xphone)"); 

		while (!(str = input.next()).equals("exit")) {
		  rowCounter = 0;
		  cat = "";
		  columnNames = "";
		  columnsNumber = 0;


		  // System.out.println(str);
		  rs = st.executeQuery("select name, phone from patron where name ~* '" + str + "' order by name;");
		  rsmd = rs.getMetaData();
		  columnsNumber = rsmd.getColumnCount();
		  for (int z = 1; z <= columnsNumber; z++) {
			padding = 15;
			columnNames = columnNames +  rsmd.getColumnName(z);
			for (int f = 0; f < padding-rsmd.getColumnName(z).length(); f++ ) {
			  columnNames = columnNames + " ";
			}
		  }
		  rowCounter = 0;
		  padding = 15;
		  while(rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
			  // if (i > 1) System.out.print(", ");
			  if (i > 1) {
				cat = cat +  ", ";
				for (int yolo = 0; yolo < padding-rs.getString(i).length(); yolo++) {
				  cat = cat + " ";
				}
			  }
			  String columnValue = rs.getString(i);
			  cat = cat + columnValue;
			}
			cat = cat + "\n";
			rowCounter++;
		  }
		  System.out.println(rowCounter + "\n");
		  System.out.println(columnNames);
		  System.out.println(cat);

		}

	  

	  
	} catch (SQLException e) {
	  e.printStackTrace();
	}

  }
}
