# 1단계 : 회원 관련 게시판 만들기
# 2단계 : 어드민 관련 관리 페이지 만들기

<hr />

## Member

#### 작업예정

*URL*

- 회원 가입 뷰 : /users/register , GET

- 회원 가입 요청 : /users/register , POST

- 중복 아이디 확인 : /confirmDuplicate , POST

- 로그인  폼 : /login , GET

- 로그인 요청 : /loginPOST , POST

- 로그아웃 : /logout , GET


 


#### 작업 중

- 


#### 완료

- 회원 가입 완료
- 로그인 처리 완료


<hr />


## Board


### Create

#### 작업예정

*URL*

<table>
	<tr>
		<th>설명</th> <th>URI</th> <th>METHOD</th>
	</tr>
	<tr>
		<th>게시글 작성 뷰</th>
		<td>/articles/create</td>
		<td>GET</td>
	</tr>
	<tr>
		<th>게시글 작성 요청</th>
		<td>/articles/create</td>
		<td>POST</td>
	</tr>
	<tr>
		<th>게시글 내부 이미지 업로드</th>
		<td>/uploadImage</td>
		<td>POST</td>
	</tr>
	<tr>
		<th>게시글 내부 이미지 뷰</th>
		<td>/displayImage</td>
		<td>GET</td>
	</tr>
	<tr>
		<th>게시글 첨부 파일 업로드 </th>
		<td>/uploadAttach</td>
		<td></td>
	</tr>
	<tr>
		<th>게시글 첨부 파일 다운로드</th>
		<td>/download</td>
		<td> </td>
	</tr>	
</table>



*logic*

- Image 업로드 > 서버로 이미지 업로드 > textarea에 이미지 추가 > 스케줄링으로 자원 정리

- File 업로드 > 서버로 업로드 > POST or NOT 경우 별로 자원 이동
  


*front*

- Images 버튼 클릭 > 서버로 이미지 업로드 > textarea에 썸네일 이미지 추가 => 완료
- Files 버튼 클릭 > 서버로 파일 업로드 or hidden > textarea 밑에 이미지아이콘 or 썸네일 이미지 보이기
- Tags에 입력하면, tag 추출해서 뷰 바꿔주기

*Backend*
 
- File 업로드 후 POST 하지 않는 경우, 스케줄링으로 자원 정리 하기


#### 진행

- 뷰 페이지 작성 중
<pre>
- Image 버튼 클릭 > 서버로 이미지 업로드 > 파일 저장 후 저장 이름 반환 > text area에 url 로 이미지 보이기 완료
- File 버튼 클릭 > 서버로 업로드(TEMP 폴더) > 다운로드 미 구현
- 재설계 필요

</pre>




#### 완료

-


<hr />

### Read

#### 작업예정

-


#### 진행

- 



#### 완료


<hr />


### Update

#### 작업예정

-


#### 진행

- 



#### 완료



<hr />

### Delete

#### 작업예정

-


#### 진행

- 



#### 완료


<hr />




<table>
	<tr>
		<th>설명</th> <th>URL</th> <th>METHOD</th>
	</tr>
	<tr>
		<th></th>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<th></th>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<th></th>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<th></th>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<th></th>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<th></th>
		<td></td>
		<td></td>
	</tr>
</table>	