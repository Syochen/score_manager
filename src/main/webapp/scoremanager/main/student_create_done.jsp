<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <%-- ① タイトル --%>
            <h2 class="h3 mb-3 fw-normal">学生情報登録</h2>
            
            <%-- ② 登録完了メッセージ --%>
            <div class="alert alert-success" role="alert">
                登録が完了しました
            </div>
            
            <div class="mt-3">
                <%-- ③ 戻る（登録画面へ戻る） --%>
                <a href="StudentCreate.action" class="me-3">戻る</a>
                
                <%-- ④ 学生一覧 --%>
                <a href="StudentList.action">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>