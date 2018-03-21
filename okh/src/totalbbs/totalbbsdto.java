package totalbbs;

import java.io.Serializable;
/*
INSERT INTO QNA(SEQ, ID, 
REF, STEP, DEPTH, TITLE, 
CONTENT, TAG,WDATE,PARENT,DEL,READCOUNT,FAVOR, LVPOINT,ANSWERCOUNT)
VALUES(5, '11', 5, 0,0, 
'qna100', 'qna100','qna100',sysdate,0,0,0,0,0,0);

INSERT INTO COMBBS(SEQ, ID, 
TITLE, CONTENT, WDATE, DEL, 
READCOUNT, COMMENTCOUNT,TAGNAME,PARENT,JOINERCOUNT,
JOINDATE,LIKEINNER)
VALUES(4, 'aa', 'combbs100','combbs100', sysdate,0,
0,0,'combbs100',0,0,'combbs100','combbs100');

INSERT INTO LIFEBBS(SEQ, ID, 
REF, STEP, DEPTH, TITLE, 
CONTENT, TAG,FILENAME,UP,UPID,DOWNID,WDATE, PARENT,
DEL,READCOUNT,DOWNCOUNT,COUNTREPLY)
VALUES(5, 'a', 5, 0,0,
'LIFEBBS100', 'LIFEBBS100','LIFEBBS100','LIFEBBS100',0,'11','',
sysdate,0,0,0,0,0);

INSERT INTO newbbs5HWCodingVO 
(SEQ, ID, TITLE,TAGNAME, CONTENT, WDATE, DEL, 
READCOUNT, LIKECOUNT,COMMENTCOUNT, SCRAPCOUNT) 
VALUES(3, '1', 
'bbs5100','bbs5100','bbs5100',SYSDATE,0,0,0,0,0 );

*/
public class totalbbsdto implements Serializable {
	@Override
	public String toString() {
		return "totalbbsdto [parent=" + parent + ", whatbbs=" + whatbbs + ", id=" + id + ", title=" + title
				+ ", readcount=" + readcount + ", likecount=" + likecount + ", comentcount=" + comentcount
				+ ", scrapcount=" +   ", wdate=" + wdate + "]";
	}

	private int parent;		//불러오는sep
	private String whatbbs;
	private String id;		//작성자
	private String title;
	private String content;
	private int readcount;
	private int likecount;
	private int comentcount;
	private int wdate;
	
	public totalbbsdto() {
		// TODO Auto-generated constructor stub
	}

	
	



	public totalbbsdto(int parent, String whatbbs, String id, String title, String content, int readcount,
			int likecount, int comentcount, int wdate) {
		super();
		this.parent = parent;
		this.whatbbs = whatbbs;
		this.id = id;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.likecount = likecount;
		this.comentcount = comentcount;
		this.wdate = wdate;
	}



	


	public String getContent() {
		return content;
	}






	public void setContent(String content) {
		this.content = content;
	}






	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getWhatbbs() {
		return whatbbs;
	}

	public void setWhatbbs(String whatbbs) {
		this.whatbbs = whatbbs;
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

	public int getComentcount() {
		return comentcount;
	}

	public void setComentcount(int comentcount) {
		this.comentcount = comentcount;
	}

	

	public int getWdate() {
		return wdate;
	}

	public void setWdate(int wdate) {
		this.wdate = wdate;
	}
	
	
}
