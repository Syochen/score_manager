package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
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
        // 検索種別（f）を取得
        String f = req.getParameter("f");

        if (f == null) {
            // 1. 最初の一覧画面表示（検索ボタンが押されていない場合）
            showInitialPage(req, res);
        } else if (f.equals("sj")) {
            // 2. 科目検索ボタンが押された場合
            new TestListSubjectExecuteAction().execute(req, res);
        } else if (f.equals("st")) {
            // 3. 学生検索ボタンが押された場合
            new TestListStudentExecuteAction().execute(req, res);
        }
    }

    private void showInitialPage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // --- データの取得 ---
        
        // 1. クラス番号一覧を取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> class_num_set = cNumDao.filter(teacher.getSchool());
        
        // 2. 科目一覧を取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // 3. 入学年度リストを生成（現在の年から10年前まで）
        List<Integer> ent_year_set = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year; i >= year - 10; i--) {
            ent_year_set.add(i);
        }

        // --- リクエスト属性にセット ---
        // JSP側の items="${...}" で指定している名前と一致させる必要があります
        req.setAttribute("class_num_set", class_num_set);
        req.setAttribute("subjects", subjects);
        req.setAttribute("ent_year_set", ent_year_set);

        // 画面表示
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}