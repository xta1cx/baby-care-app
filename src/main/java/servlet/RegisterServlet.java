package servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Diaper;
import model.History;
import model.Milk;
import model.Sleep;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<History> historyList = new ArrayList<>();
		
	    // 日付日時の表示形式
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		
		// ミルクのデータ取得
		String milkDateTime = request.getParameter("milkDateTime");
		String milkAmount = request.getParameter("milkAmount");
		String milkMemo = request.getParameter("milkMemo");
		
		// Milkオブジェクト生成
		Milk milk = new Milk();
		
		// 値をセット
		LocalDateTime milkDate = LocalDateTime.parse(milkDateTime);
		String formattedMilkDate = milkDate.format(formatter);
		
		milk.setDateTime(formattedMilkDate);
		milk.setAmount(Integer.parseInt(milkAmount));
		milk.setMemo(milkMemo);
		
		// データ確認
		System.out.println(milk.getDateTime());
		System.out.println(milk.getAmount());
		System.out.println(milk.getMemo());
		
        // ミルクのデータ出力
		System.out.println(milkDateTime);
		System.out.println(milkAmount);
		System.out.println(milkMemo);
		
		// ミルクHistoryオブジェクト追加
		History milkHistory = new History();

		milkHistory.setDateTime(milk.getDateTime());
		milkHistory.setType("ミルク");
		milkHistory.setDetail(milk.getAmount() + "ml");
		
		milkHistory.setSortDateTime(milkDate);
		historyList.add(milkHistory);
		
		// おむつのデータ取得
		String diaperDateTime = request.getParameter("diaperDateTime");
		String diaperType = request.getParameter("diaperType");
		String diaperMemo = request.getParameter("diaperMemo");
		
		// おむつオブジェクト生成
		Diaper diaper = new Diaper();

		// 値をセット
		LocalDateTime diaperDate = LocalDateTime.parse(diaperDateTime);
		String formattedDiaperDate = diaperDate.format(formatter);

		diaper.setDateTime(formattedDiaperDate);
		diaper.setType(diaperType);
		diaper.setMemo(diaperMemo);

		// データ確認
		System.out.println(diaper.getDateTime());
		System.out.println(diaper.getType());
		System.out.println(diaper.getMemo());
		
        // おむつのデータ出力
		System.out.println(diaperDateTime);
		System.out.println(diaperType);
		System.out.println(diaperMemo);
		
		// おむつHistoryオブジェクトの生成
		History diaperHistory = new History();

		diaperHistory.setDateTime(diaper.getDateTime());
		diaperHistory.setType("おむつ");
		diaperHistory.setDetail(diaper.getType());
		
		diaperHistory.setSortDateTime(diaperDate);
		historyList.add(diaperHistory);
		
		// 睡眠のデータ取得
		String sleepStart = request.getParameter("sleepStart");
		String sleepEnd = request.getParameter("sleepEnd");
		String sleepMemo = request.getParameter("sleepMemo");
		
		// String → LocalDateTime
		LocalDateTime start = LocalDateTime.parse(sleepStart);
		LocalDateTime end = LocalDateTime.parse(sleepEnd);
		
		// sleepオブジェクト生成
		Sleep sleep = new Sleep();

		// 値をセット
		String formattedStart = start.format(formatter);
		String formattedEnd = end.format(formatter);

		sleep.setStart(formattedStart);
		sleep.setEnd(formattedEnd);
		sleep.setMemo(sleepMemo);
		
		// 睡眠時間自動計算
		Duration duration = Duration.between(start, end);

		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		
		// 睡眠時間自動計算結果出力
		sleep.setSleepTime(hours + "時間" + minutes + "分");
		
		// データ確認
		System.out.println(sleep.getStart());
		System.out.println(sleep.getEnd());
		System.out.println(sleep.getSleepTime());
		System.out.println(sleep.getMemo());
		
        // 睡眠のデータ出力
		System.out.println(sleepStart);
		System.out.println(sleepEnd);
		System.out.println(sleepMemo);
		
		// 睡眠Historyオブジェクトの生成
		History sleepHistory = new History();

		sleepHistory.setDateTime(sleep.getStart());
		sleepHistory.setType("睡眠");
		sleepHistory.setDetail(sleep.getSleepTime());
		
		sleepHistory.setSortDateTime(start);
		historyList.add(sleepHistory);
		
		// 履歴を時系列順に並び替え
		Collections.sort(
		        historyList,
		        Comparator.comparing(History::getSortDateTime)
		);
		
		// 登録
		System.out.println("登録ボタンが押されました");
		
	// 各IDのセット	
    milkHistory.setId(1);
	diaperHistory.setId(2);
	sleepHistory.setId(3);
		
	// リストの作成
	List<Milk> milkList = new ArrayList<>();
	List<Diaper> diaperList = new ArrayList<>();
	List<Sleep> sleepList = new ArrayList<>();
	
	// リストに追加
	milkList.add(milk);
	diaperList.add(diaper);
	sleepList.add(sleep);
	
	// リクエストスコープに保存
	request.setAttribute("milkList", milkList);
	request.setAttribute("diaperList", diaperList);
	request.setAttribute("sleepList", sleepList);
	HttpSession session = request.getSession();
	session.setAttribute("historyList", historyList);
	
	// フォワード
	RequestDispatcher dispatcher =
			request.getRequestDispatcher("/history.jsp");

			dispatcher.forward(request, response);
	}
}