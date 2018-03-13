package qna;

import java.util.List;



public class QnaService implements QnaServiceImpl {

	private static QnaService qnaService = new QnaService();
	
	public QnaBbsDaoImpl qnadao;	
	
	private QnaService() {
		qnadao = new QnaBbsDao();		
	}	
	public static QnaService getInstance() {
		return qnaService;
	}
	
	
	
	/*@Override
	public List<QnaDto> getQnaPagingList(PagingBean paging, String searchWord, int search) {
		return qnadao.getQnaPagingList(paging, searchWord, search);
	}*/
	@Override
	public List<QnaDto> getQnaList() {
		return qnadao.getQnaList();
	}
	@Override
	public boolean writeQnaBbs(QnaDto dto) {		
		return qnadao.writeQnaBbs(dto);
	}
	@Override
	public List<QnaDto> getBbsPagingList(PagingBean paging) {		
		return qnadao.getBbsPagingList(paging);
	}
	
	
	
}
