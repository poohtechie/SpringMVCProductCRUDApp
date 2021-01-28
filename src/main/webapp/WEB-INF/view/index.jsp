<html>
<head>
<%@ include file="./base.jsp"%>
<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to Product App</h1>

				<!-- Product Table -->
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Product Name</th>
							<th scope="col">Description</th>
							<th scope="col">Price</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productListKey}" var="getProduct">
							<tr>
								<th scope="row">${getProduct.id}</th>
								<td>${getProduct.name}</td>
								<td>${getProduct.description}</td>
								<td class="font-weight-bold">&#8377; ${getProduct.price}</td>
								<td><a href="deleteProduct/${getProduct.id}"><i
										class="fas fa-trash text-danger" style="forn-size: 20px;"></i></a>
									<a href="updateProduct/${getProduct.id}"><i
										class="fas fa-pencil-alt text-primary"
										style="forn-size: 20px;"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>

				<!--Add Product Button -->
				<div class="container text-center">
					<a href="addProduct" class="btn btn-outline-success">Add
						Product</a>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
