# Mysql_Search
> mysql를 이용한 mvc 스프링 검색엔진 테스트 버전입니다. <br>
> 엘라스틱서치와 비교를 위해 만들어졌기에 검색엔진의 대부분의 기능은 생략하였습니다.

<br>


## 스택

 <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
 <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
 <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
 <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
 
<br>

## 기능
- **도서 제목으로 검색시 그에 맞는 데이터 결과 값을 불러온다.**
- **페이징기능으로 데이터를 불러올때 페이지를 통해 데이터을 보여준다.**
<br>

## API 명세서

|기능|메소드|URL|
|------|---|---|
|메인 페이지 호출하기|GET|/|
|제목,저자,ISBN으로 검색후 책 리스트 가져오기|GET|/search|
|ISBN으로 책 데이터 가져오기|GET|/search_isbn|

<br>

## ERD
<img width="687" alt="스크린샷 2022-10-25 오후 6 30 03" src="https://user-images.githubusercontent.com/67679972/197737986-45777dfc-55bd-40c6-bd98-656542720cbc.png">

<br>

## 트러블 슈팅
[mysql 검색속도 높이기](https://nonchalant-sturgeon-21a.notion.site/MySQL-6bee0130e56c43ffbd7e59bb6a45fecc)

<br>

