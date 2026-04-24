package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 画面からの入力値を受け取る
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2");   // クラス
        String subjectCd = req.getParameter("f3");  // 科目
        String numStr = req.getParameter("f4");      // 回数

        //プルダウン用のデータ準備
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        List<String> class_list = cDao.filter(teacher.getSchool());
        List<Subject> subject_list = sDao.filter(teacher.getSchool());
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> ent_year_list = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) ent_year_list.add(i);

        //検索処理
        if (entYearStr != null && !entYearStr.equals("0")) {
            int entYear = Integer.parseInt(entYearStr);
            int num = Integer.parseInt(numStr);
            
            Subject subject = sDao.get(subjectCd, teacher.getSchool());
            TestDao tDao = new TestDao();
            
            // DBから学生と成績のリストを取得
            List<Test> tests = tDao.filter(entYear, classNum, subject, num, teacher.getSchool());
            
            // 検索結果をJSPに渡す
            req.setAttribute("tests", tests);
            req.setAttribute("subject", subject);
        }

        // JSPへデータをセット
        req.setAttribute("ent_year_list", ent_year_list);
        req.setAttribute("class_num_list", class_list);
        req.setAttribute("subject_list", subject_list);
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}