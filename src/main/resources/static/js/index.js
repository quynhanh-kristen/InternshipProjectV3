var modal = document.getElementById("myModal");
// var btnSignIn = document.getElementsByClassName("header__navbar-item-signin")[0];
// var btnSignUp = document.getElementsByClassName("header__navbar-item-signup")[0];
var btnCloseModal = document.getElementsByClassName("modal-controls-back")[0];
var contentOut = document.getElementById("contentOut");

var showDetail = function(id){
    modal.style.display = "flex";
    var req = new XMLHttpRequest();
    req.open("GET", `/posts/${id}`, true);
    req.addEventListener('load', function(){
        console.log(req.status);
        console.log(req.responseText);
        var post = JSON.parse(req.responseText);
        document.getElementById('modalTitle').innerHTML = post.title;
        document.getElementById('modalUrl').src = `<img class="modal__img-post" src="https://lh3.google.com/u/1/d/${post.fileID}" alt="">`;
        document.getElementById('modalContent').innerHTML = post.content;
        document.getElementById('modalUserCreate').innerHTML = post.createdUser;
        document.getElementById('modalTotalVote').innerHTML = post.totalVote;
    });
    req.send(null);


}
btnCloseModal.onclick = function(){
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == contentOut) {
        modal.style.display = "none";
        modal.style.display = "none";
    }
  }

////// rút gọn name of title
var listName =  document.getElementsByClassName('title-name-post');
for(var i = 0; i < listName.length; i++){
    var name = document.getElementsByClassName('title-name-post')[i].innerHTML;
    if(name.length > 25) {
        console.log(name);
        name = name.substring(0,19)+"...";
        document.getElementsByClassName('title-name-post')[i].innerHTML = name;
    }
} 
//////////////////////
var btnSort = document.getElementById('home__fiter-btn');
btnSort.onclick = function(){
    if(btnSort.innerHTML === 'Mới nhất'){
        btnSort.innerHTML = 'Cũ nhất'
    } else {
        btnSort.innerHTML = 'Mới nhất'
    }
}