<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <%-- STDM0041: 固定値画面タイトル --%>
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報変更</h2>
            
            <form action="StudentUpdateExecute.action" method="post">
                <div class="mb-3">
                    <%-- STDM0042: 固定値項目タイトル(入学年度) --%>
                    <label class="form-label text-secondary small">入学年度</label>
                    <%-- STDM0043: readonlyで表示。枠を目立たせない設定 --%>
                    <input type="text" class="form-control-plaintext ps-2" name="ent_year" value="${student.entYear}" readonly>
                </div>

                <div class="mb-3">
                    <%-- STDM0044: 固定値項目タイトル(学生番号) --%>
                    <label class="form-label text-secondary small">学生番号</label>
                    <%-- STDM0045: readonlyで表示。ここも枠なし --%>
                    <input type="text" class="form-control-plaintext ps-2" name="no" value="${student.no}" readonly>
                </div>

                <div class="mb-3">
                    <%-- STDM0046: 固定値項目タイトル(氏名) --%>
                    <label class="form-label">氏名</label>
                    <%-- STDM0047: 最大文字数30、必須入力指定 --%>
                    <input type="text" class="form-control" name="name" value="${student.name}" 
                           placeholder="氏名を入力してください" maxlength="30" required>
                    <c:if test="${!empty errors.name}">
                        <div class="text-danger small">${errors.name}</div>
                    </c:if>
                </div>

                <div class="mb-3">
                    <%-- STDM0048: 固定値項目タイトル(クラス) --%>
                    <label class="form-label">クラス</label>
                    <%-- STDM0049: クラス番号リストを表示 --%>
                    <select class="form-select" name="class_num">
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-check mb-4">
                    <%-- STDM00410: 固定値在学中、STDM00411: 在学中チェックボックス --%>
                    <input class="form-check-input" type="checkbox" name="is_attend" id="is_attend"
                           <c:if test="${student.isAttend()}">checked</c:if>>
                    <label class="form-check-label" for="is_attend">在学中</label>
                </div>

                <%-- STDM00412: 変更ボタン --%>
                <button type="submit" class="btn btn-primary px-4">変更</button>
                
                <%-- STDM00413: 戻るリンク --%>
                <div class="mt-3">
                    <a href="StudentList.action" class="text-decoration-none">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>