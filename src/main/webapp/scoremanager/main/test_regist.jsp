<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- メインコンテンツの定義 --%>
<c:set var="content" scope="request">
    <style>
        .search-form { display: flex; gap: 15px; align-items: flex-end; margin-bottom: 20px; padding: 15px; border: 1px solid #0d6efd; border-radius: 5px; }
        .search-item { display: flex; flex-direction: column; }
        .search-item label { font-size: 0.8rem; font-weight: bold; margin-bottom: 5px; }
        .point-input { width: 80px; }
    </style>

    <h2 class="mb-4">成績管理</h2>

    <c:if test="${not empty errors}">
        <div class="alert alert-danger">${errors}</div>
    </c:if>

    <form action="TestRegist.action" method="post" class="search-form bg-light">
        <%-- 入学年度プルダウン --%>
        <div class="search-item">
            <label>入学年度</label>
            <select name="f1" class="form-select form-select-sm">
                <option value="0">--------</option>
                <c:forEach var="year" items="${ent_year_list}">
                    <option value="${year}" <c:if test="${year == param.f1}">selected</c:if>>${year}</option>
                </c:forEach>
            </select>
        </div>

        <%-- クラスプルダウン --%>
        <div class="search-item">
            <label>クラス</label>
            <select name="f2" class="form-select form-select-sm">
                <option value="0">--------</option>
                <c:forEach var="cNum" items="${class_num_list}">
                    <option value="${cNum}" <c:if test="${cNum == param.f2}">selected</c:if>>${cNum}</option>
                </c:forEach>
            </select>
        </div>

        <%-- 科目プルダウン --%>
        <div class="search-item">
            <label>科目</label>
            <select name="f3" class="form-select form-select-sm">
                <option value="0">--------</option>
                <c:forEach var="sub" items="${subject_list}">
                    <option value="${sub.cd}" <c:if test="${sub.cd == param.f3}">selected</c:if>>${sub.name}</option>
                </c:forEach>
            </select>
        </div>

        <%-- 回数プルダウン --%>
        <div class="search-item">
            <label>回数</label>
            <select name="f4" class="form-select form-select-sm">
                <option value="0">--------</option>
                <option value="1" <c:if test="${param.f4 == '1'}">selected</c:if>>1</option>
                <option value="2" <c:if test="${param.f4 == '2'}">selected</c:if>>2</option>
            </select>
        </div>

        <button type="submit" class="btn btn-secondary btn-sm">検索</button>
    </form>

    <c:if test="${not empty tests}">
        <div class="mt-4">
            <p class="fw-bold">科目：${subject.name} (${param.f4}回)</p>
            
            <form action="TestRegistExecute.action" method="post">
                <%-- 登録時に必要な情報をhiddenで送信 --%>
                <input type="hidden" name="f1" value="${param.f1}">
                <input type="hidden" name="f2" value="${param.f2}">
                <input type="hidden" name="f3" value="${param.f3}">
                <input type="hidden" name="f4" value="${param.f4}">

                <table class="table table-hover border">
                    <thead class="table-light">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="t" items="${tests}">
                            <tr>
                                <td>${param.f1}</td>
                                <td>${param.f2}</td>
                                <%-- StudentBean経由で値を取得 --%>
                                <td>${t.student.no}</td>
                                <td>${t.student.name}</td>
                                <td>
                                    <input type="number" name="point_${t.student.no}" 
                                           value="<c:if test="${t.point != -1}">${t.point}</c:if>" 
                                           class="form-control form-control-sm point-input" min="0" max="100">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary mt-3">登録して終了</button>
            </form>
        </div>
    </c:if>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績管理 - 得点管理システム" />
    <c:param name="content" value="${content}" />
</c:import>