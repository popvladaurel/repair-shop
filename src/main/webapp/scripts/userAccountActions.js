function changePassword(){
    document.getElementById("newkeyinput").setAttribute("style", "display:block");
    document.getElementById("newpasswordbutton").setAttribute("onclick", "hidePasswordField()");
    document.getElementById("newpasswordbutton").setAttribute("type", "submit");}

function hidePasswordField() {
    document.getElementById("newpasswordinput").setAttribute("style", "display:none");
    document.getElementById("newpasswordbutton").removeAttribute("type");
    document.getElementById("newpasswordbutton").setAttribute("onclick", "changePassword()");}

function changeOpenAPIKey(){
    document.getElementById("newkeyinput").setAttribute("style", "display:block");
    document.getElementById("newkeybutton").setAttribute("onclick", "hidePasswordField()");
    document.getElementById("newkeybutton").setAttribute("type", "submit");}

function hideKeyInputField() {
    document.getElementById("newkeyinput").setAttribute("style", "display:none");
    document.getElementById("newkeybutton").setAttribute("onclick", "hideKeyInputField()");
    document.getElementById("newkeybutton").removeAttribute("type");}


