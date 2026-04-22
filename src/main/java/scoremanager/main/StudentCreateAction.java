package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // クラス一覧を取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 入学年度のリスト（現在年-10年 〜 現在年）を作成
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // リクエストにセット
        request.setAttribute("class_num_set", list);
        request.setAttribute("ent_year_set", entYearSet);

        // 学生登録画面へ
        request.getRequestDispatcher("student_create.jsp").forward(request, response);
    }
}