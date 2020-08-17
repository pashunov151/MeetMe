window.addEventListener("load", function () {
    let el = document.getElementById("submitLink");
    if (el !== null) {
        el.onclick = function () {
            document.getElementById("logoutForm").submit();
            return false; // cancel the actual link
        }
    }
});