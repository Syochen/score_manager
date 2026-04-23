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

public class SubjectUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. パラメータ（科目コード）を取得
        String cd = request.getParameter("cd");

     // --- (略) ---

        // 2. DAOでその科目の現在のデータを取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, school);

        // --- 修正: 存在チェックと、コードのセット ---
        if (cd != null && subject == null) {
            // データがない場合はエラーメッセージをセット
            Map<String, String> errors = new HashMap<>();
            errors.put("at_error", "科目が存在しません");
            request.setAttribute("errors", errors);
            
            // お手本のようにコードを表示させるため、仮のSubjectを作るか、
            // そのままcdをリクエストにセットする。ここでは仮Beanを作るのが手っ取り早い。
            subject = new Subject();
            subject.setCd(cd); // パラメータのコードだけセット
        }
        // -----------------------

        // 3. リクエスト属性にセットしてJSPへ
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
// --- (略) ---
    }
}