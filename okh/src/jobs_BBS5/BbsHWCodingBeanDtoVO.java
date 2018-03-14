package jobs_BBS5;

import java.io.Serializable;

/*
DROP TABLE BbsHWCodingBeanDtoVO
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BbsHWCodingBeanDtoVO;

CREATE TABLE BbsHWCodingBeanDtoVO(
	SEQ NUMBER(8) PRIMARY KEY,	--게시글 고유 번호
	ID VARCHAR2(50) NOT NULL,	--아이디 외래키로 사용
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,	--작성일
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,	--조회수
	
	FILENAME VARCHAR2(50),	--파일 이름
	DOWNCOUNT NUMBER(8),	--파일 다운로드 수
	REGDATE DATE	--파일 등록일.
	
);

CREATE SEQUENCE SEQ_BbsHWCodingBeanDtoVO
START WITH 1 INCREMENT BY 1;

ALTER TABLE BbsHWCodingBeanDtoVO
ADD CONSTRAINT FK_BbsHWCodingBeanDtoVO_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

*/

public class BbsHWCodingBeanDtoVO implements Serializable {//H/W coding 테이블 부분.

	private int seq;	//시퀀스 번호
	private String id;	//아이디
	
	private int ref;	// 그룹번호
	private int step;	// 열번호
	private int depth;	// 깊이
	
	private String title;//제목
	private String content;//내용
	private String wdate;//작성일
	private int parent;	// 부모글
	
	private int del;	// 삭제
	private int readcount;//조회수
	
	
	
	
	
	public BbsHWCodingBeanDtoVO() {}

	public BbsHWCodingBeanDtoVO(int seq, String id, int ref, int step, int depth, String title, String content, String wdate,
			int parent, int del, int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
	}

	//글 작성하는 오버로드 부분.
	public BbsHWCodingBeanDtoVO(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
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

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
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

	@Override
	public String toString() {
		return "BbsDto [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", title="
				+ title + ", content=" + content + ", wdate=" + wdate + ", parent=" + parent + ", del=" + del
				+ ", readcount=" + readcount + "]";
	}
}
