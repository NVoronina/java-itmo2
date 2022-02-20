<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>

    <div class="small"></div>
    <div class="medium"></div>
    <div class="big"></div>

    <div class="main-block">
        <h1>Welcome to Cars App</h1>
        <h2>${message}</h2>
        <form id="reg-form">
            <input type="text" placeholder="name" name="name"/>
            <input type="text" placeholder="surname" name="surname"/>
            <input type="text" placeholder="email" name="email"/>
            <input type="text" placeholder="password" name="password"/>
            <input type="submit" value="Send"/>
        </form>
        <a href="index">Authorization</a>
        <div>
            <img id="cats-small" class="cat" alt="Foot" src="${pageContext.request.contextPath}/images/cat-preview.png"/>
        </div>
        <div>
            <img id="cats-small2" class="cat" alt="Foot" src="${pageContext.request.contextPath}/images/cat-preview.png"/>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/animation.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>