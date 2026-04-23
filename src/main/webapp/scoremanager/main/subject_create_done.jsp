<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目登録完了 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- ① 見出し：背景グレーの「科目情報登録」 --%>
            <h2 class="h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            
            <%-- ② 緑色の完了メッセージバー --%>
            <div class="alert alert-success py-2" role="alert">
                登録が完了しました
            </div>

            <%-- ③ リンク：設計書通り「戻る」と「科目一覧」を横並びに配置 --%>
            <div class="mt-4 d-flex">
                <%-- 続けて登録したい時用の戻るリンク --%>
                <a href="SubjectCreate.action" class="text-decoration-none me-4">戻る</a>
                
                <%-- 一覧に戻るリンク --%>
                <a href="SubjectList.action" class="text-decoration-none">科目一覧</a>
            </div>
        </section>
    </c:param>
</c:import>