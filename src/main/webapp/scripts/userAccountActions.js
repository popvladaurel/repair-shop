function changePassword(){
    document.getElementById("newpasswordinput").setAttribute("style", "display:block");
    document.getElementById("newpasswordbutton").setAttribute("onclick", "hidePasswordField()");
    document.getElementById("newpasswordbutton").setAttribute("type", "submit");}

function hidePasswordField() {
    document.getElementById("newpasswordinput").setAttribute("style", "display:none");
    document.getElementById("newpasswordbutton").removeAttribute("type");
    document.getElementById("newpasswordbutton").setAttribute("onclick", "changePassword()");}



