<link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/plugin/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript">
    $(function () {
        window.onload = numberInputPlaceholder();
    });
    function numberInputPlaceholder() {
        $(".easyui-combobox").each(function (i) {
            var span = $(this).siblings("span")[0];
            var targetInput = $(span).find("input:first");
            if (targetInput) {
                $(targetInput).attr("placeholder", $(this).attr("placeholder"));
            }
        });
    }
</script>