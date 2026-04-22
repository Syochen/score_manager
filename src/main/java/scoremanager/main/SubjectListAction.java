package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからログインユーザー（Teacher）の情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // 先生が所属している学校を取得
        School school = teacher.getSchool();
        
        // DAOを使って、その学校の科目一覧を取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(school);
        
        // リクエスト属性にセットしてJSPへ渡す
        request.setAttribute("subjects", subjects);
        
        // JSPへフォワード
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}