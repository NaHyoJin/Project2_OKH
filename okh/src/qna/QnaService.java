package qna;

import java.util.List;



public class QnaService implements QnaServiceImpl {

	
	

	private static QnaService qnaService = new QnaService();	
	
	public QnaBbsDaoImpl qnadao;	
//	public QnaAnswerDaoImpl qnaanswerdao;
	public LikeDaoImpl likedao;
	public QnaPdsDaoImpl qnapdsdao;
	
	private QnaService() {
		qnadao = new QnaBbsDao();		
	//	qnaanswerdao = new QnaAnswerDao();
		likedao = new LikeDao();
		qnapdsdao = new QnaPdsDao();
	}	
	public static QnaService getInstance() {
		return qnaService;
	}	
	
	
	@Override
	public List<QnaDto> getQnaPagingList(PagingBean paging, String searchWord, int search) {
		return qnadao.getQnaPagingList(paging, searchWord, search);
	}
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
	@Override
	public QnaDto getBbs(int seq) {
		return qnadao.getBbs(seq);
	}
	@Override
	public String RemoveHTMLTag(String changeStr) {
		return qnadao.RemoveHTMLTag(changeStr);
	}
	@Override
	public boolean qnaupdate(QnaDto dto) {
		return qnadao.qnaupdate(dto);
	}
	@Override
	public boolean answer(int seq, QnaDto dto) {
		return qnadao.answer(seq, dto);
	}
	@Override
	public String[] getTagName(String tagname) {		
		return qnadao.getTagName(tagname);
	}
	@Override
	public boolean update(int seq, String title, String content) {
		return qnadao.update(seq, title, content);
	}	
	@Override
	public boolean repAlldelete(int seq) {
		return qnadao.repAlldelete(seq);
	}
	@Override
	public List<QnaDto> getqnaBbssortPagingList(PagingBean paging, String whatsort) {
		return qnadao.getqnaBbssortPagingList(paging, whatsort);
	}

	@Override
	public boolean checkcomment(int seq) {		
		return qnadao.checkcomment(seq);
	}

	@Override
	public void likecountplus(int seq) {
		qnadao.likecountplus(seq);
		
	}
	@Override
	public void dislikecount(int seq) {
		qnadao.dislikecount(seq);
		
	}
	@Override
	public void readcountplus(int seq) {
		qnadao.readcountplus(seq);
		
	}
	@Override
	public boolean delete(int seq) {
		return qnadao.delete(seq);
	}
	@Override
	public void commentcountplus(int seq) {
		qnadao.commentcountplus(seq);
		
	}
	@Override
	public void commentcountminus(int seq) {
		qnadao.commentcountminus(seq);
		
	}
	
	@Override
	public boolean getparent(int seq) {
		return qnadao.getparent(seq);
	}
	
	@Override
	public List<QnaDto> getpdsdetail(int seq) {
		return qnadao.getpdsdetail(seq);
	}
	
	@Override
	public List<QnaDto> getdetail(int seq) {
		return qnadao.getdetail(seq);
	}
	
	@Override
	public boolean writeBbs(QnaDto bbs) {
		return qnadao.writeBbs(bbs);
	}
	////////////////////////////////like
	@Override
	public boolean addLikeID(QnaDto bbs) {
		return likedao.addDisLikeID(bbs);
	}
	@Override
	public boolean addDisLikeID(QnaDto bbs) {
		return likedao.addDisLikeID(bbs);
	}
	@Override
	public String getLikeID(int seq) {
		return likedao.getLikeID(seq);
	}
	@Override
	public String getDisLikeID(int seq) {
		return likedao.getDisLikeID(seq);
	}
	@Override
	public boolean isitlikeid(int seq, String serchlikeid) {
		return likedao.isitlikeid(seq, serchlikeid);
	}
	@Override
	public boolean isitdislikeid(int seq, String serchdislikeid) {
		return likedao.isitdislikeid(seq, serchdislikeid);
	}
	@Override
	public boolean deleteLikeID(String deleteid, int seq) {
		return likedao.deleteLikeID(deleteid, seq);
	}
	@Override
	public boolean deleteaddDisLikeID(String deleteid, int seq) {
		return likedao.deleteaddDisLikeID(deleteid, seq);
	}
	@Override
	public String[] getids(String serchid) {
		return likedao.getids(serchid);
	}
	
	
	
///////////////////////pds
	@Override
	public boolean writePds(QnaPdsDto pds) {
		return qnapdsdao.writePds(pds);
	}
	@Override
	public List<QnaPdsDto> getpdsList(int parent) {
		return qnapdsdao.getpdsList(parent);
	}
	@Override
	public QnaPdsDto getPdsBbs(int seq) {
		return qnapdsdao.getPdsBbs(seq);
	}
	@Override
	public boolean pdsdelete(int seq) {
		return qnapdsdao.pdsdelete(seq);
	}
	@Override
	public boolean pdsupdate(int seq, String filename) {
		return qnapdsdao.pdsupdate(seq, filename);
	}
	@Override
	public int getPdsSeq() {
		return qnapdsdao.getPdsSeq();
	}
	
	
}
