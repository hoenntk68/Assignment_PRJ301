function toggleView(stuID, triggerer) {
    const myImg = document.getElementById(stuID);
    console.log(myImg);
    console.log(triggerer.innerHTML)
    if (triggerer.innerHTML == 'View image') {
        myImg.style.display = 'inline';
        triggerer.innerHTML = 'Hide image';
    } else {
        myImg.style.display = 'none';
        triggerer.innerHTML = 'View image';

    }
}