var table;
$(function () {
    table = $('#data_list').DataTable({
        "ajax": {
            "url": $("#basePath").val() + "admin/admin/list",
            "type": "POST",
            "data": function (d) {
                return {
                    "ano": $('#query_ano').val(),
                    "aname": $('#query_aname').val()
                };
            }
        },
        "columns": [
            {"data": "ano"},
            {"data": "aname"},
            {"data": "password"},
            {"data": null}
        ],
        columnDefs: [
            {
                targets: 3,
                render: function (a, b, c, d) {
                    return "<button type='button' class='btn btn-xs btn-warning' id='btn_edit' onclick='showUpdate(\"" + c.id + "\")'>修改</button>&nbsp" +
                        "<button type='button' class='btn btn-xs btn-danger' id='btn_edit' onclick='showDel(\"" + c.id + "\")'>删除</button>";
                }
            }
        ],
    });
});

function query() {
    table.ajax.reload();
}

function showAdd() {
    $('#modal_add').modal('show');
}

function add() {
    if (!validAdd()) {
        return;
    }

    var param = {
        ano: $.trim($("#add_ano").val()),
        aname: $.trim($("#add_aname").val()),
        password: $.trim($("#add_password").val())
    }

    jQuery.ajax({
        type: 'POST',
        url: $("#basePath").val() + 'admin/admin/save',
        cache: false,
        data: param,
        success: function (data) {
            if (data == 1) {
                $('#modal_add').modal('hide');
                showInfo("操作成功");
                table.ajax.reload();
            } else if (data == 0) {
                showInfo("操作失败，请重试");
            } else if (data == -1) {
                showInfo("此管理员编码已存在，请重新输入");
            } else {
                showInfo("操作失败，请重试");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("操作失败，请重试");
        }
    });
}

function showUpdate(id) {
    jQuery.ajax({
        type: 'POST',
        url: $("#basePath").val() + 'admin/admin/findById',
        cache: false,
        data: {id: id},
        success: function (data) {
            $("#update_id").val(data.id);
            $("#update_ano").val(data.ano);
            $("#update_aname").val(data.aname);
            $("#update_password").val(data.password);
            $('#modal_update').modal('show');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("操作失败，请重试");
        }
    });
}

function update() {
    if (!validUpdate()) {
        return;
    }

    var param = {
        id: $.trim($("#update_id").val()),
        ano: $.trim($("#update_ano").val()),
        aname: $.trim($("#update_aname").val()),
        password: $.trim($("#update_password").val())
    }

    jQuery.ajax({
        type: 'POST',
        url: $("#basePath").val() + 'admin/admin/update',
        cache: false,
        data: param,
        success: function (data) {
            if (data) {
                $('#modal_update').modal('hide');
                showInfo("操作成功");
                table.ajax.reload();
            } else {
                showInfo("操作失败，请重试");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("操作失败，请重试");
        }
    });
}

function showDel(id) {
    $('#modal_delete').modal('show');
    $('#delete_id').val(id);
}

function del() {
    jQuery.ajax({
        type: 'POST',
        url: $("#basePath").val() +'admin/admin/delete',
        cache: false,
        data: {
            id: $('#delete_id').val()
        },
        success: function (data) {
            if (data) {
                $('#modal_delete').modal('hide');
                table.ajax.reload();
            } else {
                showInfo("操作失败，请重试");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("操作失败，请重试");
        }
    });
}

function validAdd() {
    var flag = true;

    var add_ano = $.trim($("#add_ano").val());
    if (add_ano == "") {
        $("#add_ano").parent().parent().addClass("has-error");
        $("#add_ano").next().text("请输入管理员编号");
        flag = false;
    } else if (add_ano.length > 20) {
        $("#add_ano").parent().parent().addClass("has-error");
        $("#add_ano").next().text("管理员编号长度不能大于20");
        flag = false;
    } else {
        $("#add_ano").parent().parent().removeClass("has-error");
        $("#add_ano").next().text("");
    }

    var add_aname = $.trim($("#add_aname").val());
    if (add_aname == "") {
        $("#add_aname").parent().parent().addClass("has-error");
        $("#add_aname").next().text("请输入管理员名称");
        flag = false;
    } else if (add_aname.length > 50) {
        $("#add_aname").parent().parent().addClass("has-error");
        $("#add_aname").next().text("管理员名称长度不能大于50");
        flag = false;
    } else {
        $("#add_aname").parent().parent().removeClass("has-error");
        $("#add_aname").next().text("");
    }

    var add_password = $.trim($("#add_password").val());
    if (add_password == "") {
        $("#add_password").parent().parent().addClass("has-error");
        $("#add_password").next().text("请输入密码");
        flag = false;
    } else if (add_password.length > 20) {
        $("#add_password").parent().parent().addClass("has-error");
        $("#add_password").next().text("密码长度不能大于20");
        flag = false;
    } else {
        $("#add_password").parent().parent().removeClass("has-error");
        $("#add_password").next().text("");
    }

    return flag;
}

function validUpdate() {
    var flag = true;

    var update_aname = $.trim($("#update_aname").val());
    if (update_aname == "") {
        $("#update_aname").parent().parent().addClass("has-error");
        $("#update_aname").next().text("请输入管理员名称");
        flag = false;
    } else if (update_aname.length > 50) {
        $("#update_aname").parent().parent().addClass("has-error");
        $("#update_aname").next().text("管理员名称长度不能大于50");
        flag = false;
    } else {
        $("#update_aname").parent().parent().removeClass("has-error");
        $("#update_aname").next().text("");
    }

    var update_password = $.trim($("#update_password").val());
    if (update_password == "") {
        $("#update_password").parent().parent().addClass("has-error");
        $("#update_password").next().text("请输入密码");
        flag = false;
    } else if (update_password.length > 20) {
        $("#update_password").parent().parent().addClass("has-error");
        $("#update_password").next().text("密码长度不能大于20");
        flag = false;
    } else {
        $("#update_password").parent().parent().removeClass("has-error");
        $("#update_password").next().text("");
    }

    return flag;
}

function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}