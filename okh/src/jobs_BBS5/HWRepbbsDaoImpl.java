package jobs_BBS5;

import java.util.List;




public interface HWRepbbsDaoImpl {
	public List<HWRepbbsDto> getRepBbsList(int seq);
	public boolean writeBbs(HWRepbbsDto bbs);
	//글 작성시 인간 쪽 점수 올라가는 것.
		public boolean writeBbsMemSCORE(byte score, String writescoreid);
	
	public boolean repupdate(int seq, String content);
	public boolean repdelete(int seq);
}
