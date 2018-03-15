package techreplebbs;

import java.util.List;


public interface TechRepbbsServiceImpl {
	public List<TechRepbbsDto> getRepBbsList(int seq);
	public boolean answer(int seq, TechRepbbsDto bbs);
	public boolean writeBbs(TechRepbbsDto bbs);
	public void likecountplus(int seq);
	
	public boolean repupdate(int seq, String content);
	public boolean repdelete(int seq);
}
