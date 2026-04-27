package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 学生別検索画面（実際にはtest_list.jspでタブを切り替えて表示）へ遷移
        // student_list_flagなどを立てて、JSP側で学生番号入力欄を表示させる
        req.setAttribute("isStudent", true); 
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}