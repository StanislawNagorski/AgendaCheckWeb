function targetCheck() {
    return (charCode !== 8 && charCode === 0 ||
        (charCode >= 48 && charCode <= 57))
}

function checkForm(form) {
    if (form.gessef.value === "") {
        alert("Dodaj plik Gessefa");
        return false;
    }

    if (form.planQ.value === "") {
        alert("Dodaj plik planQ");
        return false;
    }

}



