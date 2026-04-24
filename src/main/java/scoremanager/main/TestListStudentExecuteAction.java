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
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);
        
        if (student != null) {
            TestListStudentDao tlsDao = new TestListStudentDao();
            List<TestListStudent> list = tlsDao.filter(student);
            req.setAttribute("student", student);
            req.setAttribute("tests", list);
        } else {
            req.setAttribute("errors", "学生情報が存在しませんでした");
        }
        
        // 再度検索画面の初期データをセットするActionを呼ぶか、直接JSPへ
        new TestRegistAction().execute(req, res);
    }
}