<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3">成績参照</h2>
            <div class="bg-light p-3 border rounded">
                <form action="TestList.action" method="get">
                    <div class="row g-3 align-items-center mb-3">
                        <div class="col-auto">科目情報</div>
                        <div class="col-auto">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}">${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.cd}">${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto mt-auto">
                            <button type="submit" name="f" value="sj" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                    <div class="row g-3 align-items-center">
                        <div class="col-auto">学生情報</div>
                        <div class="col-6">
                            <label class="form-label">学生番号</label>
                            <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください">
                        </div>
                        <div class="col-auto mt-auto">
                            <button type="submit" name="f" value="st" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="mt-4">
                <p class="text-primary">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
            </div>
        </section>
    </c:param>
</c:import>