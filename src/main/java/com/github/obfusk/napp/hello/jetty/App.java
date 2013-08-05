package com.github.obfusk.napp.hello.jetty;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class App extends HttpServlet
{

  protected void doGet(HttpServletRequest req,
                       HttpServletResponse res)
    throws ServletException, IOException
  {                                                           //  {{{1
    String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
      .format(new Date());
    res.setContentType("text/plain");
    res.getWriter().print(
      "Hello World!" + "\n" +
      "The time is: " + now + "\n" +
      "\n" +
      "- napp-hello-jetty" + "\n"
    );
  }                                                           //  }}}1

  public static void main(String[] args)
    throws Exception
  {                                                           //  {{{1
    int p = 8888; String pe = System.getenv("PORT");
    if (args.length > 0) {
      p = Integer.parseInt(args[0]);
    } else if (pe != null && !pe.isEmpty()) {
      p = Integer.parseInt(pe);
    }
    Server srv = new Server(p);
    ServletContextHandler ctx =
      new ServletContextHandler(ServletContextHandler.SESSIONS);
    ctx.setContextPath("/"); srv.setHandler(ctx);
    ctx.addServlet(new ServletHolder(new App()), "/*");
    srv.start(); srv.join();
  }                                                           //  }}}1

}
