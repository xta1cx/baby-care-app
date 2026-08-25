<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
      
<title>履歴一覧</title>
</head>
<body>

<%-- 履歴一覧のタイトル --%>
<h1>📖 履歴一覧</h1>

<%-- 入力画面へ戻る --%>
<a href="${pageContext.request.contextPath}/input.jsp" class="back-button">
    ⬅ 入力画面へ戻る
</a>

<%-- 履歴がない場合 --%>
<c:if test="${empty historyList}">
    <div class="no-history">
    🛸<br><br>
    まだ履歴はありません<br>
    入力画面から記録してみましょう♪
</div>
</c:if>

<%-- 履歴一覧の表示 --%>
<c:forEach var="history" items="${historyList}">

<!-- 履歴カード -->
    <div class="card">
    
	<div class="history-date">
    📅 ${history.dateTime}
</div>

<%-- 履歴の種類を表示 --%>
<div class="history-type">

<c:choose>

    <c:when test="${history.type == 'ミルク'}">
        🍼 ミルク
    </c:when>

    <c:when test="${history.type == 'おむつ'}">
        💩 おむつ
    </c:when>

    <c:when test="${history.type == '睡眠'}">
        🌙 睡眠
    </c:when>

    <c:otherwise>
        ${history.type}
    </c:otherwise>
</c:choose>
</div>

<%-- 履歴の詳細 --%>
<div class="history-detail">
    ${history.detail}
</div>

<%-- 削除ボタン --%>
<div class="delete-area">
<form action="DeleteServlet" method="post">
<input type="hidden"
       name="id"
       value="${history.id}">
<input type="submit" value="🗑 削除 ">
</form>

</div>
</div>
</c:forEach>
</body>
</html>