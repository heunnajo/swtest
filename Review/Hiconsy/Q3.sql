-- 코드를 입력하세요
/*유저별로 AMOUT >= 10000 인 횟수 조회*/
SELECT USER_ID, COUNT(*) AS CNT
FROM PAYMENTS
WHERE AMOUNT >= 10000
GROUP BY USER_ID
ORDER BY USER_ID
