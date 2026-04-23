package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 2. Beanにセット
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // 3. DAOで保存（更新）
        SubjectDao sDao = new SubjectDao();
        sDao.save(subject);

        // 4. 完了画面へ（登録と同じ完了画面でもOKだけど、一応用意しておこうか）
        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}