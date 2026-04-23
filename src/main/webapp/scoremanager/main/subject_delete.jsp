<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目削除 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- ① 見出しを「科目情報削除」に変更 --%>
            <h2 class="h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
            
            <form action="SubjectDeleteExecute.action" method="post">
                <%-- ② 確認メッセージを一行で表示 --%>
                <div class="mb-4">
                    「${subject.name}(${subject.cd})」を削除してもよろしいですか
                </div>

                <%-- 削除するキーを隠して送る --%>
                <input type="hidden" name="cd" value="${subject.cd}">

                <%-- ③ 削除ボタン（赤色） --%>
                <button type="submit" class="btn btn-danger">削除</button>
                
                <%-- ④ 戻るリンク --%>
                <div class="mt-3">
                    <a href="SubjectList.action" class="text-decoration-none" style="font-size: 0.9rem;">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>