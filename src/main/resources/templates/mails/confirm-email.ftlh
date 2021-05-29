<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Спасибо за регистрацию</title>
    <style>
        #layer-2 {
            background-image: url("https://i.ytimg.com/vi/5ExpxUjttMo/maxresdefault.jpg");
            z-index: 1;
            width: 100%;
            height: 700px;
        }
        #img {
            background-color: rgba(0,0,0, 0.4);
            height: 700px;
            width: 100%;
            z-index: 3;
            box-shadow: inset 0 0 50px 20px rgba(0,0,0,0.3);
        }
        .font-color-family {
            color: white;
            font-family: "Arial Black";
            text-align: center;
        }
        #title {
            padding-top: 200px;
        }
        h2 {
            font-size: 25pt;
        }
        h1 {
            font-size: 35pt;
        }
        h3 {
            font-size: 20pt;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
    <a href="${server_url}/users/${user_id}/confirm/${confirm_code}">
        <div id="layer-2">
            <div id="img" >
                <div id="title">
                    <h2 class="font-color-family">Hello ${username}</h2>
                    <h1 id="font-size-wel" class="font-color-family">Welcome to our music school!</h1>
                    <h3 class="font-color-family">Please, click here to complete registration</h3>
                </div>

            </div>
        </div>
    </a>
</body>
</html>
