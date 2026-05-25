/*
springには、自動連番の機能があるので、insertのときに、自動連番の列の固定値は、いらない。列指定と値指定どちらも指定しないこと。
*/

--delete from medicine;
--delete from users;

--TRUNCATE TABLE medicine;
--TRUNCATE TABLE users;

INSERT INTO users (name, password)
 VALUES ('鈴木一郎', 'himitu'), ('佐藤悠介', 'okusuri'), ('田中愛子', 'check');

 INSERT INTO medicine (name, text, count, medicine_check, morning_check, daytime_check, night_check, lastDate, user_id)
  VALUES
  ('風邪薬', '食後1回2錠', 2, FALSE, FALSE, FALSE, FALSE, '1999-10-10', 1),
  ('頭痛薬', '食後1回1錠', 1, FALSE, FALSE, FALSE, FALSE, '1999-10-10', 1),
  ('イブプロフェン', '食後1回1錠', 1, FALSE, FALSE, FALSE, FALSE, '1999-10-10', 2),
  ('ビオフェルミン', '食後1回3錠', 3, FALSE, FALSE, FALSE, FALSE, '1999-10-10', 3),
  ('ロキソニン', '食後1回1錠', 2, FALSE, FALSE, FALSE, FALSE, '1999-10-10', 1);
  
--  id 表列の識別番号。history_date 記録した日時。text　記録に伴う補足説明、いつ何を飲んだか。user_id どのユーザーが記録したか。
  INSERT INTO medicine_add_history(history_date, text, user_id)
   VALUES 
    ('1999-10-10', '19:00 薬名 服薬済', 1),
    ('1999-10-11', '19:00 薬名  未服薬', 1),
    ('1999-10-12', '19:00 薬名  未服薬', 1);
    
    