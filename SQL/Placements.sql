SELECT sub.Name
FROM (SELECT f.ID, s.Name, f.Friend_ID, p.Salary AS me_Salary
    FROM Students s
    INNER JOIN Friends f ON s.ID = f.ID
    INNER JOIN Packages p ON f.ID = p.ID) sub
INNER JOIN Packages p ON sub.Friend_ID = p.ID
WHERE sub.me_Salary < p.Salary
ORDER BY p.Salary