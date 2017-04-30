<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
.btn_left {
	position: absolute;
	bottom: 30px;
	margin-left: 10px
}
</style>
<script>
	$(function() {
		//为删除图标绑定事件
		$(".divtable .pl5 .ico_del").off("click");
		$(".divtable").on("click", ".pl5 .ico_del", function() {
			alert("删除");
			//获取点击元素的id
			var id = $(this).attr("val");
			//通过ajax删除id为id的栏目
			$.post("manager/deleteArticle.action", {
				"article.id" : id
			//参数
			}, function() { //回调函数
				alert("删除成功");
				//模拟点击"栏目管理"
				$("span:contains('信息管理')").trigger("click");
			});
		});

		//为修改图标绑定事件
		$(".divtable .pl5 .ico_edit").off("click");
		$(".divtable").on("click", ".pl5 .ico_edit", function() {
			alert("修改");
			//获取要修改元素的id
			var id = $(this).attr("val");
			//将class为"rightContent"的元素替换为action返回页面
			$(".rightContent").load("updateArticleInfo.do", {
				"articleId" : id
			});
		});
		//为查询绑定事件
		$("form").off("submit");
		$("form").on("submit", function() {
			$(".divtable").load($(this).attr("action"), {
				key : $(this).find("select[name='key']").val(),
				value : $(this).find("input[name='value']").val(),
				page : $("input[id='page']").val()
			});
			//阻止表单的默认提交行为
			return false;
		});
		$("form").submit();
		
		$("button[id='delete']").off("click");
		$("button[id='delete']").on("click", function() {
			alert("删除");
			var checked = $(":checkbox:checked");
			var a = "";
			$.each(checked,function(i,c){
				var c = $(c).val();
				a = a + c + ",";
			}); 
			//通过ajax删除id为id的栏目
			$.post("deleteMoreArticle.action", {
				"checked" : a
			//参数
			}, function() { //回调函数
				alert("删除成功");
				//模拟点击"栏目管理"
				$("span:contains('信息管理')").trigger("click");
			});
			return false; 
		});
		
		//全选
		$("button[id='selectAll']").off("click");
		$("button[id='selectAll']").on("click", function() {
			$(":checkbox").attr("checked",true);
		});
		
		//反选
		$("button[id='reSelect']").off("click");
		$("button[id='reSelect']").on("click", function() {
			$(":checkbox").each(function(){
				 $(this).attr("checked", !$(this).attr("checked"));
			});
		});
		$('.pagination').jqPagination({
		    paged: function(page) {
		        console.log(page);
		        console.log($("select[name=key]").val());
		        console.log($("input[name='value']").val());
		       	$("form").off("submit");
				$("form").on("submit", function() {
					$(".divtable").load($(this).attr("action"), {
						key : $(this).find("select[name='key']").val(),
						value : $(this).find("input[name='value']").val(),
						page : page
					});
					//阻止表单的默认提交行为
					return false;
				});
				$("form").submit();
		    }
		
		});
	});
</script>
<div class="c_condition">
	<form action="articleListContent.do" method="post">
		<span> <select name="key">
				<option value="">-请选择-</option>
				<option value="title">标题</option>
				<option value="author">作者</option>
				<option value="category">栏目</option>
		</select>
		</span> <span>关键字：<input type="text" name="value"></span>
		<button class="btn_gray" type="submit">搜索</button>
	</form>
</div>
<!-- 内容 -->

<div class="divtable">
	
</div>
<div class="btn_left">
	<button id="selectAll">全选</button><button id="reSelect">反选</button><br />
	<button id="delete">删除</button>
</div>
<div class="pagination">
    <a href="#" class="first" data-action="first">&laquo;</a>
    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
    <input type="text" readonly="readonly" data-max-page="40" id="page"/>
    <a href="#" class="next" data-action="next">&rsaquo;</a>
    <a href="#" class="last" data-action="last">&raquo;</a>
</div>
