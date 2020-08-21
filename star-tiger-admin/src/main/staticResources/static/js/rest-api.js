window.MainRestApi = {
    changeUserPwd: function (oldPwd, newPwd, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/userPwd",
            "put", {oldPwd: oldPwd, newPwd: newPwd}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    }
}

window.ResourceRestApi = {
    getAllResourceTransferData: function (successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/resource/transferData",
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    getRoleResourceTransferData: function (roleFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/resource/transferData?roleFlow=" + roleFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    readResourceInfo: function (resFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/resource/" + resFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    addResource: function (resource,
                           successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/resource",
            "post", JSON.stringify(resource), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    updateResource: function (resource,
                              successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/resource/" + resource.resFlow,
            "put", JSON.stringify(resource), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    changeResourceEnableStatus: function (resFlow, enableFlag,
                                          successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/resource/enableStatus/" + resFlow + "/" + enableFlag,
            "put", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    }
}

window.RoleRestApi = {
    addRole: function (role, resourceFlows,
                       successCallback, errorCallback, completeCallback) {
        let saveDto = {
            role: role,
            resourceFlows: resourceFlows
        }
        StigerHttp.ajax(
            "/sys/role",
            "post", JSON.stringify(saveDto), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    modRole: function (role, resourceFlows,
                       successCallback, errorCallback, completeCallback) {
        let saveDto = {
            role: role,
            resourceFlows: resourceFlows
        }
        StigerHttp.ajax(
            "/sys/role",
            "put", JSON.stringify(saveDto), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    readRole: function (roleFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/role/" + roleFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    getAllRoleTransferData: function (successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/role/transferData",
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    }
}

window.MenuRestApi = {
    read: function (menuFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/menu/" + menuFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    addMenu: function (menu, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/menu",
            "post", JSON.stringify(menu), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    modMenu: function (menu, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/menu",
            "put", JSON.stringify(menu), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    changeEnableFlag: function (menuFlow, flag, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/menu/enableStatus/" + menuFlow + "/" + flag,
            "put", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    }
}

window.UserRestApi = {
    read: function (userFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/user/" + userFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    add: function (user, roleFlows,
                   successCallback, errorCallback, completeCallback) {
        let saveDto = {
            user: user,
            roleFlows: roleFlows
        }
        StigerHttp.ajax(
            "/sys/user",
            "post", JSON.stringify(saveDto), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    mod: function (user, roleFlows,
                   successCallback, errorCallback, completeCallback) {
        let saveDto = {
            user: user,
            roleFlows: roleFlows
        }
        StigerHttp.ajax(
            "/sys/user",
            "put", JSON.stringify(saveDto), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    changeLockStatus: function (userFlow, lockFlag,
                                successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/user/lock/" + userFlow + "/" + lockFlag,
            "put", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    resetPassword: function (userFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/user/resetPassword/" + userFlow,
            "put", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    }
}

window.DictRestApi = {
    read: function (dictFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/dict/" + dictFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    readItem: function (dictItemFlow, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/dict/item/" + dictItemFlow,
            "get", {}, "", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    add: function (dict, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/dict",
            "post", JSON.stringify(dict), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    mod: function (dict, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/dict",
            "put", JSON.stringify(dict), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    addItem: function (dictItem, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/dict/item",
            "post", JSON.stringify(dictItem), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    },
    modItem: function (dictItem, successCallback, errorCallback, completeCallback) {
        StigerHttp.ajax(
            "/sys/dict/item",
            "put", JSON.stringify(dictItem), "application/json", "json",
            successCallback, errorCallback, completeCallback
        );
    }
}