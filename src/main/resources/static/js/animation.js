
let start = 150;
setTimeout( function() {
    let timer = setInterval(function() {
        if (start > 1200) {
            clearInterval(timer);
            return;
        }
        draw(start += 200, 'cats-small');
    }, 1000);
}, 300);
let start2 = 100;
let timer2 = setInterval(function() {
    if (start2 > 1200) {
        clearInterval(timer2);
        return;
    }
    draw(start2 += 200, 'cats-small2');
}, 1000);

function draw(px, elemName) {
    var catsEl = document.getElementById(elemName)
    catsEl.style.left =  px + 'px';
}