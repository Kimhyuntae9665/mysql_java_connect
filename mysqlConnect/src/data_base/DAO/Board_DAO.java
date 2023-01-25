package data_base.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import data_base.Database_Connector;
import data_base.Entity.BoardEntity;

// DAO : Data Access Object 의 줄임말 
// 데이터 베이스에 접근해서 데이터 검색 및 삽입 등의 데이터베이스 작업을 담당하는 클래스 
public class Board_DAO {
	
	//데이터베이스의 Board 테이블에서 모든 컬럼을 선택해서 반환하는 메서드 
	//	SQL : SELECT * FROM Board;
	//  -예상되는 반환 결과가 0개 이상의 복수의 레코드 
	public List<BoardEntity> find(){
		//list 는 interface  list를 구현한 class로 객체해야한다  arrayList, LinkedList 
		List<BoardEntity> result = new ArrayList<BoardEntity>();
		
		
//		선언부가 위로 올라가게 배치 
		final String SQL = "SELECT * FROM Board";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
//			try-catch 문으로 잡아줘야 
//			try 문 때문에 지역변수로 한정 되니까 밖에서 전역 변수로 만들어줘야 finally 문에서 닫을 수 있다 
			 connection = Database_Connector.createConnection();
//			꼭 try-catch문으로 잡아줘야 
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
