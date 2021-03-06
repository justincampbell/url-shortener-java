import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class Web extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    resp.getWriter().print("Hello from Java!\n");
    }

  public static void main(String[] args) throws Exception{
    Server server = new Server(port());
    ServletContextHandler context =
      new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new Web()),"/*");
    server.start();
    server.join();   
  }

  private static int port() {
    String port = System.getenv("PORT");

    if (port == null) {
      return 3000;
    } else {
      return Integer.valueOf(port);
    }
  }
}
