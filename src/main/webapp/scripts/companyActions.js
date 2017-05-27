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
    //create hidden input newJ, assign the value and append it to form
    var newJ = document.createElement("input");
    newJ.setAttribute("name", "newJ");
    newJ.setAttribute("type", "hidden");
    var value = document.getElementById("newJ").value;
    newJ.setAttribute("value", value);
    form.appendChild(newJ);
    //create hidden input newCompanyName, assign the value and append it to form
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

function sortCompanies(colIndex) {
    var targetTable = document.getElementById("searchableTable");
    switching = true;
    while (switching) {
        var switching = false;
        var rowData = targetTable.getElementsByTagName("tr");
        for (var i = 1; i < (rowData.length - 1); i++) {
            var shouldSwitch = false;
            var x = rowData[i].getElementsByTagName("td")[colIndex];
            var y = rowData[i + 1].getElementsByTagName("td")[colIndex];
            if (x.innerHTML.toUpperCase() > y.innerHTML.toUpperCase()) {
                shouldSwitch= true;
                break;}}
        if (shouldSwitch) {
            rowData[i].parentNode.insertBefore(rowData[i + 1], rowData[i]);
            switching = true;}}}

function searchCompanies() {
    var searchText = document.getElementById('searchBox').value.toUpperCase();
    var targetTable = document.getElementById('searchableTable');
    var targetTableColCount;
    for (var rowIndex = 0; rowIndex < targetTable.rows.length; rowIndex++) {
        var rowData = '';
        if (rowIndex == 0) {
            targetTableColCount = targetTable.rows.item(rowIndex).cells.length;
            continue;}
        for (var colIndex = 0; colIndex < targetTableColCount; colIndex++) {
            rowData += targetTable.rows.item(rowIndex).cells.item(colIndex).textContent.toUpperCase();}
        if (rowData.indexOf(searchText) == -1)
            targetTable.rows.item(rowIndex).style.display = 'none';
        else
            targetTable.rows.item(rowIndex).style.display = 'table-row';}}