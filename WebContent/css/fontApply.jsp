<%@page import="com.kh.elefont.font.model.vo.Font"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
List<Font> fontList = (List<Font>)session.getAttribute("fontList"); 
System.out.println("fontList@jsp : " + fontList);
%>

<style>
<% 
if(!fontList.isEmpty()){
	for(Font font : fontList){
		if(font.getFontFamily() != null){
			
%>


@font-face {
    font-family: '<%= font.getFontFamily()%>';
    src: url('<%= font.getFontUrl()%>') format('woff2');
    font-weight: 300;
    font-style: normal;
    
}

<% 
		}
	} 
}
%>
</style>






