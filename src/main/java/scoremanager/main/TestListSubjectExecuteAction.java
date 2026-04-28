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
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 入力値の保持用（JSPの検索フォームに値を残すため）
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ★修正箇所：入力チェック
        if (entYear == 0 || classNum == null || classNum.isEmpty() || subjectCd == null || subjectCd.isEmpty()) {
            req.setAttribute("errors", "入学年度とクラスと科目を選択してください");
            
            // 無限ループを避けるため、Actionを新しく作るのではなく
            // 共通データ（プルダウンのリストなど）を準備するメソッドだけを呼ぶ
            TestListAction.prepareCommonData(req, teacher);
            
            // 直接JSPを表示する
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // 以降、正常系の処理（変更なし）
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, teacher.getSchool());

        TestListSubjectDao tlsDao = new TestListSubjectDao();
        List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject, teacher.getSchool());

        // 検索実行済みフラグと結果のセット
        req.setAttribute("done_sj", true);
        req.setAttribute("tests_subject", tests);
        req.setAttribute("subject", subject);

        // 共通データの準備をしてJSPへ
        TestListAction.prepareCommonData(req, teacher);
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}