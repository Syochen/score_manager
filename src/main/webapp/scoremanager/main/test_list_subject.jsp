<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3">成績参照（科目）</h2>

            <%-- 科目情報のフォーム --%>
            <div class="bg-light p-3 border rounded mb-4">
                <form action="TestList.action" method="get">
                    <div class="row g-3 align-items-center">
                        <div class="col-auto">科目情報</div>

                        <div class="col-auto">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                                        ${year}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-auto">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
                                        ${num}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-auto">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subject_set}">
                                    <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>
                                        ${sub.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <input type="hidden" name="f" value="sj">

                        <div class="col-auto align-self-end">
                            <button type="submit" class="btn btn-primary px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <%-- エラーメッセージ表示 --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger py-2" role="alert">
                    ${errors}
                </div>
            </c:if>

            <%-- 検索結果の表示エリア --%>
            <div class="mt-4">
                <h3 class="h5 mb-3">
                    科目：<c:out value="${subject.name}" />
                </h3>

                <c:choose>
                    <c:when test="${not empty tests_subject}">
                        <table class="table table-hover border">
                            <thead class="table-light">
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>1回目</th>
                                    <th>2回目</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="test" items="${tests_subject}">
                                    <tr>
                                        <td>${test.entYear}</td>
                                        <td>${test.classNum}</td>
                                        <td>${test.studentNo}</td>
                                        <td>${test.studentName}</td>
                                        <%-- ★修正点：Beanに用意されている getPoint(キー) メソッドを使って確実に値を呼ぶよ --%>
                                        <td>
                                            <c:out value="${test.getPoint(1)}" />
                                        </td>
                                        <td>
                                            <c:out value="${test.getPoint(2)}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p>学生情報が存在しませんでした</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="mt-3">
                <a href="TestList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>