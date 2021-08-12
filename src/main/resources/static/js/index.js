var modal = document.getElementById("myModal");
var btnCloseModal = document.getElementsByClassName("modal-controls-back")[0];
var contentOut = document.getElementById("contentOut");

var noticeVotedModal = document.getElementById("noticeVoted_modal");
var noticeVotedContentOut = document.getElementById("noticeVoted_contentOut");

var ip;
var tempIdPost;
var votedPost;

var showDetail = function(id){
    modal.style.display = "flex";
    var req = new XMLHttpRequest();
    req.open("GET", `/posts/${id}`, true);
    req.addEventListener('load', function(){
        console.log(req.status);
        console.log(req.responseText);
        var post = JSON.parse(req.responseText);
        document.getElementById('modalTitle').innerHTML = post.title;
        if(post.fileType.includes("image")){
            document.getElementById('modalImage').src = `https://drive.google.com/uc?id=${post.fileID}`;
        } else if (post.fileType.includes("video")){
            document.getElementById('modalVideo').innerHTML = `<iframe src="https://drive.google.com/file/d/${post.fileID}/preview" width="500" height="375" allow="autoplay"></iframe>`;
        }
        document.getElementById('modalContent').innerHTML = post.content;
        document.getElementById('modalUserCreate').innerHTML = post.createdUser;
        if(id == votedPost){
            document.getElementById('modalIconVote').classList.remove('far');
            document.getElementById('modalIconVote').classList.add("icon_voted", "fas");
        }
        document.getElementById('modalTotalVote').innerHTML = post.totalVote;
        tempIdPost = id;
    });
    req.send(null);


}

var showNoticeVotedModal = function (){
    noticeVotedModal.style.display = "flex";
}
btnCloseModal.onclick = function(){
    modal.style.display = "none";
    document.getElementById('modalImage').src =  ``;
    document.getElementById('modalVideo').innerHTML =  ``;
    document.getElementById('modalIconVote').classList.remove("icon_voted", "fas");
    document.getElementById('modalIconVote').classList.add('far');
    tempIdPost = '';
}
window.onclick = function(event) {
    if (event.target == contentOut) {
        modal.style.display = "none";
        document.getElementById('modalImage').src =  ``;
        document.getElementById('modalVideo').innerHTML =  ``;
        document.getElementById('modalIconVote').classList.remove("icon_voted", "fas");
        document.getElementById('modalIconVote').classList.add('far');
        tempIdPost = '';
    } else if (event.target == noticeVotedContentOut){
        noticeVotedModal.style.display = "none";
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
            ip = data.ip;
            callback();
        })
}

 function loadRedHeart(){
    var req = new XMLHttpRequest();
    req.open("GET", `/voted_post/${ip}`, true);
    req.addEventListener('load', function(){
        console.log(req.status);
        console.log(typeof req.responseText);
        votedPost = req.responseText;
        var listVoteIcon =  document.getElementsByClassName('vote_icon');
        for(var i = 0; i < listVoteIcon.length; i++){
            var icon = document.getElementsByClassName('vote_icon')[i];
            if(votedPost == icon.id) {
                listVoteIcon[i].classList.remove("far");
                listVoteIcon[i].classList.add("icon_voted", "fas");
            }
        }
    });
    req.send(null);
}

getIpForLoadHeart(loadRedHeart);
///////////Do Vote////////////
var doVote = function (post_id){

    if(votedPost){
        if(votedPost == post_id){
            var req = new XMLHttpRequest();
            req.open("POST", `/unVote?post_id=${post_id}&user_ip=${ip}`, true);
            req.addEventListener('load', function(){
                if(req.responseText == "true"){
                    document.querySelector(`#divVote${post_id}  .vote_icon`).classList.remove("icon_voted", "fas");

                    document.querySelector(`#divVote${post_id}  .vote_icon`).classList.add("far");

                    var voting = document.querySelector(`#divVote${post_id}  .voting`).innerHTML;
                    var temp = Number.parseInt(voting) - 1;
                    document.querySelector(`#divVote${post_id}  .voting`).innerHTML = temp;
                    votedPost = "";
                }
            });
            req.send(null);
        } else {
            showNoticeVotedModal();
        }

    } else {
        var req = new XMLHttpRequest();
        req.open("POST", `/doVote?post_id=${post_id}&user_ip=${ip}`, true);
        req.addEventListener('load', function(){
            if(req.responseText == "true"){
                document.querySelector(`#divVote${post_id}  .vote_icon`).classList.remove("far");

                document.querySelector(`#divVote${post_id}  .vote_icon`).classList.add("icon_voted", "fas");

                var voting = document.querySelector(`#divVote${post_id}  .voting`).innerHTML;
                var temp = Number.parseInt(voting) + 1;
                document.querySelector(`#divVote${post_id}  .voting`).innerHTML = temp;
                votedPost = post_id;
                console.log("vote thành công")
            }
        });
        req.send(null);
    }
}
/////////////////////
var doVoteInModal = function (){
    if(tempIdPost != ''){
        if(votedPost){
            if(votedPost == tempIdPost){
                var req = new XMLHttpRequest();
                req.open("POST", `/unVote?post_id=${tempIdPost}&user_ip=${ip}`, true);
                req.addEventListener('load', function(){
                    if(req.responseText == "true"){
                        document.querySelector(`#divVote${tempIdPost}  .vote_icon`).classList.remove("icon_voted", "fas");
                        document.querySelector(`#divVote${tempIdPost}  .vote_icon`).classList.add("far");
                        var voting = document.querySelector(`#divVote${tempIdPost}  .voting`).innerHTML;
                        var temp = Number.parseInt(voting) - 1;
                        document.querySelector(`#divVote${tempIdPost}  .voting`).innerHTML = temp;

                        document.querySelector(`.modal__vote  #modalIconVote`).classList.remove("icon_voted", "fas");
                        document.querySelector(`.modal__vote  #modalIconVote`).classList.add("far");
                        document.querySelector(`.modal__vote  #modalTotalVote`).innerHTML = temp;
                        votedPost = "";
                    }
                });
                req.send(null);
            } else {
                showNoticeVotedModal();
            }

        } else {
            var req = new XMLHttpRequest();
            req.open("POST", `/doVote?post_id=${tempIdPost}&user_ip=${ip}`, true);
            req.addEventListener('load', function(){
                if(req.responseText == "true"){
                    document.querySelector(`#divVote${tempIdPost}  .vote_icon`).classList.remove("far");

                    document.querySelector(`#divVote${tempIdPost}  .vote_icon`).classList.add("icon_voted", "fas");

                    var voting = document.querySelector(`#divVote${tempIdPost}  .voting`).innerHTML;
                    var temp = Number.parseInt(voting) + 1;
                    document.querySelector(`#divVote${tempIdPost}  .voting`).innerHTML = temp;

                    document.querySelector(`.modal__vote  #modalIconVote`).classList.remove("far");
                    document.querySelector(`.modal__vote  #modalIconVote`).classList.add("icon_voted", "fas");
                    document.querySelector(`.modal__vote  #modalTotalVote`).innerHTML = temp;
                    votedPost = tempIdPost;

                }
            });
            req.send(null);
        }
    }
}
