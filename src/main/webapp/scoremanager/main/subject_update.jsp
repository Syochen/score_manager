<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目変更 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- 見出し：背景グレーの「科目情報変更」 --%>
            <h2 class="h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
            
            <form action="SubjectUpdateExecute.action" method="post">
                <%-- 科目コード：表示用テキスト --%>
                <div class="mb-2">
                    <label class="form-label">科目コード</label>
                    <div class="py-1">${subject.cd}</div>
                    <input type="hidden" name="cd" value="${subject.cd}">
                </div>

                <%-- ★エラーメッセージ表示エリア（オレンジ色） --%>
                <c:if test="${not empty errors}">
                    <div class="mb-3">
                        <c:forEach var="error" items="${errors}">
                            <span style="color: #ffc107;">${error.value}</span><br>
                        </c:forEach>
                    </div>
                </c:if>

                <%-- 科目名入力：データがない場合は入力不可にする等の制御も可能 --%>
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" class="form-control" name="name" 
                           value="${subject.name}" required 
                           <c:if test="${empty subject}">disabled</c:if>>
                </div>

                <%-- 変更ボタン：データがない場合は非表示か無効化 --%>
                <c:if test="${not empty subject}">
                    <button type="submit" class="btn btn-primary">変更</button>
                </c:if>
                
                <%-- 戻るリンク --%>
                <div class="mt-3">
                    <a href="SubjectList.action" class="text-decoration-none" style="font-size: 0.9rem;">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>