package studysrc;
/*

select * FROM SCOMMENT

DROP TABLE SCOMMENT
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_SCOMMENT;

CREATE TABLE SCOMMENT(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	DEL NUMBER(1) NOT NULL,
	CHILD NUMBER(8) NOT NULL,
	JOINER NUMBER(8)
	LIKEINNER VARCHAR2(50)
	
);

CREATE SEQUENCE SEQ_SCOMMENT
START WITH 1 INCREMENT BY 1;

ALTER TABLE SCOMMENT
ADD CONSTRAINT FK_SCOMMENT_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);
 	
 */
public class ComCommentDto {
	
	private int seq;
	private String id;
	private String content;
	private String wdate;
	private int joiner;
	private int child;
	private int del;
	
	public ComCommentDto(int seq, String id, String content, String wdate, int joiner, int child,
			int del) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.wdate = wdate;
		this.joiner = joiner;
		this.child = child;
		this.del = del;
	}
	
	public ComCommentDto(String id, String content, int child) {
		super();
		this.id = id;
		this.content = content;
		this.child = child;
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
	public int getJoiner() {
		return joiner;
	}
	public void setJoiner(int joiner) {
		this.joiner = joiner;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "ComCommentDto [seq=" + seq + ", id=" + id + ", content=" + content + ", wdate=" + wdate + ", joiner="
				+ joiner + ", child=" + child + ", del=" + del + "]";
	}
	
	
	
	
}
