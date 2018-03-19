package jobs_BBS5;

import java.io.Serializable;
/*
INSERT INTO newbbs5HWCodingVO 
(SEQ, ID, TITLE,TAGNAME, CONTENT, WDATE, DEL, READCOUNT, LIKECOUNT,COMMENTCOUNT, SCRAPCOUNT) 
VALUES(SEQ_newbbs5HWCodingVO.NEXTVAL, 'aa', 'aa','aa','aa',SYSDATE,0,0,0,0,0 );

DROP TABLE newbbs5HWCodingVO
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_newbbs5HWCodingVO;

CREATE TABLE newbbs5HWCodingVO(
	SEQ NUMBER(8) PRIMARY KEY,	--게시글 고유 번호
	ID VARCHAR2(50) NOT NULL,	--아이디 외래키로 사용
	
	TITLE VARCHAR2(200) NOT NULL,	--제목
	TAGNAME VARCHAR2(50) NOT NULL,	--태그
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,	--작성일
	
	DEL NUMBER(1) NOT NULL,	--삭제 번호.
	READCOUNT NUMBER(8) NOT NULL,	--조회수
	LIKECOUNT NUMBER(8) NOT NULL,	--좋아요 수
	LIKEID VARCHAR2(1000),
	DISLIKEID VARCHAR2(1000),
	COMMENTCOUNT NUMBER(8) NOT NULL,
	SCRAPCOUNT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_newbbs5HWCodingVO
START WITH 1 INCREMENT BY 1;

ALTER TABLE newbbs5HWCodingVO
ADD CONSTRAINT FK_newbbs5HWCodingVO_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

SELECT B.SEQ,B.ID, B.TITLE,B.TAGNAME,B.CONTENT,B.WDATE, 
B.READCOUNT,B.LIKECOUNT,B.COMMENTCOUNT,B.POINT,B.SCRAPCOUNT,P.SEQ pdsseq,P.FILENAME,P.PARENT 
FROM newbbs5HWCodingVO B, newbbs5HWCodingPDSVO P
WHERE B.ID=P.ID AND B.SEQ=1 AND B.SEQ=P.PARENT; 

SELECT SEQ, ID, TITLE, TAGNAME, CONTENT, WDATE, DEL,
READCOUNT, LIKECOUNT, COMMENTCOUNT, POINT, SCRAPCOUNT
FROM newbbs5HWCodingVO
WHERE SEQ=1;
 */

public class newbbs5HWCodingVO implements Serializable {

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
	
	
	public newbbs5HWCodingVO(int likeidyn, int dislikeidyn) {
		super();
		this.likeidyn = likeidyn;
		this.dislikeidyn = dislikeidyn;
	}

	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, int del,
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
	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate,
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

	public newbbs5HWCodingVO(int seq, String likeid, String dislikeid) {
		super();
		this.seq = seq;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
	}

	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, int readcount,
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

	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, int del,
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

	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, int del,
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
	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, 
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
	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, int del,
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
	public newbbs5HWCodingVO(int seq, String id, String title, String tagname, String content, String wdate, int del,
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
	public newbbs5HWCodingVO(String id, String title, String tagname, String content) {
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
}
