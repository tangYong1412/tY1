function deleTe(userNumber) {
    $.ajax({
        url:'/Delete.do',
        method:'POST',
        data:{
            userNumber: userNumber,
        },
        success:function (result) {
            window.location.href = "/Table.do";
        },
        error:function (result) {
            alert(result);
        },
    })
}

function check(studentNumber) {
    if (!(studentNumber.match(/^([1-9]\d*|[0]{1,1})$/))){
        alert( "学号填写错误");
        return;
    }

    $.ajax({
        url:'/check.do',
        method:"POST",
        data:{
            userNumber: studentNumber,
            tableName: "student",
        },
        success:function (result) {
            var nameTipMsg = document.getElementById("nameTipMsg");
            nameTipMsg.innerHTML = result;
        },
        error:function (result) {
            alert(result);
        },
    })
}

var nameTipMsg1 = document.getElementsByClassName("nameTipMsg1");
function checkName(userName) {
    //汉字在1到4位的姓名
    if (!(userName.match(/^[\u4E00-\u9FA5]{1,4}$/))){
        nameTipMsg1[0].style.color = "red";
        nameTipMsg1[0].innerHTML = "姓名填写错误";
        return;
    }
    else {
        nameTipMsg1[0].style.color = "green";
        nameTipMsg1[0].innerHTML = "填写正确";
    }
}

var nameTipMsg2 = document.getElementsByClassName("nameTipMsg2");
function checkAge(userAge) {
    if (!(userAge.match(/^([1-9]\d*|[0]{1,1})$/))){
        nameTipMsg2[0].style.color = "red";
        nameTipMsg2[0].innerHTML = "年龄填写错误";
        return;
    }
    else {
        nameTipMsg2[0].style.color = "green";
        nameTipMsg2[0].innerHTML = "填写正确";
    }
}

var nameTipMsg3 = document.getElementsByClassName("nameTipMsg3");
function checkSex(userSex) {
    if (userSex != "男" && userSex != "女"){
        nameTipMsg3[0].style.color = "red";
        nameTipMsg3[0].innerHTML = "性别填写错误";
        return;
    }
    else{
        nameTipMsg3[0].style.color = "green";
        nameTipMsg3[0].innerHTML = "填写正确";
    }
}

var nameTipMsg4 = document.getElementsByClassName("nameTipMsg4");
function checkPw(passWord) {
    //密码的格式为6-12位，只能是字母、数字和下划线
    if (!(passWord.match(/^[\w]{6,12}$/))){
        nameTipMsg4[0].style.color = "red";
        nameTipMsg4[0].innerHTML = "密码至少6位，或非法符号";
        return;
    }
    else{
        nameTipMsg4[0].style.color = "green";
        nameTipMsg4[0].innerHTML = "密码填写合法";
    }
}

var nameTipMsg5 = document.getElementsByClassName("nameTipMsg5");
function checkcName(cName) {
    if(!(cName.match(/^[\u4e00-\u9fa5]+$/))){
        nameTipMsg5[0].style.color = "red";
        nameTipMsg5[0].innerHTML = "请输入汉字";
        return;
    }
    else{
        nameTipMsg5[0].style.color = "green";
        nameTipMsg5[0].innerHTML = "填写正确";
    }
}

var nameTipMsg6 = document.getElementsByClassName("nameTipMsg6");
function checkGrade(grade) {
    if (grade == ""){
        nameTipMsg6[0].style.color = "red";
        nameTipMsg6[0].innerHTML = "年龄填写错误";
        return;
    }
    else {
        nameTipMsg6[0].style.color = "green";
        nameTipMsg6[0].innerHTML = "填写正确";
    }
}

function add() {
    var studentNumber = document.getElementById("studentNumber").value;
    var studentName = document.getElementById("studentName").value;
    var studentAge = document.getElementById("studentAge").value;
    var studentSex = document.getElementById("studentSex").value;
    var studentPW = document.getElementById("studentPW").value;
    var studentCname = document.getElementById("cName").value;
    var studentGrade = document.getElementById("grade").value;

    if (!(studentNumber.match(/^([1-9]\d*|[0]{1,1})$/)) || !(studentName.match(/^[\u4E00-\u9FA5]{1,4}$/)) || studentGrade == ""|| !(studentCname.match(/^[\u4e00-\u9fa5]+$/))
        || !(studentPW.match(/^[\w]{6,12}$/)) || (studentSex != "男" && studentSex != "女") || !(studentAge.match(/^([1-9]\d*|[0]{1,1})$/))){
        alert("请输入合法操作符！！！");
        return;
    }

    if (studentNumber == ""||studentName == ""||studentAge == ""||studentSex == ""||studentPW == ""||studentCname == ""||studentGrade == ""){
        alert("请将信息填写完整！！！");
        return;
    }

    $.ajax({
        url:'/Add.do',
        method:'POST',
        data:{
            studentNumber : studentNumber,
            studentName: studentName,
            studentAge : studentAge,
            studentSex : studentSex,
            studentPW : studentPW,
            studentCname : studentCname,
            studentGrade : studentGrade,
            method: "add",
        },
        success: function () {
            window.location.href = "/Table.do";
        },
        error: function (result) {
            alert(result);
        }
    })
}

//填写增加学生信息
function openNav() {
    document.getElementById("add").style.width = "100%";
}

function closeNav() {
    document.getElementById("add").style.width = "0";
}

var nameTipMsga1 = document.getElementsByClassName("nameTipMsga1");
function checKName(userName) {
    //汉字在1到4位的姓名
    if (!(userName.match(/^[\u4E00-\u9FA5]{1,4}$/))){
        nameTipMsga1[0].style.color = "red";
        nameTipMsga1[0].innerHTML = "姓名填写错误";
        return;
    }
    else {
        nameTipMsga1[0].style.color = "green";
        nameTipMsga1[0].innerHTML = "填写正确";
    }
}

var nameTipMsga2 = document.getElementsByClassName("nameTipMsga2");
function checKAge(userAge) {
    if (!(userAge.match(/^([1-9]\d*|[0]{1,1})$/))){
        nameTipMsga2[0].style.color = "red";
        nameTipMsga2[0].innerHTML = "年龄填写错误";
        return;
    }
    else {
        nameTipMsga2[0].style.color = "green";
        nameTipMsga2[0].innerHTML = "填写正确";
    }
}

var nameTipMsga3 = document.getElementsByClassName("nameTipMsga3");
function checKSex(userSex) {
    if (userSex != "男" && userSex != "女"){
        nameTipMsga3[0].style.color = "red";
        nameTipMsga3[0].innerHTML = "性别填写错误";
        return;
    }
    else{
        nameTipMsga3[0].style.color = "green";
        nameTipMsga3[0].innerHTML = "填写正确";
    }
}

var nameTipMsga4 = document.getElementsByClassName("nameTipMsga4");
function checKPw(passWord) {
    //密码的格式为6-12位，只能是字母、数字和下划线
    if (!(passWord.match(/^[\w]{6,12}$/))){
        nameTipMsga4[0].style.color = "red";
        nameTipMsga4[0].innerHTML = "密码至少6位，或非法符号";
        return;
    }
    else{
        nameTipMsga4[0].style.color = "green";
        nameTipMsga4[0].innerHTML = "密码填写合法";
    }
}

var nameTipMsga5 = document.getElementsByClassName("nameTipMsga5");
function checKcName(cName) {
    if(!(cName.match(/^[\u4e00-\u9fa5]+$/))){
        nameTipMsga5[0].style.color = "red";
        nameTipMsga5[0].innerHTML = "请输入汉字";
        return;
    }
    else{
        nameTipMsga5[0].style.color = "green";
        nameTipMsga5[0].innerHTML = "填写正确";
    }
}

var nameTipMsga6 = document.getElementsByClassName("nameTipMsga6");
function checKGrade(grade) {
    if (grade == ""){
        nameTipMsga6[0].style.color = "red";
        nameTipMsga6[0].innerHTML = "年龄填写错误";
        return;
    }
    else {
        nameTipMsga6[0].style.color = "green";
        nameTipMsga6[0].innerHTML = "填写正确";
    }
}

//更改学生信息
function update() {
    var studentNumber = document.getElementById("studentNu").value;
    var studentName = document.getElementById("studentNa").value;
    var studentAge = document.getElementById("studentA").value;
    var studentSex = document.getElementById("studentS").value;
    var studentPW = document.getElementById("studentP").value;
    var studentCname = document.getElementById("textName").value;
    var studentGrade = document.getElementById("textGrade").value;

    if (!(studentNumber.match(/^([1-9]\d*|[0]{1,1})$/)) || !(studentName.match(/^[\u4E00-\u9FA5]{1,4}$/)) || studentGrade == ""|| !(studentCname.match(/^[\u4e00-\u9fa5]+$/))
        || !(studentPW.match(/^[\w]{6,12}$/)) || (studentSex != "男" && studentSex != "女") || !(studentAge.match(/^([1-9]\d*|[0]{1,1})$/))){
        alert("请输入合法操作符！！！");
        return;
    }

    if (studentNumber == ""||studentName == ""||studentAge == ""||studentSex == ""||studentPW == ""||studentCname == ""||studentGrade == ""){
        alert("请将信息填写完整！！！");
        return;
    }

    checkName(studentName);
    checkAge(studentAge);
    checkSex(studentSex);
    checkPw(studentPW);
    checkcName(studentCname);
    checkGrade(studentGrade);

    $.ajax({
        url:'/Add.do',
        method:'POST',
        data:{
            studentNumber : studentNumber,
            studentName: studentName,
            studentAge : studentAge,
            studentSex : studentSex,
            studentPW : studentPW,
            studentCname : studentCname,
            studentGrade : studentGrade,
            method: "update",
        },
        success: function () {
            window.location.href = "/Table.do";
        },
        error: function (result) {
            alert(result);
        }
    })
}

//填写更改学生信息
function openNav1(userNo) {
    document.getElementById("update").style.width = "50%";
    document.getElementById("studentNu").value = userNo;
}

function closeNav1() {
    document.getElementById("update").style.width = "0";
}