package qna.dao;

import java.util.List;

import qna.dto.QnaDto;

public interface qnaBbsDaoImpl {

	public List<QnaDto> getQnaList();
	
	public boolean writeQnaBbs(QnaDto dto);
	
	
}
