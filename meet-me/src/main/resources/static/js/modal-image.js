window.addEventListener('load', function () {
    let modal = document.getElementById("myModal");

    if (modal == null) {
        return;
    }
    let img = document.getElementById("myImg");
    let modalImg = document.getElementById("img01");
    let captionText = document.getElementById("caption");
    img.onclick = function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
    };

    let span = document.getElementsByClassName("close")[0];

    span.onclick = function () {
        modal.style.display = "none";
    }
});

