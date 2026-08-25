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
		
		// 入力画面からミルクの日時・量・メモを取得
		String milkDateTime = request.getParameter("milkDateTime");
		String milkAmount = request.getParameter("milkAmount");
		String milkMemo = request.getParameter("milkMemo");
		
		History milkHistory = null;
		
		// ミルクオブジェクト生成
		Milk milk = null;
		
		if (milkDateTime != null && !milkDateTime.isEmpty()
		        && milkAmount != null && !milkAmount.isEmpty()) {
			
		// 取得した日時・量・メモをMilkオブジェクトに設定
		milk = new Milk();
		
		LocalDateTime milkDate = LocalDateTime.parse(milkDateTime);
		String formattedMilkDate = milkDate.format(formatter);
		
		milk.setDateTime(formattedMilkDate);
		milk.setAmount(Integer.parseInt(milkAmount));
		milk.setMemo(milkMemo);
		
		// ミルクHistoryオブジェクトを生成
		milkHistory = new History();

		milkHistory.setDateTime(milk.getDateTime());
		milkHistory.setType("ミルク");
		milkHistory.setDetail(
			    milk.getAmount() + "ml" +
			    (milk.getMemo() != null && !milk.getMemo().isEmpty()
			        ? "<br>メモ：" + milk.getMemo()
			        : "")
			);
		
		milkHistory.setSortDateTime(milkDate);
		historyList.add(milkHistory);
		}
		
		// おむつのデータ取得
		String diaperDateTime = request.getParameter("diaperDateTime");
		String diaperType = request.getParameter("diaperType");
		String diaperMemo = request.getParameter("diaperMemo");
		
		// おむつオブジェクト生成
		Diaper diaper = new Diaper();

		// おむつの日時が入力されている場合のみ処理
		if (diaperDateTime != null && !diaperDateTime.isEmpty()) {

		    // 文字列をLocalDateTimeに変換
		    LocalDateTime diaperDate = LocalDateTime.parse(diaperDateTime);
		    String formattedDiaperDate = diaperDate.format(formatter);

		    // おむつオブジェクトに値をセット
		    diaper.setDateTime(formattedDiaperDate);
		    diaper.setType(diaperType);
		    diaper.setMemo(diaperMemo);

		// おむつHistoryオブジェクトの生成
		History diaperHistory = new History();

		diaperHistory.setDateTime(diaper.getDateTime());
		diaperHistory.setType("おむつ");
		diaperHistory.setDetail(
			    diaper.getType() +
			    (diaper.getMemo() != null && !diaper.getMemo().isEmpty()
			        ? "<br>メモ：" + diaper.getMemo()
			        : "")
			);
		
		diaperHistory.setSortDateTime(diaperDate);
		historyList.add(diaperHistory);
		}
		
		// 睡眠のデータ取得
		String sleepStart = request.getParameter("sleepStart");
		String sleepEnd = request.getParameter("sleepEnd");
		String sleepMemo = request.getParameter("sleepMemo");
		
		Sleep sleep = null;
		
		// 睡眠開始・終了時刻をLocalDateTimeに変換
		if (sleepStart != null && !sleepStart.isEmpty()
		        && sleepEnd != null && !sleepEnd.isEmpty()) {
		LocalDateTime start = LocalDateTime.parse(sleepStart);
		LocalDateTime end = LocalDateTime.parse(sleepEnd);
		
		// 睡眠オブジェクト生成
		sleep = new Sleep();

		// 日時を表示用の形式に変換
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
		
		// 睡眠Historyオブジェクトの生成
		History sleepHistory = new History();

		sleepHistory.setDateTime(sleep.getStart());
		sleepHistory.setType("睡眠");
		sleepHistory.setDetail(
			    sleep.getSleepTime() +
			    (sleep.getMemo() != null && !sleep.getMemo().isEmpty()
			        ? "<br>メモ：" + sleep.getMemo()
			        : "")
			);
		
		sleepHistory.setSortDateTime(start);
		historyList.add(sleepHistory); 
		}
		
		// 履歴を時系列順に並び替え
		Collections.sort(
		        historyList,
		        Comparator.comparing(History::getSortDateTime)
		);
		
		// 履歴に一意のIDを設定
		for (int i = 0; i < historyList.size(); i++) {
		    historyList.get(i).setId(i + 1);
		}
		
	// 各データのリストの作成
	List<Milk> milkList = new ArrayList<>();
	List<Diaper> diaperList = new ArrayList<>();
	List<Sleep> sleepList = new ArrayList<>();
	
	// 各データをリストに追加
	if (milk != null) {
	    milkList.add(milk);
	}
	
	if (diaperDateTime != null && !diaperDateTime.isEmpty()) {
	    diaperList.add(diaper);
	}
	
	if (sleep != null) {
	    sleepList.add(sleep);
	}
	
	// リクエストスコープに各リストを保存
	request.setAttribute("milkList", milkList);
	request.setAttribute("diaperList", diaperList);
	request.setAttribute("sleepList", sleepList);
	
	// 履歴をセッションに保存
	HttpSession session = request.getSession();
	session.setAttribute("historyList", historyList);
	
	// 履歴一覧画面へフォワード
	RequestDispatcher dispatcher =
			request.getRequestDispatcher("/history.jsp");
			dispatcher.forward(request, response);
	}
}