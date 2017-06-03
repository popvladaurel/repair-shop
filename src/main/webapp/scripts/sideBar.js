function checkMobile() {
    if(navigator.userAgent.match(/Android/i)
        || navigator.userAgent.match(/webOS/i)
        || navigator.userAgent.match(/iPhone/i)
        || navigator.userAgent.match(/iPad/i)
        || navigator.userAgent.match(/iPod/i)
        || navigator.userAgent.match(/BlackBerry/i)
        || navigator.userAgent.match(/Windows Phone/i)){
        document.getElementById("categoryButtons").style.display = "inline-flex";
        closeSideBar();}
    else {
        openSideBar();}}

function openSideBar() {
    document.getElementById("sideBar").style.width = "250px";
    var mq = window.matchMedia('all and (max-width: 736px)');
    if (mq.matches) {
        document.getElementById("main").style.marginRight = "0";
        document.getElementById("bottomModal").style.marginRight = "0";
        document.getElementById("bottomModalContent").style.marginRight = "0";
        document.getElementById("bottomModalText").style.paddingLeft = "0";}
    else {
        document.getElementById("main").style.marginRight = "250px";
        document.getElementById("categoryButtons").style.marginRight = "250px";
        document.getElementById("bottomModalContent").style.marginRight = "250px";
        document.getElementById("bottomModal").style.marginRight = "250px";
        document.getElementById("bottomModalText").style.paddingLeft = "250px";}}

function closeSideBar() {
    document.getElementById("sideBar").style.width = "0";
    document.getElementById("main").style.marginRight = "0";
    document.getElementById("categoryButtons").style.marginRight = "10px";
    document.getElementById("bottomModal").style.marginRight = "0";
    document.getElementById("bottomModalContent").style.marginRight = "0";
    document.getElementById("bottomModalText").style.paddingLeft = "0";}