package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
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
        School school = teacher.getSchool();

        // 1. 入力値の取得
        String entYearStr = req.getParameter("f1"); 
        String classNum = req.getParameter("f2");   
        String subjectCd = req.getParameter("f3");  
        String numStr = req.getParameter("f4");     

        // 2. プルダウンデータの準備
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        List<String> class_list = cDao.filter(school);
        List<Subject> subject_list = sDao.filter(school);
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> ent_year_list = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear; i++) ent_year_list.add(i);

        // 3. 検索処理：すべての条件が選択されている時のみ実行
        if (entYearStr != null && !entYearStr.equals("0") && 
            classNum != null && !classNum.equals("0") && 
            subjectCd != null && !subjectCd.equals("0") && 
            numStr != null && !numStr.equals("0")) {

            int entYear = Integer.parseInt(entYearStr);
            int num = Integer.parseInt(numStr);
            
            Subject subject = sDao.get(subjectCd, school);
            TestDao tDao = new TestDao();
            
            // 検索結果を取得
            List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);
            
            // JSPへ渡す（名前をJSPのc:ifと合わせる）
            req.setAttribute("tests", tests);
            req.setAttribute("subject", subject);
        }

        // 4. JSPへ表示データをセット
        req.setAttribute("ent_year_list", ent_year_list);
        req.setAttribute("class_num_list", class_list); // 修正：JSP側の名前に合わせた
        req.setAttribute("subject_list", subject_list);

        // 5. フォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}