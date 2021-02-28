<#--通用form模板-->
<#macro form>
    <form class="layui-form" method="post">
        <#nested>
    </form>
</#macro>

<#--通用form中的每一项 一行item-->
<#macro item>
    <div class="layui-form-item">
        <#nested>
    </div>
</#macro>

<#--    item中左右两边的inline-->
<#macro inline label="">
    <div class="layui-inline cms-inline-50">
        <label class="layui-form-label layui-col-md6" style="width:197px;">${label}</label>
        <div class="layui-input-block layui-col-md6 cms-inline-block">
            <#nested>
        </div>
    </div>
</#macro>

<#macro submit>
    <div class="layui-form-item">
        <div class="layui-inline cms-inline-50">
            <label class="layui-form-label layui-col-md6" style="width:197px;"></label>
            <div class="layui-input-block layui-col-md6 cms-inline-block">

            </div>
        </div>
        <div class="layui-inline cms-inline-50">
            <label class="layui-form-label layui-col-md6" style="width:197px;"></label>
            <div class="layui-input-block layui-col-md6 cms-inline-block">
                <p class="cms-flex-end cms-pt50">
                    <button class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
                    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="go">提交</button>
                </p>
            </div>
        </div>
    </div>
</#macro>