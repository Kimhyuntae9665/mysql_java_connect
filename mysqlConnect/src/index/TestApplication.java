package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import data_base.Database_Connector;

public class TestApplication {

	//mySQL Connector �� ����� Connection ��ü�� ���� 
		private static Connection connection = null;
		//MySQL ������ �ۼ��� Statement ��ü�� ���� 
		private static Statement statement = null;
		// MySQL ���� ���� ����� ���� ResultSet ��ü�� ���� 
		private static ResultSet resultSet = null;

		public static void main(String[] args) {
			
			Connection connection = null;
			
			try {
				//1. Mysql connector Driver �ε� 
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//2.MySQL Driver�� Connection ��ü ���� 
				//������ ���̽� URL,������ ���̽� ����� �̸�(ID), ������ ���̽� ����� �н����� 
				final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/peed"; 
				final String USER_NAME = "root";
				final String USER_PASSWORD = "1234"; //root
				
				connection = DriverManager.getConnection(DATABASE_URL,USER_NAME,USER_PASSWORD);
				
				 
				
				System.out.println("Database Connection Success!");
				
				// Statement ��ü�� ����ؼ� query ���� 
//				Connection ��ü�� �̿��ؼ� Statement ��ü ���� 
				statement = connection.createStatement(); 
				//Statement ��ü�� ����� SQL �� �ۼ� 
				final String SQL = "SELECT * FROM Board";
				
				//SQL ���� Statement ��ü�� ��Ƽ� ���� 
				resultSet = statement.executeQuery(SQL);
				
				while(resultSet.next()) {
					Integer id = resultSet.getInt(1);
					String boardTitle = resultSet.getString(2);
					String boardContent = resultSet.getString(3);
					
					System.out.println("id: "+ id +" / boardTitle: "+boardTitle + "/boardContent: "+ boardContent);
				}
				
			}catch(Exception exception) {
				exception.printStackTrace();
				System.out.println("Database Connection Fail!");
				
			}finally {
				//3. ����� Connection ��ü�� Close
				
				try {
					if(connection !=null && !connection.isClosed())
						connection.close();
				}catch(Exception exception) {
					exception.printStackTrace();
					
				}
			}
			
		}
}
