$(".checkBtn").click(
    function () {
        $("#checkLink").attr("href", "/student/"+$("#checkNumber").val()).post();
    }
);