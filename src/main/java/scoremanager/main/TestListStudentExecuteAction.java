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
        req.setAttribute("f4", studentNo); // 入力した番号を保持

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // プルダウン等の共通データ準備（TestListActionにある共通処理）
        TestListAction.prepareCommonData(req, teacher);

        if (studentNo != null && !studentNo.isEmpty()) {
            StudentDao sDao = new StudentDao();
            Student student = sDao.get(studentNo);

            req.setAttribute("done_st", true); // 検索実行フラグ

            // 学生が存在し、かつ学校コードが取得できるかチェック（NullPointerException対策）
            if (student != null && student.getSchool() != null && 
                student.getSchool().getCd().equals(teacher.getSchool().getCd())) {
                
                req.setAttribute("student", student);

                TestListStudentDao tlsDao = new TestListStudentDao();
                List<TestListStudent> tests = tlsDao.filter(student);
                
                if (tests != null && !tests.isEmpty()) {
                    // --- ここから統計情報の計算 ---
                    int max = -1;
                    int min = 101;
                    int sum = 0;
                    int count = 0;

                    for (TestListStudent t : tests) {
                        int p = t.getPoint();
                        if (p >= 0) { // 点数が有効な場合のみ計算
                            if (p > max) max = p;
                            if (p < min) min = p;
                            sum += p;
                            count++;
                        }
                    }

                    if (count > 0) {
                        double avg = (double) sum / count;
                        req.setAttribute("avg", String.format("%.1f", avg)); // 小数点1位まで
                        req.setAttribute("max", max);
                        req.setAttribute("min", min);
                    }
                    // --- 統計情報計算ここまで ---
                }

                req.setAttribute("tests_student", tests);
                
            } else {
                // 学生が見つからない、または学校が異なる、またはSchool情報が欠損している場合
                req.setAttribute("errors", "学生情報が存在しませんでした");
            }
        } else {
            req.setAttribute("errors", "学生番号を入力してください");
        }

        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}