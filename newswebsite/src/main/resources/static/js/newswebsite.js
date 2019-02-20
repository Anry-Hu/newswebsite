var page = 1;
var maxpage=1;

window.onload = function(){
    page  = parseInt(document.getElementById("pageNum").innerText);
    maxpage=parseInt(document.getElementById("maxpage").innerText)/10;

    //alert(document.getElementById('pageNum').innerText);

    /*loginstate = parseInt(document.getElementById("loginState").value);

    if(loginstate==1){
        document.getElementById("login").style.display="none";
        document.getElementById("top-navigation").style.display="block";
    }
    else{
        document.getElementById("top-navigation").style.display="none";
        document.getElementById("login").style.display="block";
    }*/

    document.getElementById("page").value = page.toString();
};

function moveprevious(){
    if(page > 1){
        page--;
    }
    document.getElementById("pagePrevious").value=page.toString();
}

function movenext(pagemax){
    if(page <= pagemax/10){
        page++;
    }
    document.getElementById("pageNext").value = page.toString();
}

function moveone(){
}
/*
function logout(){
    document.getElementById("top-navigation").style.display="none";
    document.getElementById("login").style.display="block";
}

function login(){
    document.getElementById("login").style.display="none";
    document.getElementById("top-navigation").style.display="block";
}
*/

function judgepage() {
    var page=parseInt(document.getElementById("pagetext").value);
    if(0<page&&page<=(maxpage+1)) {
        return true;
    }
    else {
        return false;
    }
}

function editUser(id, name, phone, password) {
    document.getElementById("updateID").value = id;
    document.getElementById("updateName").value = name;
    document.getElementById("updatePhone").value = phone;
    document.getElementById("updatePassword").value = password;
}

function editNews(id, time, title, summary, news_address, img_address, heat, tag) {
    document.getElementById("updateID").value = id;
    document.getElementById("updateTime").value = time;
    document.getElementById("updateTitle").value = title;
    document.getElementById("updateSummary").value = summary;
    document.getElementById("updateNewsAddress").value = news_address;
    document.getElementById("updateImgAddress").value = img_address;
    document.getElementById("updateHeat").value = heat;
    document.getElementById("updateTag").value = tag;
}

function editTag(id, name) {
    document.getElementById("updateID").value = id;
    document.getElementById("updateName").value = name;
}

function addUser(){
    /*if(document.getElementById("addUser_name").value.length == 0){
        alert("dad");
    }*/
}