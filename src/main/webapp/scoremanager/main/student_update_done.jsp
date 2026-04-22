<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報変更</h2>
            
            <%-- ② 変更完了メッセージ（緑色のボックス） --%>
            <div class="alert alert-success" role="alert">
                変更が完了しました
            </div>
            
            <%-- ③ 学生一覧へのリンク --%>
            <div class="mt-3">
                <a href="StudentList.action">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>