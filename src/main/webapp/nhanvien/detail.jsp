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
    <h3 style="text-align: center;">Thông Tin Chi Tiết</h3>
</header>

<main class="container">
    <section>
        <div class="row mt-4">
            <div class="col-6">
                <label><b>Ma: </b></label>
                <span>${nv.ma}</span>
            </div>
            <div class="col-6">
                <label><b>Ten: </b></label>
                <span>${nv.ten}</span>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-6">
                <label><b>Ten Dem: </b></label>
                <span>${nv.tenDem}</span>
            </div>
            <div class="col-6">
                <label><b>Họ: </b></label>
                <span>${nv.ho}</span>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-6">
                <label><b>Gioi Tinh: </b></label>
                <span>
                   <c:if test="${nv.gioiTinh=='Nam'}">Nam</c:if>
                        <c:if test="${nv.gioiTinh=='Nữ'}">Nữ</c:if>
                </span>
            </div>
            <div class="col-6">
                <label><b>Ngay Sinh: </b></label>
                <span>${nv.ngaySinh}</span>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-6">
                <label><b>Dia Chi: </b></label>
                <span>${nv.diaChi}</span>
            </div>
            <div class="col-6">
                <label><b>SDT: </b></label>
                <span>${nv.sdt}</span>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-6">
                <label><b>Mat Khau: </b></label>
                <span>${nv.matKhau}</span>
            </div>
            <div class="col-6">
                <label><b>Cua Hang: </b></label>
                <span>${nv.cuaHang.ten}</span>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-6">
                <label><b>Chuc Vu: </b></label>
                <span>${nv.chucVu.ten}</span>
            </div>
            <div class="col-6">
                <label><b>Trang Thai: </b></label>
                <span>
                     <c:if test="${nv.trangThai==0}">On</c:if>
                        <c:if test="${nv.trangThai==1}">Off</c:if>
                </span>
            </div>
        </div>
    </section>
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
