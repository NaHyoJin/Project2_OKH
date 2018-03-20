package user;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UserControler")
public class UserController extends HttpServlet {
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
				return;
			}else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "아이디 비밀번호를 확인해주세요.");
				response.sendRedirect("login.jsp");
				return;
			}
		}
// 로그아웃
		else if(command.equals("logout")) {
			request.getSession().invalidate();
			request.getSession().setAttribute("messageType", "로그아웃");
			request.getSession().setAttribute("messageContent", "좋은 하루 되세요.");
			response.sendRedirect("index.jsp");
			return;
		}
// 로그인 필요
		else if(command.equals("guest")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "로그인이 필요한 기능입니다.");
			response.sendRedirect("login.jsp");
			return;
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
			
			boolean result = userService.addMember(userID, userPassword1, userName, userAge, userGender, userEmail, null, " ");
			
			if(result) {
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "회원가입에 성공했습니다.");
				response.sendRedirect("index.jsp");
				return;
			}else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
				response.sendRedirect("join.jsp");
				return;
			}
		}
// 회원 정보 수정
		else if(command.equals("update")) {
			HttpSession session = request.getSession();
			
			if(userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("") || userPassword2 == null || userPassword2.equals("") || 
					userName == null || userName.equals("") || userAge == null || userAge.equals("") || userGender == null || userGender.equals("") || 
					userEmail == null || userEmail.equals("")) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
				response.sendRedirect("update.jsp");
				return;
			}
			if(userID.equals((String)session.getAttribute("userID"))) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "접근할 수 없습니다.");
				response.sendRedirect("index.jsp");
				return;
			}
			if(!userPassword1.equals(userPassword2)) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "비밀번호가 서로 다릅니다.");
				response.sendRedirect("update.jsp");
				return;
			}
			
			boolean result = userService.update(userID, userPassword1, userName, userAge, userGender, userEmail, null, userProfile);
			
			if(result) {
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "회원정보 수정에 성공했습니다.");
				response.sendRedirect("index.jsp");
				return;
			}else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
				response.sendRedirect("update.jsp");
				return;
			}
		}
// 프로필 사진 수정
		else if(command.equals("profile")) {
			MultipartRequest multi = null;
			int fileMaxSize = 10 * 1024 * 1024;
			String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");
			try {
				multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
			} catch (Exception e) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다");
				response.sendRedirect("loginProfileUpdate.jsp.jsp");
				return;
			}
			userID = multi.getParameter("userID");
			HttpSession session = request.getSession();

			if(userID.equals((String)session.getAttribute("userID"))) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "접근할 수 없습니다.");
				response.sendRedirect("index.jsp");
				return;
			}
			String fileName = "";
			File file = multi.getFile("userProfile");
			if(file != null) {
				String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
				if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
					String prev = new UserDao().getUser(userID).getProfile();
					File prevFile = new File(savePath + "/" + prev);
					if(prevFile.exists()) {
						prevFile.delete();
					}
					fileName = file.getName();
				}else {
					if(file.exists()) {
						file.delete();
					}
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "이미지 파일만 업로드 가능합니다.");
					response.sendRedirect("loginProfileUpdate.jsp");
					return;
				}
				new UserService().profile(userID, fileName);
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "성공적으로 프로필이 변경되었습니다.");
				response.sendRedirect("index.jsp");
				return;
			}
		}
// 내 정보
		else if(command.equals("mypage")) {
			response.sendRedirect("mypage.jsp");
		}
	}

}
