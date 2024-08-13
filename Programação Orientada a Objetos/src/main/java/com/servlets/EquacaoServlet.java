package com.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EquacaoServlet", value = "/Equation")
public class EquacaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double a = Double.parseDouble(request.getParameter("a"));
        double b = Double.parseDouble(request.getParameter("b"));
        double c = Double.parseDouble(request.getParameter("c"));
        double delta = b * b - 4 * a * c;

        String result;
        if (delta < 0) {
            result = "A equação não possui raizes reais.";
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);

            HttpSession session = request.getSession();
            Double sumX1 = (Double) session.getAttribute("sumX1");
            Double sumX2 = (Double) session.getAttribute("sumX2");

            String equation = a + "x² + " + b + "x + " + c + " = 0";
            result = "x1: " + x1 + ", x2: " + x2;

            if (sumX1 == null || sumX2 == null) {
                session.setAttribute("sumX1", x1);
                session.setAttribute("sumX2", x2);
                session.setAttribute("equation1", equation);
                session.setAttribute("result1", result);
            } else {
                session.setAttribute("sumX1", sumX1 + x1);
                session.setAttribute("sumX2", sumX2 + x2);
                session.setAttribute("equation2", equation);
                session.setAttribute("result2", result);
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html lang=\"pt-br\"><head><meta charset=\"UTF-8\"><title>Equação do Segundo Grau</title>");
            out.println("<style>");
            out.println("body {"
                    + " font-family: Arial, sans-serif;"
                    + " background-color: #f2f2f2;"
                    + " display: flex;"
                    + " justify-content: center;"
                    + " align-items: center;"
                    + " height: 100vh;"
                    + " margin: 0;"
                    + " flex-direction: column;"
                    + " background-image: url('sea-6948569.jpeg');"
                    + " background-size: cover;"
                    + " background-position: center; "
                    + "}");
            out.println("form { "
                    + " background: #ffffff00;"
                    + " border-radius: 20px;"
                    + " box-shadow: -1px 1px 15px, 10px 10px 10px;"
                    + " z-index: 999999;"
                    + " backdrop-filter: blur(3px);"
                    + " padding: 20px;"
                    + " border-radius: 10px;"
                    + " box-shadow: 1 0 10px rgba(0, 0, 0, 0.1); "
                    + "}");
            out.println("label { display: block; margin-bottom: 5px; font-weight: bold; }");
            out.println("input[type='number'] { width: 100%; padding: 8px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; }");
            out.println(".button { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }");
            out.println(".button:hover { background-color: #45a049; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h1>Calculadora de equação de Segundo Grau</h1>");

            if (session.getAttribute("equation2") != null) {
                out.println("<p>Primeira Equação: " + session.getAttribute("equation1") + "</p>");
                out.println("<p>Resultado da Primeira Equação: " + session.getAttribute("result1") + "</p>");
                out.println("<p>Segunda Equação: " + session.getAttribute("equation2") + "</p>");
                out.println("<p>Resultado da Segunda Equação: " + session.getAttribute("result2") + "</p>");
                out.println("<p>A Soma dos x1 é: " + session.getAttribute("sumX1") + "</p>");
                out.println("<p>A Soma dos x2 é: " + session.getAttribute("sumX2") + "</p>");
                out.println("<button class='button' onclick='window.location.href=\"index.html\"'>Voltar ao início</button>");

                // Reset the session values after displaying the results of two equations
                session.setAttribute("sumX1", null);
                session.setAttribute("sumX2", null);
                session.setAttribute("equation1", null);
                session.setAttribute("equation2", null);
                session.setAttribute("result1", null);
                session.setAttribute("result2", null);
            } else {
                out.println("<p>A equação é: " + equation + "</p>");
                out.println("<p>O resultado é: " + result + "</p>");
                out.println("<p>Digite uma segunda equação:</p>");
                out.println("<form action='Equation' method='post'>");
                out.println("<label for='a'>A:</label><br>");
                out.println("<input type='number' id='a' name='a' required><br>");
                out.println("<label for='b'>B:</label><br>");
                out.println("<input type='number' id='b' name='b' required><br>");
                out.println("<label for='c'>C:</label><br>");
                out.println("<input type='number' id='c' name='c' required><br>");
                out.println("<input class='button' type='submit' value='Calcular'>");
                out.println("</form>");
            }

            out.println("</body></html>");
        }
    }
}
