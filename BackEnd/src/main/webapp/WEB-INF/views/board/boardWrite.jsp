<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<!-- [S] content -->
<div class="container">
    <div>
        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">여행 정보 공유</h2>
        <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
    </div>
    <form action="" method="post">
        <div class="mb-3">
            <label for="board-write-title" class="form-label">제목: </label>
            <input type="text" class="form-control" id="board-write-title" placeholder="제목을 입력하세요" name="board-write-title" />
        </div>
        <div class="mb-3">
            <label for="board-write-content" class="form-label">내용:</label>
            <textarea class="form-control" rows="5" id="board-write-content" name="board-write-content" placeholder="내용을 입력하세요"></textarea>
        </div>
    </form>

    <div class="col text-center">
        <button type="button" class="btn btn-outline-secondary" id="btn-board-write">글작성</button>
        <button type="button" class="btn btn-outline-secondary">초기화</button>
    </div>
</div>

<!-- [E] content -->

 <script> 
//      document.querySelector('#btn-board-write').addEventListener('click', function () {
//          if (!document.querySelector('#board-write-title').value) {
//              alert('제목 입력!!!');
//              return;
//          } else if (!document.querySelector('#board-write-content').value) {
//              alert('내용 입력!!!');
//              return;
//          } else {
//              let form = document.querySelector('#form-board-write');
//              console.log(form);
//              form.setAttribute('action', '${root }/board/sharetravel/write');
//              form.submit();
//          }
//      });
</script>
<script src="/assets/js/sharetravel.js"></script>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
