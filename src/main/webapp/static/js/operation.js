 
/**
 * 保存一条机构专职记录的方法
 * @param obj 获取当前对象
 */
function saveOrgStaff(obj) {
	 
        var name=$($(obj).parent().siblings()[1]).find("input").val();
        var sex=$($(obj).parent().siblings()[2]).find("select").val();
        var card=$($(obj).parent().siblings()[3]).find("input").val();
        var workStart=$($(obj).parent().siblings()[4]).find("input").val();
        var university=$($(obj).parent().siblings()[5]).find("input").val();
        var degree=$($(obj).parent().siblings()[6]).find("select").val();
        var ssn=$($(obj).parent().siblings()[7]).find("input").val();
        var proTitle=$($(obj).parent().siblings()[8]).find("input").val();
        var dept=$($(obj).parent().siblings()[9]).find("input").val();
        var workType=$($(obj).parent().siblings()[10]).find("select").val();
        var cardPhoto=$($(obj).parent().siblings()[11]).find("input").val();
        var protitlePhoto=$($(obj).parent().siblings()[12]).find("input").val();
        $.ajax({
    		type:"post",
    		url:"http://127.0.0.1:8080/cip-bsmp/center/org/orgStaff/save",
    		data:{'name':name,'sex':sex,'card':card,'workStart':workStart,'university':university,'degree':degree,'ssn':ssn,'proTitle':proTitle,'dept':dept,'workType':workType,'cardPhoto':cardPhoto,'protitlePhoto':protitlePhoto},
    		dataType : "json",
    		success:function(data){
    			 
    		} 
    	});
}
/**
 * 保存一条招标业绩的方法
 * @param obj 获取当前对象
 */
function saveOrgAchieve(obj) {
    var orgId=$("#orgId").val();
    var num=$($(obj).parent().siblings()[1]).find("input").val();
    var name=$($(obj).parent().siblings()[2]).find("input").val();
    var type=$($(obj).parent().siblings()[3]).find("select").val();
    var entrustUnit=$($(obj).parent().siblings()[4]).find("input").val();
    var entrustMoney=$($(obj).parent().siblings()[5]).find("input").val();
    var bidMoney=$($(obj).parent().siblings()[6]).find("select").val();
    var tenderOpenTime=$($(obj).parent().siblings()[7]).find("input").val();
    var bidTime=$($(obj).parent().siblings()[8]).find("input").val();
    var noticeMedia=$($(obj).parent().siblings()[9]).find("input").val();
    var noticeDate=$($(obj).parent().siblings()[10]).find("input").val();
    $.ajax({
		type:"post",
		url:"http://127.0.0.1:8080/cip-bsmp/org/orgAchieve/save",
		data:{'orgId':orgId,'num':num,'name':name,'type':type,'entrustUnit':entrustUnit,'entrustMoney':entrustMoney,'bidMoney':bidMoney,'tenderOpenTime':tenderOpenTime,'bidTime':bidTime,'noticeMedia':noticeMedia,'noticeDate':noticeDate},
		dataType : "json",
		success:function(data){
			//todo:得获取json格式，后续在做
		} 
	});
}

/**
 * 注销项目的方法
 * @param obj  获取当前对象
 */
function cancellation(obj) {
    $(obj).siblings().removeAttr("href").css("color","#ccc");
    $(obj).css("color","#ccc");
    $($(obj).parent().parent()).css("color","#ccc");
}
 

