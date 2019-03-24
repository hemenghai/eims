$(function () {
    //表格ss
    layui.use(['table','layer'], function() {
        var layer = layui.layer;
        var table = layui.table;
        table.render({
            elem: '#enterpriseTable',
            url: './enterprise/query'
            , cols: [[
                {field:'id',type: 'checkbox'}
                , {title: '序号', type: 'numbers'}
                , {field: 'enterpriseName', title: '企业名称'}
                //,{field:'enterpriseNumber', title: '企业代码',width:200}
                , {field: 'enterpriseScale', title: '企业规模'}
                //,{field:'legalPerson', title: '法人',width:80}
                //,{field:'establishmentTime', title: '成立时间',width:110}
                //,{field:'contactNumber', title: '联系号码',width:120}
                //,{field:'industryCategory', title: '行业类别',width:100}
                , {field: 'industryName', title: '行业名称'}
                , {field: 'industryChain', title: '所属产业链'}
                //,{field:'mainBusiness1', title: '主营业务1'}
                //,{field:'mainBusiness2', title: '主营业务2'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            , page: true
            , id: "testReload"
            , request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'size' //每页数据量的参数名，默认：limit
            }, done: function (res, curr, count) {
                this.where = {};
            }
        });
        var $ = layui.$, active = {
            reload: function () {
                var name = $("#name").val();
                var scale = $("#scale").val();
                var category = $("#category").val();
                var chain = $("#chain").val();
                //执行重载
                table.reload('testReload', {
                    url: './enterprise/query',
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        name: name,
                        scale: scale,
                        category: category,
                        chain: chain
                    }, done: function (res, curr, count) {
                        this.where = {};
                    }
                });
            }

    };
        //保存（新增/修改）
        $("#saveData").click(function(data){
            var id = $("#id").val();
            var enterpriseName = $("#enterpriseName").val();
            var enterpriseNumber = $("#enterpriseNumber").val();
            var enterpriseScale = $("#enterpriseScale").val();
            var legalPerson = $("#legalPerson").val();
            var establishmentTime = $("#establishmentTime").val();
            var contactNumber = $("#contactNumber").val();
            var industryCategory = $("#industryCategory").val();
            var industryName = $("#industryName").val();
            var industryChain = $("#industryChain").val();
            var mainBusiness1 = $("#mainBusiness1").val();
            var mainBusiness2 = $("#mainBusiness2").val();
            var data = {
                enterpriseName:enterpriseName,
                enterpriseNumber:enterpriseNumber,
                enterpriseScale:enterpriseScale,
                legalPerson:legalPerson,
                establishmentTime:establishmentTime,
                contactNumber:contactNumber,
                industryCategory:industryCategory,
                industryName:industryName,
                industryChain:industryChain,
                mainBusiness1:mainBusiness1,
                mainBusiness2:mainBusiness2
            };
            var method = '';
            if (id === null || id === "" || id === undefined){
                method = 'POST';
            }else {
                method = 'PUT';
                data.enterpriseId = id;
            }
            $.ajax({
                type:method,
                url:"./enterprise",
                contentType:'application/json',
                data:JSON.stringify(data),
                dataType:"json",
                success:function (data) {
                    layer.msg("保存成功");
                    active["reload"] ? active["reload"].call(this) : '';
                },error:function (error) {
                    layer.msg("保存失败");
                }
            });
            return false;
        });
        layui.use('laydate', function() {
            var laydate = layui.laydate;
            //常规用法
            laydate.render({
                elem: '#establishmentTime'
            });
        });
        //新增
        $("#addBtn").click(function () {
            layer.open({
                title: "企业信息",
                type: 1,
                content: $("#enterpriseInfoForm"),
                area: ["60%", "400px"],
                btn: ["保存", "取消"],
                yes: function (index, layero) {
                    $("#saveData").click();
                    layer.close(index);
                }, btn2: function (index, layero) {
                    $("#resetData").click();
                }
            });
        });
        //监听行工具事件
        table.on('tool(enterpriseTable)', function (obj) {
            var data = obj.data;
            var id = data.enterpriseId;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        type: 'DELETE',
                        url: "./enterprise",
                        contentType: 'application/json',
                        data: id,
                        success: function (data) {
                            layer.msg("删除成功");
                            active["reload"] ? active["reload"].call(this) : '';
                        }, error: function (error) {
                            layer.msg("删除失败")
                        }
                    });
                    //obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    title: "企业信息",
                    type: 1,
                    content: $("#enterpriseInfoForm"),
                    area: ["60%", "400px"],
                    btn: ["保存", "取消"],
                    success: function () {
                        $("#id").val(data.enterpriseId);
                        $("#enterpriseName").val(data.enterpriseName);
                        $("#enterpriseNumber").val(data.enterpriseNumber);
                        $("#enterpriseScale").val(data.enterpriseScale);
                        $("#legalPerson").val(data.legalPerson);
                        $("#establishmentTime").val(data.establishmentTime);
                        $("#contactNumber").val(data.contactNumber);
                        $("#industryCategory").val(data.industryCategory);
                        $("#industryName").val(data.industryName);
                        $("#industryChain").val(data.industryChain);
                        $("#mainBusiness1").val(data.mainBusiness1);
                        $("#mainBusiness2").val(data.mainBusiness2);
                    },
                    yes: function (index, layero) {
                        $("#saveData").click();
                        layer.close(index);
                    }, btn2: function (index1, layero) {
                        $("#resetData").click();
                        layer.close(index1);
                    }
                });
            }
        });

        table.on("toolbar(enterpriseTable)",function (resp) {
            var checkStatus = layui.table.checkStatus(resp.config.id)
                ,data = checkStatus.data;
        });
        //批量删除
        $("#deleteBatch").click(function () {
            var checkStatus = table.checkStatus('testReload')
                ,dataList = checkStatus.data;
            if (dataList !== null && dataList !== undefined && dataList.length > 0){
                var ids = "";
                $.each(dataList,function (index,data) {
                    if (ids == ""){
                        ids = data.enterpriseId;
                    }else {
                        ids += ","+ data.enterpriseId;
                    }
                });
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({
                        type: 'DELETE',
                        url: "./enterprise",
                        contentType: 'application/json',
                        data: ids,
                        success: function (data) {
                            layer.msg("删除成功");
                            active["reload"] ? active["reload"].call(this) : '';
                        }, error: function (error) {
                            layer.msg("删除失败");
                        }
                    });
                    layer.close(index);
                });
            }else {
                layer.msg("请选择要删除的行");
            }

        });
        //数据查询
        $("#query").click(function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //数据清除
        $("#clean").click(function () {
            $("#infoForm").find('input').val("");
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //数据导入
        layui.use('upload', function () {
            var upload = layui.upload;
            //指定允许上传的文件类型
            upload.render({
                elem: '#import'
                , url: './enterprise/import'
                , accept: 'file' //普通文件
                //,exts: 'docx|xlsx' //只允许上传excel文件
                , done: function (res) {
                    if (res !== null && res.code === 0) {
                        layer.msg("导入数据成功");
                        active['reload'].call(this);
                    } else {
                        layer.msg("导入数据失败");
                    }
                }, error: function (res) {
                    layer.msg("导入数据失败")
                }
            });
        });
        //数据导出
        $("#export").click(function () {
            var name = $("#name").val();
            var scale = $("#scale").val();
            var category = $("#category").val();
            var chain = $("#chain").val();
            window.location.href = "./enterprise/export?name=" + name + "&scale=" + scale + "&category=" + category + "&chain=" + chain;
        });
        //导出模板
        $("#download").click(function () {
            window.location.href = "./enterprise/download";
        });
    });

});