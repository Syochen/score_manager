package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. パラメータの取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 2. バリデーション
        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        // 科目コードのチェック
        if (cd == null || cd.length() != 3) {
            errors.put("cd", "科目コードは3文字で入力してください");
        } else {
            // 重複チェック
            Subject subject = sDao.get(cd, school);
            if (subject != null) {
                errors.put("cd", "科目コードが重複しています");
            }
        }

        // 3. 処理の分岐
        if (errors.isEmpty()) {
            // 保存処理
            Subject newSubject = new Subject();
            newSubject.setCd(cd);
            newSubject.setName(name);
            newSubject.setSchool(school);
            sDao.save(newSubject);
            
            // 完了画面へ
            request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
        } else {
            // エラーをリクエストにセットして入力画面に戻す
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);   // 入力値を保持
            request.setAttribute("name", name); 
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        }
    }
}