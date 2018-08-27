function checkNumber(userNumber) {
    if (!(userNumber.match(/^([1-9]\d*|[0]{1,1})$/))){
        alert("账号填写错误！！！")
        return;
    }

    $.ajax({
        url:'/check.do',
        method:'POST',
        data:{
            userNumber: userNumber,
            tableName: "admin",
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

function checkPs(passWord){
    //密码的格式为6-12位，只能是字母、数字和下划线
    if (!(passWord.match(/^[\w]{6,12}$/))){
        var nameTipMs = document.getElementById("nameTipMs");
        nameTipMs.style.color = 'red';
        nameTipMs.innerHTML = "密码至少6位，或非法符号";
        return;
    }
    else{
        var nameTipMs = document.getElementById("nameTipMs");
        nameTipMs.style.color = 'green';
        nameTipMs.innerHTML = "密码合法";
        return;
    }
}

function register() {
    var useRNumber = document.getElementById("userNumber").value;
    var passWord = document.getElementById("passWord").value;
    var passWord1 = document.getElementById("passWord1").value;

    if(!(useRNumber.match(/^([1-9]\d*|[0]{1,1})$/))){
        alert("注册信息错误！！！");
        return;
    }

    if(!(passWord.match(/^[\w]{6,12}$/))){
        alert("注册信息错误！！！");
        return;
    }

    if(!(passWord1.match(/^[\w]{6,12}$/))){
        alert("注册信息错误！！！");
        return;
    }

    if (passWord1 != passWord){
        alert("前后密码不一致！！！");
        return;
    }

    $.ajax({
        url:'/Register.do',
        method:'POST',
        data:{
            userNumber: useRNumber,
            passWord: passWord,
        },
        success:function () {
                window.location.href="../jsp/SubmitAndRegister.jsp";

        },
        error:function (result) {
            alert(result);
        },
    })
}