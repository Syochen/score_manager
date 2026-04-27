package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザーの学校情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 検索条件をhiddenパラメータから取得
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");
        String classNum = req.getParameter("f2");

        // 回数を数値に変換
        int num = Integer.parseInt(numStr);

        // SubjectDaoを使用してSubjectオブジェクトを完全に取得
        // これを怠るとTestDaoの保存処理でエラー（NullPointerException）になります
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        List<Test> tests = new ArrayList<>();
        java.util.Enumeration<String> names = req.getParameterNames();
        
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            // JSPの入力欄 "point_学生番号" を抽出
            if (name.startsWith("point_")) {
                String studentNo = name.replace("point_", "");
                String pointStr = req.getParameter(name);

                // 点数が入力されている（空でない）場合のみ、保存リストに追加
                if (pointStr != null && !pointStr.isEmpty()) {
                    Test test = new Test();
                    
                    // 学生番号のみセットしたStudentオブジェクトを作成
                    Student student = new Student();
                    student.setNo(studentNo);
                    
                    // TestDao.saveが内部で参照する項目をすべてセット
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(num);
                    test.setPoint(Integer.parseInt(pointStr));
                    test.setClassNum(classNum);
                    
                    tests.add(test);
                }
            }
        }

        // TestDaoを呼び出してデータベースに保存
        TestDao tDao = new TestDao();
        tDao.save(tests, school);

        // 保存完了画面へ遷移
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}