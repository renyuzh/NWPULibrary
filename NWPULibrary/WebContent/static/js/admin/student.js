var table;
$(function () {
    table = $('#data_list').DataTable({
        "ajax": {
            "url": $("#basePath").val() + "admin/student/list",
            "type": "POST",
            "data": function (d) {
                return {
                    "sno": $('#query_sno').val(),
                    "sname": $('#query_sname').val()
                };
            }
        },
        "columns": [
            {"data": "sno"},
            {"data": "sname"},
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
        sno: $.trim($("#add_sno").val()),
        sname: $.trim($("#add_sname").val()),
        password: $.trim($("#add_password").val())
    }

    jQuery.ajax({
        type: 'POST',
        url: $("#basePath").val() + 'admin/student/save',
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
                showInfo("此学号已存在，请重新输入");
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
        url: $("#basePath").val() + 'admin/student/findById',
        cache: false,
        data: {id: id},
        success: function (data) {
            $("#update_id").val(data.id);
            $("#update_sno").val(data.sno);
            $("#update_sname").val(data.sname);
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
        sno:$.trim($("#update_sno").val()),
        sname: $.trim($("#update_sname").val()),
        password: $.trim($("#update_password").val())
    }
    jQuery.ajax({
        type: 'POST',
        url: $("#basePath").val() + 'admin/student/update',
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
        url: $("#basePath").val() + 'admin/student/delete',
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

    var add_sno = $.trim($("#add_sno").val());
    if (add_sno == "") {
        $("#add_sno").parent().parent().addClass("has-error");
        $("#add_sno").next().text("请输入管理员编号");
        flag = false;
    } else if (add_sno.length > 20) {
        $("#add_sno").parent().parent().addClass("has-error");
        $("#add_sno").next().text("管理员编号长度不能大于20");
        flag = false;
    } else {
        $("#add_sno").parent().parent().removeClass("has-error");
        $("#add_sno").next().text("");
    }

    var add_sname = $.trim($("#add_sname").val());
    if (add_sname == "") {
        $("#add_sname").parent().parent().addClass("has-error");
        $("#add_sname").next().text("请输入管理员名称");
        flag = false;
    } else if (add_sname.length > 50) {
        $("#add_sname").parent().parent().addClass("has-error");
        $("#add_sname").next().text("管理员名称长度不能大于50");
        flag = false;
    } else {
        $("#add_sname").parent().parent().removeClass("has-error");
        $("#add_sname").next().text("");
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

    var update_sname = $.trim($("#update_sname").val());
    if (update_sname == "") {
        $("#update_sname").parent().parent().addClass("has-error");
        $("#update_sname").next().text("请输入管理员名称");
        flag = false;
    } else if (update_sname.length > 50) {
        $("#update_sname").parent().parent().addClass("has-error");
        $("#update_sname").next().text("管理员名称长度不能大于50");
        flag = false;
    } else {
        $("#update_sname").parent().parent().removeClass("has-error");
        $("#update_sname").next().text("");
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