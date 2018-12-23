$(document).ready(function () {
    $(".page_btn").click(function () {
        $("[name='queryObject.currentPage']").val($(this).attr("data-page") || $("[name='queryObject.currentPage']").val());
        $("#searchForm").submit();
    });

    $("[name='queryObject.pageSize']").change(function () {
        $("#searchForm").submit();
    });

    $(".btn_input").click(function () {
        window.location.href = $(this).attr("data-href");
    });
});