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

<div class="mainArea">

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $name = $_POST['name'];
        $title = $_POST['title'];
        $contact = $_POST['contact'];
        $content = $_POST['content'];

        // メール送信
        $to = "nukikugi@gmail.com";
        $subject = "お問い合わせがありました";
        $message = "お名前: $name\n";
        $message .= "件名: $title\n";
        $message .= "ご連絡先: $contact\n";
        $message .= "本文: $content\n";
        $headers = "From: contact@nukikugi.com" . "\r\n" .
            "Reply-To: contact@nukikugi.com" . "\r\n" .
            "X-Mailer: PHP/" . phpversion();

        if (mail($to, $subject, $message, $headers)) {
            echo "<script>alert('送信完了しました');</script>"; // ブラウザに送信完了メッセージを表示
        } else {
            echo "<script>alert('送信エラーが発生しました');</script>"; // ブラウザに送信エラーメッセージを表示
        }
    }
    ?>
    <p><a href="./contact.html"><button>戻る</button></a></p>
</div>

<div class="footerArea">
    <p>Copyright @2024 Nukinikui Kugi All Rights Reserved.</p>
</div>

<!-- Bootstrapの読み込み -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous">
</script>
</body>

</html>