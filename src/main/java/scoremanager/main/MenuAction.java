package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class MenuAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得
        HttpSession session = request.getSession();
        // セッションからユーザー情報を取得
        Object user = session.getAttribute("user");

        if (user != null) {
            // ログイン済みの場合
            // メニュー画面（menu.jsp）へフォワード
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            // 未ログインの場合
            // ログイン画面へフォワード
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}