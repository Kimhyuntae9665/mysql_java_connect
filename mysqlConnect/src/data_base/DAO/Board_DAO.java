package data_base.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import data_base.Database_Connector;
import data_base.Entity.BoardEntity;

// DAO : Data Access Object �� ���Ӹ� 
// ������ ���̽��� �����ؼ� ������ �˻� �� ���� ���� �����ͺ��̽� �۾��� ����ϴ� Ŭ���� 
public class Board_DAO {
	
	//�����ͺ��̽��� Board ���̺��� ��� �÷��� �����ؼ� ��ȯ�ϴ� �޼��� 
	//	SQL : SELECT * FROM Board;
	//  -����Ǵ� ��ȯ ����� 0�� �̻��� ������ ���ڵ� 
	public List<BoardEntity> find(){
		//list �� interface  list�� ������ class�� ��ü�ؾ��Ѵ�  arrayList, LinkedList 
		List<BoardEntity> result = new ArrayList<BoardEntity>();
		
		
//		����ΰ� ���� �ö󰡰� ��ġ 
		final String SQL = "SELECT * FROM Board";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
//			try-catch ������ ������ 
//			try �� ������ ���������� ���� �Ǵϱ� �ۿ��� ���� ������ �������� finally ������ ���� �� �ִ� 
			 connection = Database_Connector.createConnection();
//			�� try-catch������ ������ 
			 statement =  connection.createStatement();
			 
			 resultSet = statement.executeQuery(SQL);
			 
			 while(resultSet.next()) {
				 
			 } 
			 
			 
			 
			 resultSet = null;
			
		}catch(Exception exception) {
			
		}finally {
			
		}
		

		
		return result;
	} 
	

}
