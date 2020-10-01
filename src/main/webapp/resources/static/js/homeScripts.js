// document.getElementById("inputTarget").onkeypress = function() {myFunction()};
// document.getElementById("inputTarget").onkeypress ;

function targetCheck() {
    return (charCode !== 8 && charCode === 0 ||
        (charCode >= 48 && charCode <= 57))
}

// document.onkeypress = function (e) {
//     e = e || window.event;
//     // use e.keyCode
// };