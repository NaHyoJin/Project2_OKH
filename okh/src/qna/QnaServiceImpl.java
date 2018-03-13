package qna;

import java.io.Serializable;
import java.util.List;




public interface QnaServiceImpl extends Serializable {

	public List<QnaDto> getQnaList();
	public boolean writeQnaBbs(QnaDto dto);
//	public List<QnaDto> getQnaPagingList(PagingBean paging, String searchWord, int search);
	public List<QnaDto> getBbsPagingList(PagingBean paging);
}
