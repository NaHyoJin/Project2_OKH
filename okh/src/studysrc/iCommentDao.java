package studysrc;

public interface iCommentDao {
	
	public boolean writecomment(ComCommentDto dto);
	public void delcomment(int seq);
	public void updatecomment(String content,int seq);
}
