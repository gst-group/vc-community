String.prototype.format = function () {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g, function (s, i) {
        return args[i];
    });
};
Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

var BASE_DIR = "/lk-vc/";
// var BASE_DIR = "/";

function loadContent(ele) {
    return true;
}

//实际选中的
var selectedGrpOrUser = {group: [], user: []};

function selectUser(ele, userName, grpName) {
    ele = $(ele);
    var checked = ele.prop("checked");
    if (checked) {//增加选中
        selectedGrpOrUser.user.push(userName);
    } else {
        selectedGrpOrUser.user.remove(userName);
    }
    if (isList1Exists()) {
        var grpEle = $("#list1" + grpName);
        var status = getSelectUserStatus();
        if (status == 0 || status == 2) {
            setHalfCheckedClass(grpEle, false);
            grpEle[0].checked = status == 2;
            setList2UsersInfo(false);
        } else {
            grpEle[0].checked = false;
            setHalfCheckedClass(grpEle, true);
            setList2UsersInfo(true);
        }
    }
}


function filterUser(form, grpId, selectionTypeValue) {
    var user_under_group_url = BASE_DIR + "admin/user/filter.do";
    var container = $("#list2");
    if (!selectionTypeValue) {
        selectionTypeValue = $("#selectionType").find("option:selected").val();
    }
    var emptyLeft = !grpId;
    var emptyRight = true;
    $("#filter").find("input").each(function (idx, ele) {
        var canSelect = (ele.type == 'radio' || ele.type == 'checkbox');
        if ((canSelect && ele.checked) || (!canSelect && $(ele).val() != "")) {
            emptyRight = false;
        }
    });
    if ((selectionTypeValue == 0 && emptyLeft) || (selectionTypeValue == 1 && emptyRight) || ((selectionTypeValue == 2 || selectionTypeValue == 3) && (emptyLeft || emptyRight))) {
        alert("过滤参数不和要求");
        return;
    }
    form = $(form);
    var reqParams = {};
    var extraData = {};
    form.find("input:not(.extendsInfoControl)").each(function (idx, ele) {
        var canSelect = (ele.type == "radio" || ele.type == "checkbox");
        if ((canSelect && ele.checked) || (!canSelect && $(ele).val() != "")) {
            reqParams[ele.name] = $(ele).val();
        }
    });
    $(".extendsInfoControl").each(function (idx, ele) {
        extraData[ele.name] = $(ele).val();
    });
    reqParams['extendsInfo'] = JSON.stringify(extraData);
    reqParams['grp'] = grpId;
    reqParams['selectionType'] = selectionTypeValue;
    $.post(user_under_group_url, reqParams,
        function (data) {
            appendContent(data, container, grpId);
        }).error(function (res) {
        alert("系统错误");
    });
}

function appendContent(data, container, grpId) {
    var html = "";
    if (data.code != 0) {
        alert(data.message);
    } else {
        var list = data.extraList;
        var listEle = $("#list1" + grpId);
        if (listEle.length > 0) {
            var isChecked = listEle.prop("checked");
        }
        for (var idx in list) {
            if (list.hasOwnProperty(idx)) {
                var user = list[idx];
                var checked = isChecked || selectedGrpOrUser.user.indexOf(user.name) != -1 ? "checked='checked'" : "";
                html += "<input type='checkbox' onchange='selectUser(this,\"{0}\",\"{1}\")' {3}/><span>{2}</span><br/>".format(user.name, grpId, user.name, checked);
            }
        }
        container.html(html);
        container.data("grp-id", grpId);
    }
}

/**
 * 设置组信息
 * @param groupContainer
 * @param name true为获取群组名
 */
function setGroupInfo(groupContainer, name) {
    groupContainer = $(groupContainer);
    selectedGrpOrUser.group = [];
    groupContainer.find("input:checked").each(function (idx, ele) {
        ele = $(ele);
        var value;
        if (name === true) {
            value = ele.next().text().trim();
        } else {
            value = ele.val();
        }
        selectedGrpOrUser.group.push(value);
    });
}

function setList2UsersInfo(isAdd) {
    $('#list2').find("input:checked").each(function (idx, ele) {
        var username = $(ele).next().html();
        if (selectedGrpOrUser.user.indexOf(username) == -1) {
            if (isAdd) {
                selectedGrpOrUser.user.push(username);
            }
        } else if (!isAdd) {
            selectedGrpOrUser.user.remove(username);
        }
    })
}

/**
 * 全选用户
 */
function selectAllUser() {
    var userContainer = $("#list2");
    userContainer.find("input[type=checkbox]").each(function (idx, ele) {
        ele.checked = true;
    });
    var grpEle = $("#list1" + userContainer.data('grp-id'));
    if (grpEle.length > 0) {
        grpEle[0].checked = true;
        setHalfCheckedClass(grpEle, false);
    }
    setList2UsersInfo(false);
}

/**
 * 全不选用户
 */
function deselectAllUser() {
    var userContainer = $("#list2");
    userContainer.find("input[type=checkbox]").each(function (idx, ele) {
        var username = $(ele).next().html();
        selectedGrpOrUser.user.remove(username);
        ele.checked = false;
    });
    var grpEle = $("#list1" + userContainer.data('grp-id'));
    if (grpEle.length > 0) {
        grpEle[0].checked = false;
        clearAllHalfCheckClass();
    }
}

/**
 * 全选所有讨论区
 * @param isSelectAll true:全选所有用户，false：取消全选
 */
function selectAllGroup(isSelectAll) {
    $("#list1").find("input[type=checkbox]").each(function (idx, ele) {
        ele.checked = !!isSelectAll;
    });
    $("#list2").find("input[type=checkbox]").each(function (idx, ele) {
        ele.checked = !!isSelectAll;
        var username = $(ele).next().html();
        if (selectedGrpOrUser.user.indexOf(username) != -1) {
            selectedGrpOrUser.user.remove(username);
        }
    });
    if (!isSelectAll) {
        selectedGrpOrUser.user = [];
    }
    clearAllHalfCheckClass();
}

/**
 * 返回list2选中状态
 * 0为未选，1为部分选中，2为全选
 */
function getSelectUserStatus() {
    var listContainer = $("#list2");
    if (listContainer.find("input:not(:checked)").length == 0) {
        return 2;
    } else if (listContainer.find("input:checked").length == 0) {
        return 0;
    } else {
        return 1;
    }
}

var halfCheckClassName = "half-checked-class";
function setHalfCheckedClass(ele, isHalfChecked) {
    var nextEle = $(ele).next();
    if (isHalfChecked) {
        nextEle.addClass(halfCheckClassName);
    } else {
        nextEle.removeClass(halfCheckClassName);
    }
}

function clearAllHalfCheckClass() {
    $("." + halfCheckClassName).removeClass(halfCheckClassName);
}

function isList1Exists() {
    return $("#list1").length != 0;
}



