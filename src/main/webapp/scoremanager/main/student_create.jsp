<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報登録</h2>
            
            <form action="StudentCreateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <select class="form-select" name="ent_year" required>
                        <option value="">選択してください</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <%-- 入力戻りの年度と一致したら選択状態にする --%>
                            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${!empty errors.ent_year}">
                        <div class="text-danger">${errors.ent_year}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <%-- value="${no}" を追加 --%>
                    <input type="text" class="form-control" name="no" value="${no}" placeholder="学生番号を入力してください" required>
                    <c:if test="${!empty errors.no}">
                        <div class="text-danger">${errors.no}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <%-- value="${name}" を追加 --%>
                    <input type="text" class="form-control" name="name" value="${name}" placeholder="氏名を入力してください" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select class="form-select" name="class_num">
                        <c:forEach var="num" items="${class_num_set}">
                            <%-- 入力戻りのクラスと一致したら選択状態にする --%>
                            <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">登録して終了</button>
                <div class="mt-3">
                    <a href="StudentList.action">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>