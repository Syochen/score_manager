package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // パラメータ取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        // 未選択チェック
        if (entYearStr.equals("0") || classNum.equals("0") || subjectCd.equals("0")) {
            req.setAttribute("errors", "入学年度とクラスと科目を選択してください");
        } else {
            int entYear = Integer.parseInt(entYearStr);
            SubjectDao sDao = new SubjectDao();
            Subject subject = sDao.get(subjectCd, teacher.getSchool());

            TestListSubjectDao tlsDao = new TestListSubjectDao();
            // DBから成績リストを取得
            List<TestListSubject> list = tlsDao.filter(entYear, classNum, subject, teacher.getSchool());

            req.setAttribute("subject", subject);
            req.setAttribute("tests", list); // これが表の中身になる
        }
        
        // プルダウンを再表示するためにTestListActionの処理を通す
        new TestRegistAction().execute(req, res);
    }
}