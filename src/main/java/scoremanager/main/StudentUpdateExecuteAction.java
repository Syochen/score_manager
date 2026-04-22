package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String isAttendStr = request.getParameter("is_attend"); 
        
        boolean isAttend = (isAttendStr != null); 

        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no); // 学生番号で現在のデータを取得
        
        if (student != null) {
            // 値をセットして更新
            student.setName(name);
            student.setClassNum(classNum);
            student.setAttend(isAttend);
            sDao.save(student); // StudentDaoのsaveはUPDATEも兼ねている
        }

        // ③ 設計書通りの完了画面へフォワード
        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    }
}