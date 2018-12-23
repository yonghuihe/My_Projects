$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var menuManageDatagrid, menuManageDialog, menuManageForm;
    menuManageDatagrid = $("#menuManage_datagrid");
    menuManageDialog = $("#menuManage_dialog");
    menuManageForm = $("#menuManage_form");
    /*
     * 初始化数据表格
     */
    menuManageDatagrid.datagrid({
        url: "/menuManage_list.do",
        fit: true,
        singleSelect: true,
        rownumbers:true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#menuManage_datagrid_tb',
        columns: [[
            {field: 'id', title: 'ID', width: 1, align: 'center'},
            {field: 'name', title: '名称', width: 1, align: 'center'},
            {field: 'type', title: '类型', width: 1, align: 'center',},
            {field: 'link', title: '链接/KEY', width: 1, align: 'center'},
            {field: 'parent', title: '父菜单', width: 1, align: 'center', formatter: parentFormatter},
            //{field: 'parent', title: '上级部门', width: 1,formatter:deptFormatter},
        ]],
    });
    function parentFormatter(value, row, index) {
        return value ? value.name : "";
    }

    /*
     * 初始化新增/编辑对话框
     */
    menuManageDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#menuManage_dialog_bt'
    });
    /*
     * 对页面按钮进行统一监听
     */
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    /*
     * 所有的操作封装到cmdObj对象中,方便管理
     */
    var cmdObj = {
        add: function () {
            menuManageForm.form("clear");
            menuManageDialog.dialog("setTitle", "新增菜单");
            menuManageDialog.dialog("open");
        },
        edit: function () {
            var rowData = menuManageDatagrid.datagrid("getSelected");
            if (rowData) {
                menuManageForm.form("clear");
                menuManageDialog.dialog("setTitle", "编辑菜单");
                menuManageDialog.dialog("open");
                if (rowData.parent)
                    rowData["parent.id"] = rowData.parent.id;
                menuManageForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = menuManageDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/menuManage_delete.do?menuManageId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    menuManageDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的数据!", "warining");
            }
        },
        reload: function () {
            menuManageDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/menuManage_update.do"
            } else {
                url = "/menuManage_save.do";
            }
            menuManageForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            menuManageDialog.dialog("close");
                            menuManageDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            menuManageDialog.dialog("close");
        },
        reloadMenu: function () {
            $.get("/menuManage_reloadMenu.do", function (data) {
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg, "info", function () {
                    });
                } else {
                    $.messager.alert("温馨提示", data.msg, "error");
                }
            }, "json")
        }
    }
});
