<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma">学生一覧</h2>
            <%-- 10行目あたり：タイトルの横に新規登録リンクを追加 --%>
			<div class="d-flex justify-content-between align-items-center mb-3">
			    <h2 class="h3 fw-normal">学生管理</h2>
			    <a href="StudentCreate.action">新規登録</a>
			</div>
            <%-- 絞り込みフォーム --%>
            <div class="bg-light p-3 mb-3">
                <form method="get">
                    <div class="row align-items-end">
                        <div class="col-3">
                            <label class="form-label">入学年度</label>
                            <select class="form-select" name="f1">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-3">
                            <label class="form-label">クラス</label>
                            <select class="form-select" name="f2">
                                <option value="0">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-3">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="f3" value="true" <c:if test="${!empty f3}">checked</c:if>>
                                <label class="form-check-label">在学中</label>
                            </div>
                        </div>
                        <div class="col-3">
                            <button class="btn btn-secondary" id="filter-button">絞込み</button>
                        </div>
                    </div>
                </form>
            </div>
			            <%-- 絞り込みフォームのすぐ下あたりに追加 --%>
			<c:if test="${!empty errors}">
			    <div class="text-danger mt-2 small">
			        <c:forEach var="error" items="${errors}">
			            ${error.value}<br>
			        </c:forEach>
			    </div>
			</c:if>

            <%-- 学生一覧テーブル --%>
            <c:choose>
                <c:when test="${students.size() > 0}">
                    <div>選択された条件：${students.size()}件</div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th class="text-center">在学中</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
                                    <td>${student.classNum}</td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${student.attend}">〇</c:when>
                                            <c:otherwise>×</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="mt-3">学生情報が存在しませんでした。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>