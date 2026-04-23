<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目管理 - 得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- ① タイトルエリア（背景グレー、新規登録を外す） --%>
            <div class="py-2 px-4 mb-2" style="background-color: #f8f9fa;">
                <h2 class="h4 mb-0">科目管理</h2>
            </div>

            <%-- ② 新規登録リンク（右下・アンダーライン付き） --%>
            <div class="text-end mb-3">
                <a href="SubjectCreate.action" 
                   class="text-decoration-underline" 
                   style="font-size: 0.9rem; color: #007bff;">新規登録</a>
            </div>

            <%-- ③ テーブル部分 --%>
            <table class="table mt-3">
                <thead>
                    <tr class="border-bottom">
                        <th class="py-3" style="width: 25%">科目コード</th>
                        <th class="py-3">科目名</th>
                        <th style="width: 10%"></th>
                        <th style="width: 10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty subjects}">
                            <c:forEach var="subject" items="${subjects}">
                                <tr class="border-bottom align-middle">
                                    <td class="py-3">${subject.cd}</td>
                                    <td class="py-3">${subject.name}</td>
                                    <td class="text-center">
                                        <a href="SubjectUpdate.action?cd=${subject.cd}" class="text-decoration-none">変更</a>
                                    </td>
                                    <td class="text-center">
                                        <a href="SubjectDelete.action?cd=${subject.cd}" class="text-decoration-none">削除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
            </table>

            <%-- ④ データがない場合のメッセージ --%>
            <c:if test="${empty subjects}">
                <div class="mt-3">
                    登録されている科目がありません。
                </div>
            </c:if>
        </section>
    </c:param>
</c:import>