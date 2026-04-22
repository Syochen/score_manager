<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:if test="${not empty user}">
    <div class="list-group list-group-flush px-3">
        <a href="Menu.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">メニュー</a>
        
        <a href="StudentList.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">学生管理</a>
        
        <div class="list-group-item border-0 pb-0">
            成績管理
            <div class="list-group list-group-flush ms-3">
                <a href="TestRegist.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">成績登録</a>
                <a href="TestList.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">成績参照</a>
            </div>
        </div>
        
        <a href="SubjectList.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">科目管理</a>
    </div>
</c:if>