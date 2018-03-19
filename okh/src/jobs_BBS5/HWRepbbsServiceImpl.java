package jobs_BBS5;

import java.util.List;


public interface HWRepbbsServiceImpl {
	public List<HWRepbbsDto> getRepBbsList(int seq);
	public boolean writeBbs(HWRepbbsDto bbs);
	
	public boolean repupdate(int seq, String content);
	public boolean repdelete(int seq);
}
