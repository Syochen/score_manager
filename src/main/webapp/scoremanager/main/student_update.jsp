<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報変更</h2>
            
            <form action="StudentUpdateExecute.action" method="post">
                <%-- 入学年度：画像に合わせてシンプルな表示に --%>
                <div class="mb-3">
                    <p class="mb-1">入学年度</p>
                    <div class="ms-2">${student.entYear}</div>
                </div>
                
                <%-- 学生番号：表示用と送信用(hidden) --%>
                <div class="mb-3">
                    <p class="mb-1">学生番号</p>
                    <div class="ms-2">${student.no}</div>
                    <input type="hidden" name="no" value="${student.no}">
                </div>
                
                <%-- 氏名 --%>
                <div class="mb-3">
                    <label class="form-label" for="name-input">氏名</label>
                    <input type="text" id="name-input" name="name" class="form-control" 
                           value="${student.name}" placeholder="氏名を入力してください" 
                           style="max-width: 600px;" required>
                </div>
                
                <%-- クラス --%>
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select class="form-select" name="class_num" style="max-width: 600px;">
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <%-- 在学中チェックボックス --%>
                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" name="is_attend" id="is_attend" 
                           value="true" <c:if test="${student.attend}">checked</c:if>>
                    <label class="form-check-label" for="is_attend">在学中</label>
                </div>
                
                <%-- ボタンとリンク：画像のデザインを再現 --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary px-3 mb-2">変更</button>
                    <div>
                        <a href="StudentList.action" class="text-decoration-none">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>