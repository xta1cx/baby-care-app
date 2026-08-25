package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.History;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        HttpSession session = request.getSession();

        List<History> historyList =
                (List<History>) session.getAttribute("historyList");
        
        // 指定されたIDの履歴をリストから削除
        historyList.removeIf(history -> history.getId() == id);
        
        // 削除後、履歴一覧画面へリダイレクト
        response.sendRedirect("history.jsp");
        }
 }