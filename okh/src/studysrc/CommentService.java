package studysrc;

public class CommentService implements iCommentService {

	private static CommentService commentService = new CommentService();
	public iCommentDao commentdao;
	
	private CommentService() {
		commentdao = new CommentDao();
	}
	
	public static CommentService getInstance() {
		return commentService;
	}

	@Override
	public boolean writecomment(ComCommentDto dto) {
		return commentdao.writecomment(dto);
	}
	public void delcomment(int seq) {
		commentdao.delcomment(seq);
	}

	@Override
	public void updatecomment(String content, int seq) {
		commentdao.updatecomment(content, seq);
		
	}
	
	
	
	
}
