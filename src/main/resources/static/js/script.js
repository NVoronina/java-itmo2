document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        if (email === '' || password === '') {
            alert('Empty required fields');
        } else {
            var form = document.querySelector('form');
            var data = {
                "email": email,
                "password": password,
            }
            var action = form.getAttribute("action");
            var method = form.getAttribute("method");
            makeAjaxRequest(action, method, JSON.stringify(data), function (response) {
                if(response.readyState === XMLHttpRequest.DONE && response.status === 200) {
                    alert('Success AUTH!')
                };
                if(response.readyState === XMLHttpRequest.DONE && response.status === 400) {
                    alert('No user found')
                };
            });
        }
    });

});

function makeAjaxRequest(url, method, data, callback) {
    var httpRequest;
    httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = (function () {
        return callback(httpRequest);
    });
    if (method && method.toUpperCase() == 'POST') {
        httpRequest.open(method, url, true);
        httpRequest.setRequestHeader("Content-type", "application/json");
        httpRequest.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        httpRequest.send(data);
    } else {
        httpRequest.open(method, url);
        httpRequest.send();
    }
}