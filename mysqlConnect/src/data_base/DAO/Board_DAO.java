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
				 Integer id = resultSet.getInt(1);
				 String boardTitle = resultSet.getString(2);
				 String boardContent = resultSet.getString(3);
				 String boardDateTime = resultSet.getString(4);
				 Integer boardLike = resultSet.getInt(5);
				 Integer boardWriter = resultSet.getInt(6);
				 
				 BoardEntity entity = new BoardEntity(id, boardTitle, boardContent, boardDateTime, boardLike, boardWriter);
//				 entity.toString();
				 result.add(entity);
			 } 
			 
			 
			 
			 resultSet = null;
			
		}catch(Exception exception) {
			
		}finally {
			try {
				if(connection!=null && !connection.isClosed())
					connection.close();
				if(statement !=null && !statement.isClosed())
					statement.close();
				if(resultSet !=null && !resultSet.isClosed())
					resultSet.close();
				
				
			}catch(Exception exception) {
				exception.printStackTrace();
			}
			
		}
		

		
		return result;
	} 
	

}
