package tutorial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PoolEditorServlet
 */
@WebServlet(name = "poolEditorServlet", urlPatterns = { "/PoolEditor" })
public class PoolEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PoolEditorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html" );
        PrintWriter pw = response.getWriter();

        pw.println( "<html><head></head><body>" );
        printBody( pw );
        pw.println( "</body></html>" );
	}

	private void printBody(PrintWriter pw) {
		pw.println( "<form id='pool' method='POST'>" );
	    pw.println( "<table>" );
	    pw.println( "<tr><th>Home Team</th><th>Away Team</th><th>Tiebreaker?</th></tr>" );
	    for (int i = 0; i < 10; i++) {
	        pw.println( "<tr><td><input name='home" + i + "'></td>" );
	        pw.println( "<td><input name='away" + i + "'></td>" );
	        pw.println( "<td><input type='radio' name='tiebreaker' value='" + i + "'/></td></tr>" );
	    }
	    pw.println( "</table>" );
	    pw.println( "</form>" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
