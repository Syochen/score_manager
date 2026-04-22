<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- border-bottom を削除 --%>
<div class="container-fluid py-1 px-5 mb-4 d-flex justify-content-between align-items-center" style="background-color: #e9f1fb;">
    
    <%-- 左側：タイトル --%>
    <div class="h2 mb-0 fw-normal text-dark mt-3">得点管理システム</div>

    <%-- 右側：ユーザー情報 --%>
    <c:if test="${not empty user}">
        <div class="d-flex align-items-center align-self-end text-secondary">
            <span class="me-3" style="font-size: 0.85rem;">${user.name} 様</span>
            <a href="Logout.action" class="text-primary text-decoration-none" style="font-size: 0.85rem;">ログアウト</a>
        </div>
    </c:if>
</div>