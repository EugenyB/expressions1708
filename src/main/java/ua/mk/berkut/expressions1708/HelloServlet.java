package ua.mk.berkut.expressions1708;

import ua.mk.berkut.expressions1708.beans.ExpressionBean;
import ua.mk.berkut.expressions1708.dao.ExpressionDao;
import ua.mk.berkut.expressions1708.service.ExpressionService;
import ua.mk.berkut.expressions1708.tables.MyExpression;

import java.io.*;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = {"/calculate","/delete", "/error", "/edit"})
public class HelloServlet extends HttpServlet {

    private ExpressionService es;

    private ExpressionDao ed;

    @Resource(name = "jdbc/expressions")
    private DataSource ds;

    @Override
    public void init() {
        es = new ExpressionService();
        ed = new ExpressionDao(ds);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getRequestURI().endsWith("/calculate")) {
            showAll(request, response);
        } else if (request.getRequestURI().endsWith("/delete")) {
            deleteExpression(request, response);
        } else if (request.getRequestURI().endsWith("/edit")) {
            processEdit(request, response);
        } else { // error
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void processEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<MyExpression> e = ed.findById(id);
        if (e.isPresent()) {
            ExpressionBean bean = new ExpressionBean();
            bean.setExpression(e.get());
            request.setAttribute("expressionBean", bean);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("calculate");
        }
    }

    private void deleteExpression(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ed.deleteById(id);
            response.sendRedirect("calculate");
        } catch (Exception e) {
            response.sendRedirect("error");
        }

    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExpressionBean bean = new ExpressionBean();
        List<MyExpression> expressions = ed.findAll();
        bean.setExpressions(expressions);
        bean.setMessage("All expressions");
        request.setAttribute("expressionBean", bean);
        request.getRequestDispatcher("/view.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String expr = request.getParameter("expr");
            try {
                double v = es.calculate(expr);
                ed.updateById(id, expr, v);
            } catch (RuntimeException ignored) {

            }
            response.sendRedirect("calculate");
        } else {
            addExpression(request, response);
        }
    }

    private void addExpression(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExpressionBean bean = new ExpressionBean();
        String expression = request.getParameter("expression");
        try {
            bean.setCurrent(expression);
            double v = es.calculate(expression);
            ed.saveToDB(expression, v);
            bean.setMessage("Result = " + v);
        } catch (RuntimeException e) {
            bean.setMessage("Error in expression");
        }
        request.setAttribute("expressionBean", bean);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    public void destroy() {
    }
}