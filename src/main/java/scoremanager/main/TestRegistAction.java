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

        // 3. 検索処理とバリデーション
        // 検索ボタンが押された（パラメータが存在する）場合のみ判定
        if (entYearStr != null && classNum != null && subjectCd != null && numStr != null) {
            
            // いずれかの項目が未選択（"0"）の場合
            if (entYearStr.equals("0") || classNum.equals("0") || subjectCd.equals("0") || numStr.equals("0")) {
                // エラーメッセージをセット
                req.setAttribute("errors", "入学年度とクラスと科目を選択してください");
            } else {
                // すべて選択されている場合は通常の検索を実行
                int entYear = Integer.parseInt(entYearStr);
                int num = Integer.parseInt(numStr);
                
                Subject subject = sDao.get(subjectCd, school);
                TestDao tDao = new TestDao();
                
                // 検索結果を取得
                List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);
                
                // JSPの表示判定用にデータをセット
                req.setAttribute("tests", tests);
                req.setAttribute("subject", subject);
            }
        }

        // 4. プルダウン用データをセット
        req.setAttribute("ent_year_list", ent_year_list);
        req.setAttribute("class_num_list", class_list); 
        req.setAttribute("subject_list", subject_list);

        // 5. JSPへフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}