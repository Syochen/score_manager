package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentNo = req.getParameter("f4");

        // 入力チェック（シーケンス図のalt [学生番号が未入力の場合]）
        if (studentNo == null || studentNo.equals("")) {
            req.setAttribute("errors", "このフィールドを入力してください。");
            new TestListAction().execute(req, res);
            return;
        }

        // 学生情報の取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);

        // 学生別成績の取得
        TestListStudentDao tlsDao = new TestListStudentDao();
        List<TestListStudent> tests = tlsDao.filter(student);

        // 結果をセットして一覧画面（学生別）へ
        req.setAttribute("tests_student", tests);
        req.setAttribute("student", student);
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}