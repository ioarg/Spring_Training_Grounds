<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Training Grounds</title>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap.min.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/common.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/customers.css'/>" >
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Training Grounds</a>
</nav>
<div class="container">
     <div class="content-container">
        <h4>Hibernate Training</h4>
        <p>Customers</p>
        <table id="info-table" class="table table-custom table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Email</th>
                </tr>
            </thead>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td><c:out value="${customer.first_name}"/></td>
                    <td><c:out value="${customer.last_name}"/></td>
                    <td><c:out value="${customer.email}"/></td>
                </tr>
            </c:forEach>
        </table>
         <div class="button-container">
            <div id="add_btn" class="btn btn-info" data-toggle="modal" data-target="#addModal">Add Customer</div>
            <div id="delete_btn" class="btn btn-info" data-toggle="modal" data-target="#deleteModal">Delete Customer</div>
            <div id="edit_btn" class="btn btn-info" data-toggle="modal" data-target="#editModal">Edit Customer</div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">Add Customer</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="inp_add_first_name">First Name</label>
                            <input type="text" class="form-control" id="inp_add_first_name" placeholder="First Name">
                        </div>
                        <div class="form-group">
                            <label for="inp_add_last_name">Last Name</label>
                            <input type="text" class="form-control" id="inp_add_last_name" placeholder="Last Name">
                        </div>
                        <div class="form-group">
                            <label for="inp_add_email">Email</label>
                            <input type="text" class="form-control" id="inp_add_email" placeholder="Email">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button id="add_confirm_btn" type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
          </div>
        </div>
    </div>
</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>" ></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>" ></script>
<script src="<c:url value='/scripts/customers_ajax.js'/>" ></script>
</html>