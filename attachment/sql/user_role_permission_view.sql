-- 嵌套一层防止视图更新源表;
-- 视图名称 user_role_permission_view
SELECT subview.*
FROM (SELECT su.id           AS ` user_id `,
             su.display_name AS ` display_name `,
             sr.role_name    AS ` role_name `,
             sp.permission   AS ` permission `,
             sp.description  AS ` permission_desc `
      FROM sys_user su
               LEFT JOIN sys_user_role sur ON su.id = sur.user_id
               LEFT JOIN sys_role sr ON sur.role_id = sr.id
               LEFT JOIN sys_role_permission srp ON sur.role_id = srp.role_id
               LEFT JOIN sys_permission sp ON srp.permission_id = sp.id
      WHERE su.is_enabled = 1
        AND sur.is_enabled = 1
        AND sr.is_enabled = 1
        AND srp.is_enabled = 1
        AND sp.is_enabled = 1) subview