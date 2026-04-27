<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content" scope="request">
    <div class="container mt-4">
        <h2 class="mb-4">成績管理</h2>
        
        <div class="alert alert-success">
            登録が完了しました
        </div>

        <div class="mt-4">
            <a href="TestRegist.action" class="btn btn-link">戻る</a>
        </div>
    </div>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="登録完了 - 得点管理システム" />
    <c:param name="content" value="${content}" />
</c:import>