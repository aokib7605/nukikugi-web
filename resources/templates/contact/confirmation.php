<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST['name'];
    $title = $_POST['title'];
    $contact = $_POST['contact'];
    $content = $_POST['content'];
}
?>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <title>劇団抜きにくい釘</title>
    <!-- Bootstrapの読み込み -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
</head>

<body>
    <div class="mainArea">
        <h1>お問い合わせ内容の確認</h1>
        <p>お名前: <?php echo $name; ?></p>
        <p>件名: <?php echo $title; ?></p>
        <p>ご連絡先: <?php echo $contact; ?></p>
        <p>本文: <?php echo $content; ?></p>

        <!-- 送信ボタン -->
        <form action="./mail.php" method="post">
            <input type="hidden" name="name" value="<?php echo $name; ?>">
            <input type="hidden" name="title" value="<?php echo $title; ?>">
            <input type="hidden" name="contact" value="<?php echo $contact; ?>">
            <input type="hidden" name="content" value="<?php echo $content; ?>">
            
            <p>
                内容に間違いがなければ、送信ボタンを押してください
            </p>
            
            <input type="submit" value="送信">
        </form>
        <p><a href="./contact.html">戻る</a></p>
    </div>
    
	<!-- Footer Start -->
	<div class="footer">
		<p th:text="${copyright}"></p>
	</div>
	<!-- Footer End -->

    <!-- Bootstrapの読み込み -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous">
        </script>
</body>

</html>