function addCompany(){
    //create form with method and action
    var form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "/companyManagementServlet?action=addCompany")
    //create hidden input newCompanyCUI, assign the value and append it to form
    var hiddenCUIinput = document.createElement("input");
    hiddenCUIinput.setAttribute("name", "newCompanyCUI");
    hiddenCUIinput.setAttribute("type", "hidden");
    var value = document.getElementById("newCompanyCUI").value;
    hiddenCUIinput.setAttribute("value", value);
    form.appendChild(hiddenCUIinput);
    //create hiden input newJ, assign the value and append it to form
    var newJ = document.createElement("input");
    newJ.setAttribute("name", "newJ");
    newJ.setAttribute("type", "hidden");
    var value = document.getElementById("newJ").value;
    newJ.setAttribute("value", value);
    form.appendChild(newJ);
    //create hiden input newCompanyName, assign the value and append it to form
    var newCompanyName = document.createElement("input");
    newCompanyName.setAttribute("name", "newCompanyName");
    newCompanyName.setAttribute("type", "hidden");
    var value = document.getElementById("newCompanyName").value;
    newCompanyName.setAttribute("value", value);
    form.appendChild(newCompanyName);
    //create hidden input newCompanyAddress, assign the value and append it to form
    var newCompanyAddress = document.createElement("input");
    newCompanyAddress.setAttribute("name", "newCompanyAddress");
    newCompanyAddress.setAttribute("type", "hidden");
    var value = document.getElementById("newCompanyAddress").value;
    newCompanyAddress.setAttribute("value", value);
    form.appendChild(newCompanyAddress);
    //create hidden input newCompanyIBAN, assign the value and append it to form
    var newCompanyIBAN = document.createElement("input");
    newCompanyIBAN.setAttribute("name", "newCompanyIBAN");
    newCompanyIBAN.setAttribute("type", "hidden");
    var value = document.getElementById("newCompanyIBAN").value;
    newCompanyIBAN.setAttribute("value", value);
    form.appendChild(newCompanyIBAN);
    //create hidden input newCompanyPhone, assign the value and append it to form
    var newCompanyPhone = document.createElement("input");
    newCompanyPhone.setAttribute("name", "newCompanyPhone");
    newCompanyPhone.setAttribute("type", "hidden");
    var value = document.getElementById("newCompanyPhone").value;
    newCompanyPhone.setAttribute("value", value);
    form.appendChild(newCompanyPhone);
    //append the form to page
    document.body.appendChild(form);
    //submit form
    form.submit();}

function listCompanies() {
    var form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "/companyManagementServlet?action=listCompanies");
    document.body.appendChild(form);
    form.submit();}