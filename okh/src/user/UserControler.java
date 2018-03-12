package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserControler")
public class UserControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=uTF-8");
		
		String command = request.getParameter("command");
		
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		String userProfile = request.getParameter("userProfile");

		IUserService userService = new UserService();
		
// 로그인
		if(command.equals("login")) {
			response.sendRedirect("login.jsp");
		}else if(command.equals("loginAf")) {
			if(userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "모든 내용을 입력해주세요.");
				response.sendRedirect("login.jsp");
				return;
			}
			
			UserDto userDto =  userService.login(userID, userPassword);
			request.getSession().setAttribute("login", userDto);
			
			if(userDto != null) {
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "로그인에 성공했습니다.");
				response.sendRedirect("index.jsp");
			}else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "아이디 비밀번호를 확인해주세요.");
				response.sendRedirect("login.jsp");
			}
		}
// ID 확인
		else if(command.equals("check")) {
			int result = userService.registerCheck(userID);
			
			response.getWriter().write(result + "");
		}
// 회원 등록
		else if(command.equals("join")) {
			response.sendRedirect("join.jsp");
		}else if(command.equals("joinAf")) {
			if(userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("") || userPassword2 == null || userPassword2.equals("") || 
					userName == null || userName.equals("") || userAge == null || userAge.equals("") || userGender == null || userGender.equals("") || 
					userEmail == null || userEmail.equals("")) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
				response.sendRedirect("join.jsp");
				return;
			}
			if(!userPassword1.equals(userPassword2)) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "비밀번호가 서로 다릅니다.");
				response.sendRedirect("join.jsp");
				return;
			}
			
			boolean result = userService.addMember(userID, userPassword1, userName, userAge, userGender, userEmail, null, userProfile);
			
			if(result) {
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "회원가입에 성공했습니다.");
				response.sendRedirect("index.jsp");
			}else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
				response.sendRedirect("join.jsp");
			}
		}
	}

}
