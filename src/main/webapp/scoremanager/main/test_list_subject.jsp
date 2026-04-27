<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3">成績一覧（科目）</h2>
            <%-- 検索フォーム部分は test_list.jsp と同様（初期値セットあり） --%>
            <div class="mt-4">
                <p>科目：${subject.name}</p>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>1回</th><th>2回</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="test" items="${tests_subject}">
                            <tr>
                                <td>${test.entYear}</td>
                                <td>${test.classNum}</td>
                                <td>${test.studentNo}</td>
                                <td>${test.studentName}</td>
                                <td>${test.points[1] != null ? test.points[1] : "-"}</td>
                                <td>${test.points[2] != null ? test.points[2] : "-"}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty tests_subject}">
                    <p>学生情報が存在しませんでした</p>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>