package studysrc;

public class comment_bbsDto {

	private int bbsseq;		//시퀀스
	private String bbsid;		// 아이디
	private String bbstitle;	//제목
	private String bbscontent;	//내용
	private String bbswdate;	//작성일
	private int bbsdel;	//삭제 0,1
	private int bbsreadcount;	//조회수
	private int bbscommentcount;		//댓글수 
	private String bbstagname;	//지역테그
	private int bbsparent;		//부모글
	private int bbsjoinercount;	// 좋아요
	private String bbsjoindate;	// 모임날
	
	private int commentseq;
	private String commentid;
	private String commentcontent;
	private String commentwdate;
	private int commentdel;
	private int commentchild;
	private int commentjoiner;
	
	
	
	public comment_bbsDto(int bbsseq, String bbsid, String bbstitle, String bbscontent, String bbswdate, int bbsdel,
			int bbsreadcount, int bbscommentcount, String bbstagname, int bbsparent, int bbsjoinercount,
			String bbsjoindate, int commentseq, String commentid, String commentcontent, String commentwdate,
			int commentdel, int commentchild, int commentjoiner) {
		super();
		this.bbsseq = bbsseq;
		this.bbsid = bbsid;
		this.bbstitle = bbstitle;
		this.bbscontent = bbscontent;
		this.bbswdate = bbswdate;
		this.bbsdel = bbsdel;
		this.bbsreadcount = bbsreadcount;
		this.bbscommentcount = bbscommentcount;
		this.bbstagname = bbstagname;
		this.bbsparent = bbsparent;
		this.bbsjoinercount = bbsjoinercount;
		this.bbsjoindate = bbsjoindate;
		this.commentseq = commentseq;
		this.commentid = commentid;
		this.commentcontent = commentcontent;
		this.commentwdate = commentwdate;
		this.commentdel = commentdel;
		this.commentchild = commentchild;
		this.commentjoiner = commentjoiner;
	}
	public int getBbsseq() {
		return bbsseq;
	}
	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}
	public String getBbsid() {
		return bbsid;
	}
	public void setBbsid(String bbsid) {
		this.bbsid = bbsid;
	}
	public String getBbstitle() {
		return bbstitle;
	}
	public void setBbstitle(String bbstitle) {
		this.bbstitle = bbstitle;
	}
	public String getBbscontent() {
		return bbscontent;
	}
	public void setBbscontent(String bbscontent) {
		this.bbscontent = bbscontent;
	}
	public String getBbswdate() {
		return bbswdate;
	}
	public void setBbswdate(String bbswdate) {
		this.bbswdate = bbswdate;
	}
	public int getBbsdel() {
		return bbsdel;
	}
	public void setBbsdel(int bbsdel) {
		this.bbsdel = bbsdel;
	}
	public int getBbsreadcount() {
		return bbsreadcount;
	}
	public void setBbsreadcount(int bbsreadcount) {
		this.bbsreadcount = bbsreadcount;
	}
	public int getBbscommentcount() {
		return bbscommentcount;
	}
	public void setBbscommentcount(int bbscommentcount) {
		this.bbscommentcount = bbscommentcount;
	}
	public String getBbstagname() {
		return bbstagname;
	}
	public void setBbstagname(String bbstagname) {
		this.bbstagname = bbstagname;
	}
	public int getBbsparent() {
		return bbsparent;
	}
	public void setBbsparent(int bbsparent) {
		this.bbsparent = bbsparent;
	}
	public int getBbsjoinercount() {
		return bbsjoinercount;
	}
	public void setBbsjoinercount(int bbsjoinercount) {
		this.bbsjoinercount = bbsjoinercount;
	}
	public String getBbsjoindate() {
		return bbsjoindate;
	}
	public void setBbsjoindate(String bbsjoindate) {
		this.bbsjoindate = bbsjoindate;
	}
	public int getCommentseq() {
		return commentseq;
	}
	public void setCommentseq(int commentseq) {
		this.commentseq = commentseq;
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	public String getCommentwdate() {
		return commentwdate;
	}
	public void setCommentwdate(String commentwdate) {
		this.commentwdate = commentwdate;
	}
	public int getCommentjoiner() {
		return commentjoiner;
	}
	public void setCommentjoiner(int commentjoiner) {
		this.commentjoiner = commentjoiner;
	}
	public int getCommentchild() {
		return commentchild;
	}
	public void setCommentchild(int commentchild) {
		this.commentchild = commentchild;
	}
	public int getCommentdel() {
		return commentdel;
	}
	public void setCommentdel(int commentdel) {
		this.commentdel = commentdel;
	}
	@Override
	public String toString() {
		return "comment_bbsDto [bbsseq=" + bbsseq + ", bbsid=" + bbsid + ", bbstitle=" + bbstitle + ", bbscontent="
				+ bbscontent + ", bbswdate=" + bbswdate + ", bbsdel=" + bbsdel + ", bbsreadcount=" + bbsreadcount
				+ ", bbscommentcount=" + bbscommentcount + ", bbstagname=" + bbstagname + ", bbsparent=" + bbsparent
				+ ", bbsjoinercount=" + bbsjoinercount + ", bbsjoindate=" + bbsjoindate + ", commentseq=" + commentseq
				+ ", commentid=" + commentid + ", commentcontent=" + commentcontent + ", commentwdate=" + commentwdate
				+ ", commentjoiner=" + commentjoiner + ", commentchild=" + commentchild + ", commentdel=" + commentdel
				+ "]";
	}
	
	
	
	
	
}
