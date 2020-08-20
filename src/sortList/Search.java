package sortList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/kadai/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
        Statement stmt = null;
        PreparedStatement ps = null;

		try {
			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Search</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>検索結果</h3>");

			//testdbデータベースへの接続
			String driverUrl = "jdbc:derby:C:\\JavaDB\\kadai;create=false";

			//コネクションの確立
			con = DriverManager.getConnection(driverUrl, "db", "db");

			//ステートメントの作成
			stmt = con.createStatement();


			//入力されたstudent_idを取得
			String food_id = request.getParameter("food_id");

			//実行するSQL文を記述
			String sql = "select * from T_YAOYA where food_id=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(food_id));

			//SQLを実行してResultSetの形式で結果を取得
			//ResultSet rs = stmt.executeQuery(sql);
			ResultSet rs = ps.executeQuery();

			out.println("<table border=\"1\">");
			//取得したレコードを一つずつ処理
			while (rs.next()) {
			    out.println("<tr>");
			    out.println("<td>" + rs.getInt("food_id") + "</td>");
			    out.println("<td>" + rs.getString("name") + "</td>");
			    out.println("<td>" + rs.getString("type_id") + "</td>");
			    out.println("<td>" + rs.getInt("cost") + "</td>");
			    out.println("</tr>");
			}
			out.println("</table>");

			//メモリの開放
			rs.close();

			out.println("<p><a href=\"Top.html\">戻る</a></p>");

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
            if (ps != null) {
                try {
                    ps.close();
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
