package qna;

import java.util.List;

public interface QnaAnswerDaoImpl {

	public boolean writeAnswer(QnaDto dto, int seq);
	
	public List<QnaAnswerDto> getCommentList(int seq);
	
	public int getSeq();
	
}
