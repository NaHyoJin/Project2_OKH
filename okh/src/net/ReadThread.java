package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import view.chattingView;

public class ReadThread extends Thread {
	
	Socket socket;

	public ReadThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {		
		super.run();
		String str = null;
		try {			
			while(true) {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				str = reader.readLine();
				if(str == null) continue;
				
				// append를 사용하면, 적은거 뒤에 바로 붙여줄 수 있다.
				chattingView.textArea.append(str+"\n");				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}






