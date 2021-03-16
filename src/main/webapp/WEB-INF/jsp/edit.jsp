<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<<jsp:include page="header.jsp"></jsp:include>

<div class="container">

<h3>User Registration</h3>
<form action='/update' method='post'>
 
    <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
            <td><b>Name</b></td>
            <td><input type='text' name='name' class='form-control' value="${user.name}" /></td>
        </tr>
 
        <tr>
            <td><b>Surname</b></td>
            <td><input type='text' name='surname' class='form-control' value="${user.surname}" /></td>
        </tr>
 
        <tr>
            <td><b>Adress</b></td>
            <td><input type='text' name='adress' class='form-control' size="20" value="${user.adress}" /></td>
            
        </tr>
 
 			<input type='hidden' id='id' rows='5' class='form-control' name='id' value="${user.id}"/>
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary">Update User Information</button>
            </td>
        </tr>
 
    </table>
</form>


</div>

<jsp:include page="footer.jsp"></jsp:include>
