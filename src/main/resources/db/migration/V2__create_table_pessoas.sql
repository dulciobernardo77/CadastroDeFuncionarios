CREATE TABLE tb_pessoas(
    id SERIAL PRIMARY KEY ,
    nome VARCHAR(255) NOT NULL ,
    idade INTEGER ,
    nacionalidade VARCHAR(20),
    bi VARCHAR(30) UNIQUE,
    sexo CHAR(1),
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(14) UNIQUE,
    nivel VARCHAR(20),
    imgUrl VARCHAR(500),
    tarefas_id INTEGER,

    CONSTRAINT fk_pessoa_tarefa
        FOREIGN KEY (tarefas_id)
            REFERENCES tb_tarefas(id)
);
