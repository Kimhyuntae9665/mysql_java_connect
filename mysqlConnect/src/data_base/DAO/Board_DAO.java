package data_base.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import data_base.Database_Connector;
import data_base.Entity.BoardEntity;
import dto.DeleteBoardDTO;
import dto.UpdateBoardDTO;
import dto.insertBoardDTO;

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
	
	
//	데이터베이스에서 Board 테이블에 레코드를 삽입 
//	SQL : INSERT INTO Board (boardTitle,boardContent,boardDateTime,boardWriter)
//	VALUES(?,?,?,?); 입력값이 변수 값으로 계속 바뀔 것 이기 때문에 
//	예상되는 반환 값은 : 0 or 1이 반환 
//	dto 사용 필드가 매개변수가 1개이상일때 
//	DTO 사용 
	
	
	public int insert(insertBoardDTO dto) {
		int result = 0;
		
		final String SQL = "INSERT INTO Board (boardTitle,boardContent,boardDateTime,boardWriter) VALUES(?,?,?,?)";
		
		Connection connection = null;
//		PreparedStatement : 동적으로 SQL문의 값을 지정할 수 있도록 함 
		PreparedStatement preparedStatement = null;
		
		SimpleDateFormat simpleDateFormat = 
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			connection = Database_Connector.createConnection();
//			PreparedDtatement 객체 생성 
			preparedStatement = connection.prepareStatement(SQL);
//			INSERT INTO Board (boardTitle,boardContent,boardDateTime,boardWriter) VALUES(?,?,?,?)
			preparedStatement.setString(1, dto.getBoardTitle());
			preparedStatement.setString(2, dto.getBoardContent());
//			java.util에 있는 new Date() 사용 
			preparedStatement.setString(3, simpleDateFormat.format(new Date()));
			preparedStatement.setInt(4, dto.getBoardWriter());
			
			result = preparedStatement.executeUpdate();
			
		} catch (Exception exception) {
			exception.printStackTrace();
			
		}finally {
			try {
				if(preparedStatement !=null && preparedStatement.isClosed()) {
					 preparedStatement.close();
				}
				if(connection !=null && connection.isClosed()) {
					 connection.close();
				}
			}
			catch(Exception exception2) {
				exception2.printStackTrace();
			}
		}
		
		return result;
	}
	
	
//	데이터 베이스에서 Board테이블의 레코드 중 입력받은 board id에 해당하는 레코드의 
//	title과 content를 입력받은 값으로 수정 
	
//	SQL : UPDATE Board SET boardTitle = ? , boardContent = ? 
//		WHERE id = ?;
//	예상되는 반환 값 : 0 or 1 

	public int update(UpdateBoardDTO dto) {  //layer 와 layer 간에 정보 교환은 DTO로 한다 
		
		
		
		int result =0;
		
		
		final String SQL = "UPDATE Board SET boardTitle = ? , boardContent = ?  WHERE id = ?";
		
		Connection connection = null;
//		preparedStatement 써서 ? 구문 처리 
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = Database_Connector.createConnection();
			preparedStatement = connection.prepareStatement(SQL);
			
//			UPDATE Board SET boardTitle = ? , boardContent = ? 
//			WHERE id = ?;
			preparedStatement.setString(1, dto.getBoardTitle());
			preparedStatement.setString(2, dto.getBoardContent());
			preparedStatement.setInt(3, dto.getId());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if(connection !=null && !connection.isClosed()) {
					connection.close();
				}
			}catch(Exception exception) {
				
			}
		}
		
		
		return result;
	}
	
//	데이터 베이스에서 Board 테이블 중 입력받은 id에 해당하는 레코드를 삭제 
//	SQL: DELETE FROM Board WHERE id = ?
//	예상 반환 값은 : 0 or 1 
//	where id =1 이면 dto가 1개 라서 dto 안쓰고 싶지만 나붕에 추가 될 가능성도 있기 때문에 dto 사용 
	public int delete( DeleteBoardDTO dto) {
		
		
		int result = 0;
		
		final String SQL = "DELETE FROM Board WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = Database_Connector.createConnection();
			preparedStatement = connection.prepareStatement(SQL);
//			DELETE FROM Board WHERE id = ?
			preparedStatement.setInt(1, dto.getId());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed())
					preparedStatement.close();
				if(connection !=null && !connection.isClosed())
					connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		return result;
	}

}
