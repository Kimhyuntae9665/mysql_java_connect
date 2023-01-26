package dto;

public class UpdateBoardDTO {
	

	private String boardTitle;
	private String boardContent;
	private Integer id;
	
	
	public UpdateBoardDTO(String boardTitle, String boardContent, Integer id) {
		
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.id = id;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "UpdateBoardDTO [boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", id=" + id + "]";
	}
	
	
	

}
