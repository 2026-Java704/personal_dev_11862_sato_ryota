/*
springには、自動連番の機能があるので、insertのときに、自動連番の列の固定値は、いらない。列指定と値指定どちらも指定しないこと。
*/

--delete from medicine;
--delete from users;

--TRUNCATE TABLE medicine;
--TRUNCATE TABLE users;

INSERT INTO users (name, password)
 VALUES ('admin', 'himitu'), ('佐藤悠介', 'okusuri'), ('田中愛子', 'check');

 INSERT INTO medicine (name, text, count, medicine_check, morning_check, daytime_check, night_check, lastDate, user_id)
  VALUES
  ('イブクイック頭痛薬DX', '1日2錠。空腹時を避けてぬるま湯で服用する。頭痛薬', 2, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  
  ('イブプロフェン(朝食後)', '朝食後1回1錠。スルホニルウレア系経口血糖降下剤。糖尿病の薬', 1, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  ('イブプロフェン(晩食後)', '晩食後1回1錠。スルホニルウレア系経口血糖降下剤。糖尿病の薬', 1, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  
  ('グリクラジド', '食後1回3錠', 3, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 3),
  
  ('レパグリニド(朝食前)', '朝食前に1錠。速効型インスリン分泌促進剤 糖尿病の薬', 3, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  ('レパグリニド(昼食前)', '昼食前に1錠。速効型インスリン分泌促進剤 糖尿病の薬', 3, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  ('レパグリニド(晩食前)', '晩食前に1錠。速効型インスリン分泌促進剤 糖尿病の薬', 3, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  
  ('ロキソニン総合風邪薬', '朝 1回2錠 食後なるべく30分以内　服用間隔は4時間以上空けること', 2, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  ('ロキソニン総合風邪薬', '昼 1回2錠 食後なるべく30分以内　服用間隔は4時間以上空けること', 2, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1),
  ('ロキソニン総合風邪薬', '晩 1回2錠 食後なるべく30分以内　服用間隔は4時間以上空けること', 2, FALSE, FALSE, FALSE, FALSE, '2026-4-1', 1);
  
--  id 表列の識別番号。history_date 記録した日時。text　記録に伴う補足説明、いつ何を飲んだか。user_id どのユーザーが記録したか。
  INSERT INTO medicine_add_history(history_date, text, user_id)
   VALUES 
    ('2026-4-2', '23:55 イブクイック頭痛薬DX 服薬済', 1),
    
    ('2026-4-2', '23:55 イブプロフェン(朝食後)  服薬済', 1),
    ('2026-4-2', '23:55 イブプロフェン(晩食後)  服薬済', 1),
    
    ('2026-4-2', '23:55 グリクラジド  服薬済', 1),
    
 	('2026-4-2', '23:55 レパグリニド(朝食前)  服薬済', 1),
 	('2026-4-2', '23:55 レパグリニド(昼食前)  服薬済', 1),
 	('2026-4-2', '23:55 レパグリニド(晩食前)  未服薬', 1),
    
    
    ('2026-4-2', '23:55 ロキソニン総合風邪薬  未服薬', 1);
    
    