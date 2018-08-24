function login(){
    var userNumber = document.getElementById("userNumber").value;
    var passWord = document.getElementById("passWord").value;
    var checkBox;
    var checkbox = document.getElementById("checkBox");
    if (checkbox.checked){
        checkBox = checkbox.value;
    }
    else {
        checkBox = "2";
    }

    if (userNumber == "" || passWord == ""){
        alert("请输入账号和密码");
        return;
    }

    $.ajax({
        url: '/Submit.do',
        method: 'POST',
        data:{
            userNumber: userNumber,
            passWord: passWord,
            checkBox: checkBox,
        },
        success:function (result) {
            if (result == "true"){
                window.location.href = "/Table.do";
            }
            else{
                alert("账号或密码错误！！！");
            }
        },
        error:function (result) {
            alert(result);
        }
    })
}

