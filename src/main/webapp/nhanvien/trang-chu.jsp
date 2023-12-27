<%--
  Created by IntelliJ IDEA.
  User: Nam Khanh
  Date: 12/4/2023
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header>
    <h3 style="text-align: center;">Quản Lý Nhân Viên</h3>
</header>

<main class="container">
    <section>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Mã</th>
                <th scope="col">Tên</th>
                <th scope="col">Tên Đệm</th>
                <th scope="col">Họ</th>
                <th scope="col">Giới Tính</th>
                <th scope="col">Ngày Sinh</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">SĐT</th>
                <th scope="col">Mật Khẩu</th>
                <th scope="col">Cửa Hàng</th>
                <th scope="col">Chức Vụ</th>
                <th scope="col">Trạng Thái</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listNV}" var="nv">
                <tr>
                    <td>${nv.ma}</td>
                    <td>${nv.ten}</td>
                    <td>${nv.tenDem}</td>
                    <td>${nv.ho}</td>
                    <td>
                        <c:if test="${nv.gioiTinh=='Nam'}">Nam</c:if>
                        <c:if test="${nv.gioiTinh=='Nữ'}">Nữ</c:if>
                    </td>
                    <td>${nv.ngaySinh}</td>
                    <td>${nv.diaChi}</td>
                    <td>${nv.sdt}</td>
                    <td>${nv.matKhau}</td>
                    <td>${nv.cuaHang.ten}</td>
                    <td>${nv.chucVu.ten}</td>
                    <td>
                        <c:if test="${nv.trangThai==0}">On</c:if>
                        <c:if test="${nv.trangThai==1}">Off</c:if>
                    </td>

                    <td>
                        <a href="/nhan-vien/detail?id=${nv.id}" class="btn btn-primary " tabindex="-1" role="button"
                           aria-disabled="true">Detail</a>
                        <a href="/nhan-vien/view-update?id=${nv.id}" class="btn btn-success " tabindex="-1" role="button"
                           aria-disabled="true">Update</a>
                        <a href="/nhan-vien/delete?id=${nv.id}" class="btn btn-danger " tabindex="-1" role="button"
                           aria-disabled="true">Remove</a>
                        <a href="/nhan-vien/view-add" class="btn btn-secondary " tabindex="-1" role="button"
                           aria-disabled="true">Add</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
    <label style="color: red">${mess}</label>
</main>

<footer><p style="text-align: center; margin-top: 50px"><b>Dương Nam Khánh</b></p></footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
        integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
        crossorigin="anonymous"></script>

</body>
</html>
