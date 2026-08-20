<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
<title>BabyCare</title>

</head>
<body>
<h1>BabyCare</h1>
<p class="subtitle">
    赤ちゃんの毎日をかんたん記録♪
</p>

<div class="container">
<form action="RegisterServlet" method="post">

<div class="card">
<h2>🍼 ミルク</h2>

<div class="row">

<label>日時</label>

<input type="datetime-local"
       name="milkDateTime"><br><br>
</div>

<div class="row">

<label>ミルク量</label>
<select name="milkAmount">
    <option value="">選択してください</option>
    <option value="10">10ml</option>
    <option value="20">20ml</option>
    <option value="30">30ml</option>
    <option value="40">40ml</option>
    <option value="50">50ml</option>
    <option value="60">60ml</option>
    <option value="70">70ml</option>
    <option value="80">80ml</option>
    <option value="90">90ml</option>
    <option value="100">100ml</option>
    <option value="110">110ml</option>
    <option value="120">120ml</option>
    <option value="130">130ml</option>
    <option value="140">140ml</option>
    <option value="150">150ml</option>
    <option value="160">160ml</option>
    <option value="170">170ml</option>
    <option value="180">180ml</option>
    <option value="190">190ml</option>
    <option value="200">200ml</option>
    <option value="210">210ml</option>
    <option value="220">220ml</option>
    <option value="230">230ml</option>
    <option value="240">240ml</option>
    <option value="250">250ml</option>
</select><br><br>
</div>

<div class="row">

<label>メモ</label>
<textarea name="milkMemo" rows="2" cols="20"></textarea><br><br>

</div>
</div>

<div class="card">
<h2>💩 おむつ</h2>

<div class="row">
    <label>日時</label>
    <input type="datetime-local" name="diaperDateTime">
</div>

<div class="row">
    <label>種類</label>

    <div class="radio-group">
        <label>
            <input type="radio" name="diaperType" value="おしっこ">
            おしっこ
        </label>

        <label>
            <input type="radio" name="diaperType" value="うんち">
            うんち
        </label>
    </div>
 </div>
    
<div class="row">
    <label>メモ</label>
    <textarea name="diaperMemo" rows="3"></textarea>
</div>
</div>

<div class="card">
<h2>🌙 睡眠</h2>

<div class="row">
    <label>開始時刻</label>
    <input type="datetime-local" name="sleepStart">
</div>

<div class="row">
    <label>終了時刻</label>
    <input type="datetime-local" name="sleepEnd">
</div>

<div class="row">
    <label>メモ</label>
    <textarea name="sleepMemo" rows="3"></textarea>
</div>

<div class="button-area">
    <input type="submit" value="💫 登録する 💫">
</div>

</form>
</div>
</body>
</html>