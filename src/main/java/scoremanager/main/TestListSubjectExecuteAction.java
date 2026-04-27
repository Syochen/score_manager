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
        // パラメータの取得
        int entYear = Integer.parseInt(req.getParameter("f1"));
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 入力チェック（シーケンス図のalt [いずれかが未入力の場合]）
        if (entYear == 0 || classNum == null || subjectCd == null || subjectCd.equals("")) {
            req.setAttribute("errors", "入学年度とクラスと科目を選択してください");
            // 前のActionを再実行して検索画面に戻す処理などが一般的
            new TestListAction().execute(req, res);
            return;
        }

        // 科目情報の取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, teacher.getSchool());

        // 成績データの取得
        TestListSubjectDao tlsDao = new TestListSubjectDao();
        List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject, teacher.getSchool());

        // 結果をセットして一覧画面（科目別）へ
        req.setAttribute("tests_subject", tests);
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}