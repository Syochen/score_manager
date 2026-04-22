<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="mx-auto mt-5" style="max-width: 800px;">
            <%-- タイトル部分：背景グレーの枠 --%>
            <div class="bg-light p-2 mb-3 border">
                <h2 class="h4 mb-0 fw-normal">ログアウト</h2>
            </div>

            <%-- メッセージ部分：緑色のバー --%>
            <div class="alert alert-success text-center py-2" role="alert" style="background-color: #9dc3a5; border: none; color: #212529;">
                ログアウトしました
            </div>

            <%-- ログインリンク：ボタンではなくシンプルなリンク --%>
            <div class="mt-4">
                <a href="Login.action" class="text-primary text-decoration-none">ログイン</a>
            </div>
        </section>
    </c:param>
</c:import>