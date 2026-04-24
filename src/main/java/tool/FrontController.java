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
            // パスを取得
            String path = request.getServletPath().substring(1);
            
            // ファイル名部分をクラス名に変換
            String name = path.replace(".a", "A").replace('/', '.');
            
            // クラス名からインスタンスを生成して実行
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
            action.execute(request, response);
            
        } catch (Exception e) {
            // エラーをログ（コンソール）に出力
            e.printStackTrace();
            
            // 画像の指示通り、エラーが発生した時にエラーページへ飛ばす
            // パスは環境に合わせて調整してね（URLが localhost:8080/kadai/error.jsp なら以下でOK）
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POSTリクエストもGETと同じ処理を行う
        doGet(request, response);
    }
}