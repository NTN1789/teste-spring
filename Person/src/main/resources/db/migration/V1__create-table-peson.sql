create table  person(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  cpf varchar(50) NOT NULL,
  dataNascimento LocalDate NOT NULL,
  email varchar(50) NOT NULL,
  PRIMARY KEY (id)
) 