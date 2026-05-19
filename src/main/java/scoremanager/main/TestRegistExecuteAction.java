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

        // 検索条件をhiddenパラメータから取得（再表示用にf1も取得）
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");

        // 回数を数値に変換
        int num = Integer.parseInt(numStr);

        // SubjectDaoを使用してSubjectオブジェクトを完全に取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        List<Test> tests = new ArrayList<>();
        
        //サーバー側での点数範囲バリデーションチェック
        java.util.Enumeration<String> checkNames = req.getParameterNames();
        while (checkNames.hasMoreElements()) {
            String name = checkNames.nextElement();
            if (name.startsWith("point_")) {
                String pointStr = req.getParameter(name);
                
                if (pointStr != null && !pointStr.isEmpty()) {
                    int point = Integer.parseInt(pointStr);
                    
                    // 0未満、または100を超える数値が1つでもあればエラー
                    if (point < 0 || point > 100) {
                        // サーバー側から画面（JSP）に渡すエラーメッセージを設定
                        req.setAttribute("errors", "点数は0～100の範囲で入力してください");
                        
                        // 入力画面を正しく再表示するために、現在の検索条件で生徒一覧を再取得
                        int entYear = Integer.parseInt(entYearStr);
                        TestDao tDao = new TestDao();
                        List<Test> errorTestList = tDao.filter(entYear, classNum, subject, num, school);
                        
                        // 画面描画に必要なデータをリクエストに再セット
                        req.setAttribute("tests", errorTestList);
                        req.setAttribute("subject", subject);
                        
                        // 保存はせずに、エラー情報を持ったまま入力画面（JSP）へ送り返す
                        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
                        return; // ここで処理を終了（登録処理へ進ませない）
                    }
                }
            }
        }

        // データの構築（エラーチェックをすべて通過した場合のみ実行される）
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