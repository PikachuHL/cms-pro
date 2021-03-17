<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="${basePath}/admin/layui/css/layui.css" media="all">
        <script src="${basePath}/common/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
        <script src="${basePath}/admin/layui/layui.js" charset="utf-8"></script>
        <script src="${basePath}/admin/js/core.js"></script>
        <link rel="stylesheet" href="${basePath}/admin/css/admin.css" media="all">
        <script type="application/javascript">
            let BASE_PATH = "${basePath!''}";
        </script>
    </head>
    <body>
    <div>
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <#if goBack??>
                        <div class="layui-card-header">
                            <a href="javascript:window.history.go(-1)" class="cms-go-back"></a>
                        </div>
                    </#if>
                    <div class="layui-card-body">
                        <div class="layui-row" style="margin:20px 0;">
                            <#nested>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</#macro>
