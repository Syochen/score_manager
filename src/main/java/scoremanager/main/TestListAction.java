package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ★追加：セッション切れチェック（teacherがnullならログインへ）
        if (teacher == null) {
            res.sendRedirect("../login/Login.action");
            return;
        }

        String f = req.getParameter("f");

        // 検索ボタン（f）の値によって、それぞれのActionへ完全に引き継ぐ
        if ("sj".equals(f)) {
            new TestListSubjectExecuteAction().execute(req, res);
            return;
        } else if ("st".equals(f)) {
            new TestListStudentExecuteAction().execute(req, res);
            return;
        }

        // --- 初期表示（検索ボタンを押していない時） ---
        prepareCommonData(req, teacher);
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }

    public static void prepareCommonData(HttpServletRequest req, Teacher teacher) throws Exception {
        // ここでteacher.getSchool()を使うので、呼び出し元でteacherがnullでないことが保証されている必要がある
        ClassNumDao cNumDao = new ClassNumDao();
        req.setAttribute("class_num_set", cNumDao.filter(teacher.getSchool()));
        
        SubjectDao sDao = new SubjectDao();
        req.setAttribute("subjects", sDao.filter(teacher.getSchool()));

        List<Integer> ent_year_set = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year; i >= year - 10; i--) {
            ent_year_set.add(i);
        }
        req.setAttribute("ent_year_set", ent_year_set);
    }
}