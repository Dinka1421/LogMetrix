INSERT INTO filters (ID, END_DATE, START_DATE, CLIENT, ENVIRONMENT, LOG) 
VALUES 
  ( 0, '2021-01-21', '2020-01-21', 'BWA', 'DEV', 'SYSTEM'), 
  (-1, '2021-01-21', '2020-11-02', 'LX', 'INT', 'ERROR'),
  (-2, '2021-10-21', '2020-01-24', 'CTN', 'INT', 'DATA'); 
  
INSERT INTO users (id, first_name, last_name, username, password)
VALUES 
  (0, 'Josko', 'Jurcevic', 'jjurcevic', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy'),
  (1, 'Dinka', 'Zadro', 'dzadro', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy');

  insert into authority (id, name)
      values
          (1, 'ROLE_ADMIN'),
          (2, 'ROLE_USER');

  insert into user_authority (user_id, authority_id)
      values
          (0, 1),
          (1, 2);