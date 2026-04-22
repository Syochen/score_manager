package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // パスを取得（例：scoremanager/main/StudentList.action）
            String path = request.getServletPath().substring(1);
            
            // ファイル名部分をクラス名に変換（例：StudentListAction）
            String name = path.replace(".a", "A").replace('/', '.');
            
            // クラス名からインスタンスを生成して実行
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
            action.execute(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // エラー時はとりあえずエラーページへ（必要に応じて）
            // request.getRequestDispatcher("/common/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POSTリクエストもGETと同じ処理を行う
        doGet(request, response);
    }
}