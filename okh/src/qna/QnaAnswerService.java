package qna;

import java.util.List;

public class QnaAnswerService implements QnaAnswerServiceImpl {

	private static QnaAnswerService qnaanswerService = new QnaAnswerService();
	
	public QnaAnswerDaoImpl qnaanswerdao;
	
	private QnaAnswerService() {
		qnaanswerdao = new QnaAnswerDao();
	}
	
	public static QnaAnswerService getInstance() {
		return qnaanswerService;
	}
	
	
////qnaanswer
	
	@Override
	public List<QnaAnswerDto> getRepBbsList(int seq) {
		return qnaanswerdao.getRepBbsList(seq);
	}
	@Override
	public boolean writeBbs(QnaAnswerDto bbs) {
		return qnaanswerdao.writeBbs(bbs);
	}
	@Override
	public boolean repupdate(int seq, String content) {
		return qnaanswerdao.repupdate(seq, content);
	}
	@Override
	public boolean repdelete(int seq) {
		return qnaanswerdao.repdelete(seq);
	}
	
	
	
}
