(function () {
    window.addEventListener("load", function (e) {

        let next = document.getElementById("next");
        let prev = document.getElementById("prev");
        if (next === null || prev === null) {
            return;
        }
        let secondPage = document.getElementById("sp");
        secondPage.style.display = "none";

        next.addEventListener("click", function (e) {
            commonBehaviorButton(e, document.getElementById("fp"), document.getElementById("sp"));
        });
        prev.addEventListener("click", function (e) {
            commonBehaviorButton(e, document.getElementById("sp"), document.getElementById("fp"));
        });
    });

    function commonBehaviorButton(e, fP, sP) {
        e.preventDefault();
        fP.style.display = "none";
        sP.style.display = "";
    }
}());