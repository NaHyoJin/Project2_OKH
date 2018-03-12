package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import singleton.Singleton;
import view.chattingView;

public class WriteClass {
	
	Socket socket;
	
	String str;
	String id;
	
	public WriteClass() {
	}
	
	public void sendMsg(Socket socket,String textWrite) {

		this.socket = socket;
		
		Singleton s = Singleton.getInstance();
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(socket.getOutputStream(), true);
			/*
			if(cv.isFirst) {	// 첫번째 전송
				InetAddress iaddr = socket.getLocalAddress();
				String ip = iaddr.getHostAddress();	// 232.34.45.23
				
				
				str = "[" + id + "]님 로그인 (" + ip + ")";				
			}else {				// 대화		
				*/
			this.id = s.memCtrl.loginId;
			str = "[" + id + "]" + textWrite;				
//			}		
			
			pw.println(str);
			pw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}





