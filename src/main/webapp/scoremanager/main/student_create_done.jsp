<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        学生登録完了 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- ① 見出しを登録画面と合わせる --%>
            <h2 class="h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            
            <%-- ② 緑色の完了メッセージバー --%>
            <div class="alert alert-success py-2" role="alert">
                登録が完了しました
            </div>

            <div class="mt-4 d-flex">
                <%-- ③ 戻るリンク --%>
                <a href="StudentCreate.action" class="text-decoration-none me-4">戻る</a>
                
                <%-- ④ 科目一覧リンク --%>
                <a href="StudentList.action" class="text-decoration-none">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>