document.getElementsByClassName("close")[0].onclick = function() {
    document.getElementById('modalDiv').style.display = "none";}

window.onclick = function(event) {
    if (event.target == document.getElementById('modalDiv')) {
        document.getElementById('modalDiv').style.display = "none";}}
