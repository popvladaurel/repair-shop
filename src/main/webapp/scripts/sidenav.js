function openNav(sidenavNumberToOpen, sideNavNumberToClose) {
    closeNav(sideNavNumberToClose);
    document.getElementById("sidenav" + sidenavNumberToOpen).style.width = "250px";
    var mq = window.matchMedia('all and (max-width: 736px)');
    if (mq.matches) {
        document.getElementById("main").style.marginRight = "0";
        document.getElementById("modalDiv").style.marginRight= "0";
        document.getElementById("modalContent").style.marginRight= "0";
        document.getElementById("modalText").style.paddingLeft= "0";}
    else {
        document.getElementById("main").style.marginRight = "250px";
        document.getElementById("modalContent").style.marginRight= "250px";
        document.getElementById("modalDiv").style.marginRight= "250px";
        document.getElementById("preferences").style.marginRight = "250px";
        document.getElementById("modalText").style.paddingLeft= "250px";}}

function closeNav(sidenavNumberToClose) {
    document.getElementById("sidenav" + sidenavNumberToClose).style.width = "0";
    document.getElementById("main").style.marginRight= "0";
    document.getElementById("preferences").style.marginRight= "10px";
    document.getElementById("modalDiv").style.marginRight= "0";
    document.getElementById("modalContent").style.marginRight= "0";
    document.getElementById("modalText").style.paddingLeft= "0";}

function openFrame(frameNumberToOpen, frameNUmberToClose) {
    closeFrame(frameNUmberToClose);
    document.getElementById("frame" + frameNumberToOpen).style.display = "block";}

function closeFrame(frameNUmberToClose) {
    document.getElementById("frame" + frameNUmberToClose).style.display = "none";}