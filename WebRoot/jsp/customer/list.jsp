﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/default/easyui.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript">
	
	//获取级别name
	function getLevelName(value,row,index) {
		if (row.baseDictLevel) {
			return row.baseDictLevel.dict_item_name;
		}
	}
	//获取来源name
	function getSourceName(value,row,index) {
		if (row.baseDictSource) {
			return row.baseDictSource.dict_item_name;
		}
	}
	
	//获取行业name
	function getIndustryName(value,row,index) {
		if (row.baseDictIndustry) {
			return row.baseDictIndustry.dict_item_name;
		}
	}
	//页面加载完成初始化table
	$(function() {
		$('#dg')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/customer/customer_findByPage.action',
							columns : [ [ {
								field : 'cust_name',
								title : '客户名称',
								width:100
							}, {
								field : 'baseDictLevel.dict_item_name',
								title : '客户级别',
								formatter: getLevelName,
								width:100
							}, {
								field : 'baseDictSource.dict_item_name',
								title : '客户来源',
								formatter: getSourceName,
								width:100
							}, {
								field : 'baseDictIndustry.dict_item_name',
								title : '所属行业',
								formatter: getIndustryName,
								width:100
							}, {
								field : 'cust_address',
								title : '联系地址',
								width:100
							}, {
								field : 'cust_phone',
								title : '联系电话',
								width:100
							} ] ],
							pagination:true,//显示分布工具栏
							pageList:[3,5,10],//每页显示多少条数据集合
							pageSize:3, //默认每页显示3条
							fitColumns:true
						});
		//查询所属行业
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/baseDict/baseDict_findByTypeCode.action",
			data:"dict_type_code=001",
			dataType:"json",
			success:function(msg){
				for(var i=0; i<msg.length; i++) {
					$("#cust_industry").append("<option value='"+msg[i].dict_id+"'>"+msg[i].dict_item_name+"</option>");
					
				}
			}
		});
		
		//查询信息来源
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/baseDict/baseDict_findByTypeCode.action",
			data:"dict_type_code=002",
			dataType:"json",
			success:function(msg){
				for(var i=0; i<msg.length; i++) {
					$("#cust_source").append("<option value='"+msg[i].dict_id+"'>"+msg[i].dict_item_name+"</option>");
					
				}
			}
		});
		
		//查询级别
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/baseDict/baseDict_findByTypeCode.action",
			data:"dict_type_code=006",
			dataType:"json",
			success:function(msg){
				for(var i=0; i<msg.length; i++) {
					$("#cust_level").append("<option value='"+msg[i].dict_id+"'>"+msg[i].dict_item_name+"</option>");
					
				}
			}
		});
	});
	
	//点击搜索按钮执行的函数
	function doSearch() {
		$('#dg').datagrid('load', {
			'cust_name' : $('#cust_name').val(),
			'baseDictLevel.dict_id' : $('#cust_level').val(),
			'baseDictSource.dict_id' : $('#cust_source').val(),
			'baseDictIndustry.dict_id' : $('#cust_industry').val()
		});
	}
	
	
	//编辑客户函数
	function editCustomer(){
		var row = $("#dg").datagrid('getSelected');
		if (row) {
			if (row) {
				location.href="${pageContext.request.contextPath}/customer/customer_findById.action?cust_id="+row.cust_id;
			}
		} else {
			$.messager.alert("系统消息","请选中一行");
		}
		
	}
	
	//删除客户函数
	function destroyCustomer(){
		//获取当前选中行
		var row = $("#dg").datagrid('getSelected');
		//如果选中了某一行
		if (row) {
			$.messager.confirm("系统消息","您确认要删除记录吗",function(r) {
				if (r) {
					location.href="${pageContext.request.contextPath}/customer/customer_delete.action?cust_id="+row.cust_id;
				}
			});
		} else {
			$.messager.alert("系统消息","请选中一行");
		}
	}
</script>
</HEAD>
<BODY>
	<!-- 表格上方的工具栏 -->
	<div id="tb" style="padding: 3px">
		<!-- 操作栏：包括编辑、删除 -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCustomer()">编辑客户</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCustomer()">删除客户</a> 
		<br>
		<!-- 搜索栏 -->
		<span>客户姓名:</span><input id="cust_name" type="text">
		<span>客户级别:</span>
		<select id="cust_level">
			<option value="">---请选择---</option>
		</select>
		<span>客户来源:</span>
		<select id="cust_source">
			<option value="">---请选择---</option>
		</select>
		<span>所属行业:</span>
		<select id="cust_industry">
			<option value="">---请选择---</option>
		</select>
		<!-- 搜索按钮 -->
		<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">筛选</a>
	</div>
	<!-- 显示数据的表格，toolbar属性：指定表格的工具栏 -->
	<table id="dg" toolbar="#tb" width="98%"></table>
</BODY>
</HTML>
