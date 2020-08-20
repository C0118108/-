package sortList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Show
 */
@WebServlet("/kadai/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
        Statement stmt = null;

		try {
			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Show</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>商品の一覧</h3>");

			//testdbデータベースへの接続
			String driverUrl = "jdbc:derby:C:\\JavaDB\\kadai;create=false";

			//コネクションの確立
			con = DriverManager.getConnection(driverUrl, "db", "db");

			//ステートメントの作成
			stmt = con.createStatement();

			//実行するSQL文を記述
			String sql = "select * from T_YAOYA";

			//SQLを実行してResultSetの形式で結果を取得
			ResultSet rs = stmt.executeQuery(sql);

			List<Yaoya> ylist = new ArrayList<>();

            //データベースから値を取得して出力
            while (rs.next()) {
                //1つのレコードの情報を格納するインスタンスstdを作成
                Yaoya yao = new Yaoya();

                //stdにレコードの値をセット
                yao.setFood_id(rs.getInt("food_id"));
                yao.setName(rs.getString("name"));
                yao.setType_id(rs.getInt("type_id"));
                yao.setCost(rs.getInt("cost"));

                //リストにstdを追加
                ylist.add(yao);
            }

        	/*out.println("<table border=\"1\">");
			//取得したレコードを一つずつ処理
			while (rs.next()) {
			    out.println("<tr>");
			    out.println("<td>" + rs.getInt("food_id") + "</td>");
			    out.println("<td>" + rs.getString("name") + "</td>");
			    out.println("<td>" + rs.getInt("type_id") + "</td>");
			    out.println("<td>" + rs.getInt("cost") + "</td>");
			    out.println("</tr>");
			}
			out.println("</table>");

			//メモリの開放
			rs.close();

			out.println("<p><a href=\"Top.html\">戻る</a></p>");
			*/

            request.setAttribute("ylist", ylist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Show.jsp");
			dispatcher.forward(request, response);

			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
            //サーブレット内での例外をアプリケーションのエラーとして表示
            throw new ServletException(e);
        } finally {
            //例外が発生する・しないにかかわらず確実にデータベースから切断
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
