package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class General 
{
	protected Connection connector;
	private final String host = "localhost";
	private final String port = "3306";
	private final String database = "repoIo";
	private final String user = "repoIo";
	private final String password = "repoIoPass";
	
	public General() 
	{
		System.out.println("General constructor");
	}
	
	public void openConnection() throws ClassNotFoundException, SQLException
    {
		Class.forName("org.mariadb.jdbc.Driver");
        this.connector = DriverManager.getConnection(
                "jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.database,
                this.user,
                this.password
        );
    }
	
	public void close() throws SQLException
    {
        this.connector.close();
    }
	
	
	/**
	 * Hace una consulta para obtener un conjunto de datos del servidor de base de datos
	 * @param sql 
	 * @return ArrayList<String[]> donde la primera fila siempre es un array con el nombre de las columnas
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<String[]> getBySQL(String sql) throws SQLException, ClassNotFoundException
    {
        System.out.println(sql);
        openConnection();
       
        PreparedStatement stament = this.connector.prepareStatement(sql);
        ResultSet resultSet = stament.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        
        int columnsCount = metaData.getColumnCount();
        String columns[] = new String[columnsCount];
        ArrayList<String[]> rows = new ArrayList<String[]>();
        
        for (int i = 1; i <= columnsCount; i++) 
            columns[i - 1] = metaData.getColumnLabel(i);	//En metadata se empieza en 1
        
        //La primer fila siempre trae el nombre de las columnas
        rows.add(columns);
        
        while (resultSet.next()) 
        {
            String temp[] = new String[columnsCount];
            for (int i = 1; i <= columnsCount; i++) 
                temp[i -1] = resultSet.getString(metaData.getColumnLabel(i));
            
            rows.add(temp);
        }

        resultSet.close();
        close();
        
        return rows;
    }
}