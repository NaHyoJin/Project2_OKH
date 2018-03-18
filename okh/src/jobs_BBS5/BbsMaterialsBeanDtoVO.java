package jobs_BBS5;

import java.io.Serializable;

///////////////////////이게 PDS다.

/*
	DROP TABLE BbsMaterialsBeanDtoVO
	CASCADE CONSTRAINT;
	
	DROP SEQUENCE SEQ_BbsMaterialsBeanDtoVO;
	
	CREATE TABLE BbsMaterialsBeanDtoVO(	--게시판하고 자료실 같이 있게 일단 만들어보자. 안써도 무방.
		SEQ NUMBER(8) PRIMARY KEY,
		ID VARCHAR2(50) NOT NULL,
		
		REF NUMBER(8) NOT NULL,
		STEP NUMBER(8) NOT NULL,
		DEPTH NUMBER(8) NOT NULL,
		
		TITLE VARCHAR2(200) NOT NULL,
		CONTENT VARCHAR2(4000) NOT NULL,
		TAG VARCHAR2(200),
		FILENAME VARCHAR2(50),
		UP NUMBER(8) NOT NULL,
		DOWN NUMBER(8) NOT NULL,
		WDATE DATE NOT NULL,		--게시판 작성 날짜.
		PARENT NUMBER(8) NOT NULL,
		
		DEL NUMBER(1) NOT NULL,
		READCOUNT NUMBER(8) NOT NULL,	--조회수
		DOWNCOUNT NUMBER(8) NOT NULL,	--다운로드 수
		REGDATE DATE	--등록일.
	);

	CREATE SEQUENCE SEQ_BbsMaterialsBeanDtoVO
	START WITH 1 INCREMENT BY 1;
	
	ALTER TABLE BbsMaterialsBeanDtoVO
	ADD CONSTRAINT FK_BbsMaterialsBeanDtoVO_ID FOREIGN KEY(ID)
	REFERENCES okhmem(ID);
*/
public class BbsMaterialsBeanDtoVO implements Serializable {//자료실 bean 테이블 부분.

	private int seq;	//시퀀스 번호
	private String id;	//아이디
	
	private int ref;	// 그룹번호
	private int step;	// 열번호
	private int depth;	// 깊이
	
	private String title;//제목
	private String content;//내용
	private String tag;//태그 관련 사용시 준비.
	private String filename;//파일 이름.올릴 파일 이름 필요시 사용.
	private int up;			// 추천
	private int down;		// 반대
	private String wdate;//작성일
	private int parent;	// 부모글
	
	private int del;	// 삭제. 삭제시 1. 기본값 0.
	private int readcount;//조회수
	private int downcount; //다운로드 수.
	private String regdate;//파일 등록일.
	
	
	public BbsMaterialsBeanDtoVO() {
		// TODO Auto-generated constructor stub
	}
	

	public BbsMaterialsBeanDtoVO(int seq, String id, String title, String content, String tag, String filename) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
	}


	public BbsMaterialsBeanDtoVO(String id, String title, String content, String tag, String filename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
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


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public int getUp() {
		return up;
	}


	public void setUp(int up) {
		this.up = up;
	}


	public int getDown() {
		return down;
	}


	public void setDown(int down) {
		this.down = down;
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


	public int getDowncount() {
		return downcount;
	}


	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	@Override
	public String toString() {
		return "BbsMaterialsBeanDtoVO [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", filename=" + filename + ", readcount=" + readcount + ", downcount=" + downcount + ", regdate="
				+ regdate + "]";
	}


	public BbsMaterialsBeanDtoVO(int seq, String id, int ref, int step, int depth, String title, String content,
			String tag, String filename, int up, int down, String wdate, int parent, int del, int readcount,
			int downcount, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
		this.up = up;
		this.down = down;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
	}
	
	
	
}
