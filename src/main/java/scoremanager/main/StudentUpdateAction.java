package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 2. リクエストパラメータ（学生番号）を取得
        String no = request.getParameter("no");

        // 3. DAOの準備
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // 4. 学生の詳細データを取得
        Student student = sDao.get(no);
        // 5. クラスの一覧を取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 6. レスポンス値をセット
        request.setAttribute("student", student);
        request.setAttribute("class_num_set", list);

        // 7. 学生変更画面（student_update.jsp）へフォワード
        request.getRequestDispatcher("student_update.jsp").forward(request, response);
    }
}