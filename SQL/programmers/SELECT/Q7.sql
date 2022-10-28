-- 코드를 입력하세요
/*3월에 태어난 여성 회원 목록 출력하기*/
/*SELECT MEMBER_ID, MEMBER_NAME, GENDER,DATE_OF_BIRTH*/
SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH,'%Y-%m-%d') AS DATE_OF_BIRTH
FROM MEMBER_PROFILE
WHERE TLNO IS NOT NULL AND MONTH(DATE_OF_BIRTH) = '3' AND GENDER = 'W'
ORDER BY MEMBER_ID