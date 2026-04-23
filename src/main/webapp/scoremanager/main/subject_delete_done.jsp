<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目削除完了 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- ① 見出しを「科目情報変更」に統一 --%>
            <h2 class="h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
            
            <%-- ② 緑色の完了メッセージバー --%>
            <div class="alert alert-success py-2" role="alert">
                削除が完了しました
            </div>

            <%-- ③ 科目一覧へのリンクのみ配置 --%>
            <div class="mt-4">
                <a href="SubjectList.action" class="text-decoration-none">科目一覧</a>
            </div>
        </section>
    </c:param>
</c:import>