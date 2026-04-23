package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ取得
        String cd = request.getParameter("cd");

        // 削除用Beanの準備
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(school);

        // DAOで削除実行
        SubjectDao sDao = new SubjectDao();
        sDao.delete(subject);

        // 削除完了画面へ
        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}