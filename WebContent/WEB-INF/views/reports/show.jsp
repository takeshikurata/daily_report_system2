<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee_name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == report.employee_id and report.approval_status == 0}">
                    <p><a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a></p>
                </c:if>

                <c:if test="${sessionScope.login_employee.id == report.employee_id and report.approval_status == 0}">
                <!--
                    <form method="POST" action="<c:url value='/reports/approval_request' />">
                        <input type="hidden" name="_token" value="${_token}" />
                        <input type="hidden" name="id" value="${report.id}" />
                        <button type="submit">この日報を承認依頼する</button>
                    </form>
                -->
                    <c:if test="${sessionScope.login_employee.admin_flag == 0}">
                        <p><a href="#" onclick="confirmApprovalRequest();">この日報を承認依頼する</a></p>
                        <form method="POST" action="<c:url value='/reports/approval_request' />">
                            <input type="hidden" name="_token" value="${_token}" />
                            <input type="hidden" name="id" value="${report.id}" />
                        </form>
                        <script>
                            function confirmApprovalRequest() {
                                if (confirm("承認依頼します。よろしいですか？")) {
                                    document.forms[0].submit();
                                }
                            }
                        </script>
                    </c:if>
                    <c:if test="${sessionScope.login_employee.admin_flag == 1}">
                        <p><a href="#" onclick="confirmApprovalRequest();">この日報を確定する</a></p>
                        <form method="POST" action="<c:url value='/reports/approve' />">
                            <input type="hidden" name="_token" value="${_token}" />
                            <input type="hidden" name="id" value="${report.id}" />
                        </form>
                        <script>
                            function confirmApprovalRequest() {
                                if (confirm("確定します。よろしいですか？")) {
                                    document.forms[0].submit();
                                }
                            }
                        </script>
                    </c:if>

                </c:if>

                <c:if test="${sessionScope.login_employee.admin_flag == 1 and report.approval_status == 1}">
                    <p><a href="#" onclick="confirmApprove();">この日報を承認する</a></p>
                    <form method="POST" action="<c:url value='/reports/approve' />">
                        <input type="hidden" name="_token" value="${_token}" />
                        <input type="hidden" name="id" value="${report.id}" />
                    </form>
                    <script>
                        function confirmApprove() {
                            if (confirm("承認します。よろしいですか？")) {
                                document.forms[0].submit();
                            }
                        }
                    </script>
                    <p><a href="#" onclick="confirmReject();">この日報を却下する</a></p>
                    <form method="POST" action="<c:url value='/reports/reject' />">
                        <input type="hidden" name="_token" value="${_token}" />
                        <input type="hidden" name="id" value="${report.id}" />
                    </form>
                    <script>
                        function confirmReject() {
                            if (confirm("却下します。よろしいですか？")) {
                                document.forms[1].submit();
                            }
                        }
                    </script>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/reports/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>