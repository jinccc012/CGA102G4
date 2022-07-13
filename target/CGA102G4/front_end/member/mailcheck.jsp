<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="utf-8">
            <title>密碼驗證</title>
            <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        </head>

        <body>

            <script>
                if (${ check } == true) {
                    swal({
                        title: "修改密碼成功!",
                        icon: "success",
                    }).then((willDelete) => {
                        if (willDelete) {
                            window.location.replace("<%=request.getContextPath() %>/front_end/Login/Login.html");
                        }
                    });
                }else {
                	
                    swal({
                        title: "${mess}!",
                        icon: "error",
                    }).then((willDelete) => {
                        if (willDelete) {
                            window.location.replace("<%=request.getContextPath() %>/front_end/Login/Login.html");
                        }
                    });
                }

            </script>
        </body>

        </html>