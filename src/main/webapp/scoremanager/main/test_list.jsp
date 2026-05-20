<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section>
            <h2 class="h3 mb-3">成績参照</h2>
            
            <%-- --- 科目情報のフォーム --- --%>
            <div class="bg-light p-3 border rounded mb-3">
                <form action="TestList.action" method="get">
                    <div class="row g-3 align-items-center">
                        <div class="col-auto">科目情報</div>
                        <div class="col-auto">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto mt-auto">
                            <button type="submit" name="f" value="sj" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                    <c:if test="${not empty errors && empty f4}">
                        <div class="mt-2 text-warning" style="margin-left: 80px;">${errors}</div>
                    </c:if>
                </form>
            </div>

            <%-- --- 学生情報のフォーム --- --%>
           <div class="bg-light p-3 border rounded text-dark">
    <form action="TestList.action" method="get">
        <div class="row g-3 align-items-center">
            <div class="col-auto">学生情報</div>
            <div class="col-6">
                <label class="form-label">学生番号</label>
                <input type="text" name="f4" class="form-control" 
                       placeholder="学生番号を入力してください" value="${f4}" maxlength="10" required>
            </div>
            <div class="col-auto mt-auto">
                <button type="submit" name="f" value="st" class="btn btn-secondary">検索</button>
            </div>
        </div>
        <c:if test="${not empty errors && not empty f4}">
            <div class="mt-2 text-warning" style="margin-left: 80px;">${errors}</div>
        </c:if>
    </form>
</div>
            <%-- --- 検索結果表示エリア --- --%>
            <div class="mt-4">
                <c:choose>
                    <%-- 1. 科目別検索結果 --%>
                    <c:when test="${not empty done_sj}">
                        <c:choose>
                            <c:when test="${not empty tests_subject}">
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
                                                <td>${test.entYear}</td><td>${test.classNum}</td>
                                                <td>${test.studentNo}</td><td>${test.studentName}</td>
                                                <%-- 科目別でも60点未満を赤くしたい場合はここに条件を追加可能 --%>
                                                <td>${test.points[1] != null ? test.points[1] : "-"}</td>
                                                <td>${test.points[2] != null ? test.points[2] : "-"}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>成績情報が存在しませんでした</p>
                            </c:otherwise>
                        </c:choose>
                    </c:when>

                    <%-- 2. 学生別検索結果 --%>
                    <c:when test="${not empty done_st}">
                        <c:choose>
                            <c:when test="${not empty student && not empty tests_student}">
                                <p>氏名：${student.name} (${student.no})</p>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>科目名</th><th>科目コード</th><th>回数</th><th>点数</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="test" items="${tests_student}">
                                            <%-- 点数が0以上かつ60点未満の場合に背景を赤く(table-danger)する --%>
                                            <tr class="${test.point >= 0 && test.point < 60 ? 'table-danger' : ''}">
                                                <td>${test.subjectName}</td>
                                                <td>${test.subjectCd}</td>
                                                <td>${test.num}</td>
                                                <td>${test.point == -1 ? "-" : test.point}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <%-- 統計情報の表示エリア --%>
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
                            <c:otherwise>
                                <p>成績情報が存在しませんでした</p>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    
                    <%-- 3. 初期表示 --%>
                    <c:otherwise>
                        <c:if test="${empty errors}">
                            <p class="text-primary">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </c:param>
</c:import>