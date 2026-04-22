package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. パラメータの取得
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 2. 認証処理 (TeacherDaoを使用)
        TeacherDao tDao = new TeacherDao();
        Teacher teacher = tDao.login(id, password);

        // 3. 判定
        if (teacher != null) {
            // 認証成功：セッションにユーザー情報を格納
            HttpSession session = request.getSession(true);
            teacher.setAuthenticated(true);
            session.setAttribute("user", teacher);

            // メインメニュー（Menu.action）へリダイレクト
            response.sendRedirect("Menu.action");
        } else {
            // 認証失敗：エラーメッセージをセットしてログイン画面に戻す
            request.setAttribute("errors", "IDまたはパスワードが確認できませんでした");
            request.setAttribute("id", id);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}