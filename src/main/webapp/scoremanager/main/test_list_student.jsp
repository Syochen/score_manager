<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3">成績参照（学生）</h2>

            <%-- 学生情報のフォームのみ表示（科目フォームは出さない＝初期化） --%>
            <div class="bg-light p-3 border rounded text-dark mb-4">
                <form action="TestList.action" method="get">
                    <div class="row g-3 align-items-center">
                        <div class="col-auto">学生情報</div>
                        <div class="col-6">
                            <input type="text" name="f4" class="form-control" value="${f4}" placeholder="学生番号を入力" required>
                        </div>
                        <div class="col-auto">
                            <button type="submit" name="f" value="st" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="mt-4">
    <%-- 1. まず学生情報を表示（studentが空でないなら必ず出す） --%>
    <c:if test="${not empty student}">
        <label class="form-label">氏名：${student.name} (${student.no})</label>

        <c:choose>
            <%-- 2. 成績データがある場合：テーブルと統計を表示 --%>
            <c:when test="${not empty tests_student}">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>科目名</th>
                            <th>科目コード</th>
                            <th>回数</th>
                            <th>点数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="test" items="${tests_student}">
                            <tr class="${test.point >= 0 && test.point < 60 ? 'table-danger' : ''}">
                                <td>${test.subjectName}</td>
                                <td>${test.subjectCd}</td>
                                <td>${test.num}</td>
                                <td>${test.point == -1 ? "-" : test.point}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <%-- 統計情報：平均点がある場合のみ表示 --%>
                <c:if test="${not empty avg}">
                    <div class="mt-3 p-3 bg-white border rounded">
                        <div class="row text-center">
                            <div class="col">
                                <small class="text-muted d-block">平均点</small>
                                <span class="h4">${avg}</span><small>点</small>
                            </div>
                            <div class="col border-start">
                                <small class="text-muted d-block">最高点</small>
                                <span class="h4">${max}</span><small>点</small>
                            </div>
                            <div class="col border-start">
                                <small class="text-muted d-block">最低点</small>
                                <span class="h4">${min}</span><small>点</small>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:when>

            <%-- 3. 学生はいるが、成績データが空の場合 --%>
            <c:otherwise>
                <p>成績情報が存在しませんでした</p>
            </c:otherwise>
        </c:choose>
    </c:if>


</div>
             

            <div class="mt-3">
                <a href="TestList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>