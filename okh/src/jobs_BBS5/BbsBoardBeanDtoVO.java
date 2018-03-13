package jobs_BBS5;

import java.io.Serializable;

public class BbsBoardBeanDtoVO implements Serializable{//일반 게시판 부분.
	
	
	private int seq;//시퀀스
	private String id;//해당 아이디
	
	private int ref;	// 그룹번호
	private int step;	// 열번호
	private int depth;	// 깊이
	
	private String title;//제목
	private String content;//내용
	private String wdate;//작성일
	private int parent;	// 부모글
	
	private int del;	// 삭제
	private int readcount;//조회수

	
}
