<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目登録 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- 見出し --%>
            <h2 class="h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            
            <form action="SubjectCreateExecute.action" method="post">
                <%-- 科目コード入力 --%>
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <input type="text" class="form-control" name="cd" 
                           placeholder="科目コードを入力してください" value="${cd}" required>
                    
                    <%-- 
                         画像①・②の再現：エラーメッセージをオレンジ色で表示 
                         入力欄(input)の直下に配置
                    --%>
                    <c:if test="${not empty errors.get('cd')}">
                        <div class="mt-1" style="color: #f0ad4e;">${errors.get('cd')}</div>
                    </c:if>
                </div>

                <%-- 科目名入力 --%>
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" class="form-control" name="name" 
                           placeholder="科目名を入力してください" value="${name}" required>
                </div>

                <%-- 登録ボタン --%>
                <button type="submit" class="btn btn-primary">登録</button>
                
                <%-- 戻るボタン --%>
                <div class="mt-3">
                    <a href="SubjectList.action" class="text-decoration-none" style="font-size: 0.9rem;">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>