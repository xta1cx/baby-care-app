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

        System.out.println(id);
        
        HttpSession session = request.getSession();

        List<History> historyList =
                (List<History>) session.getAttribute("historyList");
        // 削除
        historyList.removeIf(history -> history.getId() == id);
        
        // 履歴一覧画面へ戻る
        response.sendRedirect("history.jsp");
        }
 }