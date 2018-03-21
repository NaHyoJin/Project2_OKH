package qna;

import java.io.Serializable;

/*

DROP TABLE QNA
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_QNA;

CREATE TABLE QNA(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	TAGNAME VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	LIKECOUNT NUMBER(8) NOT NULL,
	LIKEID VARCHAR2(1000),
	DISLIKEID VARCHAR2(1000),
	COMMENTCOUNT NUMBER(8) NOT NULL,
	SCRAPCOUNT NUMBER(8) NOT NULL	
);

CREATE SEQUENCE SEQ_QNA
START WITH 1 INCREMENT BY 1;

ALTER TABLE QNA
ADD CONSTRAINT FK_QNA_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);



*/

public class QnaDto implements Serializable{

	/*private int seq;
	private String id;
	private int ref;			// 그룹번호
	private int step;			// 열번호
	private int depth;			// 깊이
	private String title;
	private String content;
	private String tag;
	private String wdate;
	private int parent;			// 부모글
	private int del;
	private int readcount;
	private int favor;			// 찬성,반대
	private int lvpoint;		// 활동 포인트 글+5, 댓글+2
	private int answercount;	// 답변수
	
	private String favorid;
	private int scrapcount;*/
	
	private int seq;
	private String id;
	
	private String title;
	private String tagname;
	private String content;
	private String wdate;
	
	private int del;	// 삭제
	private int readcount;
	private int likecount;
	private String likeid;
	private String dislikeid;
	private int commentcount;
	private int scrapcount;
	
	private String filename;
	private int parent;
	private int pdsseq;
	private int pdsys;
	
	private int likeidyn;
	private int dislikeidyn;
	
	public QnaDto() {}

	public QnaDto(int likeidyn, int dislikeidyn) {
		super();
		this.likeidyn = likeidyn;
		this.dislikeidyn = dislikeidyn;
	}

	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int likecount, String likeid, String dislikeid, int likeidyn) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.likeidyn = likeidyn;
	}
	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate,
			int readcount, int likecount, String likeid, String dislikeid, int dislikeidyn) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.dislikeidyn = dislikeidyn;
	}
	public int getLikeidyn() {
		return likeidyn;
	}

	public void setLikeidyn(int likeidyn) {
		this.likeidyn = likeidyn;
	}

	public int getDislikeidyn() {
		return dislikeidyn;
	}

	public void setDislikeidyn(int dislikeidyn) {
		this.dislikeidyn = dislikeidyn;
	}

	public QnaDto(int seq, String likeid, String dislikeid) {
		super();
		this.seq = seq;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
	}

	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, int readcount,
			int likecount, String likeid, String dislikeid, int commentcount, int scrapcount, String filename,
			int parent, int pdsseq, int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.commentcount = commentcount;
		this.scrapcount = scrapcount;
		this.filename = filename;
		this.parent = parent;
		this.pdsseq = pdsseq;
		this.pdsys = pdsys;
	}

	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int likecount, String likeid, String dislikeid, int commentcount, int scrapcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.commentcount = commentcount;
		this.scrapcount = scrapcount;
	}

	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int likecount, String likeid, String dislikeid, int commentcount, int scrapcount,
			String filename, int parent, int pdsseq, int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.commentcount = commentcount;
		this.scrapcount = scrapcount;
		this.filename = filename;
		this.parent = parent;
		this.pdsseq = pdsseq;
		this.pdsys = pdsys;
	}

	//조인으로얻어온값들
	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, 
			int readcount, int commentcount, int likecount, int scrapcount, String filename, int parent,
			int pdsseq,int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
		this.filename = filename;
		this.parent = parent;
		this.pdsseq = pdsseq;
		this.pdsys = pdsys;
	}

	//자료유무판단
	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int commentcount, int likecount, int scrapcount, int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
		this.pdsys = pdsys;
	}


	public int getPdsys() {
		return pdsys;
	}


	public void setPdsys(int pdsys) {
		this.pdsys = pdsys;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public int getParent() {
		return parent;
	}


	public void setParent(int parent) {
		this.parent = parent;
	}


	public int getPdsseq() {
		return pdsseq;
	}


	public void setPdsseq(int pdsseq) {
		this.pdsseq = pdsseq;
	}




	//게시판dto
	public QnaDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int commentcount, int likecount, int scrapcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
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

	//글쓰기할때dto
	public QnaDto(String id, String title, String tagname, String content) {
		super();
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
	}


	public int getCommentcount() {
		return commentcount;
	}


	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}


	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getScrapcount() {
		return scrapcount;
	}
	public void setScrapcount(int scrapcount) {
		this.scrapcount = scrapcount;
	}

	@Override
	public String toString() {
		return "QnaDto [seq=" + seq + ", id=" + id + ", title=" + title + ", tagname=" + tagname + ", content="
				+ content + ", wdate=" + wdate + ", del=" + del + ", readcount=" + readcount + ", likecount="
				+ likecount + ", likeid=" + likeid + ", dislikeid=" + dislikeid + ", commentcount=" + commentcount
				+ ", scrapcount=" + scrapcount + ", filename=" + filename + ", parent=" + parent + ", pdsseq=" + pdsseq
				+ ", pdsys=" + pdsys + ", likeidyn=" + likeidyn + ", dislikeidyn=" + dislikeidyn + "]";
	}
	
	
}
