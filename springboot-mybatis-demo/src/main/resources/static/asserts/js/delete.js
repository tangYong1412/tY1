$(".delete").click(function () {
    //删除当前学生信息
    $("#deleteForm").attr("action", $(this).attr("delete_url")).submit();
});