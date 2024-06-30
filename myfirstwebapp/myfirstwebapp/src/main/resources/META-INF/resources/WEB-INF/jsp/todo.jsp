	<%@ include file="common/header.jspf" %>
   	<%@ include file="common/navigation.jspf" %>
   	
   	<div class="container">
       <div>
       	<h1 class="text-info">Welcome ${name}</h1>
       </div>
       <hr>
       <h2>Your todos are:</h2>
       
       <table class="table">
       	<thead>
       		<tr>
       			<th>DESCRIPTION</th>
       			<th>TARGET DATE</th>
       			<th>IS DONE?</th>
       			<th></th>
       			<th></th>
       		</tr>
       	</thead>
       	
       	<tbody>
       		<c:forEach items="${todos}" var="todo">
       			<tr>
       				<td>${todo.description}</td>
       				<td>${todo.targetDate}</td>
       				<td>${todo.done}</td>
       				<td><a href="update-todo?id=${todo.id}" class="btn btn-success">UPDATE</a></td>
       				<td>
       					<a href="delete-todo?id=${todo.id}" class="btn btn-warning">
       						DELETE
       					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
 								<path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
						</svg>
       					</a>
       				</td>
       			</tr>
       		</c:forEach>
       	</tbody>
       	
       </table>
       <a href="add-todo" class="btn btn-success">Add Todo</a>
      </div>
      <%@ include file="common/footer.jspf" %>