$(function () {
    //表格ss
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#enterpriseTable',
            url: './enterprise'
            ,cols: [[
                {field:'enterpriseName', title: '企业名称',width:230}
                ,{field:'enterpriseNumber', title: '企业代码',width:200}
                ,{field:'enterpriseScale', title: '企业规模',width:90}
                ,{field:'legalPerson', title: '法人',width:80}
                ,{field:'establishmentTime', title: '成立时间',width:110}
                ,{field:'contactNumber', title: '联系号码',width:120}
                ,{field:'industryCategory', title: '行业类别',width:100}
                ,{field:'industryName', title: '行业名称'}
                ,{field:'industryChain', title: '所属产业链',width:100}
                ,{field:'mainBusiness1', title: '主营业务1'}
                ,{field:'mainBusiness2', title: '主营业务2'}
            ]]
            ,page: true
            ,id:"testReload"
            ,request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'size' //每页数据量的参数名，默认：limit
            }
        });
        var $ = layui.$, active = {
            reload: function(){
                var name = $("#name").val();
                var scale = $ ("#scale").val();
                var category = $("#category").val();
                var chain = $("#chain").val();

                //执行重载
                table.reload('testReload', {
                    url: './enterprise',
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        name:name,
                        scale:scale,
                        category:category,
                        chain:chain
                    }
                });
            }
        };

        //数据查询
        $("#query").click(function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $("#clean").click(function () {
            $("#infoForm").find('input').val("");
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        })

    });



});