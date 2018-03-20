package jobs_BBS5;

import java.util.List;


public interface HWRepbbsServiceImpl {
	public List<HWRepbbsDto> getRepBbsList(int seq);
	//덧글 작성 부분.
	public boolean writeBbs(HWRepbbsDto bbs);
	//글 작성시 인간 쪽 점수 올라가는 것.
		public boolean writeBbsMemSCORE(byte score, String writescoreid);
	
	public boolean repupdate(int seq, String content);
	//덧글 삭제 부분.
	public boolean repdelete(int seq);
	//덧글 삭제 시 점수 빼는 부분 시간 되면 만들자.
}
