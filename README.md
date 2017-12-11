# xy-inc

Projeto de criação de uma API RESTful para cadastro de filmes e seus principais atributos.

Os modelos abordados nesse projeto foram ACTOR, CATEGORY, AWARD e MOVIE.

## Arquitetura

Para a persistencia dos dados foi utilizado o banco de dados MySql, e para a construção da API foi utlizado o SpringBoot, além do Hibernate como framework JPA e o Maven para gestão das dependências e compilação.

## Criação do banco de dados

Executar esses comandos no console do MySql

```
CREATE DATABASE xy_inc;
CREATE USER 'xy_inc_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL on xy_inc.* to 'xy_inc_user'@'localhost';

CREATE DATABASE xy_inc_tests;
GRANT ALL on xy_inc_tests.* to 'xy_inc_user'@'localhost';


CREATE TABLE CATEGORY (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(1000)
);

CREATE TABLE MOVIE (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(1000),
    RELEASINGDATE DATE,
    BUDGET DECIMAL(15,2),
    CATEGORY_ID INT,
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);


CREATE TABLE ACTOR (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    BIRTHDATE DATE,
    NATIONALITY VARCHAR(255),
    GENDER VARCHAR(1)
);

CREATE TABLE MOVIE_ACTOR (
  MOVIE_ID INT(11) NOT NULL,
  ACTOR_ID INT(11) NOT NULL,
  PRIMARY KEY (MOVIE_ID,ACTOR_ID),
  KEY MOVIE_ID (MOVIE_ID),
  CONSTRAINT MOVIE_ACTOR_ibfk_1
   FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE (ID),
  CONSTRAINT MOVIE_ACTOR_ibfk_2
   FOREIGN KEY (ACTOR_ID) REFERENCES ACTOR (ID)
);

CREATE TABLE AWARD (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    AWARDDATE DATE,
    MOVIE_ID INT ,
    FOREIGN KEY (MOVIE_ID) REFERENCES MOVIE(ID)
);

```

## Execução

Clonar o repositório:

```
https://github.com/alexandrearduini/xy-inc.git
```

Executar o SpringBoot:
```
mvn spring-boot:run
```

É possível testar os serviços [aqui](https://documenter.getpostman.com/view/2644951/xy-inc/7EN2UL4)
