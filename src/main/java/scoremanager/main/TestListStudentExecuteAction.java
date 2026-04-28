package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentNo = req.getParameter("f4");
        req.setAttribute("f4", studentNo); 

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        TestListAction.prepareCommonData(req, teacher);

        if (studentNo != null && !studentNo.isEmpty()) {
            StudentDao sDao = new StudentDao();
            Student student = sDao.get(studentNo);

            req.setAttribute("done_st", true); 

            // 学生が存在し、かつ先生と同じ学校かチェック
            if (student != null && student.getSchool() != null && 
                student.getSchool().getCd().equals(teacher.getSchool().getCd())) {
                
                req.setAttribute("student", student);

                // 成績リストの取得
                TestListStudentDao tlsDao = new TestListStudentDao();
                List<TestListStudent> tests = tlsDao.filter(student);
                
                // JSP側の名前 "${tests_student}" に合わせる
                req.setAttribute("tests_student", tests);

                if (tests != null && !tests.isEmpty()) {
                    int max = -1;
                    int min = 101;
                    int sum = 0;
                    int count = 0;

                    for (TestListStudent t : tests) {
                        int p = t.getPoint();
                        if (p >= 0) { 
                            if (p > max) max = p;
                            if (p < min) min = p;
                            sum += p;
                            count++;
                        }
                    }

                    if (count > 0) {
                        double avg = (double) sum / count;
                        // 四捨五入して小数点1位まで
                        req.setAttribute("avg", String.format("%.1f", avg)); 
                        req.setAttribute("max", max);
                        req.setAttribute("min", min);
                    }
                }
            } else {
                req.setAttribute("errors", "学生情報が存在しませんでした");
            }
        } else {
            req.setAttribute("errors", "学生番号を入力してください");
        }

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}