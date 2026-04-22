<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- ヘッダーなどの共通パーツがあればここでインクルード --%>

<h2>科目管理</h2>
<div class="mb-3">
    <a href="SubjectCreate.action">新規登録</a>
</div>

<table class="table table-hover">
    <thead>
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subject" items="${subjects}">
            <tr>
                <td>${subject.cd}</td>
                <td>${subject.name}</td>
                <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
                <td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%-- フッターなどの共通パーツ --%>