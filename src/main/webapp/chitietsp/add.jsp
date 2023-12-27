<%--
  Created by IntelliJ IDEA.
  User: Nam Khanh
  Date: 12/4/2023
  Time: 10:45 AM
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
    <h3 style="text-align: center;">Thêm Thông Tin</h3>
</header>
<main class="container">
    <section>
        <form action="/chi-tiet-sp/add" method="post">
            <div class="row mt-4">
                <div class="col-6">
                    <label>Tên SP</label>
                    <select name="sanPham">
                        <c:forEach items="${listSP}" var="sp">
                            <option value="${sp.id}">${sp.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-6">
                    <label>Tên NSX</label>
                    <select name="nsx">
                        <c:forEach items="${listNSX}" var="nsx">
                            <option value="${nsx.id}">${nsx.ten}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-6">
                    <label>Tên MS</label>
                    <select name="mauSac">
                        <c:forEach items="${listMS}" var="ms">
                            <option value="${ms.id}">${ms.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-6">
                    <label>Tên Dong SP</label>
                    <select name="dongSP">
                        <c:forEach items="${listDSP}" var="dsp">
                            <option value="${dsp.id}">${dsp.ten}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <div class="row mt-4">
                <div class="col-6">
                    <label>Nam BH</label>
                    <input type="number" class="form-control" name="namBH"/>

                </div>
                <div class="col-6">
                    <label>Mo Ta </label>
                    <input type="text" class="form-control" name="moTa"/>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-6">
                    <label>So Luong</label>
                    <input type="number" class="form-control" name="soLuongTon"/>
                </div>
                <div class="col-6">
                    <label>Gia Nhap</label>
                    <input type="number" class="form-control" name="giaNhap"/>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-6">
                    <label>Gia Ban</label>
                    <input type="number" class="form-control" name="giaBan"/>
                </div>
            </div>

            <div class="row mt-4" style="justify-content: center">
                <button class="btn btn-success col-1 m-3" type="submit">
                    Add
                </button>
            </div>
        </form>
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
