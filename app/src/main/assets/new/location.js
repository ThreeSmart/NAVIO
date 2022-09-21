var oBar=document.getElementById('person');
setInterval(frame, 2000);

function frame() {
    const queryString = window.location.search;

    const urlParams = new URLSearchParams(queryString);

    const x = parseInt(urlParams.get('x'))
    const y = parseInt(urlParams.get('y'))

    const s = httpGet("http://16.170.209.14:7801/coordinates/get_current?userId=" + x);
    const data = JSON.parse(s)

    oBar.setAttribute("cx", (30500 - data.x * 10));
    oBar.setAttribute("cy",(13000 - data.y * 10));
}

function httpGet(url) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}