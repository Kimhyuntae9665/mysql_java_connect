package index;

import java.util.List;

import data_base.DAO.Board_DAO;
import data_base.Entity.BoardEntity;

public class MainApplication {
	
	

	public static void main(String[] args) {
//		DAO �ν��Ͻ� ���� 
		Board_DAO dao = new Board_DAO();
//		�����ͺ��̽����� Board ���̺��� ��ü ���ڵ带 �˻��ؼ� ��� 
		
		List<BoardEntity> findResult = dao.find(); //Board_DAO���� ���� �Լ� find()
		
		for(BoardEntity entity:findResult) {
//			toString()�̶� return ���� �ƹ��� ���ڿ� �̶� sop ��¹��ȿ� �Է��ؾ� �Ѵ� 
			System.out.println(entity.toString());
		}
	}

}
