<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>iGTB Payments File Generator</title>
</head>
<body>
<form action="createFile" method="post" enctype="multipart/form-data">
<div id="header" align="center" >iGTB Payments File Generator</div>
<br>
<br>
<br>
<div id="container" align="center">
 
 <table>
   <tr>
     <td>File Type:</td>
     <td><select name="fileType" id="fileType">
     <option>ALL</option>
     <option>MT101</option>
     <option>CMO103</option>
     <option>ISO PAIN001</option>
     </select>  </td>
   </tr>
   <tr>
   	<td>Source File:</td>
   	<td><input type="file" name="file" id="file"/> </td>
   </tr>
     <tr>
   	<td><input type="submit" value="Process"/></td>
   	<td><input type="reset" value="Rest"/> </td>
   </tr>
 </table>
   

</div>

</form>
</body>
</html>