CREATE TABLE tb_tarefas(
     id SERIAL PRIMARY KEY,
     nome_da_tarefa VARCHAR(255) NOT NULL,
     dificuldade VARCHAR(30)
);