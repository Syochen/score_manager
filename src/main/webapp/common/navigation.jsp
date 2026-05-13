<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:if test="${not empty user}">
    <div class="px-3">
        <ul class="list-group list-group-flush">
            <%-- メニュー --%>
            <li class="list-group-item border-0 p-0">
                <a href="Menu.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">メニュー</a>
            </li>
            
            <%-- 学生管理 --%>
            <li class="list-group-item border-0 p-0">
                <a href="StudentList.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">学生管理</a>
            </li>
            
            <%-- 成績管理 --%>
            <li class="list-group-item border-0 pb-0">
                成績管理
                <ul class="list-group list-group-flush ms-3">
                    <li class="list-group-item border-0 p-0">
                        <a href="TestRegist.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">成績登録</a>
                    </li>
                    <li class="list-group-item border-0 p-0">
                        <a href="TestList.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">成績参照</a>
                    </li>
                </ul>
            </li>
            
            <%-- 科目管理 --%>
            <li class="list-group-item border-0 p-0">
                <a href="SubjectList.action" class="list-group-item list-group-item-action border-0 text-primary text-decoration-none">科目管理</a>
            </li>
        </ul>
    </div>
</c:if>