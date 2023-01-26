package index;

import java.util.List;
import java.util.Scanner;

import data_base.DAO.Board_DAO;
import data_base.Entity.BoardEntity;
import dto.DeleteBoardDTO;
import dto.UpdateBoardDTO;
import dto.insertBoardDTO;

public class MainApplication {
	
	

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		Board_DAO board_DAO = new Board_DAO();
		
		if(path.equals("POST /board")) {
			System.out.println("boardTitle : ");
			String boardTitle = scanner.nextLine();
			System.out.println("boardContent : ");
			String boardContent = scanner.nextLine();
			System.out.println("boardWriter : ");
			Integer boardWriter = scanner.nextInt();
			
//			인스턴스 생성(사용자로부터 입력받아)
			insertBoardDTO insertBoardDto = new insertBoardDTO(boardTitle, boardContent, boardWriter);
			
			int result = board_DAO.insert(insertBoardDto);
			if(result==1) {
				System.out.println("Insert Succcess");
			}
			
			
		}
		
		else if(path.equals("DELETE /board")) {
			System.out.println("id: ");
			Integer id = scanner.nextInt();
			
			DeleteBoardDTO deleteBoardDto = new DeleteBoardDTO(id);
			
			 int result = board_DAO.delete(deleteBoardDto);
			
			if(result ==1) System.out.println("Delete Sucess");
			
			else System.out.println("Delete Failed");
		}
		
		else if(path.equals("GET /boardList")) {
			
			List<BoardEntity> boardList = board_DAO.find();
			for(BoardEntity board:boardList)
				System.out.println(board.toString());
		}
		
		else if(path.equals("PATCH /board")) {
			System.out.println("boardTitle : ");
			String boardTitle = scanner.nextLine();
			System.out.println("boardContent : ");
			String boardContent = scanner.nextLine();
			System.out.println("id : ");
			Integer id = scanner.nextInt();
			
			
			UpdateBoardDTO updateBoardDto = new UpdateBoardDTO( boardContent,boardTitle,id);
			
			int result = board_DAO.update(updateBoardDto);
			
			if(result==1) System.out.println("Update Success");
			
			else System.out.println("Update Failed");
		}
		
		else {
			System.out.println("404 Not found");
		}

	}

}
