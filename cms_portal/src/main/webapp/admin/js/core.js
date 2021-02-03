let core={
    //限流工具类
    throttle:function(method,args,context){
        clearTimeout(method.tId);
        method.tId=setTimeout(function(){
            method.call(context,args);
        },200);
    },
    http:function(option){
        this.cancel && this.cancel.abort();
        let opt={load:true},loadHandler,options ={
            url:"",
            method:"post",
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            beforeSend:function(){
                this.load &&  (loadHandler = LayUtil.layer.init(function(inner,layer){
                    inner.loading(0,{shade:0.1})
                }))
            },
            success:function(res){
                if(res.restCode===CONSTANT.HTTP.SUCCESS){
                    loadHandler.closeLoading();
                }
            }

        };
        Object.assign(opt,options,option);
        this.cancel=$.ajax(opt);
    }
};

const  CONSTANT = {
  //http相关
  HTTP:{
      SUCCESS:200,
      ERROR:500
  }

};



// layui工具类
function LayUtil(){

}


LayUtil.prototype = {
    construct:LayUtil,
    //弹窗
    layer:(function(LayUtil){
        function Inner(){

        }

        Inner.prototype={
            construct:Inner,
            init:function(callback){
                let that = this;
                layui.use('layer',function(){
                    that.layer = layui.layer;
                    if(callback instanceof Function){
                        callback(that,that.layer);
                    }
                })
                return this;
            },
            //显示loading加载
            loading:function(config={}){
                this.layer.load(config);
            },
            closeLoading:function(){
                this.layer.closeAll('loading');
            }
        }
        LayUtil.layer = new Inner();
    })(LayUtil),
    //form表单
    form:(function(LayUtil){
        function Inner(){
        }
        Inner.prototype={
            construct:Inner,
            init:function(callback){
                let that =this;
                layui.use('form',function(){
                        that.form = layui.form;
                        that.form.render();
                        if(callback instanceof Function){
                            callback(that,that.form)
                        }
                });
                return this;
            },
            //提交表单
            submit:function(callback,name,type="submit"){
                this.form.on(type+"("+(name===undefined?'login':name)+")",function(obj){
                    if(callback instanceof Function){
                        callback(obj);
                        return false;
                    }
                    return true;
                })
            },
            //验证
            verify:function(validator){
                this.form.verify(validator);
            }
        }
        LayUtil.form = new Inner();
    })(LayUtil)




}
