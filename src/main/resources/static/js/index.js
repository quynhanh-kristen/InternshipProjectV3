var modal = document.getElementById("myModal");
// var btnSignIn = document.getElementsByClassName("header__navbar-item-signin")[0];
// var btnSignUp = document.getElementsByClassName("header__navbar-item-signup")[0];
var btnCloseModal = document.getElementsByClassName("modal-controls-back")[0];
var contentOut = document.getElementById("contentOut");
var ip;

var showDetail = function(id){
    modal.style.display = "flex";
    var req = new XMLHttpRequest();
    req.open("GET", `/posts/${id}`, true);
    req.addEventListener('load', function(){
        console.log(req.status);
        console.log(req.responseText);
        var post = JSON.parse(req.responseText);
        document.getElementById('modalTitle').innerHTML = post.title;
        if(post.fileType === "image"){
            document.getElementById('modalImage').src = `https://lh3.google.com/u/1/d/${post.fileID}`;
        } else if (post.fileType === "video"){
            document.getElementById('modalVideo').innerHTML = `<iframe src="https://drive.google.com/file/d/${post.fileID}/preview" width="500" height="375" allow="autoplay"></iframe>`;
        }

        document.getElementById('modalContent').innerHTML = post.content;
        document.getElementById('modalUserCreate').innerHTML = post.createdUser;
        document.getElementById('modalTotalVote').innerHTML = post.totalVote;
    });
    req.send(null);


}
btnCloseModal.onclick = function(){
    modal.style.display = "none";
    document.getElementById('modalImage').src =  ``;
    document.getElementById('modalVideo').innerHTML =  ``;
}
window.onclick = function(event) {
    if (event.target == contentOut) {
        modal.style.display = "none";
        document.getElementById('modalImage').src =  ``;
        document.getElementById('modalVideo').innerHTML =  ``;
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
//////////làm đỏ tim////////////
 function getIpForLoadHeart(callback){
    $.getJSON("https://api.ipify.org?format=json",
        function(data) {
            ip = data.ip
            callback()
        })
}
var votedPost;
 function loadRedHeart(){
    var req = new XMLHttpRequest();
    req.open("GET", `/voted_post/${ip}`, true);
    req.addEventListener('load', function(){
        console.log(req.status);
        console.log(req.responseText);
        votedPost = JSON.parse(req.responseText);
        var listVoteIcon =  document.getElementsByClassName('vote_icon');
        for(var i = 0; i < listVoteIcon.length; i++){
            var icon = document.getElementsByClassName('vote_icon')[i];
            if(votedPost.includes(parseInt(icon.id))) {
                listVoteIcon[i].classList.remove("far")
                listVoteIcon[i].classList.add("icon_voted", "fas")
            }
        }
    });
    req.send(null);
}

getIpForLoadHeart(loadRedHeart);
///////////Do Vote////////////
var doVote = function (post_id){

    if(votedPost.includes(parseInt(post_id))){
        console.log("sai")
    } else {
        var req = new XMLHttpRequest();
        req.open("GET", `/doVote?post_id=${post_id}&user_ip=${ip}`, true);
        req.addEventListener('load', function(){
            console.log(req.status);
            console.log(typeof req.responseText);
            if(req.responseText == "true"){
                document.querySelector(`#divVote${post_id}  .vote_icon`).classList.remove("far");

                document.querySelector(`#divVote${post_id}  .vote_icon`).classList.add("icon_voted", "fas");

                var voting = document.querySelector(`#divVote${post_id}  .voting`).innerHTML
                var temp = Number.parseInt(voting) + 1;
                document.querySelector(`#divVote${post_id}  .voting`).innerHTML = temp;
            }
        });
        req.send(null);
        console.log("đúng")
    }
}
