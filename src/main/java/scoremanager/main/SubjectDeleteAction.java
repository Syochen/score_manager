package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 削除対象のコードを取得
        String cd = request.getParameter("cd");

        // DAOでデータを取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, school);

        // リクエスト属性にセットして確認画面へ
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
    }
}