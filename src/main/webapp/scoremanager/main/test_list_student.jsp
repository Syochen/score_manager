<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3">成績一覧（学生）</h2>
            <%-- 検索フォーム部分は初期値セット付きで表示 --%>
            <div class="mt-4">
                <p>氏名：${student.name} (${student.no})</p>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>科目名</th><th>科目コード</th><th>回数</th><th>点数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="test" items="${tests_student}">
                            <tr>
                                <td>${test.subjectName}</td>
                                <td>${test.subjectCd}</td>
                                <td>${test.num}</td>
                                <td>${test.point}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:param>
</c:import>