<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Моя тестовая страница</title>
    <META HTTP-EQUIV="Content-type" CONTENT="video/mp4:">
    <style>
        .video-container {
            position: relative;
            padding-bottom: 56.25%;
            height: 0;
            overflow: hidden;
        }
        .video-container video {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border-width: 0;
            outline-width: 0;
        }
    </style>
</head>
<body>
    <section>
        <div class="video-container">
            <video autoplay muted loop>
                <source src="greeting.mp4" type="video/mp4" codecs="avc1.42E01E, mp4a.40.2">
            </video>
        </div>
    </section>
    <section>
        <ul>
            <li><a href="/tutors">Преподаватели</a></li>
            <li><a href="/courses">Курсы</a></li>
            <li><a href="/events">Мероприятия</a></li>
            <li><a href="/price">Цены</a></li>
            <li><a href="/profile">Профиль</a></li>
            <li><a href="/contact_info">Контакты</a></li>
        </ul>
    </section>
    <script>
        var $video  = $('video'),
            $window = $(window);

        $(window).resize(function() {
            var height = $window.height();
            $video.css('height', height);

            var videoWidth = $video.width(),
                windowWidth = $window.width(),
                marginLeftAdjust =   (windowWidth - videoWidth) / 2;

            $video.css({
                'height': height,
                'marginLeft' : marginLeftAdjust
            });
        }).resize();
    </script>
</body>
</html>
