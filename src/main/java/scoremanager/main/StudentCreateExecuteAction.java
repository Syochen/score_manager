package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        StudentDao sDao = new StudentDao();
        Map<String, String> errors = new HashMap<>();

     // 1. 入学年度のチェック
        int entYear = 0;
        if (entYearStr == null || entYearStr.equals("0") || entYearStr.isEmpty()) {
            errors.put("ent_year", "入学年度を選択してください");
        } else {
            entYear = Integer.parseInt(entYearStr);
        }

        // 2. 学生番号の重複チェック
        if (errors.isEmpty()) {
            Student existing = sDao.get(no);
            if (existing != null) {
                // シーケンス図通りのエラーメッセージ
                errors.put("no", "学生番号が重複しています");
            }
        }

        // エラーがあったら登録画面に戻る
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            // ※本来はクラス一覧なども再取得が必要なので StudentCreateAction を呼び直すのが理想
            new StudentCreateAction().execute(request, response);
            return;
        }

        // 3. 登録処理
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true); // 新規登録時は在学中に設定
        student.setSchool(teacher.getSchool());

        sDao.save(student);

        // 4. 登録完了画面へ
        request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
    }
}