let core = {
    //限流工具类
    throttle: function (method, args, context) {
        clearTimeout(method.tId);
        method.tId = setTimeout(function () {
            method.call(context, args);
        }, 200);
    },

    http: function (option, callback) {
        this.cancel && this.cancel.abort();
        //load: 加载loading, autoComplete：自动完成, goBack: 自动回退
        let opt = {load: true, autoComplete: true, goBack: true};
        let loadHandler;
        let loadTime;
        let options = {
            url: "",
            method: "post",
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            beforeSend: function () {
                this.load && ((loadTime = new Date().getTime()) && (loadHandler = LayUtil.layer.init(function (inner, layer) {
                    inner.loading(0, {shade: 0.1})
                })))
            },
            success: function (res) {
                //处理loading 加载
                if (this.load && loadHandler) {
                    let time = 0;
                    if (new Date().getTime() - loadTime < 500) {
                        time = 500;
                    }
                    setTimeout(function () {
                        loadHandler.closeLoading();
                    }, time)
                }
                let that = this;
                let handler;
                //延时
                setTimeout(function () {
                    //判断请求接口
                    switch (res.restCode) {
                        case CONSTANT.HTTP.ERROR:
                            core.prompt.alert(res.restInfo);
                            break;
                        case CONSTANT.HTTP.SUCCESS:
                            if (that.autoComplete) {
                                if(that.goBack){
                                    handler = function () {
                                        //后退刷新
                                        window.location.href = document.referrer;
                                    }
                                }

                                core.prompt.msg(res.restInfo, {shade: 0.3, time: 1200}, handler);
                            }
                            break;
                    }
                    //处理自定义回调
                    (callback instanceof Function) && callback(res)
                }, 600)
            }
        };
        Object.assign(opt, options, option);
        this.cancel = $.ajax(opt);
    },

    // 初始化 提示相关 内容
    prompt: {
        // 警告弹窗
        alert: function (content, opt) {
            core.prompt.msg(content, $.extend({},
                {
                    icon: 5,
                    shift: 6,
                    shade: 0.3,
                    time: 1500,
                    area: 'auto',
                    shadeClose: true
                }, opt));
        },

        // 信息框提示
        msg: function (content, option, callback) {
            LayUtil.layer.init(function (inner) {
                inner.msg(content, option, callback);
            })
        },

        // 询问框
        confirm:function (content, option, callback) {
            LayUtil.layer.init(function (inner){
                inner.confirm(content, option, callback);
            })
        }
    },

    // 业务相关
    business: {
        delete: function (data, callback) {
            let config = {url:"delete.do", goBack: false, data:{id:data.id}}
            core.prompt.confirm("确认删除?", {icon:3,title:'提示'}, function () {
                core.http(config, callback)
            })
        }
    }

};


const CONSTANT = {
    //http相关
    HTTP: {
        SUCCESS: 200,
        ERROR: 500
    }
};


// layui工具函数
function LayUtil() {
}

//树形表格属性
LayUtil.treeTableOption = {
    treeColIndex: 1,
    treeSpid: 0,
    treeIdName: 'id',
    treePidName: 'parentId',
    elem: '#treeTable',
    page: false
}

//下拉树选项
LayUtil.selectTreeOption = {
    elem: "#selectTree",
    url: "",
    dataType: "json",
    async: false,
    method: 'post',
    ficon: ["1", "-1"],
    dataStyle: "layuiStyle",
    initLevel: 1,
    width: "100%",
    dot: false,
    checkbar: false,
    formatter: {
        title: function (data) {
            let s = data.name;
            if (data.children) {
                s += ' <span style=\'color:blue\'>(' + data.children.length + ')</span>';
            }
            return s;
        }
    },
    response: {
        statusCode: 200,
        statusName: "restCode",
        treeId: "id",
        message: "restCode",
        rootName: "data",
        title: 'name'
    },
};

//layui的表格固定配置
LayUtil.dataGridOption = {
    id: "dataGrid",
    elem: '#dataGrid',
    method: 'post',
    page: true,
    url: '',
    limit: 10,
    request: {
        pageName: 'pageCurrent', //页码的参数名称，默认：page
        limitName: 'pageSize' //每页数据量的参数名，默认：limit
    },
    response: {
        statusName: 'restCode', //数据状态的字段名称，默认：code
        statusCode: 200, //成功的状态码，默认：0
        msgName: 'restCode', //状态信息的字段名称，默认：msg
        countName: 'pageCount', //数据总数的字段名称，默认：count
        rootName: 'data'
    },
    parseData: function (res) {
        // 下面字符串是对上面字符串的解释
        return {
            "restCode": res.restCode,
            "pageCount": res.data ? res.data.pageCount : undefined,
            "data": res.data ? res.data.content : undefined
        }
    }
};

//树形配置
LayUtil.treeOption = {
    elem: "#tree",
    url: "",
    dataType: "json",
    async: false,
    method: 'get',
    dataStyle: "layuiStyle",
    initLevel: 1,
    data: "",
    dot: false,
    checkbar: false,
    contentType:'application/json;charset=UTF-8',
    nodeIconArray: {"1": {"open": "dtree-icon-wenjianjiazhankai", "close": "dtree-icon-weibiaoti5"}},
    icon: ["1", "7"],
    formatter: {
        title: function (data) {
            let s = data.name;
            if (data.children) {
                s += ' <span style=\'color:blue\'>(' + data.children.length + ')</span>';
            }
            return s;
        }
    },
    response: {
        statusCode: 200,
        statusName: "restCode",
        treeId: "id",
        message: "restCode",
        rootName: "data",
        title: 'name'
    },
};

LayUtil.prototype = {
    construct: LayUtil,

    //弹窗
    layer: (function (LayUtil) {
        function Inner() {
        }

        Inner.prototype = {
            construct: Inner,
            init: function (callback) {
                let that = this;
                layui.use('layer', function () {
                    that.layer = layui.layer;
                    if (callback instanceof Function) {
                        callback(that, that.layer);
                    }
                })
                return this;
            },

            //显示loading加载
            loading: function (config = {}) {
                this.layer.load(config);
            },

            //关闭loading
            closeLoading: function () {
                this.layer.closeAll('loading');
            },

            // 信息提示框
            msg: function (content, option, callback) {
                return layer.msg(content, option, callback);
            },

            //询问框
            confirm:function (content, option, callback) {
                let that = this;
                this.layer.confirm(content, option, function (index) {
                    // 关闭询问框
                    that.layer.close(index);
                    (callback instanceof Function) && callback();
                })
            }
        }
        LayUtil.layer = new Inner();
    })(LayUtil),

    //form表单
    form: (function (LayUtil) {
        function Inner() {
        }

        Inner.prototype = {
            construct: Inner,

            init: function (callback) {
                let that = this;
                layui.use('form', function () {
                    that.form = layui.form;
                    that.form.render();
                    if (callback instanceof Function) {
                        callback(that)
                    }
                });
                return this;
            },

            //提交表单
            submit: function (callback, name, type = "submit") {
                this.form.on(type + "(" + (name === undefined ? 'go' : name) + ")", function (obj) {
                    if (callback instanceof Function) {
                        callback(obj);
                        return false;
                    }
                    return true;
                })
            },

            //验证
            verify: function (validator) {
                this.form.verify(validator);
            }

        }
        LayUtil.form = new Inner();
    })(LayUtil),

    //树形表格
    treeTable: (function (LayUtil) {
        function Inner() {
        }

        Inner.prototype = {
            construct: Inner,

            init: function (config, callback) {
                let that = this;
                let option = $.extend({}, LayUtil.treeTableOption, config);

                layui.extend({
                    treetable: '{/}' + BASE_PATH + '/admin/layui/lay/modules/treetable'
                }).use(['treetable', 'table'], function () {
                    that.treetable = layui.treetable;
                    that.treetable.render(option);
                    that.table = layui.table;
                    that.rightTool(function (obj) {
                        if(obj.event!==undefined && obj.event==="del"){
                            that.delete(obj.data, $.extend({}, LayUtil.treeTableOption, config))
                        }
                    });
                    (callback instanceof Function) && callback(that);
                });
                return this;
            },
            rightTool: function (callback, filter="treeTable") {
                // 用于监听 lay-filter = filter 的 table
                // obj.data 获取当前行数据, obj.event 获取lay-event对应的值
                this.table.on('tool('+filter+')', function (obj) {
                    (callback instanceof Function) && callback(obj);
                })
            },
            delete:function (data, option) {
                let that = this;
                core.business.delete(data, function () {
                    that.treetable.render(option);
                })
            }
        }
        LayUtil.treeTable = new Inner();
    })(LayUtil),

    //下拉树形
    selectTree: (function (LayUtil) {
        function Inner() {
        }

        Inner.prototype = {
            construct: Inner,
            init: function (config, callback) {
                let that = this;
                let option = $.extend({}, LayUtil.selectTreeOption, config);
                // {/}的意思即代表采用自有路径，即不跟随 base 路径 你还得把第三方js改成layui.denfine()那种格式
                layui.extend({
                    dtree: '{/}' + BASE_PATH + "/admin/layui/lay/modules/selectTree"
                }).use('dtree', function () {
                    that.dtree = layui.dtree;
                    that.dtree.renderSelect(option);
                    (callback instanceof Function) && callback(that, that.dtree);
                });
                return this;
            }
        };
        LayUtil.selectTree = new Inner();
    })(LayUtil),

    // dataGrid
    dataGrid:(function (LayUtil){
        function Inner() {
        }
        Inner.prototype = {
            construct:Inner,

            init:function (config, callback) {
                let option = $.extend({}, LayUtil.dataGridOption, config);
                let that = this;
                layui.use("table", function () {
                    that.table = layui.table;
                    that.table.render(option);
                    (callback instanceof Function) && (callback(that))
                });

                return this;
            }
        }
        LayUtil.dataGrid = new Inner();
    })(LayUtil),

    // dtree
    tree:(function (LayUtil) {
        function Inner() {
        }

        Inner.prototype = {
            construct:Inner,

            init:function (config) {
                let that = this;
                let option = $.extend({},LayUtil.treeOption,config);
                this.id = option.elem;

                if (option.checkbar!==undefined && option.checkbar) {
                    // 自定扩展的二级非最后一级图标，从1开始
                    option.nodeIconArray = {
                        "1": {
                            "open": "dtree-icon-wenjianjiazhankai",
                            "close": "dtree-icon-weibiaoti5"
                        }
                    };
                    option.icon = ["1", "8"];
                }

                layui.extend({
                    dtree: '{/}' + BASE_PATH + '/admin/layui/lay/modules/dtree'
                }).use('dtree', function () {
                    that.dtree = layui.dtree;
                    that.dtree.render(option);
                });

                return this;
            },

            getChecked: function (obj, name) {

                // dtree提供的方法来获取 被选中复选框 的值
                // 方法的参数为dtreeoption 中 elem的值(去掉"#"), 也就是页面上的那个id
                let arr = this.dtree.getCheckbarNodesParam(this.id.replace("#", ""));

                if(arr!==undefined) {
                    for (let i = 0; i < arr.length; i++) {
                        obj[name+"["+i+"]"] = arr[i].nodeId;
                    }
                }
            }
        };

        LayUtil.tree = new Inner();
    })(LayUtil)

};
