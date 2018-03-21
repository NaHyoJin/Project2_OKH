package qna;

import java.io.Serializable;

public class LikeDto implements Serializable {

	private int seq;	
	private String whatbbs;
	private String likeid;
	private String dislikeid;
	private String scrapid;
	private String wdate;
	private int parent;
	
	public LikeDto(int seq, String whatbbs, String likeid, String dislikeid, String scrapid, String wdate, int parent) {
		super();
		this.seq = seq;
		this.whatbbs = whatbbs;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.scrapid = scrapid;
		this.wdate = wdate;
		this.parent = parent;
	}

	public LikeDto(String whatbbs, String likeid, String dislikeid, String scrapid, int parent) {
		super();
		this.whatbbs = whatbbs;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.scrapid = scrapid;
		this.parent = parent;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWhatbbs() {
		return whatbbs;
	}

	public void setWhatbbs(String whatbbs) {
		this.whatbbs = whatbbs;
	}

	public String getLikeid() {
		return likeid;
	}

	public void setLikeid(String likeid) {
		this.likeid = likeid;
	}

	public String getDislikeid() {
		return dislikeid;
	}

	public void setDislikeid(String dislikeid) {
		this.dislikeid = dislikeid;
	}

	public String getScrapid() {
		return scrapid;
	}

	public void setScrapid(String scrapid) {
		this.scrapid = scrapid;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "LikeDto [seq=" + seq + ", whatbbs=" + whatbbs + ", likeid=" + likeid + ", dislikeid=" + dislikeid
				+ ", scrapid=" + scrapid + ", wdate=" + wdate + ", parent=" + parent + "]";
	}
	
	
}
