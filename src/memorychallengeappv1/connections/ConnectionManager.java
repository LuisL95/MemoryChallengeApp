/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorychallengeappv1.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ser
 */
public class ConnectionManager 
{
    
        private static final String USERNAME = "Luis";
	private static final String PASSWORD = "1234";
	
	private static final String M_CONN_STRING =
			"jdbc:mysql://localhost/db_memorychallengeapp";

	public static Connection getConnection() throws SQLException 
                {
		return (Connection) DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
                
		}
		
	public static void processException(SQLException e)
        {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}
        
        
}
