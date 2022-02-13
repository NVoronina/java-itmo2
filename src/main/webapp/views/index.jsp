<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="stars js-stars -visible" style="height: 100%;">
    <div class="small"></div>
    <div class="medium"></div>
    <div class="big"></div>

    <div class="main-block">
        <h1>Welcome to Cars App</h1>
        <h2>${message}</h2>
        <form id="auth-form" action="api/login" method="post">
            <input type="text" placeholder="email" name="email" id="email"/>
            <input type="text" placeholder="password" name="password" id="password"/>
            <input type="submit" value="Send"/>
        </form>
        <a href="registration">Registration</a>
        <div>
            <img id="cats-small" class="cat" alt="Foot" src="${pageContext.request.contextPath}/images/cat-preview.png"/>
        </div>
        <div>
            <img id="cats-small2" class="cat" alt="Foot" src="${pageContext.request.contextPath}/images/cat-preview.png"/>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/animation.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>