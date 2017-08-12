package article.service;

import java.util.Map;

import article.model.Writer;

public class WriteRequest {
	//게시글 쓰는데 필요한 작성자, 제목, 내용데이터 정의
	private Writer writer;
	private String title;
	private String content;
	
	public WriteRequest(Writer writer, String title , String content){
		this.writer=writer;
		this.title=title;
		this.content=content;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	//데이터가 유효한지 여부를 검사.잘못된 데이터가 존재하면 errors 맵 객체에 관련 코드를 추가
	public void validate(Map<String,Boolean> errors){
		if(title==null || title.trim().isEmpty())
			errors.put("title",Boolean.TRUE);
	}
}
