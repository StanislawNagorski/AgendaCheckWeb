function targetCheck() {
    return (charCode !== 8 && charCode === 0 ||
        (charCode >= 48 && charCode <= 57))
}

function checkForm(form) {

    let gessefFileName = form.gessef.value;
    let planQFileName = form.planQ.value;

    if (gessefFileName === "") {
        alert("Dodaj plik Gessef")
        return false;
    }

    if (planQFileName === "") {
        alert("Dodaj plik PlanuQ")
        return false;
    }

    const popularNamesGessef = ["gessef", "gesef", "pilota"];
    if (isNameNotValid(gessefFileName, popularNamesGessef)) {
        alert("Sprawdź czy na pewno dodałeś poprawny plik Gessef");
        return false;
    } else {
        heightLightToGreen("gessefTxt");
    }

    const popularNamesPlanQ = ["division", "sum", "report", "godziny", "hours"];
    if (isNameNotValid(planQFileName, popularNamesPlanQ)) {
        alert("Sprawdź czy na pewno dodałeś poprawny plik PlanuQ \n" +
            "Może mieć zmienioną nazwę na np Godziny październik");
        return false;
    } else {
        heightLightToGreen("planQTxt");
    }
}

function isNameNotValid(fileName, popularNames) {
    let name;
    for (name of popularNames) {
        if (fileName.toLowerCase().includes(name)) {
            return false;
        }
    }
    return true;
}

function heightLightToGreen(elementID) {
    let elementById = document.getElementById(elementID);
    elementById.style.color = "green";
    elementById.style.fontWeight = "900";
}

