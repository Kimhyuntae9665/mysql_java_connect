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
	
	
//	�����ͺ��̽����� Board ���̺� ���ڵ带 ���� 
//	SQL : INSERT INTO Board (boardTitle,boardContent,boardDateTime,boardWriter)
//	VALUES(?,?,?,?); �Է°��� ���� ������ ��� �ٲ� �� �̱� ������ 
//	����Ǵ� ��ȯ ���� : 0 or 1�� ��ȯ 
//	dto ��� �ʵ尡 �Ű������� 1���̻��϶� 
//	DTO ��� 
	
	
	public int insert(insertBoardDTO dto) {
		int result = 0;
		
		final String SQL = "INSERT INTO Board (boardTitle,boardContent,boardDateTime,boardWriter) VALUES(?,?,?,?)";
		
		Connection connection = null;
//		PreparedStatement : �������� SQL���� ���� ������ �� �ֵ��� �� 
		PreparedStatement preparedStatement = null;
		
		SimpleDateFormat simpleDateFormat = 
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			connection = Database_Connector.createConnection();
//			PreparedDtatement ��ü ���� 
			preparedStatement = connection.prepareStatement(SQL);
//			INSERT INTO Board (boardTitle,boardContent,boardDateTime,boardWriter) VALUES(?,?,?,?)
			preparedStatement.setString(1, dto.getBoardTitle());
			preparedStatement.setString(2, dto.getBoardContent());
//			java.util�� �ִ� new Date() ��� 
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
	
	
//	������ ���̽����� Board���̺��� ���ڵ� �� �Է¹��� board id�� �ش��ϴ� ���ڵ��� 
//	title�� content�� �Է¹��� ������ ���� 
	
//	SQL : UPDATE Board SET boardTitle = ? , boardContent = ? 
//		WHERE id = ?;
//	����Ǵ� ��ȯ �� : 0 or 1 

	public int update(UpdateBoardDTO dto) {  //layer �� layer ���� ���� ��ȯ�� DTO�� �Ѵ� 
		
		
		
		int result =0;
		
		
		final String SQL = "UPDATE Board SET boardTitle = ? , boardContent = ?  WHERE id = ?";
		
		Connection connection = null;
//		preparedStatement �Ἥ ? ���� ó�� 
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
	
//	������ ���̽����� Board ���̺� �� �Է¹��� id�� �ش��ϴ� ���ڵ带 ���� 
//	SQL: DELETE FROM Board WHERE id = ?
//	���� ��ȯ ���� : 0 or 1 
//	where id =1 �̸� dto�� 1�� �� dto �Ⱦ��� ������ ���ؿ� �߰� �� ���ɼ��� �ֱ� ������ dto ��� 
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
