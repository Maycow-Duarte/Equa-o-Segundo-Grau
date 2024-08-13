package com.filters;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "EquacaoFilter", urlPatterns = { "/Equation" })
public class EquacaoFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String aParam = request.getParameter("a");
        String bParam = request.getParameter("b");
        String cParam = request.getParameter("c");

        if (aParam == null || aParam.isEmpty() || bParam == null || bParam.isEmpty() || cParam == null || cParam.isEmpty()) {
            displayFormWithErrorMessage(response, "Por favor, preencha todos os campos.");
            return;
        }

        double a = Double.parseDouble(aParam);
        double b = Double.parseDouble(bParam);
        double c = Double.parseDouble(cParam);
        double delta = b * b - 4 * a * c;

        if (a == 0) {
        	
            displayFormWithErrorMessage(response, "O coeficiente A nao pode ser igual a 0");
        } else if (delta < 0) {
            displayFormWithErrorMessage(response, "Nao existem raizes reais para essa equacao.");
        } else {
            chain.doFilter(request, response);
        }
    }
	

    private void displayFormWithErrorMessage(ServletResponse response, String errorMessage) throws IOException {
        
    	PrintWriter out = response.getWriter();
        out.println("<html lang=\"pt-br\"><head><meta charset=\"UTF-8\"><title>Equação do Segundo Grau</title>");
        out.println("<html><body>");
        out.println("<p>" + errorMessage + "</p>");
        out.println("<form action='Equation' method='post'>");
        out.println("<label for='A'>a:</label><br>");
        out.println("<input type='number' id='a' name='a'><br>");
        out.println("<label for='B'>b:</label><br>");
        out.println("<input type='number' id='b' name='b'><br>");
        out.println("<label for='C'>c:</label><br>");
        out.println("<input type='number' id='c' name='c'><br>");
        out.println("<input type='submit' value='CALCULAR'>");
        out.println("</form>");
        out.println("</body></html>");
        
        out.println("<html lang=\"pt-br\"><head><meta charset=\"UTF-8\"><title>Equação do Segundo Grau</title>");
        out.println("<style>");
        out.println("body { "
        		+ " font-family: Arial, sans-serif; background-color: #f2f2f2;"
        		+ " display: flex;"
        		+ " justify-content: center;"
        		+ " align-items: center;"
        		+ " height: 100vh;"
        		+ " margin: 0; "
        		+ " flex-direction: column;"
        		+ " background-image: url('sea-6948569.jpeg');"
        		+ " background-size: cover;"
        		+ " background-position: center; "
        		+ "}");
        out.println("form {"
        		+ " background: #ffffff00; "
        		+ " border-radius: 20px;"
        		+ " box-shadow: -1px 1px 15px, 10px 10px 10px;"
        		+ " z-index: 999999;"
        		+ " backdrop-filter: blur(3px);"
        		+ " padding: 20px;"
        		+ " border-radius: 10px;"
        		+ " box-shadow: 1 0 10px rgba(0, 0, 0, 0.1); "
        		+ "}");
        out.println("label { "
        		+ " display: block;"
        		+ " margin-bottom: 5px;"
        		+ " font-weight: bold;"
        		+ "}");
        out.println("input[type='number'] {"
        		+ " width: 100%;"
        		+ " padding: 8px;"
        		+ " margin-bottom: 15px;"
        		+ " border: 1px solid #ccc;"
        		+ " border-radius: 5px;"
        		+ " box-sizing: border-box; "
        		+ "}");
        out.println("input[type='submit'] { "
        		+ " background-color: #4CAF50;"
        		+ " color: white;"
        		+ " padding: 10px 20px;"
        		+ " border: none;"
        		+ " border-radius: 5px;"
        		+ " cursor: pointer;"
        		+ " font-size: 16px; "
        		+ "}");
        out.println("input[type='submit']:hover { "
        		+ " background-color: #45a049; "
        		+ "}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<form action='Equation' method='post'>");
        
        
        
    }
}