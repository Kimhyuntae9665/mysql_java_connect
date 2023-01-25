package index;

import java.util.List;

import data_base.DAO.Board_DAO;
import data_base.Entity.BoardEntity;

public class MainApplication {
	
	

	public static void main(String[] args) {
//		DAO 인스턴스 생성 
		Board_DAO dao = new Board_DAO();
//		데이터베이스에서 Board 테이블의 전체 레코드를 검색해서 출력 
		
		List<BoardEntity> findResult = dao.find(); //Board_DAO에서 만든 함수 find()
		
		for(BoardEntity entity:findResult) {
//			toString()이라도 return 값이 아무리 문자열 이라도 sop 출력문안에 입력해야 한다 
			System.out.println(entity.toString());
		}
	}

}
